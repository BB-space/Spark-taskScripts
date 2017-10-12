// CID-手动解析部分Android8模型需要的数据
import java.{util => ju}
import scala.collection.JavaConverters._
import com.alibaba.fastjson.JSON
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

sc.hadoopConfiguration.set("io.compression.codecs","io.sensesecure.hadoop.xz.XZCodec")
// val pn = "rocket_clean" 
// val pn = "turboc_cleaner"
// val pn = "powerd_cleaner"  //-----已经完成，是因为碰巧正在导入数据---- 失败 java.io.FileNotFoundException (File does not exist: /bzdata/powerd_cleaner_client_activate_request/20170829-14/20170829-14_19-ip-172-31-11-179.xz.downloading
// val pn = "xpro_camera_lite"

val date = "201708"
// val tmp_str = s"/bzdata/${pn}_client_activate_request/$date*"
val pn = "all"

val save_str = "PUUID"
def jsonParseArray(jsonArrString:String):Seq[Object]={
    var resultArr = Seq[Object]()
    try{
       if (JSON.parseArray(jsonArrString)!=null){
          resultArr = JSON.parseArray(jsonArrString).toArray()
        }else{
          resultArr = Seq[Object]()
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

val rdd_json = sc.textFile(s"/bzdata/rocket_clean_client_activate_request/$date*,/bzdata/turboc_cleaner_client_activate_request/$date*,/bzdata/powerd_cleaner_client_activate_request/$date*,/bzdata/xpro_camera_lite_client_activate_request/$date*").map(x=>x.split("\\t").last).repartition(128)
rdd_json.saveAsTextFile("tmp/rdd_json_all_08")

val rdd_json = sc.textFile("tmp/rdd_json_all_08")
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
        // android_serial
        val android_serial = jsonParseObjectWithKey(hardware_id,"android_serial")
        // mac_addr
        val mac_addr = jsonParseObjectWithKey(hardware_id,"mac_addr")
        // cpu_serial
        val cpu_serial = jsonParseObjectWithKey(hardware_id,"cpu_serial")
        // imei
        val imei = jsonParseObjectWithKey(hardware_id,"imei")

        (puuid,android_id,advertising_id,model,emcc_id,installTime,package_name,version_code,client_id,android_serial,mac_addr,cpu_serial,imei)
}.toDF("puuid","android_id","advertising_id","model","emcc_id","installTime","package_name","version_code","client_id","android_serial","mac_addr","cpu_serial","imei")
basicDF.repartition(128).write.mode("overwrite").parquet("PUUID_basic"+"/"+date+"_"+pn)

val df = sqlContext.read.parquet("PUUID_basic/201708_all")









































