package com.apus.dmp.user.driver

import com.apus.ml.feature.extractor.UserIDFeatureExtractor
import com.apus.ml.feature.extractor.UserIDLabel
import com.apus.ml.feature.selector.FeatureSelector
import com.apus.ml.observation.{DFConversion, WeightedLabeledObservation}
import com.apus.ml.pipeline.{FeatureAggregationStage, FeatureExtractionStage, LabelStage}
import org.apache.spark.sql.{SQLContext,DataFrame}
import org.apache.spark.sql.functions._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.Row


  // 需要获取的6个ID
  val targetString = "android_id_s,imei_s,mac_addr_s,android_serial_s,cpu_serial_s,advertising_id_s"
  val targetList = targetString.split(",").toList
  // 6个ID列 的列名
  val allId_columnName = "allSixId"
  // 标志 join结果 的列名
  val join_result_columnName = "tag"
  // 标记 新数据中同一用户 的列名
  val joinedKey_user_columnName = "index"
  
  import sqlContext.implicits._
  val checkdate = "2017-05-10"
  val savepath = "/user/zhoutong/shellTest/result_tmp.parquet"
  val parquetPath_basic = "/user/hive/warehouse/apuscommon.db/xal_basic/"
  val df_basic = sqlContext.read.parquet(parquetPath_basic)

  // >>>>>>>>>historyDF
  val historyBasic = df_basic.filter(s"dt<'$checkdate'")
  val targetDF_history = historyBasic.filter("et = 'register'").select(targetList.head, targetList(1), targetList(2), targetList(3), targetList(4), targetList(5)).distinct()
  // df->rdd,拼上每个Field的名字,是为了防止某一记录的mac号和另一记录的imei相同
  val resultRDD = targetDF_history.map {
    r =>
      val resultList: List[String] = targetList.map {
        str =>
          val tmp = str + "=" + r.getAs[String](str)
          tmp
      }
      resultList
  }
  // 展开RDD
  val flatRDD_history = resultRDD.flatMap(a => a)
  val df_result_history = flatRDD_history.toDF(s"$allId_columnName").withColumn(s"$join_result_columnName", lit("1"))
  val historyDF =  df_result_history.distinct()

  val newBasic = df_basic.filter(s"dt='$checkdate'")
  val targetDF_new = newBasic.filter("et = 'register'").select(targetList.head, targetList(1), targetList(2), targetList(3), targetList(4), targetList(5)).distinct()
  // df->rdd,拼上每个Field的名字,是为了防止某一记录的mac号和另一记录的imei相同
  // 另外,还需要拼上index来标记用户
  /**
    * tempRDD.first
    * : List[String] = List(android_id_s=072e25bdd76d1b6d, imei_s=080911692305320, mac_addr_s=1C:9D:E2:84:E9:34, android_serial_s=ff69dbde2735d6e7, cpu_serial_s=0000088900004e4b, advertising_id_s=ddec2711-76fa-47e9-943f-d9e1f05dba4d)
    */
  val tempRDD = targetDF_new.map {
    r =>
      val resultList: List[String] = targetList.map {
        str =>
          val tmp = str + "=" + r.getAs[String](str)
          tmp
      }
      resultList
  }
  val resultWithIndexRDD = tempRDD.zipWithIndex()
  /**
    * resultRDD.first()
    * List[(String, Long)] = List((android_id_s=..,0), (imei_s=..,0), (mac_addr_s=..,0), (android_serial_s=..,0), (cpu_serial_s=..,0), (advertising_id_s=..,0))
    */
  val resultRDD_hasIndex = resultWithIndexRDD.map(a => a._1.map(b => (b, a._2)))
  // 展开RDD
  val flatRDD_new = resultRDD_hasIndex.flatMap(a => a)
  val df_result_new = flatRDD_new.toDF(s"$allId_columnName", s"$joinedKey_user_columnName")
  val newDF = df_result_new

  val joinedDF = newDF.join(historyDF, Seq(s"$allId_columnName"), "left_outer")

// 3.1 构造 baseRecord
  val baseRecord = joinedDF.select("index").distinct()
  // 3.2 注册为临时表
  val tableName = "tableName"
  joinedDF.registerTempTable(tableName)
  // 3.3 extractor类处理
  // 3.4 使用封装好的类抽取Features
  // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>特征提取
  val extractor = new UserIDFeatureExtractor(
    dataSrc = tableName,
    joinField = joinedKey_user_columnName,
    featureName = "cid_feature"
  )
  val fes = new FeatureExtractionStage{
    lazy val inputs = baseRecord
  }.add(extractor)
  val extractorDF = fes.run()
  extractorDF.write.mode("overwrite").parquet(savepath)
  // println(">>> extractorDF")
  // extractorDF.show()
  // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生成标注  check
  val cidLabel = new UserIDLabel(
    tableName = tableName,
    labelFieldName = "label"
  )
  val ls = new LabelStage {
    override def inputs: DataFrame = extractorDF
  }.add(cidLabel)
  val labeledDF = ls.run()

  val valueCounts = labeledDF.map{
      r => r.getAs[Row]("base_record").getAs[Long]("label")
    }.countByValue()


  // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>特征聚合
  val fas = new FeatureAggregationStage {
    lazy val inputs = ls.run()
    def removeFeatureSets = Seq[String]()
  }
  val lsDataSet = fas.run()  //      check

  // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>生成训练数据集 
  // val tempTable = "fas"
  // lsDataSet.registerTempTable(tempTable)
  // val sqlQuerySample = s"SELECT * FROM $tempTable WHERE base_record.label != 5" //男女
  // val trainDataSet = sqlContext.sql(sqlQuerySample)

  // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>训练数据集转为Observation   
  //val scs = new SparseChi2Selector[WeightedLabeledObservation](numFeatures)
  val fs = new FeatureSelector[WeightedLabeledObservation]{
    def baseRecordToObs = DFConversion.mlDatasetToWeightedLabeledObservation
    //override def labeledSelector = Some(scs)
    //override def getFeatureScore = true
  }
  val trainObservation = fs.fitTransform(trainDataSet)   // not done

  // 将转换过的训练数据集转为DF，保存
  val trainDataOutputPath = "/user/zhoutong/trainDataOutputPath/"
  val predictDataOutputPath = "/user/zhoutong/predictDataOutputPath/"
  val headData = trainObservation.first()
  val trainRowRDD = trainObservation.map(_.toRow)
  val DF = sqlContext.createDataFrame(trainRowRDD, headData.schema)
  DF.write.parquet(trainDataOutputPath)
  val predictRowRdd = fs.transform(lsDataSet).map(_.toRow)
  val predictDF = sqlContext.createDataFrame(predictRowRdd, headData.schema)
  predictDF.write.mode("overwrite").parquet(predictDataOutputPath)


val resultRDD = joinedDF.map{
  r =>
    val id = r.getAs[String]("allSixId")
    val tag = r.getAs[String]("tag")
    val new_tag = id+tag
    new_tag
}



    









