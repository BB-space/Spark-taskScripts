import java.{util => ju}
import scala.collection.JavaConverters._
import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

sc.hadoopConfiguration.set("io.compression.codecs","io.sensesecure.hadoop.xz.XZCodec")
val pn = "rocket_clean" 
// val pn = "turboc_cleaner"
// val pn = "powerd_cleaner"  //-----已经完成，是因为碰巧正在导入数据---- 失败 java.io.FileNotFoundException (File does not exist: /bzdata/powerd_cleaner_client_activate_request/20170829-14/20170829-14_19-ip-172-31-11-179.xz.downloading
// val pn = "xpro_camera_lite"

val date = "201708"
val tmp_str = s"/bzdata/${pn}_client_activate_request/$date*"
val save_str = "PUUID"
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

val rdd_json = sc.textFile(tmp_str).map(x=>x.split("\\t").last).repartition(128)
// 给明理找json
// val rdd_result=rdd_json.filter{
//     x=>
//         val tmp1=jsonParseObjectWithKey(x,"message").split("\\t").last
//         val request_log = jsonParseObjectWithKey(tmp1,"request_log")
//         // puuid
//         val hardware_id = jsonParseObjectWithKey(request_log,"hardware_id")
//         val puuid = jsonParseObjectWithKey(hardware_id,"puuid")
//         val targetPUUID = "1|91f7b373-a03b-46fa-9475-da2f9447a5bf|3787467538,1|fa68a367-f311-4e2c-a965-eface4b9d0fe|3787467538,1|219dd2ee-5992-4ea6-99e0-ae21809ecda1|3787467538,1|0b016c06-2d03-47ec-9ef0-e85e1616ee00|3787467538,1|cf998ea5-afa0-4160-8740-f048258c62e1|3787467538,1|732d0294-da47-4763-abc1-803d5d88b53e|3787467538,1|bba0b84d-207a-4396-a36d-ccc243f7a12d|3787467538,1|2dd3279c-e851-4121-800b-123a488c3f1a|3787467538,1|4d46cb93-9248-4a63-8207-0db5d2f52194|3787467538,1|40e3c4f6-aeeb-4392-ab1b-8a373cb1233a|3787467538,1|6f3f4a6c-6964-4ffd-8f84-46bb5d6351f7|3787467538,1|21193d26-e21e-4284-9efe-d3acb06421db|3787467538,1|34cffc99-7ba6-4bd6-9c16-badcb5f75fa7|3787467538,1|82866eb6-1157-4fe1-8c56-b80e952fdf70|3787467538,1|d07841fe-63ce-4175-a53c-2e69c5f837d2|3787467538,1|f7183404-612f-4623-b3f8-633096883fc7|3787467538,1|76568eca-2b28-44ad-b787-ca17fd665bb3|3787467538,1|4c56edf0-19b7-43ba-8bcc-981b81076c42|3787467538,1|a66344d1-c498-4989-833f-82fb10cb9716|3787467538,1|74dd2bf2-87e4-4059-b9f6-c7c030617186|3787467538,1|bf05fe6c-94cc-436f-b2d5-93ac1c02842b|3787467538,1|3b575fd8-5d62-465c-80f1-f9cd715d7484|3787467538,1|f083e321-6360-4cfe-9644-f5b9e66b754a|3787467538,1|cb45f44f-cc24-45a2-bdef-22d92eb3a2e5|3787467538,1|1bc09c06-77b9-4da9-81bf-dccee6ae4fe7|3787467538,1|0f847346-daa5-45f5-bb9f-e495d7f92461|3787467538,1|877bf56f-c55a-4cbe-8e0b-50864e497470|3787467538,1|43215bde-4a71-488d-9cf7-e059cb5f67f6|3787467538,1|ebcc1106-e8e4-4f65-9611-4d69cce8ba57|3787467538".split(",")
//         val targetPUUID_Str = "|1|0978e211-55de-4a2a-94ec-231babd3c20c|3787467538"
//         // targetPUUID.contains(puuid)
//         puuid == targetPUUID_Str
// }.saveAsTextFile(s"minliFoundJsonRDD_$pn")
// end -- 找json
//
val basicDF = rdd_json.map{
    x=>
        val tmp1=jsonParseObjectWithKey(x,"message").split("\\t").last
        val request_log = jsonParseObjectWithKey(tmp1,"request_log")
        // puuid
        val hardware_id = jsonParseObjectWithKey(request_log,"hardware_id")
        val puuid = jsonParseObjectWithKey(hardware_id,"puuid")
        // android_id, advertising_id
        val base_info = jsonParseObjectWithKey(request_log,"base_info")
        val system_info = jsonParseObjectWithKey(base_info,"system_info")
        val android_id = jsonParseObjectWithKey(system_info,"android_id")
        val advertising_id = jsonParseObjectWithKey(system_info,"advertising_id")
        // installTime
        val host_info = jsonParseObjectWithKey(base_info,"host_info")
        val installTime = jsonParseObjectWithKey(host_info,"install_time")
        // model
        val device_info = jsonParseObjectWithKey(request_log,"device_info")
        val model = jsonParseObjectWithKey(device_info,"model")
        // emcc_id
        val emcc_id = jsonParseObjectWithKey(hardware_id,"emmc_id")
        // version
        val version_code = jsonParseObjectWithKey(host_info,"version_code")
        // client_id
        val client_id = jsonParseObjectWithKey(host_info,"client_id")
        // package_name
        val package_name = jsonParseObjectWithKey(host_info,"package_name")
        (puuid,android_id,advertising_id,model,emcc_id,installTime,package_name,version_code,client_id)
}.toDF("puuid","android_id","advertising_id","model","emcc_id","installTime","package_name","version_code","client_id")
basicDF.repartition(128).write.mode("overwrite").parquet("PUUID_basic"+"/"+date+"_"+pn)

val infoDF= basicDF.filter("puuid != ''")
infoDF.repartition(128).write.mode("overwrite").parquet(save_str+"/"+date+"_"+pn)


// 检验分析
// 23,289,604
   // 237
   // 8668659 (8,668,659)  十万分之二
// groupBy(puuid) count: 5067951  (5,067,951)
// 对应超过两种机型的puuid有 130
val df = sqlContext.read.parquet("PUUID/*")
val rdd = df.groupBy("puuid").agg(collect_list("model") as "model").rdd.filter(x=>x.getAs[Seq[String]]("model").distinct.length>1)
val df_dup_puuid = sqlContext.createDataFrame(rdd,rdd.first.schema) // 一个puuid对应超过一种model的 130个  
val df_dup_detail = df_dup_puuid.drop("model").join(df,Seq("puuid"),"inner") // 这130个的详细情况，这个df的count是477

//
df.groupBy("package_name").count.show
+--------------------+--------+
|        package_name|   count|
+--------------------+--------+
|com.xpro.camera.lite| 7003366|
|  com.powerd.cleaner| 1944279|   上报次数（不是puuid个数）
|  com.turboc.cleaner| 4282392|
|    com.rocket.clean|10059567|
+--------------------+--------+

df.filter("package_name='com.xpro.camera.lite'").groupBy("puuid").count.count  // 2,355,292
df.filter("package_name='com.powerd.cleaner'").groupBy("puuid").count.count    // 1,010,582
df.filter("package_name='com.turboc.cleaner'").groupBy("puuid").count.count    // 2,124,858
df.filter("package_name='com.rocket.clean'").groupBy("puuid").count.count      // 3,380,702

激活用户 8月
acecamera   2918074   (2355292)  80.7%
fastcleaner 1212337   (1010582)  83%
turboc      1863473   (2124858)
rocektclean 4290312   (3380702)  78.7%


df.groupBy("puuid").count.orderBy(desc("count")).show
+-------------------------------------------------+-----+
|puuid                                            |count|
+-------------------------------------------------+-----+
|1|1ccaf8df-ae32-4cee-a3a6-bcd58b59b4aa|1145806568|5786 |
|1|0978e211-55de-4a2a-94ec-231babd3c20c|3787467538|4543 |
|1|d04944f4-503c-4142-bd0e-bac8bfdd5289|4254659843|1767 |
|1|ae8f4b07-a669-4315-b6be-83efe8e40b5c|4254659843|1009 |
|1|855ef884-bdbe-4676-94de-87e520a6b70d|1145806568|963  |
|1|392a0335-c7ad-472a-9544-58445ed0de7b|4254659843|799  |
|1|c5b42b4f-359a-40cb-93e4-75be91e5af4b|3787467538|435  |
|1|d01685b1-35cb-4faf-afb4-65cabfbf5784|4254659843|403  |
|1|4d195587-e7f4-4b36-b927-2579de349779|3558265869|383  |
|1|4a71988b-d39a-40d9-93ab-2094e1da621d|4254659843|340  |
|1|8ed7bb93-f7e0-447b-be1d-bfbf1b71e6a2|3558265869|340  |
+-------------------------------------------------+-----+


df.filter("package_name='com.xpro.camera.lite'").groupBy("puuid").count.orderBy(desc("count")).show
+-------------------------------------------------+-----+
|puuid                                            |count|
+-------------------------------------------------+-----+
|1|0978e211-55de-4a2a-94ec-231babd3c20c|3787467538|4543 |
|1|c5b42b4f-359a-40cb-93e4-75be91e5af4b|3787467538|435  |
|1|9fc8df0d-5734-43e7-81e3-57c8c07c9523|3787467538|254  |
|1|66933a4a-cb6d-487b-b7da-aa198d1f1ed3|3787467538|238  |
|1|e34bfc07-b29f-46c3-8f5f-768ecd60e695|3787467538|227  |
|1|daa5a72f-c659-491f-afe7-01e323a804bc|3787467538|222  |
|1|2016678a-bce5-44e9-9a3b-75af1b698725|3787467538|183  |
|1|2fe3b176-46c5-438f-b104-377c5ffe9bd7|3787467538|170  |
|1|a5c74b35-6163-49ff-8953-e157185744cb|3787467538|153  |
|1|72bc0804-808d-4ab4-9f10-689b8a03e342|3787467538|146  |
|1|2b42341b-ba3c-41ab-920f-53bfb0d56bd2|3787467538|141  |
|1|52737a3b-86d1-47fe-b6ae-6b7947462c34|3787467538|133  |
|1|24f71868-25ca-4e10-aebb-8af235326aa5|3787467538|131  |
|1|0fff3c4b-972a-4d98-96ea-982076f08a40|3787467538|128  |
|1|c2dbb22b-6d47-4af5-8ff8-b71a12832894|3787467538|127  |
|1|a7eea017-ca37-4355-abbe-a59feb650617|3787467538|124  |
|1|51da4c73-5083-4b96-9aed-37b1cf3290cd|3787467538|124  |
|1|2006c7e4-05b3-48c1-b53e-b0a2e52aa0e0|3787467538|121  |
|1|3db90b02-b646-4e78-957b-fb7a2f8963bb|3787467538|120  |
|1|8a99c96f-9e26-40ea-a45d-eb126a415019|3787467538|118  |
+-------------------------------------------------+-----+


2.565139244637527e-05

hdfs dfs -cat /bzdata/rocket_clean_client_activate_request/*/* | xz -d | grep "puuid\":\"bc3fb4fa-1694-4cf0-9417-81dfae974c58|4254659843"
*/*/


df_dup_detail.groupBy("puuid").agg(collect_list("package_name") as "package_name").map(x=>(x.getAs[String]("puuid"),x.getAs[Seq[String]]("package_name").distinct)).toDF("puuid","package_name").orderBy("package_name")



acecamera
fastcleaner















