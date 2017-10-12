

//
//

import java.{util => ju}
import scala.collection.JavaConverters._
import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


sc.hadoopConfiguration.set("io.compression.codecs","io.sensesecure.hadoop.xz.XZCodec")
val str = "/bzdata/query/2017060[10-17]-*"
// val str = "tmp/query/20170701"
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
    var result = ""
    try {
      val getResult = JSON.parseObject(jsonStr).get(key)
      if (getResult != null){
        result = getResult.toString
      }else{
        result = ""
      }
    }catch {
      case _: Exception =>
    }

    result
}

// DF完成

val infoDF = sc.textFile(str).map{
    x=>
        
        val rawStr = x.split("\t").last // 2017-07-01 00:29:06 +0800 +\t+ api.query  +\t+ json 

        val jsonStr = rawStr.substring(12,rawStr.length-2).replace("\\\"","\"").replace("\\\\/","/").replace("\\\\\"","/")

        val cid = jsonParseObjectWithKey(jsonStr,"clientId")

        val jArrStr = jsonParseObjectWithKey(jsonStr,"p")
        val p_arr = jsonParseArray(jArrStr).toSeq // 打点上传的数据中,每个app的数据
        val targetInfoArr = if(p_arr.nonEmpty){
          val pair_arr =p_arr.map{
            str=>
              // 取 每个app 的pn和fit
              (jsonParseObjectWithKey(str.toString,"fit").toLong,jsonParseObjectWithKey(str.toString,"pn"))
          }.sortBy(x=>x._1) // Seq((fit,pn),(fit,pn)..)

          val pair_map = pair_arr.groupBy(_._1) //Map(fit->Seq((fit,pn),(fit,pn)))
          val kvArr = pair_arr.map{
            pair=>
              val fit = pair._1
              val pn = pair._2
              val value = pair_map.get(fit).get.map(x=>x._2).mkString(",")
              (fit,value)
          }.distinct
          kvArr
        }else Seq[(Long,Seq[String])]()
        val time = jsonParseObjectWithKey(jsonStr,"time")
        val apiVersionHeader = jsonParseObjectWithKey(jsonStr,"apiVersionHeader")
        val installTime = jsonParseObjectWithKey(jsonStr,"installTime")
        val channelId = jsonParseObjectWithKey(jsonStr,"channelId")
        val versionCode = jsonParseObjectWithKey(jsonStr,"versionCode")
        (cid,targetInfoArr,time,installTime,apiVersionHeader,channelId,versionCode)
}.repartition(128).toDF("cid","info","time","installTime","apiVersionHeader","channelId","versionCode").filter("channelId = '100946' OR channelId = '101606' OR channelId = '100658' OR channelId = '100785' OR channelId = '101513'")

infoDF.write.mode("overwrite").parquet("tmp/queryDF/20170701")
infoDF.groupBy("apiVersionHeader").count.write.parquet("tmp/queryDF/20170501_groupBy")


// 测试有没有flag
sc.textFile(str).map{
  x=>
    val rawStr = x.split("\t").last
    val jsonStr = rawStr.substring(12,rawStr.length-2).replace("\\\"","\"").replace("\\\\/","/").replace("\\\\\"","/")
    // ---- test
    val flag = jsonParseObjectWithKey(jsonStr,"flag")
    if (flag.length>=1){
      // 有flag,上传的p是所有的产品
      "yes"
    }else{
      // 无flag,聚合上传的P对应的产品并去重,得到尽量多的产品数据
      null
    }
}.filter(x=>x!=null).count



import java.util.Date
import java.text.SimpleDateFormat
import org.apache.spark.sql.Row
def getDate(ts:Long): String = {
    // val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val df = new SimpleDateFormat("yyyy-MM-dd")
    val date = df.format(ts)
    date
}
val anti = sqlContext.read.parquet("/user/zhoutong/anti_dog/infoDF_20170[5-8][1-3][0-9]").repartition(128)
val anti_o = sqlContext.read.parquet("/user/zhoutong/anti_dog/infoDF_20170611").repartition(128)
anti_o.cache
val new_anti = anti_0.map{
    r=>
    val list = r.getAs[Seq[Row]]("info").map{
        row=>
        Seq[String](row.getAs[Long]("_1").toString,row.getAs[String]("_2"))
    }
    (r.getAs[String]("cid"),list,r.getAs[String]("apiVersionHeader"),r.getAs[String]("time"))
}.toDF("cid","info","apiVersionHeader","time")

val result_anti = new_anti.withColumn("info",explode($"info")).map{
    r=>
        (r.getAs[String]("cid"),
        r.getAs[Seq[String]]("info").head.toLong,
        r.getAs[Seq[String]]("info").last,
        r.getAs[String]("apiVersionHeader"),
        r.getAs[String]("time"))
}.toDF("cid","fit","pns","apiVersionHeader","time").groupBy("cid","fit").agg(collect_list($"pns") as "pns").orderBy("cid","fit")
// 还没有把这个更新到code里，只在这里写了作为草稿，因为包名中包含逗号的应该还是少数
val prettyUDF = udf((x:Seq[String])=>x.flatMap(a=>a.split(",")).distinct) // 这个是针对把数组mkString(",")了的那次，要再拆开
val countUDF = udf((x:Seq[String])=>x.length)
val timeStampUDF = udf((x:Long) => getDate(x))
val mkStringUDF = udf((x:Seq[String])=>x.mkString("\u0394"))
val mkSeqUDF =udf((x:Seq[String])=>x.flatMap(a=>a.split("\u0394")))
val dropSystemAppUDF = udf((x:Seq[String]) => x.filter(x=> !x.contains("android")))
val pretty_anti=result_anti
.withColumn("pns",prettyUDF($"pns"))
.withColumn("pns",dropSystemAppUDF($"pns"))
.withColumn("fit_day",timeStampUDF($"fit"))
.withColumn("pns",mkStringUDF($"pns"))
.map(r=>(r.getAs[String]("cid"),r.getAs[String]("fit_day"),r.getAs[String]("pns")+"\u0001"+r.getAs[Long]("fit").toString)).toDF("cid","fit_day","pns&fit")
.groupBy("cid","fit_day").agg(collect_list($"pns&fit") as "pns&fit").orderBy("cid","fit_day")
.map(r=>(r.getAs[String]("cid"),r.getAs[String]("fit_day"),r.getAs[Seq[String]]("pns&fit").map(x=>x.split("\u0001").head),r.getAs[Seq[String]]("pns&fit").map(x=>x.split("\u0001").last))).toDF("cid","fit_day","pns","fit")
.withColumn("pns",mkSeqUDF($"pns"))
.withColumn("pns_count",countUDF($"pns"))
.filter("pns_count != 0")
.withColumn("fit_count",countUDF($"fit"))
.select("cid","fit_day","pns_count","fit_count","pns")
pretty_anti.write.mode("overwrite").parquet("/user/zhoutong/tmp/queryDF/pretty_anti_all")




val pretty_anti_all = sqlContext.read.parquet("/user/zhoutong/tmp/queryDF/pretty_anti_all")
val statisticDF_basic = pretty_anti_all.groupBy("cid").agg(collect_list($"pns_count") as "pns_count")
val mvUDF = udf{
    (x:Seq[Int]) => 
    val total = x.reduce((x:Int,y:Int)=>x+y)
    val length = x.length
    total*1.0/length 
}
val statisticDF_mv = statisticDF_basic.withColumn("mv",mvUDF($"pns_count"))
val sdUDF = udf{
      (x:Seq[Int],mv:Double) =>
        val d = x.map{
          v=>
            (v-mv)*(v-mv)
        }.sum
        math.sqrt(d*1.0/x.length)
}
val delta = udf{
    (pns_count:Int,mv:Double,sd:Double) =>
    math.abs(pns_count-mv)
}
val statisticDF_sd = statisticDF_mv.withColumn("sd",sdUDF($"pns_count",$"mv"))
val statisticDF_dropped = statisticDF_sd.drop("pns_count")
val statisticDF = pretty_anti_all.join(statisticDF_dropped,Seq("cid"),"left_outer")
statisticDF.write.mode("overwrite").parquet("/user/zhoutong/tmp/queryDF/statisticDF")
// 可以看看这个，发现有的cid的手机，安装量非常夸张


pretty_anti.orderBy(desc("pns_count")).show(false)


pretty_anti.map(_.getAs[String]("cid")).filter(_.startsWith("d04ae8af84aefdd46"))
// +--------------------+-------------+--------------------+---------+
// |                 cid|          fit|                 pns|pns_count|
// +--------------------+-------------+--------------------+---------+
// |d04ae8af84aefdd46...|1488459806663|[com.snaptube.pre...|       76|
// |d04ae8af84aefdd46...|1488459416292|[com.dzed.player,...|       75|
// |d04ae8af84aefdd46...|1488453223999|[com.dewmobile.ku...|       75|
// |d04ae8af84aefdd46...|1488460831317|[com.alibaba.intl...|       75|
// |d04ae8af84aefdd46...|1488453206404|[com.ygocn, com.y...|       75|
// |d04ae8af84aefdd46...|1488459613363|[com.lenovo.anysh...|       75|
// |d04ae8af84aefdd46...|1488408390468|[com.whatsapp, co...|       75|
// |d04ae8af84aefdd46...|1488451874108|[com.nemo.vidmate...|       75|
// |d04ae8af84aefdd46...|1488461760405|[com.facebook.orc...|       74|
// |d04ae8af84aefdd46...|1488449447491|[com.duapps.clean...|       74|
// |d04ae8af84aefdd46...|1488046002844|[com.facebook.lit...|       58|
// |2a46ffc8af0126550...|1451608200857|[com.cmcm.locker,...|       52|
// |2a46ffc8af0126550...|1464002263000|[com.google.andro...|       52|
// |2a46ffc8af0126550...|1451608655072|[com.android.memo...|       52|
// |2a46ffc8af0126550...|1451608282747|[com.ksmobile.lau...|       52|
// |2a46ffc8af0126550...|1464002245000|[com.revo.demo,co...|       52|
// |2a46ffc8af0126550...|1464002650000|[com.opera.mini.n...|       52|
// |4e1b507495dbdccd7...|1447312508000|[com.revo.demo,co...|       51|
// |aadbd73bf603773c3...|1489457641477|[com.arif, com.ar...|       51|
// |4e1b507495dbdccd7...|1420070722322|[system.agent, sy...|       51|
// +--------------------+-------------+--------------------+---------+

infoDF.groupBy("cid").count.count
infoDF.count +" "+infoDF_o.count +" "+infoDF_o.distinct.count +
23581 724111 724078  



