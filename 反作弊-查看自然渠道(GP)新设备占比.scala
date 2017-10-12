import java.{util => ju}
import scala.collection.JavaConverters._
import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


sc.hadoopConfiguration.set("io.compression.codecs","io.sensesecure.hadoop.xz.XZCodec")
val str = "/bzdata/query/2017080[10-17]-*"

def jsonParseArray(jsonArrString:String):Array[Object]={
    var resultArr = Array[Object]()
    try{
       if (JSON.parseArray(jsonArrString)!=null){
          resultArr = JSON.parseArray(jsonArrString).toArray()
        }else{
          resultArr = Array[Object]()
        } 
    }catch{
        case _: Exception =>
    }
    resultArr
}

def jsonParseObjectWithKey(jsonStr: String, key: String): String= {
    var result = "N/A"
    try {
      val getResult = JSON.parseObject(jsonStr).get(key)
      if (getResult != null){
        result = getResult.toString
      }else{
        result = "N/A"
      }
    }catch {
      case _: Exception =>
    }
    result
}


val infoDF = sc.textFile(str).map{
      x=>
        val rawStr = x.split("\t").last
        val jsonStr = rawStr.substring(12,rawStr.length-2).replace("\\\"","\"").replace("\\\\/","/").replace("\\\\\"","/")

        val cid = jsonParseObjectWithKey(jsonStr,"clientId")

        val jArrStr = jsonParseObjectWithKey(jsonStr,"p")
        val p_arr = jsonParseArray(jArrStr) // 打点上传的数据中,每个app的数据
        val targetInfoArr = if(p_arr.nonEmpty){
          val pair_arr =p_arr.map{
            str=>
              // 取 每个app 的pn和fit
              (jsonParseObjectWithKey(str.toString,"fit").toLong,jsonParseObjectWithKey(str.toString,"pn"))
          }.sortBy(x=>x._1) // 40种pn 16种fit

          val pair_map = pair_arr.groupBy(_._1)
          val kvArr = pair_arr.map{
            pair=>
              val key = pair._1
              val value = pair_map.get(pair._1).get.map(x=>x._2).mkString(",")
              (key,value)
          }.distinct

          kvArr
        }else Array[(Long,String)]()
        val time = jsonParseObjectWithKey(jsonStr,"time")
        val apiVersionHeader = jsonParseObjectWithKey(jsonStr,"apiVersionHeader")
        val installTime = jsonParseObjectWithKey(jsonStr,"installTime")
        val channelId = jsonParseObjectWithKey(jsonStr,"channelId")
        val versionCode = jsonParseObjectWithKey(jsonStr,"versionCode")
        (cid,targetInfoArr,time,installTime,apiVersionHeader,channelId,versionCode)
    }.repartition(128)
      .toDF("cid","info","time","installTime","apiVersionHeader","channelId","versionCode").filter("channelId = 'google-play'")

infoDF.write.mode("overwrite").parquet("tmp/infoDF")
// val info=sqlContext.read.parquet("tmp/infoDF")




import java.util.Date
import java.text.SimpleDateFormat
import org.apache.spark.sql.Row
import scala.util.control._

def getDate(ts:Long): String = {
    // val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    val df = new SimpleDateFormat("yyyy-MM-dd")
    val date = df.format(ts)
    date
}
def getSecond(ts:Long):String={
  val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  val second = df.format(ts)
  second
}
val timeStampUDF = udf((x:Long) => getDate(x))

// 1.即反作弊.scala文件中的anti
val basicInfoDF = sqlContext.read.parquet("tmp/infoDF")
val anti = basicInfoDF
anti.cache
// 2.进一步取出需要的V3版本的，并拿出installTime
val new_anti = anti.filter("apiVersionHeader='3'").map{
    r=>
    val infoList = r.getAs[Seq[Row]]("info").map{
        row=>
        Seq[String](row.getAs[Long]("_1").toString,row.getAs[String]("_2"))
    }
    val cid = r.getAs[String]("cid")
    val installTime = r.getAs[String]("installTime")
    val time = r.getAs[String]("time")
    (cid,infoList,installTime,time)
}.toDF("cid","info","installTime","time").withColumn("installTime",timeStampUDF($"installTime"))

// 3.用timeStampUDF把time规范化，然后用registerDateUDF取最早的时间就是激活时间
val registerDateUDF = udf((x:Seq[String])=>x.min)
val registerDF = new_anti.groupBy("cid").agg(collect_list($"time") as "register_time").withColumn("register_time",registerDateUDF($"register_time"))
registerDF.write.mode("overwrite").parquet("tmp/registerDF")

// 3.1 挑选安装时间
val installTimeUDF = udf((x:Seq[String]) =>x.head)
val installTimeDF = new_anti.groupBy("cid").agg(collect_list($"installTime") as "installTime").withColumn("installTime",installTimeUDF($"installTime"))
installTimeDF.write.mode("overwrite").parquet("tmp/installTimeDF")


//              ---registerDF
//             |
// new_anti -- |
//             |
//              ---tmp3
// 4.处理基础数据，拿出cid和安装时间
val result_anti = new_anti.withColumn("info",explode($"info")).map{
    r=>
        (r.getAs[String]("cid"),
        r.getAs[Seq[String]]("info").head.toLong,
        r.getAs[Seq[String]]("info").last,
        r.getAs[String]("installTime"),
        r.getAs[String]("time"))
}.toDF("cid","fit","pns","installTime","time").groupBy("cid","fit").agg(collect_list($"pns") as "pns").orderBy("cid","fit")

    val prettyUDF = udf((x: Seq[String]) => x.flatMap(a => a.split(",")).distinct) // 这个是针对把数组mkString(",")了的那次，要再拆开
    val dropSystemAppUDF = udf((x:Seq[String]) => x.filter(x=> !(x.contains("com.android") || x.contains("com.google.android"))))
    val timeStampUDF = udf((x: Long) => getDate(x))
    val mkStringUDF = udf((x: Seq[String]) => x.mkString("\u0394"))
    val mkSeqUDF = udf((x: Seq[String]) => x.flatMap(a => a.split("\u0394")))
    val countUDF = udf((x: Seq[String]) => x.length)

// tmp 
val pretify = result_anti.withColumn("pns", prettyUDF($"pns"))
val dropSys = pretify.withColumn("pns",dropSystemAppUDF($"pns"))
dropSys.filter("cid = '00046c043e952e4b05d98cb32485a82f'")


// end--tmp 
val pretty_anti = result_anti
      .withColumn("pns", prettyUDF($"pns"))
      .withColumn("pns",dropSystemAppUDF($"pns"))
      .withColumn("fit_day", timeStampUDF($"fit"))
      .withColumn("pns", mkStringUDF($"pns"))
      .map(r => (r.getAs[String]("cid"), r.getAs[String]("fit_day"), r.getAs[String]("pns") + "\u0001" + r.getAs[Long]("fit").toString)).toDF("cid", "fit_day", "pns&fit")
      .groupBy("cid", "fit_day").agg(collect_list($"pns&fit") as "pns&fit").orderBy("cid", "fit_day")
      .map(r => (r.getAs[String]("cid"), r.getAs[String]("fit_day"), r.getAs[Seq[String]]("pns&fit").map(x => x.split("\u0001").head), r.getAs[Seq[String]]("pns&fit").map(x => x.split("\u0001").last))).toDF("cid", "fit_day", "pns", "fit")
      .withColumn("pns", mkSeqUDF($"pns"))
      .withColumn("pns_count", countUDF($"pns"))
      // .withColumn("fit",timeStampAtSecondsUDF($"fit"))
      .withColumn("fit_count", countUDF($"fit"))
      .select("cid", "fit_day", "pns_count", "fit_count", "pns")
    pretty_anti.write.mode("overwrite").parquet("tmp/pretty_anti_all")
val pretty_anti_all = sqlContext.read.parquet("tmp/pretty_anti_all")
    val statisticDF_basic = pretty_anti_all.groupBy("cid").agg(collect_list($"pns_count") as "pns_count")
    val mvUDF = udf{
      (x:Seq[Int]) =>
        val total = x.sum
        val length = x.length
        total*1.0/length
    }
    val sdUDF = udf{
      (x:Seq[Int],mv:Double) =>
        val d = x.map{
          v=>
            (v-mv)*(v-mv)
        }.sum
        math.sqrt(d*1.0/x.length)
    }
    val statisticDF_mv = statisticDF_basic.withColumn("mv",mvUDF($"pns_count"))
    val statisticDF_sd = statisticDF_mv.withColumn("sd",sdUDF($"pns_count",$"mv"))
    val statisticDF_dropped = statisticDF_sd.drop("pns_count")

    val analysisDF_basic = pretty_anti_all.join(statisticDF_dropped,Seq("cid"),"left_outer")
    val deltaUDF = udf{
      (pns_count:Int,mv:Double,sd:Double) =>
        math.abs(pns_count-mv)/sd
    }

    val analysisDF = analysisDF_basic.withColumn("delta",deltaUDF($"pns_count",$"mv",$"sd"))

  val pairToStringUDF = udf((fit_day:String,pns_count:Int,fit_count:Int,delta:Double)=> fit_day+"\u0394"+pns_count.toString+"\u0394"+fit_count.toString+"\u0394"+delta)
    val tmp1 = analysisDF.withColumn("fit_day",pairToStringUDF($"fit_day",$"pns_count",$"fit_count",$"delta"))
    val tmp2 = tmp1.groupBy("cid").agg(collect_list($"fit_day") as "fit_day")
    val tmp3 = tmp2.map{
      r=>
        val cid = r.getAs[String]("cid")
        val strArr = r.getAs[Seq[String]]("fit_day") // fit_day&pns_count&fit_count&delta
        // 拆分strArr为四个数组(长度相同)
        val fit_day_arr=strArr.map(str=>str.split("\u0394").head) // (2016-06-01,2017-06-03,..)
        val pns_count_arr = strArr.map(str=>str.split("\u0394")(1)) // (10,2,1...)
        val fit_count_arr = strArr.map(str=>str.split("\u0394")(2)) // (10,2,1...)
        val delta_arr = strArr.map(str=>str.split("\u0394")(3)) // (1.7,0.5,0.4...)
        // 找出日期
        var targetDate:String = null
        // 哪几天的delta大于1.5,找出最后最近(latest)一天的index
        if(delta_arr.nonEmpty){
          val filtered = delta_arr.filter(x=>x.toDouble>1.5)
          if(filtered.nonEmpty){
            val latestDate_bigDelta_index = delta_arr.indexOf(filtered.last)
            if(fit_day_arr.length>latestDate_bigDelta_index+1){
              //如果这个用户在delta>1.5之后还有记录,就查看是否符合用户安装app的行为
              val loop = new Breaks()
              loop.breakable{
                for(i<-latestDate_bigDelta_index+1 until fit_day_arr.length){
                  // 检查这个用户的第i天的安装行为
                  if(pns_count_arr(i)==fit_count_arr(i)){
                    targetDate = fit_day_arr(i)
                    println(i)
                    loop.break()
                  }else targetDate = "每天的pns_count都对不上fit_count"
                }
              }
            }else{
              targetDate = "用户在异常值之后没有再被统计到日期"
            }
          }else{
            targetDate = "delta_arr没有大于1.5的"
          }
        }else{
          targetDate = "delta_arr是空"
        }
        (cid,targetDate)
      }.toDF("cid","targetDate")
tmp3.write.mode("overwrite").parquet("tmp/tmp3") // 367285

// 展示用
val pretty_anti_all = sqlContext.read.parquet("tmp/pretty_anti_all").select("cid","fit_day","pns_count","fit_count")
// 激活时间time
val registerDF = sqlContext.read.parquet("tmp/registerDF")
// 开始使用时间
val targetDateDF = sqlContext.read.parquet("tmp/tmp3")
// 安装时间（但是 468755
val installTimeDF = sqlContext.read.parquet("tmp/installTimeDF")
// 用pretty_anti_all 分析一下
val showDF = pretty_anti_all.join(registerDF,Seq("cid"),"left_outer").join(installTimeDF,Seq("cid"),"left_outer").join(targetDateDF,Seq("cid"),"left_outer")


// resultDF是简洁形式
val basicInfoDF = sqlContext.read.parquet("tmp/infoDF")
val resultDF = basicInfoDF.select("cid").distinct.join(registerDF,Seq("cid"),"left_outer").join(installTimeDF,Seq("cid"),"left_outer").join(targetDateDF,Seq("cid"),"left_outer")
resultDF.write.mode("overwrite").parquet("tmp/resultDF")
val r = sqlContext.read.parquet("tmp/resultDF") // 468,755
val rdd1 = r.filter("register_time is not null").rdd.filter(x=> x.getAs[String]("targetDate").length==10) 
val r1 = sqlContext.createDataFrame(rdd1,rdd1.first.schema) // 336,560/367,285 92% , 30,725因故无法判断用户开始使用时间，8%
r1.filter("targetDate < installTime") // 254,356 /336,560 75%
r1.filter("targetDate = installTime") // 46,319/336,560 开始使用的那天正好是安装launcher那天 13%
r1.filter("targetDate > installTime").rdd.filter(x=> x.getAs[String]("targetDate").length==10) // 35,885/336,560  新设备 10%


r.filter("register_time is not null") // apiV3
// google-play
// info.count      468755  468,755
// apiV3           367285  367,285
// 


00046c043e952e4b05d98cb32485a82f|1        |1        |2015-01-01|0       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|22       |13       |2016-11-16|685     |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|14       |14       |2017-04-02|137     |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-04-03|1       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-04-10|7       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-04-25|15      |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|2        |2        |2017-04-27|2       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-05-01|4       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-05-06|5       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|2        |2        |2017-05-12|6       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|2        |2        |2017-05-23|11      |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-05-27|4       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-05-29|2       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-05-30|1       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |
|00046c043e952e4b05d98cb32485a82f|1        |1        |2017-05-31|1       |2017-04-02 |2017-06-01 07:30:36|2017-04-03       |












