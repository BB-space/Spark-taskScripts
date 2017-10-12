val predictDate = "2017-07-01"
val savePath = "/user/zhoutong/Model/ver=1_0701"

val aggregatedDF_idx = sqlContext.read.parquet(savePath+"/aggregatedDF_idx")
val predictResultDF_idx = sqlContext.read.parquet(savePath+"/predictResultDF_idx")


import com.apus.ml.observation.WeightedLabeledObservation
val trainRowDF = sqlContext.read.parquet("test_dirtyData_noCPU/Model/ver=1_0701/trainRowDF")
val r = trainRowDF.first
val line = WeightedLabeledObservation.fromRow(r).zipWithIndex()
val labelIndex = line._1.label, line._2
// trainRowDF.map(r => WeightedLabeledObservation.fromRow(r)).zipWithIndex()



 42,216
 19,148 15,006/30,567  30,005/30,567
163,994 

val df1 = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-01/*/*/et=register").distinct
val df2 = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-02/*/*/et=register").distinct
val df_0711 = sqlContext.read.parquet("/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-07-11/*/*/et=register").distinct
df_0711.groupBy("client_id_s").count.orderBy(desc("count")).show(false)
df_0711.filter("client_id_s='b76bb631531599d934f36f8afc7cb2e3'").select("client_id_s","cpu_serial_s","imei_s","android_id_s","advertising_id_s","android_serial_s","mac_addr_s","country_s").show
// val j12 = df1.join(df2,Seq("client_id_s"),"inner")

df1.groupBy("client_id_s").count.orderBy(desc("count")).show(false)
df1.count                                //  1,984,161
df1.select("client_id_s").distinct.count //  1,135,391
df2.count                                //  1,972,176
df2.select("client_id_s").distinct.count //  1,129,543
// j12.count                                // 67,572,039

df2                       30,430 val df2 = jResultDF.filter(jResultDF("original_country_s").equalTo(jResultDF("country_s")))
df                        15,248 val df = jResultDF.filter(jResultDF("original_city_s").equalTo(jResultDF("city_s")))
jResultDF                 31,000（predict_notNullDF 和 h1 的join）
h1                    10,913,837（这天以前的历史数据中的 3个ID和城市和国家和CID，去重）
predict_notNullDF        164,052（按打分得到的正样本的三个ID和真实数据join，得到正样本的真实城市和国家和CID）
predict_selectAndTransDF 202,804
predict_originalDF       202,886（从去重过的数据中用6个ID join出正样本的国家和城市和CID）
newBasicSelectedDF     1,192,894（原始数据中，select了6个ID和国家和城市和CID，并去重）
newBasic               1,984,161（指定日期当天，未处理过的原始数据，有重复）
dupThreeIDDF             197,044 distinct=> 197,044（指定的3个ID都重复的正样本，DF存有6个ID）--> 分母
jDF                    1,118,681 
dupDF zongshu            504,815

 |-- client_id: string (nullable = true)
 |-- used_app_top: array (nullable = true)
 |    |-- element: string (containsNull = true)
 |-- used_app_category_top: array (nullable = true)
 |    |-- element: string (containsNull = true)
 |-- install_app_list: array (nullable = true)
 |    |-- element: string (containsNull = true)
 |-- install_app_category_top: array (nullable = true)
 |    |-- element: string (containsNull = true)
 |-- latest_register_time: array (nullable = true)
 |    |-- element: string (containsNull = true)
 |-- first_register_time: long (nullable = true)
 |-- last_ad_click: long (nullable = true)




root
 |-- advertising_id_s: string (nullable = true)
 |-- android_id_s: string (nullable = true)
 |-- android_serial_s: string (nullable = true)
 |-- api_head_s: string (nullable = true)
 |-- api_version_header_l: long (nullable = true)
 |-- appid_s: string (nullable = true)
 |-- battery_level_l: long (nullable = true)
 |-- campaign_s: string (nullable = true)
 |-- channel_id_s: string (nullable = true)
 |-- charging_l: long (nullable = true)
 |-- city_s: string (nullable = true)
 |-- client_id_s: string (nullable = true)
 |-- client_ip_s: string (nullable = true)
 |-- client_time_l: long (nullable = true)
 |-- connected_wifi_s: string (nullable = true)
 |-- contributor_s: string (nullable = true)
 |-- country_s: string (nullable = true)
 |-- cpu_cores_l: long (nullable = true)
 |-- cpu_frequency_s: string (nullable = true)
 |-- cpu_name_s: string (nullable = true)
 |-- cpu_serial_s: string (nullable = true)
 |-- cpu_type_s: string (nullable = true)
 |-- currency_s: string (nullable = true)
 |-- current_mcc_mnc_s: string (nullable = true)
 |-- domain_name_s: string (nullable = true)
 |-- dpi_l: long (nullable = true)
 |-- emmc_id_s: string (nullable = true)
 |-- event_id_s: string (nullable = true)
 |-- facing_camera_s: string (nullable = true)
 |-- fake_ip_s: string (nullable = true)
 |-- flow_size_s: string (nullable = true)
 |-- height_l: long (nullable = true)
 |-- imei_s: string (nullable = true)
 |-- install_non_market_apps_b: boolean (nullable = true)
 |-- install_source_s: string (nullable = true)
 |-- install_time_l: long (nullable = true)
 |-- ip_s: string (nullable = true)
 |-- is_default_b: boolean (nullable = true)
 |-- is_gp_s: string (nullable = true)
 |-- is_limit_ad_tracking_b: boolean (nullable = true)
 |-- is_lock_s: string (nullable = true)
 |-- is_pad_s: string (nullable = true)
 |-- is_proxy_b: boolean (nullable = true)
 |-- is_roaming_b: boolean (nullable = true)
 |-- is_root_b: boolean (nullable = true)
 |-- is_support_camera_s: string (nullable = true)
 |-- is_support_nfc_s: string (nullable = true)
 |-- is_system_s: string (nullable = true)
 |-- is_vpn_b: boolean (nullable = true)
 |-- last_connect_ip_s: string (nullable = true)
 |-- last_connect_time_s: string (nullable = true)
 |-- last_response_time_s: string (nullable = true)
 |-- last_transfer_rate_s: string (nullable = true)
 |-- local_timezone_s: string (nullable = true)
 |-- locale_s: string (nullable = true)
 |-- log_id_s: string (nullable = true)
 |-- log_level_s: string (nullable = true)
 |-- log_protocol_s: string (nullable = true)
 |-- log_protocol_vc_s: string (nullable = true)
 |-- log_server_time_s: string (nullable = true)
 |-- mac_addr_s: string (nullable = true)
 |-- mac_info_s: string (nullable = true)
 |-- manufacturer_s: string (nullable = true)
 |-- mcc_s: string (nullable = true)
 |-- memory_free_l: long (nullable = true)
 |-- memory_total_l: long (nullable = true)
 |-- model_s: string (nullable = true)
 |-- module_s: string (nullable = true)
 |-- network_interface_s: string (nullable = true)
 |-- network_operator_s: string (nullable = true)
 |-- network_type_l: long (nullable = true)
 |-- old_client_id_s: string (nullable = true)
 |-- opengles_version_s: string (nullable = true)
 |-- os_version_s: string (nullable = true)
 |-- package_name_s: string (nullable = true)
 |-- phone_name_s: string (nullable = true)
 |-- pid_s: string (nullable = true)
 |-- product_id_s: string (nullable = true)
 |-- ptr_s: string (nullable = true)
 |-- reason_s: string (nullable = true)
 |-- record__from_id_s: string (nullable = true)
 |-- record__location_info__accuracy_s: string (nullable = true)
 |-- record__location_info__altitude_s: string (nullable = true)
 |-- record__location_info__bssid_s: string (nullable = true)
 |-- record__location_info__cell_id_s: string (nullable = true)
 |-- record__location_info__latitude_s: string (nullable = true)
 |-- record__location_info__location_fix_time_s: string (nullable = true)
 |-- record__location_info__longitude_s: string (nullable = true)
 |-- record__name_s: string (nullable = true)
 |-- record__network_type_s: string (nullable = true)
 |-- record__strategy_s: string (nullable = true)
 |-- record__time_s: string (nullable = true)
 |-- record__value_s: string (nullable = true)
 |-- record__vc_s: string (nullable = true)
 |-- referer_s: string (nullable = true)
 |-- registered_mcc_mnc_s: string (nullable = true)
 |-- rom_version_s: string (nullable = true)
 |-- sdk_level_l: long (nullable = true)
 |-- server_ip_s: string (nullable = true)
 |-- server_time_s: string (nullable = true)
 |-- sign_hash_s: string (nullable = true)
 |-- sim_country_s: string (nullable = true)
 |-- site_id_s: string (nullable = true)
 |-- storage_free_l: long (nullable = true)
 |-- storage_total_l: long (nullable = true)
 |-- tags_s: string (nullable = true)
 |-- time_s: string (nullable = true)
 |-- token_s: string (nullable = true)
 |-- update_time_l: long (nullable = true)
 |-- upgrade_version_s: string (nullable = true)
 |-- user_agent_s: string (nullable = true)
 |-- version_code_l: long (nullable = true)
 |-- version_name_s: string (nullable = true)
 |-- width_s: string (nullable = true)
 |-- record__value___c0_s: string (nullable = true)



+-----+---------------------------+-------------------+
|index|feature                    |params             |
+-----+---------------------------+-------------------+
|15   |android_id_s=unique        |-2.8508631855490485|
|13   |imei_s=unique              |-2.312088488304846 |
|1    |android_serial_s=unique    |-1.9072761721899738|
|4    |imei_s=missing             |-1.4230134182302243|
|6    |advertising_id_s=unique    |-1.3819701626248546|
|11   |mac_addr_s=missing         |-1.1612306013752405|
|14   |mac_addr_s=unique          |-1.1589318181156243|
|2    |advertising_id_s=missing   |-1.1072893818473277|
|16   |android_serial_s=missing   |-1.0869471819159942|
|12   |cpu_serial_s=unique        |-1.0781842752448945|
|0    |android_id_s=missing       |-1.0525912631816623|
|3    |cpu_serial_s=missing       |-0.6707466365929711|
|7    |advertising_id_s=duplicated|1.9294434388048551 |
|5    |mac_addr_s=duplicated      |2.2781759662556884 |
|10   |cpu_serial_s=duplicated    |2.4757134578957007 |
|8    |imei_s=duplicated          |2.4995534631107112 |
|9    |android_id_s=duplicated    |2.8557297106153072 |
|17   |android_serial_s=duplicated|3.119898443542356  |
+-----+---------------------------+-------------------+





