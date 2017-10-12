// 初始化第一天的uniqueID
val checkDate = "2017-06-01"
val basic = sqlContext.read.parquet(s"/user/hive/warehouse/apuscommon.db/xal_basic/dt=$checkDate/*/*/et=register").distinct()
val targetList = "android_id_s,imei_s,mac_addr_s,android_serial_s,cpu_serial_s,advertising_id_s".split(",").toList
val targetDF_history = basic.select(targetList.head, targetList(1), targetList(2), targetList(3), targetList(4), targetList(5)).distinct()
val resultRDD = targetDF_history.map {
      r =>
        val resultList: List[String] = targetList.map {
          str =>
            val tmp = str + "=" + r.getAs[String](str)
            tmp
        }
        resultList
    }
val flatRDD_history = resultRDD.flatMap(a => a)
val df_result_history = flatRDD_history.toDF("allSixId").withColumn("tag", lit("1"))












