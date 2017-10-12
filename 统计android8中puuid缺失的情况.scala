
val os = "message__request_log__base_info__system_info__os_version"
val cid = "message__client_id"
val pid = "message__request_log__hardware_id__puuid"
val mac = "message__request_log__hardware_id__mac_addr"
val imei = "message__request_log__hardware_id__imei"
val cpu = "message__request_log__hardware_id__cpu_serial"
val android_serail = "message__request_log__hardware_id__android_serial"
val aid = "message__request_log__base_info__system_info__android_id"
val adv = "message__request_log__base_info__system_info__advertising_id"

// *******
val a8 = sqlContext.read.parquet("tmp/ods_201709_filterregister_android8")
a8.groupBy(os).count.orderBy(desc("count")).show
val a8DF = a8.select(os,cid,pid,mac,imei,cpu,android_serail,aid,adv,"pn").toDF("os","cid","pid","mac","imei","cpu","android_serail","aid","adv","pn")

// ******

val df_09 = sqlContext.read
.options(Map("basePath"->"/user/hive/warehouse/ods_events.db/ods_events_xal_hour/","mergeSchema"->"true"))
.parquet("/user/hive/warehouse/ods_events.db/ods_events_xal_hour/dt=2017-09-*/pn=*/hour=*/et=register")

df_09.filter(s"$pid is null").groupBy(cid).count.count+"/"+df_09.groupBy(cid).count.count
// 201709激活用户中，puuid为空的有:466417
df_09.filter(s"$p is null").groupBy(cid).count.count 

// 201709激活用户中，版本为安卓8的有：
val rdd = df_09.rdd.filter(x=>x.getAs[String](os).startsWith("8."))
val df = sqlContext.createDataFrame(rdd,rdd.first.schema)
df.repartition(256).write.parquet("tmp/ods_201709_filterregister_android8")




