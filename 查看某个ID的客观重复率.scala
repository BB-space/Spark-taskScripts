// 查看某个ID的客观重复率

// val basicDF = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-0[6-7]-[0-3][0-9]/*/*/et=register")

// val allID_0731 = sqlContext.read.parquet("CID/BaseDataUDFCross/uniqueID/dt=2017-07-31")
// val adv = allID_0731.rdd.filter(x=>x.getAs[String]("allSixId").startsWith("advertising_id_s")).map(row=>row.getAs[String]("allSixId").split("=").last)
// val aid = allID_0731.rdd.filter(x=>x.getAs[String]("allSixId").startsWith("android_id_s")).map(row=>row.getAs[String]("allSixId").split("=").last)


// val df0731 = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-31/*/*/et=register")
// val groupedDF=df0731.groupBy("advertising_id_s").agg(collect_list("model_s") as "model_s")
// .map(x=>(x.getAs[String]("advertising_id_s"),x.getAs[Seq[String]]("model_s").head)).toDF("advertising_id_s","model_s")


// groupedDF.join(basicDF,Seq("advertising_id_s"),"inner")

// // 1. 0731里，一天中上报的adv对应超过一种机型的有10/1103001个 ， 这10个就说明是adv自身重复了
// // groupedDF.rdd.filter(x=>x.getAs[Seq[String]]("model_s").distinct.length>1).count 




// 直接拿07一个月的数据
// 判断逻辑：如果同一个idValue对应不止一种机型，那就说明这个idValue客观上重复了
val basicDF = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-[0-3][0-9]/*/*/et=register")
basicDF.cache


// val adv_groupedDF = basicDF.groupBy("advertising_id_s").agg(collect_list("model_s") as "model_s")
// // idValue对应超过一种 model_s
// val adv_dup = adv_groupedDF.rdd.filter(x=>x.getAs[Seq[String]]("model_s").distinct.length>1).count  //1331
// // id的个数
// val adv_total=adv_groupedDF.count // 21573791
// // 1331/21573791  6.169523010582609E-5

// val aid_groupedDF = basicDF.groupBy("android_id_s").agg(collect_list("model_s") as "model_s")
// val aid_dup = aid_groupedDF.rdd.filter(x=>x.getAs[Seq[String]]("model_s").distinct.length>1).count //1762
// val aid_total = aid_groupedDF.count // 23619457
// // 1762/23619457  7.459951344351396E-5


// val imei_groupedDF = basicDF.groupBy("imei_s").agg(collect_list("model_s") as "model_s")
// val imei_dup = imei_groupedDF.rdd.filter(x=>x.getAs[Seq[String]]("model_s").distinct.length>1).count // 15998
// val imei_total = imei_groupedDF.count // 22594076
// // 15998/22594076 7.08061706086144E-4

val cpu_serial_groupedDF = basicDF.groupBy("cpu_serial_s").agg(collect_list("model_s") as "model_s")
cpu_serial_groupedDF.cache
val cpu_serial_dup = cpu_serial_groupedDF.rdd.filter(x=>x.getAs[Seq[String]]("model_s").distinct.length>1).count // 15418
val cpu_total = cpu_serial_groupedDF.count // 5008124
// 0.0030785978941415986 千分之三

val mac_addr_groupedDF = basicDF.groupBy("mac_addr_s").agg(collect_list("model_s") as "model_s")
mac_addr_groupedDF.cache
val mac_addr_dup = mac_addr_groupedDF.rdd.filter(x=>x.getAs[Seq[String]]("model_s").distinct.length>1).count // 117401
val mac_addr_total = mac_addr_groupedDF.count // 15403606
// 0.007621656902935586 千分之七

val android_serial_groupedDF = basicDF.groupBy("android_serial_s").agg(collect_list("model_s") as "model_s")
android_serial_groupedDF.cache
val android_serial_dup = android_serial_groupedDF.rdd.filter(x=>x.getAs[Seq[String]]("model_s").distinct.length>1).count // 18503
val android_serial_total = android_serial_groupedDF.count // 19733455
// 9.376462459310851E-4 万分之四

val df = sc.parallelize(Seq(adv_dup,adv_total,aid_dup,aid_total,imei_dup,imei_total)).toDF("adv_dup","adv_total","aid_dup","aid_total","imei_dup","imei_total")
df.write.mode("overwrite").parquet("/user/zhoutong/tmp/resultOfIDsDuplicatedRatioDF")



/opt/spark1/bin/spark-submit --master yarn-client --driver-memory 4g --num-executors 16 --executor-memory 16g --executor-cores 4 --conf spark.port.maxRetries=100 --conf spark.driver.maxResultSize=2g --class com.apus.dmp.user.driver.ZTScript_IDDuplicatedRatio apus-up-0.2-SNAPSHOT.jar 















