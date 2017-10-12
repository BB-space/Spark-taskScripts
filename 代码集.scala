
scala 求标准差和求均值，求每个值与均值差多少个标准差
def mv(arr:Seq[Int]) : Double={
    arr.sum.toDouble/arr.length
}
def sd(arr:Seq[Int]) : Double={
    val mv_value = mv(arr)
    math.sqrt(arr.map(x=> (x-mv_value)*(x-mv_value)).sum.toDouble/arr.length)
}
def delta(arr:Seq[Int]) : Seq[Double]={
    arr.map{
        x=>
        (x-mv(arr))/sd(arr).toDouble
    }
}


sqlContext.createDataFrame(u2.rdd.filter(x=>x.getAs[String]("allSixId").startsWith("mac")),u2.rdd.filter(x=>x.getAs[String]("allSixId").startsWith("mac")).first.schema).show(100,false)



val savePath = "CID/BaseDataUDFCross/Model/ver=1_0610_white_v2_label_v2"
val featurePath = savePath+"/featureMap"
val modelPath = savePath+"/lrModel"
val trainObservation_fast = sqlContext.read.parquet(savePath + "/trainRowDF").map(r => WeightedLabeledObservation.fromRow(r)).zipWithIndex().filter(r=>r._1.label == 1L)
val t = trainObservation_fast.filter(r=>r._1.label == 0L)
val lrModel = LogisticRegressionModelSaveWrite.loadModel(sc,modelPath)
val result_rdd = lrModel.predictScoreWithObs(trainObservation_fast.map(_._1))
val resultDF = result_rdd.map{
    x=>
      (x._2,
        x._1.features.asInstanceOf[SparseVector].indices,
        x._1.strAttrs.get("id"),
        x._1.strAttrs.get("featureArray"),
        x._1.strAttrs.get("model_s"),
        x._1.strAttrs.get("country_s"),
        x._1.strAttrs.get("client_id_s"))
  }.toDF("score","features","id","features_value","model_s","country_s","client_id_s")
// >>>>>> 要拼上featureMap的特征
val featureMapRDD = sc.textFile(savePath+"/featureMap").map(x=>(x.split("\t").last,x.split("\t").head))
val featureMap = featureMapRDD.collectAsMap()

val d = udf((x:Seq[Int])=>x.map(a=>featureMap.get(a.toString).get))
val newJDF = resultDF.withColumn("features_tag_str",d($"features"))
newJDF.groupBy("score").count.orderBy("score").show

val jDF = sqlContext.read.parquet(savePath + "/jDF")
val mkStringUDF = udf((x:Seq[String])=>x.mkString("\uffff"))
val selected_jDF = jDF.select("score","features_tag") 
selected_jDF.groupBy("score").count.orderBy("score").show




// ------ 
// jion到的7个都要查model_s，如果所有的model_s都不相同说明这肯定不是一个老用户。
// 如果这7个有某一个model_s相同，country_s相同，说明这应该是相同的用户？？？
// val df = j.map{
//     r=>
//     val m=r.getAs[String]("model_s")
//     val hm=r.getAs[String]("h_model_s")
//     val fv=r.getAs[String]("features_value")
//     val str = m+"\uffff"+hm
//     (fv,str)
// }.toDF("features_value","model_s")
// val groupedDF = df.groupBy("features_value").agg(collect_list("model_s") as "model_s")
// val mkSeqHistoryModelUDF = udf((x:Seq[String])=>x.map(str=>str.split("\uffff").last))
// val getOriginalModelUDF = udf((x:Seq[String])=>x.head.split("\uffff").head)
// groupedDF.withColumn("h_model_s",mkSeqHistoryModelUDF($"model_s")).withColumn("model_s",getOriginalModelUDF($"model_s"))

val d1=j2.select("features_value","model_s")
val d2=j2.groupBy("features_value").agg(collect_list("h_model_s") as "h_model_s")
val d3=d1.join(d2,Seq("features_value"),"inner")
d3.select("h_model_s","model_s").rdd.filter(r=> !r.getAs[Seq[String]]("h_model_s").contains(r.getAs[String]("model_s"))).count
d3.select("h_model_s","model_s").rdd.filter(x=>x.getAs[Seq[String]]("h_model_s").distinct.length>1)
// 0/72 和 132个
// 0/370    0/1234755
// 阈值0.95: 
//      在android_id_s_android_serial_s_imei_s进行join得到的结果中
//      共有 72 个, h_model_s中没有出现过model_s的为 0 个
//      
//



// -------------------------------
val date = "2017-07-01"
val n = sqlContext.read.parquet(s"/user/zhoutong/submit/dt=$date/newDF")
val h = sqlContext.read.parquet(s"/user/zhoutong/submit/dt=$date/historyDF")
val j = sqlContext.read.parquet(s"/user/zhoutong/submit/dt=$date/joinedDF")
val l = sqlContext.read.parquet(s"/user/zhoutong/submit/dt=$date/labeledDF")
val e = sqlContext.read.parquet(s"/user/zhoutong/submit/dt=$date/extractorDF")
val aggregatedDF = sqlContext.read.parquet("/user/zhoutong/aggregatedDF").drop("dt")

val new02 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-02/newDF")
val new03 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-03/newDF")
val new04 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-04/newDF")
val his01 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-01/historyDF")
val his02 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-02/historyDF")
val his03 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-03/historyDF")
val his04 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-04/historyDF")

val cn1 = new01.count  // 6,712,086
val ch1 = his01.count  // 196,111,716
val cn2 = new02.count  // 6,708,486
val ch2 = his02.count  // 200,596,933
val cn3 = new03.count  // 5,837,370
val ch3 = his03.count  // 200,596,933 200724857
val cn4 = new04.count  // 6,983,634
val ch4 = his04.count  // 200,596,933
val nDF = sqlContext.read.parquet("/user/zhoutong/uniqueID/dt=2017-07-01/")

val n1 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-01/newDF").toDF("allSixId","id1")
val n2 = sqlContext.read.parquet("/user/zhoutong/submit/dt=2017-07-02/newDF").toDF("allSixId","id2")
val j12 = n1.join(n2, Seq("allSixId"), "left_outer")  
j12.orderBy("id2").show
// ---------------------------
// 使用模型
val trainObservation_fast = 
val lrModel = LogisticRegressionModelSaveWrite.loadModel(sc,modelPath)
val result_rdd = lrModel.predictScore(trainObservation_fast.map(_._1))
val predictResultDF_tmp1 = result_rdd.zipWithIndex().toDF("score","index")
val predictResultDF_idx = predictResultDF_tmp1.rdd.map(r=>(r.getAs[Double]("score"),r.getAs[Long]("index"))).toDF("score","index")

// percentile 查看百分位
val name = "tableName"
df_score.registerTempTable(name)
var df_ini = sqlContext.sql(s"SELECT percentile_approx(score,0.01) from $name")
for (i<-2 to 99){
  val t= i/100.0
  df_ini=df_ini.unionAll(sqlContext.sql(s"SELECT percentile_approx(score,$t) from $name"))
}
val tmpRDD = df_ini.rdd.zipWithIndex()
val percentileDF = tmpRDD.map(line=> (line._1.getAs[Double]("_c0"),line._2)).toDF("percentile","idx")


// 查看joinedDF中的id 有多少个是 "0"
joinedDF.map(_.getAs[String]("allSixId")).countByValue.toList.filter(_._2>1).sortBy(_._2).foreach(println)
// 查看重复个数
new01.groupBy("allSixId").count.orderBy(desc("count")).show
// 查看 advertising_id_s开头的
dfNotNull.map(_.getAs[String]("allSixId"))         // 会把原DF的"allSixId"列取成一个RDD
         .filter(_.startsWith("advertising_id_s")) // 找出 "advertising_id_s" 开头的保留下来 :RDD
         .countByValue                             // 按出现了几次进行计数，得到一个Map类型 可以用 res.get("key")
         .toList                                   // Map类型转成List更好操作
         .filter(_._2 > 1)                         // 找出第二项（即出现次数）大于1的
         .sortBy(_._2)							   // 按第二项（出现次数）排序
         .foreach(println)						   // 输出每一个
// 查看DF中以 mac_addr_s 开头，并且重复出现的数据
newDF.map(_.getAs[String]("allSixId")).filter(_.startsWith("cpu_serial_s")).countByValue.toList.filter(_._2 > 1).sortBy(_._2).foreach(println)
// 找出这个DF里面以cpu_serial_s=000开头的数据
historyDF.map(_.getAs[String]("allSixId")).filter(_.startsWith("cpu_serial_s=000")) // 得到一个RDD
// 找出 labeledDF 中所有标记是1的
val v1 = labeledDF.filter(labeledDF("base_record").getField("label").equalTo(1))
v1.map{
    r=>
    val sequence=r.getAs[Seq[Row]]("cid_feature")
    sequence.map{
        row=>
        val featureStr=row.getAs[String]("_1")
    }
    }
// labeledDF.map(_.getAs[Row]("base_record")).filter(_.getAs[Long]("label")==1)







// Spark书的代码示例 - 累加器 accumulator
val blankLines = sc.accumulator(0)
val callSigns = file.flatMap(line => {
	if (line == ""){
		blankLines += 1
	}
	line.split(" ")
	})
callSigns.saveAsTextFile("output.txt")
println("Blank lines:"+blankLines.value)

// Spark书的代码示例 - 广播变量 broadcast
// 有一个RDD contactCounts
val signPrefixes = sc.broadcast(loadCallSignTable())
val countryContact_counts = contactCounts.map{
	case (sign, count) =>
	val country = lookupInArray(sign, signPrefixes.value)
	(country, count)
}.reduceByKey((x,y) => x + y)
countryContact_counts.saveAsTextFile(outputDir + "/countries.txt")

// Spark书的代码示例 - 共享连接池+分区操作
val contactsList = validSigns.distinct().mapPartitions{
	signs =>
	val mapper = createMapper()
	val client = new HttpClient()
	client.start()
	signs.map{signs=>
		createExhangeForSign(sign)
		// 获取响应
	}.map{case (sign,exchange) => 
			(sign,readExchangeCallLog(mapper,exchange)) }.filter(x => x._2 != null) // 删除空的呼叫日志
}


// 把脏数据文件放到HDFS上，使用SparkRDD的方式去检测

val ais_rdd = sc.textFile("/user/zhoutong/dirty_data/android_id_s.txt").zipWithIndex()
val adis_rdd = sc.textFile("/user/zhoutong/dirty_data/advertising_id_s.txt").zipWithIndex()
val ass = sc.textFile("/user/zhoutong/dirty_data/android_serial_s.txt").zipWithIndex()
val mac_rdd = sc.textFile("/user/zhoutong/dirty_data/mac_addr_s.txt").zipWithIndex()
val imei_rdd = sc.textFile("/user/zhoutong/dirty_data/imei_s.txt").zipWithIndex()
val cpu_rdd = sc.textFile("/user/zhoutong/dirty_data/cpu_serial_s.txt").zipWithIndex()

if(ais_rdd.lookup(idValue).nonEmpty){idValue=dirtyDataTag}
if(adis_rdd.lookup(idValue).nonEmpty){idValue=dirtyDataTag}
if(ass.lookup(idValue).nonEmpty){idValue=dirtyDataTag}
if(mac_rdd.lookup(idValue).nonEmpty){idValue=dirtyDataTag}
if(imei_rdd.lookup(idValue).nonEmpty){idValue=dirtyDataTag}
if(cpu_rdd.lookup(idValue).nonEmpty){idValue=dirtyDataTag}

// broadcast 查询
    val a = sc.textFile().map(x => (x,"")).collectAsMap
    val b = sc.broadcast(a)
    df.map{
        x => 
        val v = b.value.getOrElse(x.getAs[String]("id"), null).filter()
    }


// 查看模型参数以及其对应的是哪个feature
//从Map中取到Any=ArrayBuffer后怎么办
val paramsArr = lrModel.getParams().get("coefficients").get.asInstanceOf[org.apache.spark.mllib.linalg.DenseVector].toArray
val df_params = sc.parallelize(paramsArr).zipWithIndex().toDF("params","index")
val res8 = fs.getParams().get("featureToColMap").getOrElse("NoneItem")
val df_feature = sc.parallelize(res8.asInstanceOf[ArrayBuffer[(String,Int)]].toSeq).toDF("feature","index")
val df_result = df_feature.join(df_params,Seq("index"),"left_outer")
df_feature.join(df_params,Seq("index"),"left_outer").orderBy("params").show(160,false)

//


//
j.rdd.filter(x=>x.getAs[String]("allSixId").split("=").head=="android_id_s").filter(x=>x.getAs[String]("tag")=="1").count
// 查看 日期 中， joinedDF advertising_id_s 重复的个数、重复次数为1的个数，重复次数最多那个id为几次

val date = "2017-07-02"


val id = "android_id_s"
var arr = Array(id)
val dateArr = Seq("2017-07-01", "2017-07-02", "2017-07-03", "2017-07-04", "2017-07-05", "2017-07-06", "2017-07-07", "2017-07-08", "2017-07-09", "2017-07-10", "2017-07-11", "2017-07-12", "2017-07-13", "2017-07-14", "2017-07-15", "2017-07-16", "2017-07-17", "2017-07-18", "2017-07-19")
dateArr.foreach{
    x=>
    val date = x
    val j = sqlContext.read.parquet(s"CID/analysis/submit/dt=$date/joinedDF")
    val rdd = j.rdd.filter(x=>x.getAs[String]("allSixId").split("=").head==s"$id").filter(x=>x.getAs[String]("tag")=="1")
    val df_n = sqlContext.createDataFrame(rdd,rdd.first.schema)
    val id_1 = df_n.groupBy("allSixId").count.orderBy(desc("count")).filter("count = '1'").count
    val id_all = df_n.count
    val id_percent = id_1*1.0/id_all
    val id_repeat_most = df_n.groupBy("allSixId").count.orderBy(desc("count")).first
    arr :+ id_percent.toString
    println(">>>>>>>>>>>>>")
}

// 把joinedDF变成 ((id1,id2,id3,id4,id5,id6) , id) 这种，并且只把重复的挑出来
// 首先找到重复的id，作为新的idDF即rDF

val date = "2017-06-01"

// val joinedDF = sqlContext.read.parquet(s"/user/zhoutong/CID/analysis/submit/dt=$date/joinedDF")
val joinedDF = sqlContext.read.parquet(s"test_dirtyData_noCPU/submit/dt=$date/joinedDF")
// 查看 重复的ID

val idDF = joinedDF.filter("tag is not null").select("id").distinct
val agg_joiendDF = joinedDF.groupBy("id").agg(collect_list("allSixId") as "allSixId")
val rDF = idDF.join(agg_joiendDF,Seq("id"),"left_outer") // id->2017-07-01 allSixId->(id1,id2,id3..)
val idNameSeq = rDF.first.getAs[Seq[String]]("allSixId").map(x=>x.split("=").head)
val repeatedDF = rDF.map{
    r=>
    val allSixIdSeq = r.getAs[Seq[String]]("allSixId")
    val id = r.getAs[String]("id")
    val newSeq=allSixIdSeq.map(x=>(x.split("=").head,x.split("=").last))
    (id,newSeq.head._2,newSeq(1)._2,newSeq(2)._2,newSeq(3)._2,newSeq(4)._2)
}.toDF("id",idNameSeq.head,idNameSeq(1),idNameSeq(2),idNameSeq(3),idNameSeq(4))
val theDate_selectedSeq = idNameSeq :+ "country_s" :+ "model_s" :+ "client_id_s" :+ "height_s" :+ "memory_total_s" :+ "storage_total_s" :+ "mcc_s" :+ "sdk_level_l"
val theDate_joinSeq = idNameSeq
val theDate_basicDF = sqlContext.read.parquet(s"/user/hive/warehouse/apuscommon.db/xal_basic/dt=$date/*/*/et=register")
val theDate_selectedDF = theDate_basicDF.select(theDate_selectedSeq.head,theDate_selectedSeq.drop(1):_*).distinct
val theDate_joinedDF = repeatedDF.join(theDate_selectedDF,idNameSeq,"inner")
.withColumnRenamed("client_id_s","original_client_id_s")
.withColumnRenamed("model_s","original_model_s")
.withColumnRenamed("country_s","original_country_s")
.withColumnRenamed("height_s","original_height_s")
.withColumnRenamed("memory_total_s","original_memory_total_s")
.withColumnRenamed("storage_total_s","original_storage_total_s")
.withColumnRenamed("mcc_s","original_mcc_s")
.withColumnRenamed("sdk_level_l","original_sdk_level_l")
/**
*repeatedDF
*+------------------+--------------------+-----------------+-----------------+-----------------+--------------------+----------------+
*|                id|    android_serial_s| advertising_id_s|     cpu_serial_s|       mac_addr_s|              imei_s|    android_id_s|
*+------------------+--------------------+-----------------+-----------------+-----------------+--------------------+----------------+
*
*theDate_selectedDF
*+----------------+-----------------+--------------------+----------------+----------------+---------------+---------+-----------------+--------------------+
*|android_serial_s|       mac_addr_s|    advertising_id_s|    cpu_serial_s|    android_id_s|         imei_s|country_s|          model_s|         client_id_s|
*+----------------+-----------------+--------------------+----------------+----------------+---------------+---------+-----------------+--------------------+
*
*theDate_joinedDF
*+----------------+--------------------+--------------------+----------------+----------------+--------------------+------------------+---------+---------+--------------------+
*|android_serial_s|          mac_addr_s|    advertising_id_s|    cpu_serial_s|    android_id_s|              imei_s|                id|country_s|  model_s|         client_id_s|
*+----------------+--------------------+--------------------+----------------+----------------+--------------------+------------------+---------+---------+--------------------+
*
*/
val theDate_joinedDF_b = sc.broadcast(theDate_joinedDF) // 28
val history_basicDF = sqlContext.read.parquet(s"/user/hive/warehouse/apuscommon.db/xal_basic/").filter(s"dt<'$date'").filter(s"et='register'")
val history_basic_renameDF = history_basicDF.withColumnRenamed("height_l","height_s")
val history_selectedDF = history_basic_renameDF.select(theDate_selectedSeq.head,theDate_selectedSeq.drop(1):_*)
val theDate_join_history = theDate_joinedDF_b.value.join(history_selectedDF,idNameSeq,"inner")



uniqueID.count == uniqueID.select("allSixId").distinct.count


// mingli  明理，增强版，可惜各个ID的名字改动太乱_l _s，除此之外空值的表示方法太乱了 N/A /N -1 各种
val date = "2017-07-02"
val verifyArr = Seq("advertising_id_s","imei_s","country_s","model_s","mcc_s","height_l","memory_total_l","storage_total_l","sdk_level_l")
val hasAndroidIDArr = Seq("android_id_s") ++ verifyArr
val df1 = sqlContext.read.parquet(s"/user/hive/warehouse/apuscommon.db/xal_basic/dt=$date/*/*/et=register")
.select(hasAndroidIDArr.head,hasAndroidIDArr.drop(1):_*)
.withColumnRenamed("advertising_id_s","ori_advertising_id_s")
.withColumnRenamed("imei_s","ori_imei_s")
.withColumnRenamed("country_s","ori_country_s")
.withColumnRenamed("model_s","ori_model_s")
.withColumnRenamed("mcc_s","ori_mcc_s")
.withColumnRenamed("height_l","ori_heihgt_l")
.withColumnRenamed("memory_total_l","ori_memory_total_l")
.withColumnRenamed("storage_total_l","ori_storage_total_l")
.withColumnRenamed("sdk_level_l","ori_sdk_level_l").distinct



val df2_endWith_S = sqlContext.read.parquet(
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-05-2[2-9]/*/*/et=register",
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-05-3[0-1]/*/*/et=register",
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-06-[0-1][0-9]/*/*/et=register",
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-06-2[0-8]/*/*/et=register")
.withColumnRenamed("height_s","height_l")
.withColumnRenamed("memory_total_s","memory_total_l")
.withColumnRenamed("storage_total_s","storage_total_l")
.select(hasAndroidIDArr.head,hasAndroidIDArr.drop(1):_*).distinct
val df2 = sqlContext.read.parquet(
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-05-[0-1][0-9]/*/*/et=register",
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-05-2[0-1]/*/*/et=register",
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-06-29/*/*/et=register",
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-06-30/*/*/et=register",
    "/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-01/*/*/et=register")
.select(hasAndroidIDArr.head,hasAndroidIDArr.drop(1):_*).distinct.unionAll(df2_endWith_S)
// df2 52,251,505
df2.write.mode("overwrite").parquet(s"mingli/${date}_df2")
val df_2 = sqlContext.read.parquet("df2").distinct // 49,917,144 
val df1_b = sc.broadcast(df1)
val df2_b = sc.broadcast(df_2)
val j_anid = df1_b.value.join(df2_b.value,Seq("android_id_s"),"inner")
j_anid.write.mode("overwrite").parquet(s"mingli/${date}_j_anid")
val df2_count = df2.count //        52,251,505   53,170,531  
val df1_count = df1.count //         1,126,874    1,128,009  
val repeate_count = j_anid.count //  2,472,201    2,822,591 


j_anid.groupBy("android_id_s").count.orderBy(desc("count")).show

/*
*j_anid
*+----------------+--------------------+---------------+-------------+--------------+---------+------------+------------------+-------------------+---------------+--------------------+---------------+---------+--------------+-----+--------+--------------+---------------+-----------+
*|    android_id_s|ori_advertising_id_s|     ori_imei_s|ori_country_s|   ori_model_s|ori_mcc_s|ori_heihgt_l|ori_memory_total_l|ori_storage_total_l|ori_sdk_level_l|    advertising_id_s|         imei_s|country_s|       model_s|mcc_s|height_l|memory_total_l|storage_total_l|sdk_level_l|
*+----------------+--------------------+---------------+-------------+--------------+---------+------------+------------------+-------------------+---------------+--------------------+---------------+---------+--------------+-----+--------+--------------+---------------+-----------+
*/
// mINGLI -- 稳妥版本

val MINGLI_ARR=Seq("advertising_id_s","android_id_s","imei_s","country_s","model_s")

val df_all = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-0[5-6]-[0-3][0-9]/*/*/et=register").select(MINGLI_ARR.head,MINGLI_ARR.drop(1):_*).distinct
val df_71 = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-01/*/*/et=register")
.select(MINGLI_ARR.head,MINGLI_ARR.drop(1):_*).distinct
.withColumnRenamed("android_id_s","ori_android_id_s")
.withColumnRenamed("imei_s","ori_imei_s")
.withColumnRenamed("country_s","ori_country_s")
.withColumnRenamed("model_s","ori_model_s")

val joinedDF = df_71.join(df_all,Seq("advertising_id_s"),"inner")


val ori_adv_udf = udf((x:String)=>if(x==raw"N/A"||x==raw"\N") raw"\N" else x)
val adv_udf = udf((x:String)=>if(x==raw"N/A"||x==raw"\N") raw"\N" else x)
val clean_MINGLI = joinedDF.withColumn("new_ori_advertising_id_s",ori_adv_udf($"ori_advertising_id_s"))
.withColumn("new_advertising_id_s",ori_adv_udf($"advertising_id_s"))
.drop("advertising_id_s").withColumnRenamed("new_advertising_id_s","advertising_id_s")
.drop("ori_advertising_id_s").withColumnRenamed("new_ori_advertising_id_s","ori_advertising_id_s")
clean_MINGLI.write.mode("overwrite").parquet("mingli/clean_MINGLI_0701")
// val MINGLI_read = sqlContext.read.parquet("mingli/clean_MINGLI")
clean_MINGLI.cache

// val pretty = clean_MINGLI.select("advertising_id_s","ori_android_id_s","android_id_s","ori_imei_s","imei_s","ori_country_s","country_s","ori_model_s","model_s")
// kind1
val e =joinedDF.filter("ori_android_id_s != android_id_s AND ori_imei_s != imei_s AND ori_model_s != model_s AND ori_country_s != country_s")
.select("advertising_id_s").distinct.count
val d =df_71.select("advertising_id_s").distinct.count
val r=e*1.0/d




val df_all = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-0[5-6]-[0-3][0-9]/*/*/et=register").select(MINGLI_ARR.head,MINGLI_ARR.drop(1):_*).distinct
val df_0 = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-01/*/*/et=register")
.select(MINGLI_ARR.head,MINGLI_ARR.drop(1):_*).distinct
//advertising_id_s
val df_71 = df_0.withColumnRenamed("android_id_s","ori_android_id_s")
.withColumnRenamed("imei_s","ori_imei_s")
.withColumnRenamed("country_s","ori_country_s")
.withColumnRenamed("model_s","ori_model_s")
val a = df_71.map{
    r=>
    (r.getAs[String]("advertising_id_s"),Seq(r.getAs[String]("ori_imei_s"),r.getAs[String]("ori_android_id_s"),r.getAs[String]("ori_country_s"),r.getAs[String]("ori_model_s")))
}
val df_71_b = sc.broadcast(a.collectAsMap)
val result = df_all_1.map{
    r=>
    val imei_s=r.getAs[String]("imei_s")
    val android_id_s = r.getAs[String]("android_id_s")
    val advertising_id_s = r.getAs[String]("advertising_id_s")
    val country_s = r.getAs[String]("country_s")
    val model_s = r.getAs[String]("model_s")
    val result = df_71_b.value.getOrElse(advertising_id_s,-1)
    if(result == -1){
        Seq(null)
    }else{
        Seq(advertising_id_s) ++ result.asInstanceOf[Seq[String]] ++ Seq(android_id_s,imei_s,country_s,model_s)
    }
}.filter(x=>x.head!=null)
.map(x=>(x.head,x(1),x(2),x(3),x(4),x(5),x(6),x(7),x(8)))
.toDF("advertising_id_s","ori_imei_s","ori_android_id_s","ori_country_s","ori_model_s","android_id_s","imei_s","country_s","model_s")

val e = result.filter("ori_android_id_s != android_id_s AND ori_imei_s != imei_s AND ori_model_s != model_s AND ori_country_s != country_s")
.select("advertising_id_s").distinct.count //1500267
val d = df_71.select("advertising_id_s").distinct.count
val r_imei= e*1.0/d


// imei_s
val df_71 = df_0.withColumnRenamed("android_id_s","ori_android_id_s")
.withColumnRenamed("advertising_id_s","ori_advertising_id_s")
.withColumnRenamed("country_s","ori_country_s")
.withColumnRenamed("model_s","ori_model_s")

val a = df_71.map{
    r=>
    (r.getAs[String]("imei_s"),Seq(r.getAs[String]("ori_advertising_id_s"),r.getAs[String]("ori_android_id_s"),r.getAs[String]("ori_country_s"),r.getAs[String]("ori_model_s")))
}
val df_71_b = sc.broadcast(a.collectAsMap)
val result = df_all.map{
    r=>
    val imei=r.getAs[String]("imei_s")
    val android_id_s = r.getAs[String]("android_id_s")
    val advertising_id_s = r.getAs[String]("advertising_id_s")
    val country_s = r.getAs[String]("country_s")
    val model_s = r.getAs[String]("model_s")
    val result = df_71_b.value.getOrElse(imei,-1)
    if(result == -1){
        Seq(null)
    }else{
        Seq(imei) ++ result.asInstanceOf[Seq[String]] ++ Seq(android_id_s,advertising_id_s,country_s,model_s)
    }
}.filter(x=>x.head!=null)
.map(x=>(x.head,x(1),x(2),x(3),x(4),x(5),x(6),x(7),x(8)))
.toDF("imei_s","ori_advertising_id_s","ori_android_id_s","ori_country_s","ori_model_s","android_id_s","advertising_id_s","country_s","model_s")

val e_imei = result.filter("ori_android_id_s != android_id_s AND ori_advertising_id_s != advertising_id_s AND ori_model_s != model_s AND ori_country_s != country_s")
.select("imei_s").distinct.count //1500267
val d_imei = df_71.select("imei_s").distinct.count
val r_imei= e*1.0/d

// android_serial_s
val arr = Seq("android_serial_s","advertising_id_s","android_id_s","country_s","model_s")
val df_all = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-0[5-6]-[0-3][0-9]/*/*/et=register").select(arr.head,arr.drop(1):_*).distinct
val df_0 = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-01/*/*/et=register").select(arr.head,arr.drop(1):_*).distinct
val df_71 = df_0.withColumnRenamed("android_id_s","ori_android_id_s")
.withColumnRenamed("advertising_id_s","ori_advertising_id_s")
.withColumnRenamed("country_s","ori_country_s")
.withColumnRenamed("model_s","ori_model_s")


val a = df_71.map{
    r=>
    (r.getAs[String]("android_serial_s"),Seq(r.getAs[String]("ori_advertising_id_s"),r.getAs[String]("ori_android_id_s"),r.getAs[String]("ori_country_s"),r.getAs[String]("ori_model_s")))
}
val df_71_b = sc.broadcast(a.collectAsMap)
val result = df_all.map{
    r=>
    val android_serial_s=r.getAs[String]("android_serial_s")
    val android_id_s = r.getAs[String]("android_id_s")
    val advertising_id_s = r.getAs[String]("advertising_id_s")
    val country_s = r.getAs[String]("country_s")
    val model_s = r.getAs[String]("model_s")
    val result = df_71_b.value.getOrElse(android_serial_s,-1)
    if(result == -1){
        Seq(null)
    }else{
        Seq(android_serial_s) ++ result.asInstanceOf[Seq[String]] ++ Seq(android_id_s,advertising_id_s,country_s,model_s)
    }
}.filter(x=>x.head!=null)
.map(x=>(x.head,x(1),x(2),x(3),x(4),x(5),x(6),x(7),x(8)))
.toDF("android_serial_s","ori_advertising_id_s","ori_android_id_s","ori_country_s","ori_model_s","android_id_s","advertising_id_s","country_s","model_s")
// imeis_s. result count 474,351
val e = result.filter("ori_android_id_s != android_id_s AND ori_advertising_id_s != advertising_id_s AND ori_model_s != model_s AND ori_country_s != country_s")
.select("android_serial_s").distinct.count //1500267
val d = df_71.select("android_serial_s").distinct.count
val r_imei= e*1.0/d


// mac_addr_s
val arr = Seq("mac_addr_s","advertising_id_s","android_id_s","country_s","model_s")
val df_all = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-0[5-6]-[0-3][0-9]/*/*/et=register").select(arr.head,arr.drop(1):_*).distinct
val df_0 = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-01/*/*/et=register").select(arr.head,arr.drop(1):_*).distinct
val df_71 = df_0.withColumnRenamed("android_id_s","ori_android_id_s")
.withColumnRenamed("advertising_id_s","ori_advertising_id_s")
.withColumnRenamed("country_s","ori_country_s")
.withColumnRenamed("model_s","ori_model_s")


val a = df_71.map{
    r=>
    (r.getAs[String]("mac_addr_s"),Seq(r.getAs[String]("ori_advertising_id_s"),r.getAs[String]("ori_android_id_s"),r.getAs[String]("ori_country_s"),r.getAs[String]("ori_model_s")))
}
val df_71_b = sc.broadcast(a.collectAsMap)
val result = df_all.map{
    r=>
    val mac_addr_s=r.getAs[String]("mac_addr_s")
    val android_id_s = r.getAs[String]("android_id_s")
    val advertising_id_s = r.getAs[String]("advertising_id_s")
    val country_s = r.getAs[String]("country_s")
    val model_s = r.getAs[String]("model_s")
    val result = df_71_b.value.getOrElse(mac_addr_s,-1)
    if(result == -1){
        Seq(null)
    }else{
        Seq(mac_addr_s) ++ result.asInstanceOf[Seq[String]] ++ Seq(android_id_s,advertising_id_s,country_s,model_s)
    }
}.filter(x=>x.head!=null)
.map(x=>(x.head,x(1),x(2),x(3),x(4),x(5),x(6),x(7),x(8)))
.toDF("mac_addr_s","ori_advertising_id_s","ori_android_id_s","ori_country_s","ori_model_s","android_id_s","advertising_id_s","country_s","model_s")
// imeis_s. result count 474,351
val e = result.filter("ori_android_id_s != android_id_s AND ori_advertising_id_s != advertising_id_s AND ori_model_s != model_s AND ori_country_s != country_s")
.select("mac_addr_s").distinct.count //1500267
val d = df_71.select("mac_addr_s").distinct.count
val r_imei= e*1.0/d

//-l 08 09 10 18 19 20 |21|
//-s |0522| 0524 0525 0528 0601 |0628|
//-l |0629|

// 查看某个规则（标记为6）的id


// 一、查看 0630 那天，因为两个mis，三个以上Unique的规则的正样本，他们跟历史中某个用户的国家、机型是否一样
// 1.数据准备
val sixIdArrInOrder = Seq("android_id_s","advertising_id_s","android_serial_s","mac_addr_s","cpu_serial_s","imei_s")
val tobeSelected = Seq("country_s","model_s") ++ sixIdArrInOrder
val newBasic = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-06-30/*/*/et=register")
val n = newBasic.select(tobeSelected.head,tobeSelected.drop(1):_*).distinct
.withColumnRenamed("country_s","ori_country_s")
.withColumnRenamed("model_s","model_s")

val hBasic = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-06-[0-2][0-9]/*/*/et=register")
val h = hBasic.select(tobeSelected.head,tobeSelected.drop(1):_*).distinct
// 2.日期0630数据处理
import com.apus.ml.feature.extractor.UserIDLabel
import com.apus.ml.pipeline.LabelStage
import org.apache.spark.sql.{Row, DataFrame, SQLContext}
val date = "2017-06-30"
val savepath = s"/user/zhoutong/CID/analysis/submit/dt=$date"
val joinedDF_fast = sqlContext.read.parquet(savepath+"/joinedDF")
val tableName = "tableName"
joinedDF_fast.registerTempTable(tableName)
val extractorDF_fast = sqlContext.read.parquet(savepath+"/extractorDF")
val cidLabel = new UserIDLabel(
  tableName = tableName,
  labelFieldName = "label"
)
val ls = new LabelStage {
  override def inputs: DataFrame = extractorDF_fast
}.add(cidLabel)
val labeledDF = ls.run() // count 1,905,015
labeledDF.write.mode("overwrite").parquet(s"tmp/labeledDF$date")
val l = sqlContext.read.parquet(s"tmp/labeledDF$date")
ll.rdd.filter(x=>x.getAs[Row]("base_record").getAs[Long]("label")==1L).count
652193 + 470644  = 1152378 +  82722= 1123078
val result = l.rdd.filter(x=>x.getAs[Row]("base_record").getAs[Long]("label")==6L)
val r = sqlContext.createDataFrame(result,result.first.schema)
val fRDD = r.map(r=>r.getAs[Row]("base_record").getAs[Seq[String]]("allSixId"))
val idRDD = fRDD.map{
    arr=>
    arr.map{
        str=>
        str.split("=").last
    }
}.map(arr=>(arr.head,arr(1),arr(2),arr(3),arr(4),arr(5)))
// idRDD[Seq(String)] -> idRDD pair
val idNameSeq = fRDD.first.map{
    str=>
    str.split("=").head
}
val idDF = idRDD.toDF(idNameSeq.head,idNameSeq(1),idNameSeq(2),idNameSeq(3),idNameSeq(4),idNameSeq(5))
// 3. join查看结果
val j_n = idDF.join(n,idNameSeq,"inner")
val j_h = h.join(j_n,Seq("android_id_s","mac_addr_s","imei_s"),"inner")



val params4 = sqlContext.read.parquet("CID/BaseData/Model/ver=1_0630_fourTag/featureAndParamsDF")
val ratio4 = sqlContext.read.parquet("CID/BaseData/Model/ver=1_0630_fourTag/featureRatioDF")
val params3 = sqlContext.read.parquet("CID/BaseData/Model/ver=1_0630_threeTag/featureAndParamsDF")
// val ratio3 = sqlContext.read.parquet("CID/BaseData/Model/ver=1_0630_threeTag/featureRatioDF")





// 解析json 使用google 的 gson

    import java.{util => ju}
    import scala.collection.JavaConverters._
    import com.google.gson.Gson
    import com.google.gson.reflect.TypeToken

    val gson = new Gson
    val jsonString = "{\"test1\":\"value-test1\",\"test2\":\"value-test2\"}"
    val mapType = new TypeToken[ju.HashMap[String, String]] {}.getType
    val map = gson.fromJson[ju.Map[String, String]](jsonString, mapType).asScala


