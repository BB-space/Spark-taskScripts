// 反作弊-从infoDF开始做-查看自然取到的新设备占比


// 一、shell提前导入
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
val registerDateUDF = udf((x:Seq[String])=>x.min)
val installTimeUDF = udf((x:Seq[String]) =>x.head)
//计算均值的udf
val mvUDF = udf{
      (x:Seq[Int]) =>
        val total = x.sum
        val length = x.length
        total*1.0/length
    }
// 计算标准差
val sdUDF = udf{
  (x:Seq[Int],mv:Double) =>
    val d = x.map{
      v=>
        (v-mv)*(v-mv)
    }.sum
    math.sqrt(d*1.0/x.length)
}
// 计算与均值差了多少个标准差
val deltaUDF = udf{
  (pns_count:Int,mv:Double,sd:Double) =>
    (pns_count-mv)/sd
}
// 二、 selectedInfoDF
// val basicInfoDF = sqlContext.read.parquet("tmp/anti_0810_0817/infoDF")
// basicInfoDF.cache

// val basicInfoDF = sqlContext.read.parquet("anti_dog/infoDF_201708[1-3][0-9]")
// basicInfoDF.cache
// basicInfoDF.repartition(128).write.parquet("tmp/allChannelDF")

// val basicInfoDF = sqlContext.read.parquet("tmp/InfoDF_allChannel")
// basicInfoDF.cache

val basicInfoDF = sqlContext.read.parquet("tmp/infoDF_accept_101606_launcher").filter("accept is not null")
basicInfoDF.cache

val selectedInfoDF = basicInfoDF.map{
    r=>
        val infoList = r.getAs[Seq[Row]]("info").map{
            row=>
            Seq[String](row.getAs[Long]("_1").toString,row.getAs[String]("_2"))
        }
        val cid = r.getAs[String]("cid")
        val installTime = r.getAs[String]("installTime")
        val time = r.getAs[String]("time")
        val channelId = r.getAs[String]("channelId")
        (cid,infoList,installTime,time,channelId)
    }.toDF("cid","info","installTime","time","channelId").withColumn("installTime",timeStampUDF($"installTime"))

// 三、 registerTime - 取最早的打点时间记录为注册激活时间
val registerDF = selectedInfoDF.groupBy("cid").agg(collect_list($"time") as "register_time").withColumn("register_time",registerDateUDF($"register_time"))
registerDF.write.mode("overwrite").parquet("tmp/registerDF")
// 四、 installTime - 安装时间
val installTimeDF = selectedInfoDF.filter("installTime != ''").groupBy("cid").agg(collect_list($"installTime") as "installTime").withColumn("installTime",installTimeUDF($"installTime"))
installTimeDF.write.mode("overwrite").parquet("tmp/installTimeDF")
val channelIDDF = selectedInfoDF.groupBy("cid").agg(collect_list($"channelId") as "channelId").map(x=>(x.getAs[String]("cid"),x.getAs[Seq[String]]("channelId").head)).toDF("cid","channelID")
channelIDDF.write.mode("overwrite").parquet("tmp/channelIDDF")

// 五、 fit_day - 
// 1. 取cid 和 fit 
val fit_day_1 = selectedInfoDF.withColumn("info",explode($"info")).map{
    r=>
        (r.getAs[String]("cid"),
        r.getAs[Seq[String]]("info").head.toLong,
        r.getAs[Seq[String]]("info").last,
        r.getAs[String]("installTime"),
        r.getAs[String]("time"))
}.toDF("cid","fit","pns","installTime","time").groupBy("cid","fit").agg(collect_list($"pns") as "pns").orderBy("cid","fit")

// 2.一些UDF
//   之前有过对pn做mkString(",")，而且有一些重复的记录被直接统计进来，所以还要distinct
val prettyUDF = udf((x: Seq[String]) => x.flatMap(a => a.split(",")).distinct)
//   移除系统应用
val dropSystemAppUDF = udf((x:Seq[String]) => x.filter(x=> !(x.contains("com.android") || x.contains("com.google.android"))))
//   为了方便agg，把Seq变成String
val mkStringUDF = udf((x: Seq[String]) => x.mkString("\u0394"))
//   把String拆回Seq
val mkSeqUDF = udf((x: Seq[String]) => x.flatMap(a => a.split("\u0394")))
val mkSeqpns_UDF = udf((x: Seq[String]) => x.flatMap(a => a.split("\u0394")).distinct)
//   计算Seq的个数
val countUDF = udf((x: Seq[String]) => x.length)

// 3.把mkString的拆开并distinct，然后移除系统应用(filter掉,去掉空数组)
// 不用去掉系统应用
val fit_day_2_rdd = fit_day_1
    .withColumn("pns",prettyUDF($"pns"))
    // .withColumn("pns",dropSystemAppUDF($"pns"))
    .rdd.filter(x=>x.getAs[Seq[String]]("pns").nonEmpty)
val fit_day_2 = sqlContext.createDataFrame(fit_day_2_rdd,fit_day_2_rdd.first.schema)

// 4.把fit按日归并， pns从Seq变成String，再把这两列合成一列用"\u0001"分割的字符串
val fit_day_3 = fit_day_2
    .withColumn("fit_day", timeStampUDF($"fit"))
    .withColumn("pns", mkStringUDF($"pns"))
    .map(r => (r.getAs[String]("cid"), r.getAs[String]("fit_day"), r.getAs[String]("pns") + "\u0001" + r.getAs[Long]("fit").toString)).toDF("cid", "fit_day", "pns&fit")
// 5.按cid合并后，得到pns和fit的count
val fit_day_4 = fit_day_3
    .groupBy("cid", "fit_day").agg(collect_list($"pns&fit") as "pns&fit").orderBy("cid", "fit_day")
    .map(r => (r.getAs[String]("cid"), r.getAs[String]("fit_day"), r.getAs[Seq[String]]("pns&fit").map(x => x.split("\u0001").head), r.getAs[Seq[String]]("pns&fit").map(x => x.split("\u0001").last))).toDF("cid", "fit_day", "pns", "fit")
    .withColumn("pns", mkSeqpns_UDF($"pns"))
    .withColumn("pns_count", countUDF($"pns"))
    .withColumn("fit_count", countUDF($"fit"))
    .select("cid", "fit_day", "pns_count", "fit_count", "pns")
// 6.计算每个cid的pns_count的均值、标准差
val fit_day_5 = fit_day_4.groupBy("cid").agg(collect_list($"pns_count") as "pns_count")
    .withColumn("mv",mvUDF($"pns_count"))
    .withColumn("sd",sdUDF($"pns_count",$"mv"))
    .select("cid","mv","sd")
// 7.把统计数据join回去
val fitDayDF = fit_day_4.join(fit_day_5,Seq("cid"),"left_outer")
    .withColumn("delta_pns",deltaUDF($"pns_count",$"mv",$"sd"))
    .select("cid","fit_day","pns_count","fit_count","delta_pns","pns")
    // .select("cid","fit_day","pns_count","fit_count","mv","sd","delta_pns","pns")

// 六、 timeInterval - 每条安装记录的间隔
def getTimeInterval(d1:String,d2:String): String = {
  val df = new SimpleDateFormat("yyyy-MM-dd")
  val d1Stamp = df.parse(d1).getTime
  val d2Stamp = df.parse(d2).getTime
  ((d2Stamp - d1Stamp)/(1000*60*60*24)).toString
}
val timeIntervalUDF = udf{
  (x:Seq[String])=>
    var arr = Seq[String]("0")
    for(i<- 0 until x.length-1){
      arr = arr :+ getTimeInterval(x(i),x(i+1))
    }
    x.zip(arr)
}
val t = fitDayDF.groupBy("cid").agg(collect_list("fit_day") as "fit_day_interval")
    .withColumn("fit_day_interval",timeIntervalUDF($"fit_day_interval"))
    .withColumn("fit_day_interval",explode($"fit_day_interval"))
    .map(x=>(x.getAs[String]("cid"),x.getAs[Row]("fit_day_interval").getAs[String]("_1"),x.getAs[Row]("fit_day_interval").getAs[String]("_2").toInt))
    .toDF("cid","fit_day","interval")
val intervalDF_1 = fitDayDF.join(t,Seq("cid","fit_day"),"left_outer").orderBy("cid","fit_day")
// 针对fit_day_interval中的异常大的值，初步设定为大于180就抛弃
// -1是因为第一天的interval不应该纳入计算，第一天是手动设置为interval=0的，但是x.length会把第一天也算作分母里的一个
val filterUDF = udf((x:Seq[Int])=>x.filter(i=>i<180))
val mvUDF_interval = udf((x:Seq[Int])=>x.sum.toDouble/(x.length-1))
val sdUDF_interval =  udf{
  (x:Seq[Int],mv:Double) =>
    val d = x.map(v=> (v-mv)*(v-mv)).sum
    math.sqrt(d*1.0/(x.length-1))
}
val intervalDF_2 = intervalDF_1.groupBy("cid").agg(collect_list("interval") as "interval")
    .withColumn("interval",filterUDF($"interval"))
    .withColumn("interval_mv",mvUDF_interval($"interval"))
    .withColumn("interval_sd",sdUDF_interval($"interval",$"interval_mv"))
    .select("cid","interval_mv","interval_sd")

// 针对fit_day_interval中去掉了异常值的情况，要单独做deltaUDF
val deltaUDF4Interval = udf{
  (interval:Int,mv:Double,sd:Double) =>
    if(interval<180) 
    {
        if(interval != 0) (interval-mv)/sd  else 999
    }
    else 99999
}
// [cid: string, fit_day: string, pns_count: int, fit_count: int, delta_pns: double, pns: array<string>, interval: int, interval_mv: double, interval_sd: double, delta: double]
val intervalDF = intervalDF_1.join(intervalDF_2,Seq("cid"),"left_outer")
    .withColumn("delta_interval",deltaUDF4Interval($"interval",$"interval_mv",$"interval_sd"))
    .select("cid","fit_day","fit_count","pns_count","delta_pns","interval","delta_interval","pns")
    // .select("cid","fit_day","interval","interval_mv","interval_sd","delta_interval","fit_count","pns_count","delta_pns","pns")
    
    
intervalDF.write.mode("overwrite").parquet("tmp/intervalDF")

// ------------------ pause -------------------------------------------------------------------------------------------------
val intervalDF = sqlContext.read.parquet("tmp/intervalDF").filter("fit_day>'2010-01-01'")

import scala.util.control._
// 七、找出targetDate_interval
val targetDate_interval = intervalDF
    .map{
        r=>
        (r.getAs[String]("cid"),r.getAs[String]("fit_day")+"\u0001"+r.getAs[Int]("delta_interval").toString+"\u0001" + r.getAs[Int]("delta_pns").toString)
    }.toDF("cid","f&d")
    .groupBy("cid").agg(collect_list("f&d") as "f&d")
    .map{
        r=>
        val cid = r.getAs[String]("cid")
        val FAndDArr = r.getAs[Seq[String]]("f&d")
        // 去掉异常值如1970-01-01
        FAndDArr.filter(x=>x.split("\u0001").head>"2007-01-01")
        val fitArr=FAndDArr.map(x=>x.split("\u0001").head)
        val delta_interval_arr = FAndDArr.map(x=>x.split("\u0001")(1))
        val delta_pns_arr = FAndDArr.map(x=>x.split("\u0001")(2))

        // 遍历找连续两个小于-0.7的delta，然后确定开始的fit
        var counter = 0
        var targetDate = "initialString"
        val loop = new Breaks()

        var threshold = 0.7
        // if (delta_interval_arr.filter(x=> x!="99999.0"&&x!="NaN").nonEmpty){
        //     val d = delta_interval_arr.filter(x=> x!="99999.0"&&x!="NaN").sorted
        //     val idx = delta_interval_arr.filter(x=> x!="99999.0"&&x!="NaN").sorted.length/2
        //     threshold = d(idx).toDouble
        // }
        threshold = -0.15

        loop.breakable{
            // 算的是间隔，所以第一天是不能用的（虽然interval置为0，但这里看的是interval_delta）
            for (i <- 1 to delta_interval_arr.length-1){
                val delta = delta_interval_arr(i)
                print(">>>>"+"\n"+i+"\n"+delta+"\n"+fitArr(i)+"\n")
                if(delta.toDouble == 0){
                    targetDate = fitArr(i)
                    loop.break()
                }else{
                    if(delta.toDouble <= threshold) counter += 1 else counter = 0
                    targetDate = "noConsistentInterval"
                    if(counter >= 1) {
                        // if(delta_pns_arr(i-3).toDouble<1.5) targetDate = fitArr(i-3) else targetDate=fitArr(i-2)
                        if(i-2>=0) targetDate = fitArr(i-2) else targetDate=fitArr(i-1)
                        loop.break()
                    }
                }
            }
        }
        // if (targetDate=="noConsistentInterval"){

        // }

        (cid,targetDate)
    }.toDF("cid","targetDate_interval")

// 七、找出根据app安装的异常值
val targetDate_pnsCount = intervalDF
    .filter("fit_day>'2007-01-01'")
    .filter("delta_pns > 1.5")
    .groupBy("cid").agg(collect_list("fit_day") as "targetDate_pnsCount")

// 00249d08ee36192c3b48a877b93cce57
val installTimeDF = sqlContext.read.parquet("tmp/installTimeDF")
val channelIDDF = sqlContext.read.parquet("tmp/channelIDDF")
val resultDF_tmp = intervalDF.join(targetDate_interval,Seq("cid"),"left_outer").join(installTimeDF,Seq("cid"),"left_outer").join(targetDate_pnsCount,Seq("cid"),"left_outer").join(channelIDDF,Seq("cid"),"left_outer").join(registerDF,Seq("cid"),"left_outer")
// IMPORTANT
// 先处理没Join上的
val dealTheNullOfJoin = udf((targetDate_pnsCount:Seq[String])=>if(targetDate_pnsCount==null) Seq() else targetDate_pnsCount)
// 如果根据连续日期找到的前推的一天，所有的安装数量异常值都在这一天后面，取最近的那条安装数量异常记录
val findOutlierBehindUDF = udf{
    (targetDate_interval:String,targetDate_pnsCount:Seq[String])=>
        if(targetDate_pnsCount.nonEmpty){
            if (targetDate_pnsCount.head>targetDate_interval) targetDate_pnsCount.head
            else if (targetDate_pnsCount.head == targetDate_interval && targetDate_pnsCount.length>2) targetDate_pnsCount(1)
            else targetDate_interval
        }else{
            targetDate_interval
        } 
    }
val resultDF = resultDF_tmp.withColumn("targetDate_pnsCount",dealTheNullOfJoin($"targetDate_pnsCount")).withColumn("targetDateTime",findOutlierBehindUDF($"targetDate_interval",$"targetDate_pnsCount"))
resultDF.write.mode("overwrite").parquet("tmp/resultDF")


val r = sqlContext.read.parquet("tmp/resultDF")
// 过滤掉取不到installTime的 或 取不到targetDate的
val rdd = r.rdd.filter(x=>x.getAs[String]("targetDate_interval").length==10 && x.getAs[String]("installTime")!=null)
val df = sqlContext.createDataFrame(rdd,rdd.first.schema)
df.cache



// 22474/377990.0
df.filter("targetDate_interval>installTime").groupBy("cid").count.count.toString +"/"+ df.groupBy("cid").count.count// 28656  40708.  (9756,counter>=1, delta < -0.25)(7238,counter>=1, delta < -0.1) 2405
df.filter("targetDate_interval>installTime").show
df.groupBy("cid").count.count // 368938    368937
// 0.009 3507/368938
// 0.79 368938/463353 积累了一定时间的用户行为，app使用的API版本号>=V3
// 0.92 426445/463353 积累了一段时间的用户行为，能够判断开始使用的时间

// 积累了有用户行为，能判断使用时间的
// 368938 426444  463353
val rdd3 = r.rdd.filter(x=>x.getAs[String]("targetDate_interval").length==10)
sqlContext.createDataFrame(rdd3,rdd3.first.schema).groupBy("cid").count.count

// apiV3版本
val rdd4 = r.rdd.filter(x=>x.getAs[String]("installTime")!=null)
sqlContext.createDataFrame(rdd4,rdd4.first.schema).groupBy("cid").count.count

// 判断的使用时间有问题的  10787/415130   0.02
val rdd2=rdd.filter(x=>x.getAs[String]("targetDate_interval")<="2013-01-01")
sqlContext.createDataFrame(rdd2,rdd2.first.schema).groupBy("cid").count.count
sqlContext.createDataFrame(rdd2,rdd2.first.schema).show
// sqlContext.createDataFrame(rdd2,rdd2.first.schema).groupBy("cid").agg(collect_list("targetDate_interval") as "targetDate_interval").show

// resultDF的cid就是全部的cid
df.groupBy("cid").count.count.toString + "/"+ resultDF.groupBy("cid").count.count.toString 


// 070a1c599dd92ea98318123df32557cb

// 48144
//597247 
//628050


resultDF.dropDuplicates(Seq("cid")).groupBy("channelID").count.show
// +---------+------+
// |channelID| count|
// +---------+------+
// |   101513| 44428|
// |   100658|192815|
// |   101606| 20027|
// |   100946|303089|
// |   100785| 36888|
// +---------+------+
resultDF.dropDuplicates(Seq("cid")).filter("installTime is null").groupBy("channelID").count.show
// +---------+------+
// |channelID| count|
// +---------+------+
// |   101513| 40660|
// |   100658|178821|
// |   101606|     1|
// |   100946|282692|
// |   100785| 34146|
// +---------+------+
x=>x.getAs[String]("targetDate_interval").length==10 && 

r.drop("interval_mv").drop("interval_sd").drop("pns").show
val df_n = r.filter("channelID = 'google-play'")
val df_n = r.filter("channelID = '101606'")
df_n.cache
df_n.groupBy("cid").count.count // google-play:463335 101606:35361
val apiV3RDD = df_n.rdd.filter(x=>x.getAs[String]("installTime")!=null)
sqlContext.createDataFrame(apiV3RDD,apiV3RDD.first.schema).groupBy("cid").count.count // V3: 401450/463335 35361/35361
val canfindInterval = df_n.rdd.filter(x=>x.getAs[String]("targetDate_interval").length!=10)
sqlContext.createDataFrame(canfindInterval,canfindInterval.first.schema).groupBy("cid").count.count // 记录不够: 13259/463335(2.8%)  4333/35361(12%)
sqlContext.createDataFrame(canfindInterval,canfindInterval.first.schema).show(100)  // 无法判断是预装还是错误


// val rdd0 = df_n.rdd.filter(x=>x.getAs[String]("targetDate_interval").length==10 && x.getAs[String]("installTime")!=null)
val rdd0 = df_n.rdd.filter(x=>x.getAs[String]("targetDate_interval").length==10 && x.getAs[String]("installTime")!=null)
val df_nn = sqlContext.createDataFrame(rdd0,rdd0.first.schema)
// 可以用的记录： 389997/463335(84%)  31028/35361(). 9108/9842(92%)
df_nn.groupBy("cid").count.count+"/"+ df_n.groupBy("cid").count.count // 375976/463335(81%)  31028/35361(87%)  
// 101606渠道判错的: 2286/31028(7.3%). 581/9065(6%)
df_nn.filter("targetDateTime<installTime").groupBy("cid").count.count.toString +"/"+ df_nn.groupBy("cid").count.count
//  101606判断对的：  28742/31028(92.6%).  8529/9108(93%)
df_nn.filter("targetDateTime>installTime OR targetDateTime=installTime").groupBy("cid").count.count.toString +"/"+ df_nn.groupBy("cid").count.count
// GP渠道判断错的： 33449/389997(8%)
df_nn.filter("targetDateTime>installTime").groupBy("cid").count.count.toString +"/"+ df_nn.groupBy("cid").count.count

// df_nn.filter("targetDate_interval>installTime OR targetDate_interval=installTime OR targetDate_interval='initialString'").groupBy("cid").count.count.toString +"/"+ df_nn.groupBy("cid").count.count

//   19264/25747  22234/25747


df_nn.filter("targetDateTime<installTime").show(100) // 错误
df_nn.filter("targetDateTime=installTime").show(100) // 预装
df_nn.filter("targetDateTime>installTime").show(100) // 预装
// 26058 + 35361


     129,27,2,2,1,1














