大致思路：
showDF
+--------------------------------+----------+---------+---------+-------------------+-----------+----------+
|cid                             |fit_day   |pns_count|fit_count|register_time      |installTime|targetDate|
+--------------------------------+----------+---------+---------+-------------------+-----------+----------+
|00000613d55300f94343bf2b4c6296e6|2016-04-18|21       |13       |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-03-16|1        |1        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-03-20|1        |1        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-03-22|1        |1        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-03-30|1        |1        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-04-11|4        |4        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-04-28|1        |1        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-05-05|1        |1        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-05-08|1        |1        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-05-11|2        |2        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-05-12|2        |2        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-05-14|2        |2        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-05-30|2        |2        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-06-01|4        |4        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-06-04|2        |2        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-06-05|1        |1        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2017-06-07|2        |2        |2017-06-01 03:38:35|2017-05-11 |2017-03-16|
+--------------------------------+----------+---------+---------+-------------------+-----------+----------+

groupBy+collect_list 把fit_day放到一起，然后处理这个数组得到日期间隔，再explode，这时候schema是 cid,fit_day。
然后按cid join回来
这里的问题有两个，一个是怎么算日期间隔，一个是collect_list和explode顺序会变吗,不过肉眼可以检验一下


// 展示用
val pretty_anti_all = sqlContext.read.parquet("tmp/pretty_anti_all").select("cid","fit_day","pns_count","fit_count")
// 激活时间time
val registerDF = sqlContext.read.parquet("tmp/registerDF")
// 开始使用时间
val targetDateDF = sqlContext.read.parquet("tmp/tmp3")
// 安装时间（但是 468755
val installTimeDF = sqlContext.read.parquet("tmp/installTimeDF")
val showDF = pretty_anti_all.join(registerDF,Seq("cid"),"left_outer").join(installTimeDF,Seq("cid"),"left_outer").join(targetDateDF,Seq("cid"),"left_outer")


import java.util.Date
import java.text.SimpleDateFormat
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
val intervalDF = showDF.groupBy("cid").agg(collect_list("fit_day") as "fit_day_interval")
.withColumn("fit_day_interval",timeIntervalUDF($"fit_day_interval"))
.withColumn("fit_day_interval",explode($"fit_day_interval"))
.map(x=>(x.getAs[String]("cid"),x.getAs[Row]("fit_day_interval").getAs[String]("_1"),x.getAs[Row]("fit_day_interval").getAs[String]("_2")))
.toDF("cid","fit_day","interval")

val j_show = showDF.join(intervalDF,Seq("cid","fit_day"),"inner").select("cid","pns_count","fit_count","fit_day","interval","installTime","register_time","targetDate").orderBy("cid","fit_day")
j_show.write.mode("overwrite").parquet("tmp/j_show")




+--------------------------------+---------+---------+----------+--------+-----------+-------------------+----------+
|cid                             |pns_count|fit_count|fit_day   |interval|installTime|register_time      |targetDate|
+--------------------------------+---------+---------+----------+--------+-----------+-------------------+----------+
|00000613d55300f94343bf2b4c6296e6|21       |13       |2016-04-18|0       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|1        |1        |2017-03-16|332     |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|1        |1        |2017-03-20|4       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|1        |1        |2017-03-22|2       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|1        |1        |2017-03-30|8       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|4        |4        |2017-04-11|12      |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|1        |1        |2017-04-28|17      |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|1        |1        |2017-05-05|7       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|1        |1        |2017-05-08|3       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2        |2        |2017-05-11|3       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2        |2        |2017-05-12|1       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2        |2        |2017-05-14|2       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2        |2        |2017-05-30|16      |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|4        |4        |2017-06-01|2       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2        |2        |2017-06-04|3       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|1        |1        |2017-06-05|1       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00000613d55300f94343bf2b4c6296e6|2        |2        |2017-06-07|2       |2017-05-11 |2017-06-01 03:38:35|2017-03-16|
|00002fa6fe658828aa3b2d62c3fab095|23       |2        |2014-07-30|0       |2017-05-03 |2017-06-01 05:30:23|2017-05-01|
|00002fa6fe658828aa3b2d62c3fab095|1        |1        |2017-05-01|1006    |2017-05-03 |2017-06-01 05:30:23|2017-05-01|
|00002fa6fe658828aa3b2d62c3fab095|2        |2        |2017-05-02|1       |2017-05-03 |2017-06-01 05:30:23|2017-05-01|
+--------------------------------+---------+---------+----------+--------+-----------+-------------------+----------+

val j_show = sqlContext.read.parquet("tmp/j_show")
j_show.filter("fit_day = targetDate").show(200,false) // 找到targetDate，单独观察那一天的情况，interval没有规律
j_show.filter("targetDate > installTime").show(200,false) // 找到targetDate之前就installTime的记录

val tagTargetOfFitDayUDF = udf{
    (fit_day:String,interval:String,targetDate:String)=>
    var interval_new:String = interval
    if(fit_day==targetDate){
        interval_new = interval+"_tag"
    }
    interval_new
}
j_show.withColumn("interval",tagTargetOfFitDayUDF($"fit_day",$"interval",$"targetDate")).groupBy("cid").agg(collect_list("interval") as "interval_seq")

j_show.groupBy("cid").agg(collect_list("interval") as "interval_seq")




// 评估，按7天来看，有连续三条 “每日安装记录”，且每条记录之间的日期间隔不超过7天
import scala.util.control._
val findIntervalUDF = udf{
    (intervalAndFitDay:Seq[String]) =>
    // 形如 interval\u0394fit_day 这种
        var counter = 0
        val loop = new Breaks()
        var beginDate:String = null

        loop.breakable{
            for(i <- 1 until intervalAndFitDay.length-1){
                if(intervalAndFitDay(i).split("\u0394").head.toInt<7) counter+=1 else counter=0
                if (counter>=2) {
                    beginDate = intervalAndFitDay(i-2).split("\u0394").last
                    loop.break()
                }else{
                    beginDate = "没有连续三条数据的interval<7"
                }
            }
        }
        beginDate
}
val mkStringUDF = udf{
    (interval:String,fit_day:String)=>
    interval+"\u0394"+fit_day
}
val beginDateDF = j_show
.withColumn("stringSeq",mkStringUDF($"interval",$"fit_day"))
.groupBy("cid").agg(collect_list("stringSeq") as "stringSeq")
.withColumn("stringSeq",findIntervalUDF($"stringSeq"))

val r = j_show.join(beginDateDF,Seq("cid"),"inner")










