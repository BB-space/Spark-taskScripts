//
val df_count = df_score.groupBy("score").count()
val tableName = "tmp"
df_count.registerTempTable(tableName)
val threshold = 0.1
df_score.sqlContext.sql(s"SELECT Percentile('score',$threshold) from $tableName")

val rd = l.map{
    r=>
    if(r.getAs[Row]("base_record").getAs[Long]("label") == 6L){
        r.getAs[Row]("base_record").getAs[Seq[String]]("allSixId") :+ r.getAs[Row]("base_record").getAs[Long]("label").toString
    }else{
        Seq()
    }
}.filter(x=>x!=Seq()).map(x=>(x.head,x(1),x(2),x(3),x(4),x(5)))
val df = rd.toDF


//shell可用
import com.apus.ml.crossvalidation.{IndexedCrossValidation, StratifiedKFold}
import com.apus.ml.feature.extractor.{UserIDFeatureExtractor, UserIDLabel}
import com.apus.ml.feature.selector.FeatureSelector
import com.apus.ml.feature.transformer.{FullCrosser, ContinuousBucketizer}
import com.apus.ml.model.{Metric, GridSearch, ModifiedLogisticRegression}
import com.apus.ml.observation.{DFConversion, WeightedLabeledObservation}
import com.apus.ml.pipeline.{FeatureTransformationStage, FeatureAggregationStage, FeatureExtractionStage, LabelStage}
import com.apus.ml.util.TrainModelUtils
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.Row
import java.io.{BufferedReader, InputStreamReader, PrintWriter, File}

import scala.collection.mutable.ArrayBuffer
// 常量
val checkdate = "2017-05-10"
val targetString = "android_id_s,imei_s,mac_addr_s,android_serial_s,cpu_serial_s,advertising_id_s"
val targetList = targetString.split(",").toList
val allId_columnName = "allSixId"
val join_result_columnName = "tag"
val joinedKey_user_columnName = "id"
val diry_data_tag = "dirtyData"
val should_save_df = true
val parquetPath_basic = "/user/hive/warehouse/apuscommon.db/xal_basic/"

// >>>>>> 定义的工具函数
def dfCleaner(df:DataFrame,row2Name:String,dirtyDataTag:String):DataFrame ={
    // ------ advertising_id_s 脏数据
    val set_adv_id = readResource("/advertising_id_s.txt")
    // ------ android_id_s 脏数据
    val set_android_id = readResource("/android_id_s.txt")
    // ------ android_serial_s 脏数据
    val set_android_serial = readResource("/android_serial_s.txt")
    // cpu_serial_s 脏数据
    val set_cpu = readResource("/cpu_serial_s.txt")
    // ------ imei 脏数据
    val set_imei = readResource("/imei.txt")
    // ------ mac_addr_s 脏数据
    val set_mac = readResource("/mac_addr_s.txt")

    import df.sqlContext.implicits._
    val idRDD = df.map{
      r =>
        val id =r.getAs[String]("allSixId") // historyDF和newDF都有这列
      val idName = id.split("=")(0)
        var idValue = id.split("=")(1)
        idName match {
          case "android_id_s" =>
            if(isDirtyData(set_android_id,idValue)){idValue = dirtyDataTag}
          case "advertising_id_s" =>
            if(isDirtyData(set_adv_id,idValue)){idValue = dirtyDataTag}
          case "imei_s" =>
            if(isDirtyData(set_imei,idValue)){idValue = dirtyDataTag}
          case "mac_addr_s" =>
            if(isDirtyData(set_mac,idValue)){idValue = dirtyDataTag}
          case "android_serial_s" =>
            if(isDirtyData(set_android_serial,idValue)){idValue = dirtyDataTag}
          case "cpu_serial_s" =>
            if(isDirtyData(set_cpu,idValue)){idValue = dirtyDataTag}
        }
        val new_id = idName+"="+idValue
        (new_id,r.getAs[String](row2Name))
    }
    idRDD.toDF("allSixId",row2Name)
  }
def readResource(path:String):Set[String]={
    val input_stream=this.getClass.getResourceAsStream(path)
    val reader=new BufferedReader(new InputStreamReader(input_stream))
    var buffer = ArrayBuffer[String]()
    while(reader.readLine()!=null){buffer += reader.readLine()}
    val arr = buffer.toArray
    arr.toSet
  }
def isDirtyData(set:Set[String],value:String):Boolean={
    if(value=="\\N"){
      true
    }else{
      set.intersect(Set(value)).toList.nonEmpty
    }
  }

// >>>>>> df_basic
val df_basic = sqlContext.read.parquet(parquetPath_basic)

// >>>>>> newDF
val newBasic = df_basic.filter(s"dt='$checkdate'")
val targetDF_new = newBasic.filter("et = 'register'").select(targetList.head, targetList(1), targetList(2), targetList(3), targetList(4), targetList(5)).distinct()
val tempRDD = targetDF_new.map {
      r =>
        val resultList: List[String] = targetList.map {
          str =>
            val tmp = str + "=" + r.getAs[String](str)
            tmp
        }
        resultList
    }
val resultWithIndexRDD = tempRDD.zipWithIndex().map(line=>(line._1, line._2.toString))
val resultRDD_hasIndex = resultWithIndexRDD.map(a => a._1.map(b => (b, a._2)))
val flatRDD_new = resultRDD_hasIndex.flatMap(a => a)

val df_result_new = flatRDD_new.toDF(s"$allId_columnName", s"$joinedKey_user_columnName")
val newDF = dfCleaner(df_result_new,joinedKey_user_columnName,diry_data_tag)




