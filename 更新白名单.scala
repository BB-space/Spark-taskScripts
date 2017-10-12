读取整个ods中的打点记录，观察6个id的重复，groupBy，取count最多的一百个

import com.alibaba.fastjson.JSON

val os = "message__request_log__base_info__system_info__os_version"
val cid = "message__client_id"
val pid = "message__request_log__hardware_id__puuid"
val mac = "message__request_log__hardware_id__mac_addr"
val imei = "message__request_log__hardware_id__imei"
val cpu = "message__request_log__hardware_id__cpu_serial"
val android_serail = "message__request_log__hardware_id__android_serial"
val aid = "message__request_log__base_info__system_info__android_id"
val adv = "message__request_log__base_info__system_info__advertising_id"

var target = cpu
val df = sqlContext.read.parquet("/user/hive/warehouse/ods_events.db/ods_events_xal_hour/dt=2017-0[7-9]-*/pn=*/hour=*/et=register")
df.select(cid,pid,mac,imei,cpu,android_serail,aid,adv).dropDuplicates(Seq(aid)).groupBy(android_serail).count.orderBy(desc("count")).show(100,false)


df.select(cid,mac).dropDuplicates(Seq(cid)).withColumn(mac,macUDF($"message__request_log__hardware_id__mac_addr")).groupBy(mac).count.orderBy(desc("count")).show(100,false)
val macUDF = udf{
    (x:String)=>
    var str : String = "initial"
    if(jsonParseArray(x).nonEmpty){
        // Array({"name":"wlan0","up":false,"mac":"00904c112233"}, {"name":"rmnet3","up":false,"mac":"580203040509"}, {"name":"rmnet2","up":false,"mac":"580203040508"}, {"name":"rmnet4","up":false,"mac":"58020304050a"}, {"name":"rmnet0","up":true,"mac":"580203040506"}, {"name":"rmnet1","up":false,"mac":"580203040507"}, {"name":"rmnet_ims","up":false,"mac":"58020304050b"})
        jsonParseArray(x).foreach{
            item=>
            // Object = {"name":"wlan0","up":false,"mac":"00904c112233"}
            if(item.toString.contains("wlan0")){
                str=jsonParseObjectWithKey(item.toString,"mac").toString
            }else{
                str="no wlan0"
            }
        }
    }else{
        str="empty array"
    }
    str
}


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
    var result = "json fail"
    try {
      val getResult = JSON.parseObject(jsonStr).get(key)
      if (getResult != null){
        result = getResult.toString
      }else{
        result = "json fail"
      }
    }catch {
      case _: Exception =>
    }

    result
}







