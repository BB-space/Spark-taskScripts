val rawDF = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-0[6-8]-[0-3][0-9]/*/*/et=register").repartition(128)
val targetString = "android_id_s,imei_s,mac_addr_s,android_serial_s,cpu_serial_s,advertising_id_s,model_s"
val targetList = targetString.split(",").toList
val selectedDF = rawDF.select(targetList.head,targetList.drop(1):_*).distinct
selectedDF.write.mode("overwrite").parquet("tmp/selectedDF_hasModel_0601_0815")

val df = sqlContext.read.parquet("tmp/selectedDF_hasModel_0601_0815")
df.cache
val aid_repeatM = "1f2003e1e02dbd36"
val adv_repeatM = "f741b85f-fbab-4eb3-8e44-358e07c3bc51"
val cpu_repeatM = "0000000000000000"
val mac_repeatM = "02:00:00:00:00:00"
val imei_repeatM = "012345678912345"
val aser_repeatM = "0123456789ABCDEF"

df.filter(s"android_id_s = '$aid_repeatM'").groupBy("model_s").count.orderBy(desc("count")).show(false)
// 1f2003e1e02dbd36 : 最多的model_s 是 DW-UBT3G7X 2091个
// a603292c3f3c9b9e : 最多的model_s 是 DW-UBT7DC* 1717
df.filter(s"model_s = 'DW-UBT3G7X'").groupBy("android_id_s").count.orderBy(desc("count")).show(false)
// 这个 DW-UBT3G7X 共有 16915，
//其aid 为 1f2003e1e02dbd36 有 2091
//其aid 为 4ca031e098bf9284 有 1508

df.filter(s"advertising_id_s = '$adv_repeatM'").groupBy("model_s").count.orderBy(desc("count")).show(false)

df.filter(s"imei_s = '$imei_repeatM'").groupBy("model_s").count.orderBy(desc("count")).show(false)

df.filter(s"cpu_serial_s = '$cpu_repeatM'").groupBy("model_s").count.orderBy(desc("count")).show(false)

df.filter(s"mac_addr_s = '$mac_repeatM'").groupBy("model_s").count.orderBy(desc("count")).show(false)

df.filter(s"android_serial_s = '$aser_repeatM'").groupBy("model_s").count.orderBy(desc("count")).show(false)