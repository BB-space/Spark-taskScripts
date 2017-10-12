// 用0901的数据初始化uniqueID
val date = "2017-08-15"
val initial = sqlContext.read.parquet(s"/user/hive/warehouse/ods_events.db/ods_events_xal_hour/dt=$date/*/*/et=register")

val puuidKey = "message__request_log__hardware_id__puuid"
val aidKey = "message__request_log__base_info__system_info__android_id"
val advKey = "message__request_log__base_info__system_info__advertising_id"
val imeiKey = "message__request_log__hardware_id__imei"
val aserKey = "message__request_log__hardware_id__android_serial"
val cpuKey = "message__request_log__hardware_id__cpu_serial"
val macKey = "message__request_log__hardware_id__mac_addr"
val modelKey = "message__request_log__device_info__model"
val cidKey = "message__client_id"
val installTimeKey = "message__request_log__base_info__host_info__install_time"


val toSelect = Seq("message__request_log__base_info__system_info__android_id",
    "message__request_log__base_info__system_info__advertising_id",
    "message__request_log__hardware_id__android_serial",
    "message__request_log__hardware_id__cpu_serial",
    "message__request_log__hardware_id__mac_addr",
    "message__request_log__hardware_id__imei",
    "message__request_log__hardware_id__puuid",
    "message__request_log__device_info__model",
    "message__client_id",
    "message__request_log__base_info__host_info__install_time")

val a8_0901 = initial.select(toSelect.head,toSelect.drop(1):_*)

// 变成形如 [allSixId: string, tag: string]  的uniqueID
val df = a8_0901.map{
    r=>
    val aid = if(r.getAs[String](aidKey) == "") "android_id_s=" + "/N" else "android_id_s=" + r.getAs[String](aidKey)
    val cpu =  if(r.getAs[String](cpuKey) == "") "cpu_serial_s=" +"/N" else "cpu_serial_s=" +r.getAs[String](cpuKey)
    val mac = if(r.getAs[String](macKey) == "") "mac_addr_s=" + "/N" else "mac_addr_s=" + r.getAs[String](macKey)
    val imei =if(r.getAs[String](imeiKey) == "")  "imei_s=" + "/N" else  "imei_s=" + r.getAs[String](imeiKey)
    val adv = if(r.getAs[String](advKey) == "") "advertising_id_s=" +  "/N" else "advertising_id_s=" + r.getAs[String](advKey)
    val aser = if(r.getAs[String](aserKey) == "") "android_serial_s=" +  "/N" else "android_serial_s=" + r.getAs[String](aserKey)
    val puuid = if(r.getAs[String](puuidKey) == "") "puuid=" +  "/N" else "puuid=" + r.getAs[String](puuidKey)
    Seq(aid,cpu,mac,imei,adv,aser,puuid)
}.flatMap(a=>a).toDF("allSixId").withColumn("tag",lit("1")).dropDuplicates(Seq("allSixId"))


df.repartition(128).write.mode("overwrite").parquet(s"tmp/a8_uniqueID/dt=$date")



// 用0902的数据做newDF
val date = "2017-09-02"
val historyDF = sqlContext.read.parquet(s"tmp/a8_uniqueID/").filter("dt<'{$date}'")

// val basicDF = sqlContext.read.parquet(s"/user/hive/warehouse/ods_events.db/ods_events_xal_hour/dt=$date/*/*/et=register")
val basicDF = sqlContext.read.parquet(s"/user/hive/warehouse/ods_events.db/ods_events_xal_hour/dt=$date").filter("et = 'register'")
    // .select(toSelect.head,toSelect.drop(1):_*)
val resultWithIndexDF = basicDF.dropDuplicates(Seq(aidKey,advKey,imeiKey,aserKey,macKey,cpuKey)).map{
    r=>
    val aid = if(r.getAs[String](aidKey) == "") "android_id_s=" + "/N" else "android_id_s=" + r.getAs[String](aidKey)
    val cpu =  if(r.getAs[String](cpuKey) == "") "cpu_serial_s=" +"/N" else "cpu_serial_s=" +r.getAs[String](cpuKey)
    val mac = if(r.getAs[String](macKey) == "") "mac_addr_s=" + "/N" else "mac_addr_s=" + r.getAs[String](macKey)
    val imei =if(r.getAs[String](imeiKey) == "")  "imei_s=" + "/N" else  "imei_s=" + r.getAs[String](imeiKey)
    val adv = if(r.getAs[String](advKey) == "") "advertising_id_s=" +  "/N" else "advertising_id_s=" + r.getAs[String](advKey)
    val aser = if(r.getAs[String](aserKey) == "") "android_serial_s=" +  "/N" else "android_serial_s=" + r.getAs[String](aserKey)
    val puuid = if(r.getAs[String](puuidKey) == "") "puuid=" +  "/N" else "puuid=" + r.getAs[String](puuidKey)
    val allSixId = Seq(aid,cpu,mac,imei,adv,aser)
    val model_s = r.getAs[String]("message__request_log__device_info__model")
    val clientId = r.getAs[String]("message__client_id")
    val installTime = r.getAs[String]("message__request_log__base_info__host_info__install_time")
    (allSixId,model_s,clientId,installTime)
    }.zipWithIndex.map(x=>(x._1._1,x._1._2,x._1._3,x._1._4,date+"_"+x._2.toString)).toDF("allSixId","model","clientId","installTime","id")
resultWithIndexDF.write.mode("overwrite").parquet("tmp/resultWithIndexDF")

val newDF = resultWithIndexDF.withColumn("allSixId",explode($"allSixId")).drop("model").drop("clientId").drop("installTime")
newDF.write.mode("overwrite").parquet("tmp/newDF")


// join
val joinedDF = newDF.join(historyDF,Seq("allSixId"),"left_outer").orderBy("id")

// update UniqueID












//
root
 |-- api_record: string (nullable = true)
 |-- base_time: string (nullable = true)
 |-- message__client_id: string (nullable = true)
 |-- message__client_ip: string (nullable = true)
 |-- message__log_id: string (nullable = true)
 |-- message__log_level: string (nullable = true)
 |-- message__request_log__aes_iv: string (nullable = true)
 |-- message__request_log__base_info__encrypt_info: string (nullable = true)
 |-- message__request_log__base_info__encrypt_info__content: string (nullable = true)
 |-- message__request_log__base_info__encrypt_info__iv: string (nullable = true)
 |-- message__request_log__base_info__encrypt_info__key: string (nullable = true)
 |-- message__request_log__base_info__encrypt_info__type: string (nullable = true)
 |-- message__request_log__base_info__encrypt_info__vc: string (nullable = true)
 |-- message__request_log__base_info__host_info__channel_id: string (nullable = true)
 |-- message__request_log__base_info__host_info__client_id: string (nullable = true)
 |-- message__request_log__base_info__host_info__install_time: string (nullable = true)
 |-- message__request_log__base_info__host_info__installer_source: string (nullable = true)
 |-- message__request_log__base_info__host_info__is_system: string (nullable = true)
 |-- message__request_log__base_info__host_info__module: string (nullable = true)
 |-- message__request_log__base_info__host_info__package_name: string (nullable = true)
 |-- message__request_log__base_info__host_info__sign_hash: string (nullable = true)
 |-- message__request_log__base_info__host_info__tags: string (nullable = true)
 |-- message__request_log__base_info__host_info__token: string (nullable = true)
 |-- message__request_log__base_info__host_info__update_time: string (nullable = true)
 |-- message__request_log__base_info__host_info__version_code: string (nullable = true)
 |-- message__request_log__base_info__host_info__version_name: string (nullable = true)
 |-- message__request_log__base_info__network_info__connected_wifi: string (nullable = true)
 |-- message__request_log__base_info__network_info__is_proxy: string (nullable = true)
 |-- message__request_log__base_info__network_info__is_vpn: string (nullable = true)
 |-- message__request_log__base_info__network_info__network_interface: string (nullable = true)
 |-- message__request_log__base_info__network_info__network_type: string (nullable = true)
 |-- message__request_log__base_info__system_info__advertising_id: string (nullable = true)
 |-- message__request_log__base_info__system_info__android_id: string (nullable = true)
 |-- message__request_log__base_info__system_info__battery_level: string (nullable = true)
 |-- message__request_log__base_info__system_info__charging: string (nullable = true)
 |-- message__request_log__base_info__system_info__client_time: string (nullable = true)
 |-- message__request_log__base_info__system_info__install_non_market_apps: string (nullable = true)
 |-- message__request_log__base_info__system_info__is_limit_ad_tracking: string (nullable = true)
 |-- message__request_log__base_info__system_info__is_roaming: string (nullable = true)
 |-- message__request_log__base_info__system_info__last_connect_time: string (nullable = true)
 |-- message__request_log__base_info__system_info__last_response_time: string (nullable = true)
 |-- message__request_log__base_info__system_info__last_transfer_rate: string (nullable = true)
 |-- message__request_log__base_info__system_info__local_zone: string (nullable = true)
 |-- message__request_log__base_info__system_info__locale: string (nullable = true)
 |-- message__request_log__base_info__system_info__memory_free: string (nullable = true)
 |-- message__request_log__base_info__system_info__os_version: string (nullable = true)
 |-- message__request_log__base_info__system_info__sdk_level: string (nullable = true)
 |-- message__request_log__base_info__system_info__sim_country: string (nullable = true)
 |-- message__request_log__base_info__system_info__storage_free: string (nullable = true)
 |-- message__request_log__client_time: string (nullable = true)
 |-- message__request_log__device_info__is_pad: string (nullable = true)
 |-- message__request_log__device_info__manufacturer: string (nullable = true)
 |-- message__request_log__device_info__memory_total: string (nullable = true)
 |-- message__request_log__device_info__model: string (nullable = true)
 |-- message__request_log__device_info__pengles_version: string (nullable = true)
 |-- message__request_log__device_info__resolution: string (nullable = true)
 |-- message__request_log__device_info__rom_version: string (nullable = true)
 |-- message__request_log__device_info__storage_total: string (nullable = true)
 |-- message__request_log__encryption_key: string (nullable = true)
 |-- message__request_log__hardware_id__android_serial: string (nullable = true)
 |-- message__request_log__hardware_id__cpu_frequence: string (nullable = true)
 |-- message__request_log__hardware_id__cpu_name: string (nullable = true)
 |-- message__request_log__hardware_id__cpu_num: string (nullable = true)
 |-- message__request_log__hardware_id__cpu_serial: string (nullable = true)
 |-- message__request_log__hardware_id__emmc_id: string (nullable = true)
 |-- message__request_log__hardware_id__imei: string (nullable = true)
 |-- message__request_log__hardware_id__mac_addr: string (nullable = true)
 |-- message__request_log__hardware_id__puuid: string (nullable = true)
 |-- message__request_log__hardware_id__support_camera: string (nullable = true)
 |-- message__request_log__hardware_id__support_nfc: string (nullable = true)
 |-- message__request_log__reason: string (nullable = true)
 |-- message__request_log__referrer: string (nullable = true)
 |-- message__request_log__upgrade_version: string (nullable = true)
 |-- message__server_time: string (nullable = true)
 |-- message_time: string (nullable = true)

//"message__androidId","message__advertisingId","message__androidSerial","message__cpuSerial","message__macAddr","message__imei","message__model","message__gaid","message__apiVersionHeader","message__clientId","message__oldClientId","message__installTime","message__userAgent"


 [message__request_log__base_info__system_info__last_response_time, message__request_log__base_info__system_info__sdk_level, message__request_log__base_info__network_info__is_vpn, message__request_log__base_info__encrypt_info__content, api_record, base_time, message__request_log__base_info__system_info__memory_free, android_id_s, installTime, message__request_log__reason, message__request_log__base_info__host_info__channel_id, message__request_log__hardware_id__cpu_num, message__request_log__base_info__host_info__is_system, message__request_log__hardware_id__support_nfc, android_serial_s, message__request_log__base_info__host_info__installer_source, message__request_log__base_info__encrypt_info__vc, message__request_log__base_info__host_info__version_code, message__request_log__client_time, message__request_log__base_info__encrypt_info, message__request_log__base_info__system_info__battery_level, message__request_log__base_info__host_info__sign_hash, message__request_log__base_info__network_info__network_interface, message__request_log__referrer, message__request_log__base_info__host_info__module, message__request_log__base_info__system_info__sim_country, message__request_log__hardware_id__support_camera, message__request_log__base_info__system_info__last_transfer_rate, message__request_log__base_info__system_info__install_non_market_apps, message__request_log__base_info__encrypt_info__type, message__request_log__base_info__encrypt_info__iv, message__request_log__base_info__system_info__locale, model_s, message__request_log__base_info__system_info__client_time, message__request_log__hardware_id__emmc_id, message__request_log__base_info__system_info__local_zone, message__request_log__aes_iv, message__request_log__base_info__network_info__is_proxy, message__server_time, message__request_log__device_info__rom_version, message__request_log__base_info__host_info__token, message__request_log__base_info__host_info__version_name, message__request_log__device_info__is_pad, message__request_log__upgrade_version, puuid, message__request_log__base_info__host_info__update_time, message__request_log__device_info__pengles_version, message__request_log__base_info__system_info__is_limit_ad_tracking, message__request_log__encryption_key, message__request_log__base_info__system_info__os_version, message__request_log__hardware_id__cpu_frequence, productName, message_time, message__log_level, message__request_log__base_info__encrypt_info__key, message__request_log__device_info__memory_total, message__request_log__device_info__manufacturer, message__request_log__base_info__host_info__client_id, message__request_log__base_info__network_info__connected_wifi, message__request_log__base_info__host_info__tags, message__request_log__hardware_id__cpu_name, message__request_log__device_info__storage_total, message__log_id, message__request_log__base_info__network_info__network_type, message__client_ip, mac_addr_s, message__request_log__base_info__system_info__is_roaming, message__request_log__base_info__system_info__charging, message__request_log__device_info__resolution, message__request_log__base_info__system_info__storage_free, message__request_log__base_info__system_info__last_connect_time, cpu_serial_s, imei_s, advertising_id_s, client_id_s]
