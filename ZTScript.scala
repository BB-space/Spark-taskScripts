/**
  * Created by zac on 2017/8/17.
  */

import java.text.SimpleDateFormat

import com.alibaba.fastjson.JSON
import org.apache.hadoop.mapred.InvalidInputException
import org.apache.spark.sql.functions._
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.Map
import scala.util.control._

object ZTScript {

  // 把时间戳变成日期
  val timeStampUDF = udf((x: Long) => getDate(x))
  // 激活时间(最早的一次上报时间)
  val registerDateUDF = udf((x: Seq[String]) => x.min)
  // 安装时间(因为是groupBy+collect_list所以有多个重复的,取第一个)
  val installTimeUDF = udf((x: Seq[String]) => x.head)
  //计算均值的udf
  val mvUDF = udf((x: Seq[Int]) => x.sum.toDouble / x.length)
  // 计算标准差
  val sdUDF = udf {
    (x: Seq[Int], mv: Double) =>
      val variance = x.map(v => (v - mv) * (v - mv)).sum
      math.sqrt(variance * 1.0 / x.length)
  }
  // 计算与均值差了多少个标准差
  val deltaUDF = udf((pns_count: Int, mv: Double, sd: Double) => (pns_count - mv) / sd)
  var date: String = null
  var targetDate: String = null
  var channelFilterSQL: String = null
  // true:根据日期和渠道号解析json并生成infoDF, false:根据infoDF分析
  var modeInfoOrAnalysis: Boolean = true
  var productName: String = null
  var savePath: String = null
  var queryBasePath: String = "/bzdata/query"
  var cidBasePath: String = "/user/hive/warehouse/apuscommon.db/xal_basic"
  var package2productName: Map[String, String] = null
  var product2packageName: Map[String, String] = null


  def main(args: Array[String]) {
    var i = 0
    val len = args.length
    while (i < len - 1) {
      args(i) match {
        case "-modeInfoOrAnalysis" => modeInfoOrAnalysis = if (args(i + 1) == "true") true else false
        case "-date" => date = args(i + 1) //
        case "-targetDate" => targetDate = args(i+1)
        case "-channelFilterSQL" => channelFilterSQL = args(i + 1)
        case "-pn" => productName = args(i + 1)
        case "-savePath" => savePath = args(i + 1)
        case _ =>
      }
      i += 1
    }
    // -modeInfoOrAnalysis true -date 20170[8-9][0-3][0-9] -channel channelId='101606' -pn com.apusapps.launcher -savePathInfoDF anti_dog

//    require(productName != null, "-pn should be like 'launcher'")
//    require(savePath != null, "-savePathInfoDF should be like '/user/zhoutong/anti_dog'")
//    if (modeInfoOrAnalysis) {
//      // 生成infoDF
//      require(regxDate != null, "-date should be formated as 20170[6-7][0-3][0-9]")
//      require(channelFilterSQL != null, "-channel should be like channelId = '100946' OR channelId = '101606' OR channelId = '100658' OR channelId = '100785' OR channelId = '101513'")
//
//    }
    // "channelId='100658' OR channelId = '101253' OR channelId ='100946'"

    // INITIAL
    val conf = new SparkConf()
    val sc = new SparkContext(conf)
    sc.hadoopConfiguration.set("io.compression.codecs", "io.sensesecure.hadoop.xz.XZCodec")
    val sqlContext = new HiveContext(sc)
    prepare(sc)
    val jsonSourcePath = s"/bzdata/query/$date-*" // /bzdata/query/20170[7-8][0-3][0-9]-*
    val funnelPath = "/bzdata/funnel_log"
    // targetDate 2017-07-01

    // DATA FORMAT
    if (modeInfoOrAnalysis) {
//      val regxDate = "20170[8-9][0-3][0-9]" // 脚本里用for循环生成,一次性生成时间太久
//      val channelFilterSQL = "channelId='101606'"
//      val savePath = "tmp/anti_08-09"
//      val jsonSourcePath = queryBasePath+s"/$regxDate-*"
      handle_json(date, savePath, channelFilterSQL, jsonSourcePath, sc, sqlContext)
      //TODO
      /**
        * sqlContext.sql("select * from apuscommon.query_apps limit 19").show
        * 直接使用茂军解析的数据,不用再解json了
       */
    } else {
      analysis(channelFilterSQL,savePath,date,productName,targetDate,sc,sqlContext)
    }


  }



  //  handle_json(regxDate,savePath,jsonSourcePath,sc,sqlContext)
  def handle_json(date: String,savePath: String, channelFilterSQL: String, jsonSourcePath: String, sc: SparkContext, sqlContext: HiveContext): Unit = {
    import sqlContext.implicits._
    // regxDate = 20170801 23581  0802 24538  0803 25570
    //  df.count + " " + df.distinct.count + " " + df.groupBy("cid").count.count
    // 73689 73686 11684 080[1-3]的数据 groupBy之后会少很多是因为每天同一个用户都有很多条数据上来
    try {
      val infoDF = sc.textFile(jsonSourcePath).map {
        x =>
          if (x.split("\t").nonEmpty) {
            val rawStr = x.split("\t").last
            val jsonStr = jsonParseObjectWithKey(rawStr, "message")

            val cid = jsonParseObjectWithKey(jsonStr, "clientId")
            val jArrStr = jsonParseObjectWithKey(jsonStr, "p")
            val p_arr = jsonParseArray(jArrStr) // 打点上传的数据中,每个app的数据
            val targetInfoArr = if (p_arr.nonEmpty) {
                val pair_arr = p_arr.map {
                  str =>
                    // 取 每个app 的pn和fit
                    (jsonParseObjectWithKey(str.toString, "fit").toLong, jsonParseObjectWithKey(str.toString, "pn"))
                }.sortBy(x => x._1) // 40种pn 16种fit

                val pair_map = pair_arr.groupBy(_._1)
                val kvArr = pair_arr.map {
                  pair =>
                    val key = pair._1
                    val value = pair_map.get(pair._1).get.map(x => x._2).mkString(",")
                    (key, value)
                }.distinct
                kvArr
              } else Array[(Long, String)]()
            val time = jsonParseObjectWithKey(jsonStr, "time")
            val installTime = jsonParseObjectWithKey(jsonStr, "installTime")
            val channelId = jsonParseObjectWithKey(jsonStr, "channelId")
            (cid,targetInfoArr, time, installTime, channelId)
          } else {
            val cid = x
            val targetInfoArr = Array[(Long, String)]()
            val time: String = null
            val installTime: String = null
            val channelId: String = null
            (cid,targetInfoArr, time, installTime, channelId)
          }
      }.toDF("cid", "info", "time", "installTime", "channelId").filter(channelFilterSQL).repartition(128)
      val regex = """'([^\']*)'""".r
      val channel = regex.findAllIn(channelFilterSQL).toList.map(_.filter(_!='\'')).mkString("&")
      infoDF.write.mode("overwrite").parquet(savePath + s"/infoDF/dt=$date/channel=$channel")
      println(s">>>>>>>>>>>> json parse,generate infoDF of $date from $channel done")
    } catch {
      case ex: InvalidInputException => print(s"$date cannot find the file")
    }
  }



//  def QI_filtered(savePath: String, regxDate: String, pn: String, sc: SparkContext, hiveContext: HiveContext): Unit = {
//    // funnelDF 0801 ~ 0831  的count 和 distinct.count 1412210 1412210
//    import hiveContext.implicits._
//    try {
//      val funnel_log = sc.textFile(s"/bzdata/funnel_log/$regxDate/${pn}_*")
//      val funnel_df = funnel_log.map {
//        x =>
//          (jsonParseObjectWithKey(x, "clientId"), jsonParseObjectWithKey(x, "channelId"),
//            jsonParseObjectWithKey(x, "accept"), pn)
//      }.toDF("cid", "channelId", "accept", "pn").repartition(128)
//      funnel_df.write.mode("overwrite").parquet(savePath + s"/funnelDF/dt=${regxDate.replace('[', '-').filter(x => x != ']')}")
//    } catch {
//      case ex1: InvalidInputException => println(s">>>>>>>> $regxDate $pn 文件不存在")
//    }
//  }

  def CID_filter(cidBasePath: String, targetDate: String, pn: String, sc: SparkContext, hiveContext: HiveContext): DataFrame = {

    val filterRegisterDF = hiveContext.read.parquet(cidBasePath + s"/dt=$targetDate/pn=$pn/*/et=filterregister")
    val resultDF = filterRegisterDF.select("client_id_s", "channel_id_s")
      .withColumnRenamed("channel_id_s", "filterDF_channel")
      .withColumnRenamed("client_id_s", "cid")
    resultDF

  }

  def analysis(channelFilterSQL: String, savePath: String, regxDate: String, pn:String, targetDate:String, sc: SparkContext, hiveContext: HiveContext): Unit = {
    import hiveContext.implicits._
    //    val filterCIDDF = CID_filter(savePath)
    val regex = """'([^\']*)'""".r
    val channel = regex.findAllIn(channelFilterSQL).toList.map(_.filter(_!='\'')).mkString("&")
    val infoDF = hiveContext.read.parquet(savePath + s"/infoDF/dt=$regxDate/channel=$channel").filter(channelFilterSQL)
    val cidFilterDF = CID_filter(cidBasePath,targetDate,pn,sc,hiveContext)


    generateRegisterInstallAndChannelId(regxDate, targetDate,savePath, cidFilterDF, infoDF, hiveContext)
    generateIntervalDF(regxDate,targetDate,savePath, cidFilterDF, infoDF, hiveContext)
    generateResultDF(regxDate,targetDate,savePath,hiveContext,sc)
    println(">>>>>>> analysis complited")
    val ratio = finalRatio(regxDate,savePath,hiveContext,sc).toSeq
    sc.parallelize(ratio).toDF.write.mode("overwrite").parquet(savePath+s"/ratio/dt=${regxDate.replace('[', '-').filter(x => x != ']')}")

  }

  //
  def generateRegisterInstallAndChannelId(regxDate: String, targetDate:String,savePath: String, cidFilterDF: DataFrame, infoDF: DataFrame, sqlContext: HiveContext): Unit = {
    import sqlContext.implicits._
    val minOfArrUDF = udf((x: Seq[String]) => if (x.nonEmpty) x.min else "emptyTimeArr")
    val earliestTimeDF = infoDF.orderBy("cid", "time").groupBy("cid").agg(collect_list("time") as "timeArr").withColumn("register_time", minOfArrUDF($"timeArr")).drop("timeArr")
    // registerTime - 取最早的打点时间记录为注册激活时间
    val registerDF = cidFilterDF.select("cid").join(earliestTimeDF, Seq("cid"), "inner").repartition(256)
    registerDF.write.mode("overwrite").parquet(savePath + s"/registerDF/dt=$targetDate")

    val basicInfoDF_multiUse = cidFilterDF.select("cid").join(infoDF, Seq("cid"), "inner")
    basicInfoDF_multiUse.cache()


    val selectedInfoDF = basicInfoDF_multiUse.map {
      r =>
        val infoList = r.getAs[Seq[Row]]("info").map {
          row =>
            Seq[String](row.getAs[Long]("_1").toString, row.getAs[String]("_2"))
        }
        val cid = r.getAs[String]("cid")
        val installTime = r.getAs[String]("installTime")
        val time = r.getAs[String]("time")
        val channelId = r.getAs[String]("channelId")
        (cid, infoList, installTime, time, channelId)
    }.toDF("cid", "info", "installTime", "time", "channelId").withColumn("installTime", timeStampUDF($"installTime"))



    // installTime - 安装时间
    val installTimeDF = selectedInfoDF.filter("installTime != '' AND installTime is not null AND installTime != 'nothing'").groupBy("cid").agg(collect_list($"installTime") as "installTime").withColumn("installTime", installTimeUDF($"installTime"))
    installTimeDF.write.mode("overwrite").parquet(savePath + s"/installTimeDF/dt=$targetDate")
    // channelIDDF
    val channelIDDF = selectedInfoDF.groupBy("cid").agg(collect_list($"channelId") as "channelId").map(x => (x.getAs[String]("cid"), x.getAs[Seq[String]]("channelId").head)).toDF("cid", "channelID")
    channelIDDF.write.mode("overwrite").parquet(savePath + s"/channelIDDF/dt=$targetDate")
  }

  def generateIntervalDF(regxDate: String, targetDate:String,savePath: String, cidFilterDF: DataFrame, infoDF: DataFrame, sqlContext: HiveContext): Unit = {
    import sqlContext.implicits._
    // 用 latest的时间 和 cid 做join,取出用户最后一次上报的信息(app安装记录)
    val maxOfArrUDF = udf((x: Seq[String]) => if (x.nonEmpty) x.max else "emptyTimeArr")
    val latestTimeDF = infoDF.orderBy("cid", "time").groupBy("cid").agg(collect_list("time") as "timeArr").withColumn("time", maxOfArrUDF($"timeArr")).drop("timeArr")
    val latestInfoDF = latestTimeDF.join(infoDF, Seq("cid", "time"))
    latestInfoDF.write.mode("overwrite").parquet("tmp/latestInfoDF")
    val latestInfoDF_read = sqlContext.read.parquet("tmp/latestInfoDF")

    val basicInfoDF_0 = cidFilterDF.join(latestInfoDF_read, Seq("cid"), "inner")
    basicInfoDF_0.write.mode("overwrite").parquet(savePath + s"/basicInfoDF/dt=${regxDate.replace('[', '-').filter(x => x != ']')}")

    val basicInfoDF = sqlContext.read.parquet(savePath + s"/basicInfoDF/dt=${regxDate.replace('[', '-').filter(x => x != ']')}")
    val selectedInfoDF = basicInfoDF.map {
      r =>
        val infoList = r.getAs[Seq[Row]]("info").map {
          row =>
            Seq[String](row.getAs[Long]("_1").toString, row.getAs[String]("_2"))
        }
        val cid = r.getAs[String]("cid")
        val installTime = r.getAs[String]("installTime")
        val time = r.getAs[String]("time")
        val channelId = r.getAs[String]("channelId")
        (cid, infoList, installTime, time, channelId)
    }.toDF("cid", "info", "installTime", "time", "channelId").withColumn("installTime", timeStampUDF($"installTime"))
    // cid 和 fit
    val fit_day_1 = selectedInfoDF.withColumn("info", explode($"info")).map {
      r =>
        (r.getAs[String]("cid"),
          r.getAs[Seq[String]]("info").head.toLong,
          r.getAs[Seq[String]]("info").last,
          r.getAs[String]("installTime"),
          r.getAs[String]("time"))
    }.toDF("cid", "fit", "pns", "installTime", "time").groupBy("cid", "fit").agg(collect_list($"pns") as "pns").orderBy("cid", "fit")

    //   之前有过对pn做mkString(",")，而且有一些重复的记录被直接统计进来，所以还要distinct
    val prettyUDF = udf((x: Seq[String]) => x.flatMap(a => a.split(",")).distinct)
    //   为了方便agg，把Seq变成String
    val mkStringUDF = udf((x: Seq[String]) => x.mkString("\u0394"))
    //   把String拆回Seq
    val mkSeqpns_UDF = udf((x: Seq[String]) => x.flatMap(a => a.split("\u0394")).distinct)
    //   计算Seq的个数
    val countUDF = udf((x: Seq[String]) => x.length)

    val fit_day_2_rdd = fit_day_1
      .withColumn("pns", prettyUDF($"pns"))
      // .withColumn("pns",dropSystemAppUDF($"pns"))
      .rdd.filter(x => x.getAs[Seq[String]]("pns").nonEmpty)
    val fit_day_2 = sqlContext.createDataFrame(fit_day_2_rdd, fit_day_2_rdd.first.schema)

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
      .withColumn("mv", mvUDF($"pns_count"))
      .withColumn("sd", sdUDF($"pns_count", $"mv"))
      .select("cid", "mv", "sd")
    // 7.把统计数据join回去
    val fitDayDF = fit_day_4.join(fit_day_5, Seq("cid"), "left_outer")
      .withColumn("delta_pns", deltaUDF($"pns_count", $"mv", $"sd"))
      .select("cid", "fit_day", "pns_count", "fit_count", "delta_pns", "pns")

    fitDayDF.write.mode("overwrite").parquet("tmp/fitDayDF")

    val fitDayDF_read = sqlContext.read.parquet("tmp/fitDayDF")
    // 六、 timeInterval - 每条安装记录的间隔
    def getTimeInterval(d1: String, d2: String): String = {
      val df = new SimpleDateFormat("yyyy-MM-dd")
      val d1Stamp = df.parse(d1).getTime
      val d2Stamp = df.parse(d2).getTime
      ((d2Stamp - d1Stamp) / (1000 * 60 * 60 * 24)).toString
    }
    val timeIntervalUDF = udf {
      (x: Seq[String]) =>
        val orderedList = x.sorted
        var arr = Seq[String]("0")
        for (i <- 0 until orderedList.length - 1) {
          arr = arr :+ getTimeInterval(orderedList(i), orderedList(i + 1))
        }
        orderedList.zip(arr)
    } // f6eca91c47a3b20652cb873b303c2f53 65fb292467ca1075db7104bea1cbddc3 07325a2da87659c2c18ddc0173cd9084 8a8ddfcc830977bdaa98313d6e30955f
    // 这里开始计算日期间隔
    val t = fitDayDF_read.groupBy("cid").agg(collect_list("fit_day") as "fit_day_interval")
      .withColumn("fit_day_interval", timeIntervalUDF($"fit_day_interval"))
      .withColumn("fit_day_interval", explode($"fit_day_interval"))
      .map(x => (x.getAs[String]("cid"), x.getAs[Row]("fit_day_interval").getAs[String]("_1"), x.getAs[Row]("fit_day_interval").getAs[String]("_2").toInt))
      .toDF("cid", "fit_day", "interval")
    val intervalDF_1 = fitDayDF_read.join(t, Seq("cid", "fit_day"), "left_outer").orderBy("cid", "fit_day")
    // 针对fit_day_interval中的异常大的值，初步设定为大于180就抛弃
    // -1是因为第一天的interval不应该纳入计算，第一天是手动设置为interval=0的，但是x.length会把第一天也算作分母里的一个
    val filterUDF = udf((x: Seq[Int]) => x.filter(i => i < 180))
    val mvUDF_interval = udf((x: Seq[Int]) => x.sum.toDouble / (x.length - 1))
    val sdUDF_interval = udf {
      (x: Seq[Int], mv: Double) =>
        val d = x.map(v => (v - mv) * (v - mv)).sum
        math.sqrt(d * 1.0 / (x.length - 1))
    }
    val intervalDF_2 = intervalDF_1.groupBy("cid").agg(collect_list("interval") as "interval")
      .withColumn("interval", filterUDF($"interval"))
      .withColumn("interval_mv", mvUDF_interval($"interval"))
      .withColumn("interval_sd", sdUDF_interval($"interval", $"interval_mv"))
      .select("cid", "interval_mv", "interval_sd")

    // 针对fit_day_interval中去掉了异常值的情况，要单独做deltaUDF
    val deltaUDF4Interval = udf {
      (interval: Int, mv: Double, sd: Double) =>
        if (interval < 180) {
          if (interval != 0) (interval - mv) / sd else 999
        }
        else 99999
    }
    // [cid: string, fit_day: string, pns_count: int, fit_count: int, delta_pns: double, pns: array<string>, interval: int, interval_mv: double, interval_sd: double, delta: double]
    val intervalDF = intervalDF_1.join(intervalDF_2, Seq("cid"), "left_outer")
      .withColumn("delta_interval", deltaUDF4Interval($"interval", $"interval_mv", $"interval_sd"))
      .join(latestInfoDF_read.select("cid","time"),Seq("cid"),"left_outer")
      .select("cid", "fit_day", "fit_count", "pns_count","delta_pns", "interval", "delta_interval", "pns","time")
    // .select("cid","fit_day","interval","interval_mv","interval_sd","delta_interval","fit_count","pns_count","delta_pns","pns")

    intervalDF.write.mode("overwrite").parquet(savePath + s"/intervalDF/dt=$targetDate")
  }

  def generateResultDF(regxDate: String, targetDate:String,savePath: String, sqlContext: HiveContext, sc: SparkContext): Unit = {
    import sqlContext.implicits._

    val intervalDF = sqlContext.read.parquet(savePath + s"/intervalDF/dt=${regxDate.replace('[', '-').filter(x => x != ']')}").filter("fit_day>'2010-01-01'")


    val targetDate_interval = intervalDF
      .map {
        r =>
          (r.getAs[String]("cid"), r.getAs[String]("fit_day") + "\u0001" + r.getAs[Int]("delta_interval").toString + "\u0001" + r.getAs[Int]("delta_pns").toString)
      }.toDF("cid", "blendedInfo")
      .groupBy("cid").agg(collect_list("blendedInfo") as "blendedInfo")
      .map {
        r =>
          val cid = r.getAs[String]("cid")
          // 去掉异常值如1970-01-01
          val blendedInfo = r.getAs[Seq[String]]("blendedInfo").filter(x => x.split("\u0001").head > "2007-01-01")
          val fitArr = blendedInfo.map(x => x.split("\u0001").head)
          val delta_interval_arr = blendedInfo.map(x => x.split("\u0001")(1))
          val delta_pns_arr = blendedInfo.map(x => x.split("\u0001")(2))

          // 遍历找连续两个小于-0.7的delta，然后确定开始的fit
          var counter = 0
          var targetDate = "initialString"
          val loop = new Breaks()

          var threshold = 0.7
          threshold = -0.15

          loop.breakable {
            // 算的是间隔，所以第一天是不能用的（虽然interval置为0，但这里看的是interval_delta）
            for (i <- 1 until delta_interval_arr.length) {
              val delta = delta_interval_arr(i)
              print(">>>>" + "\n" + i + "\n" + delta + "\n" + fitArr(i) + "\n")
              if (delta.toDouble == 0) {
                targetDate = fitArr(i)
                loop.break()
              } else {
                if (delta.toDouble <= threshold) counter += 1 else counter = 0
                targetDate = "noConsistentInterval"
                if (counter >= 1) {
                  // if(delta_pns_arr(i-3).toDouble<1.5) targetDate = fitArr(i-3) else targetDate=fitArr(i-2)
                  if (i - 1 >= 0) targetDate = fitArr(i - 1) else targetDate = fitArr(i)
                  loop.break()
                }
              }
            }
          }
          // 如果时间不连续,就取倒数第二个,没有倒数第二个就取最后一个
          //           if (targetDate=="noConsistentInterval" && fitArr.nonEmpty){
          //             targetDate = fitArr.last
          //           }

          (cid, targetDate)
      }.toDF("cid", "targetDate_interval")

    // 七、找出根据app安装的异常值
    val targetDate_pnsCount = intervalDF
      .filter("fit_day>'2007-01-01'")
      .filter("delta_pns > 1.5")
      .groupBy("cid").agg(collect_list("fit_day") as "targetDate_pnsCount")

    val installTimeDF = sqlContext.read.parquet(savePath + s"/installTimeDF/dt=$targetDate")
    val channelIDDF = sqlContext.read.parquet(savePath + s"/channelIDDF/dt=$targetDate")
    val registerDF = sqlContext.read.parquet(savePath + s"/registerDF/dt=$targetDate")
    val resultDF_tmp = intervalDF.join(targetDate_interval, Seq("cid"), "left_outer").join(installTimeDF, Seq("cid"), "left_outer").join(targetDate_pnsCount, Seq("cid"), "left_outer").join(channelIDDF, Seq("cid"), "left_outer").join(registerDF, Seq("cid"), "left_outer")
    // IMPORTANT
    // 先处理没Join上的
    val dealTheNullOfJoin = udf((targetDate_pnsCount: Seq[String]) => if (targetDate_pnsCount == null) Seq() else targetDate_pnsCount)
    // 如果根据连续日期找到的前推的一天，所有的安装数量异常值都在这一天后面，取最近的那条安装数量异常记录
    val findOutlierBehindUDF = udf {
      (targetDate_interval: String, targetDate_pnsCount: Seq[String]) =>
        if (targetDate_pnsCount.nonEmpty) {
          if (targetDate_pnsCount.head > targetDate_interval) targetDate_pnsCount.head
          else if (targetDate_pnsCount.head == targetDate_interval && targetDate_pnsCount.length > 2) targetDate_pnsCount(1)
          else targetDate_interval
        } else {
          targetDate_interval
        }
    }
    val resultDF = resultDF_tmp.withColumn("targetDate_pnsCount", dealTheNullOfJoin($"targetDate_pnsCount")).withColumn("targetDateTime", findOutlierBehindUDF($"targetDate_interval", $"targetDate_pnsCount"))
    val processed_resultDF = dealNoConsistentInterval(resultDF,sqlContext)
    val outlierDF = findCertainOutlier(processed_resultDF)
    outlierDF.write.mode("overwrite").parquet(savePath + s"/outlierDF/dt=$targetDate")
    processed_resultDF.write.mode("overwrite").parquet(savePath + s"/resultDF/dt=$targetDate")
  }

  def finalRatio(regxDate: String, savePath: String, sqlContext: HiveContext, sc: SparkContext): Map[String, String] = {
    val df_n = sqlContext.read.parquet(savePath + s"/resultDF/dt=${regxDate.replace('[', '-').filter(x => x != ']')}")
    // 不可用数据
    val rdd1 = df_n.rdd.filter(x => x.getAs[String]("targetDate_interval").length != 10 || x.getAs[String]("installTime") == null)
    val df_nnn = sqlContext.createDataFrame(rdd1, rdd1.first.schema)



    val rdd0 = df_n.rdd.filter(x => x.getAs[String]("targetDate_interval").length == 10 && x.getAs[String]("installTime") != null)
    val df_nn = sqlContext.createDataFrame(rdd0, rdd0.first.schema)
    val usefulDataRatio = df_nn.groupBy("cid").count.count + "/" + df_n.groupBy("cid").count.count // 375976/463335(81%)  31028/35361(87%)
    val preInstallRatio = df_nn.filter("targetDateTime>installTime OR targetDateTime=installTime").groupBy("cid").count.count.toString + "/" + df_nn.groupBy("cid").count.count
    Map("usefulDataRatio" -> usefulDataRatio, "preInstallRatio" -> preInstallRatio)
  }

  def dealNoConsistentInterval(resultDF:DataFrame,hiveContext: HiveContext):DataFrame={
    import hiveContext.implicits._
    import org.apache.spark.sql.functions._
    // 如果发现installTime在安装记录的第一个甚至更早,说明这手机最早安装的就是launcher,直接把targetDate判断到安装记录的第一个.
    val tmp0 = resultDF.groupBy("cid").agg(collect_list("fit_day") as "fitArr")
    val joinedDF = resultDF.join(tmp0,Seq("cid"),"left_outer")
    val df = joinedDF.filter("targetDateTime = 'noConsistentInterval'").map{
      r=>
        val cid = r.getAs[String]("cid")
        val installTime = r.getAs[String]("installTime")
        val fitArr = r.getAs[Seq[String]]("fitArr")
        var targetDateTime = r.getAs[String]("targetDateTime")
        if(installTime<=fitArr.head) targetDateTime=fitArr.head
        (cid,targetDateTime)
    }.toDF("cid","newTargetDateTime")

    val chooseNewTargetDateTime = udf((targetDateTime:String,newTargetDateTime:String) => if(newTargetDateTime!=null) newTargetDateTime else targetDateTime)
    resultDF.join(df,Seq("cid"),"left_outer").withColumn("targetDateTime",chooseNewTargetDateTime($"targetDateTime",$"newTargetDateTime")).drop("newTargetDateTime")
  }

  def findCertainOutlier(reslutDF:DataFrame):DataFrame={
    reslutDF.filter("targetDateTime<installTime").select("cid","fit_day","targetDateTime","installTime","channelID","register_time","time")

  }

  def jsonParseArray(jsonArrString: String): Array[Object] = {
    var resultArr = Array[Object]()
    if (JSON.parseArray(jsonArrString) != null) {
      resultArr = JSON.parseArray(jsonArrString).toArray()
    } else {
      resultArr = Array[Object]()
    }
    resultArr
  }
  def jsonParseObjectWithKey(jsonStr: String, key: String): String = {
    var result = ""
    try {
      val getResult = JSON.parseObject(jsonStr).get(key)
      if (getResult != null) {
        result = getResult.toString
      } else {
        result = "nothing"
      }
    } catch {
      case _: Exception =>
    }
    result
  }
  def getDate(ts: Long): String = {
    val df = new SimpleDateFormat("yyyy-MM-dd")
    val date = df.format(ts)
    date
  }
  def prepare(sc: SparkContext): Unit = {
    product2packageName = sc.textFile("/user/zhoutong/package2product.txt").map {
      x =>
        val packageName: String = x.trim.split("\t").head
        val productName: String = x.trim.split("\t").last
        (productName, packageName)
    }.collectAsMap() // newshunter -> com.newshunter

    package2productName = sc.textFile("/user/zhoutong/package2product.txt").map {
      x =>
        val packageName: String = x.trim.split("\t").head
        val productName: String = x.trim.split("\t").last
        (packageName, productName)
    }.collectAsMap() // com.newshunter -> newshunter

  }

}
