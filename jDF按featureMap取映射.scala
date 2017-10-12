import org.apache.spark.sql.Row
//jDF 已经改为在生成时候就映射好了
val jDF =sqlContext.read.parquet("CID/BaseDataUDFCross/Model/ver=1_0618_white_v2/jDF")
val featureMapRDD = sc.textFile("CID/BaseDataUDFCross/Model/ver=1_0618_white_v2/featureMap").map(x=>(x.split("\t").last,x.split("\t").head))
val featureMap = featureMapRDD.collectAsMap()


val d = udf((x:Seq[Int])=>x.map(a=>featureMap.get(a.toString).get))
val newJDF = jDF.withColumn("newfeatures",d($"features")).drop("features")
newJDF.select("score","newfeatures","id").orderBy(desc("score")).show(100,false)


// 算一下jdf的百分位 即对unknown标注的打分百分位
    var str = ""
    for (i<-1 to 99){
      val t = i/100.0
      str = str + t.toString+","
    }
    str = str.stripSuffix(",")
    val name = "tableName"
    newJDF.registerTempTable(name)
    val df_percentile = sqlContext.sql(s"SELECT percentile_approx(score,array($str)) from $name")

// 取十个trainRowDF的值生成OBS，观察shell输出结果
import com.apus.ml.observation.WeightedLabeledObservation
import com.apus.ml.model.{GridSearch, LogisticRegressionModelSaveWrite, Metric, ModifiedLogisticRegression}
import org.apache.spark.mllib.linalg.SparseVector
val trainRowDF = sqlContext.read.parquet("CID/BaseDataUDFCross/Model/ver=1_0618_white_v2/trainRowDF")
val rdd = sc.parallelize(trainRowDF.rdd.filter(r=>r.getAs[Double]("label")==5L).take(30))
val sampleDF = sqlContext.createDataFrame(rdd,rdd.first.schema)
val trainRowDF_fast =sampleDF
val trainObservation_tmp = trainRowDF_fast.map(r => WeightedLabeledObservation.fromRow(r)).zipWithIndex()

val trainObservation_fast = trainObservation_tmp.filter(line=>line._1.label == 5L)
trainObservation_fast.repartition(128)
trainObservation_fast.cache()
val modelPath = "CID/BaseDataUDFCross/Model/ver=1_0618_white_v2/lrModel"
val savePath = "CID/BaseDataUDFCross/Model/ver=1_0618_white_v2/"
val lrModel = LogisticRegressionModelSaveWrite.loadModel(sc,modelPath)
val result_rdd = lrModel.predictScoreWithObs(trainObservation_fast.map(_._1)) // (weightObs,score)
val resultDF = result_rdd.map{
        x=>
          (x._2,x._1.features.asInstanceOf[SparseVector].indices,x._1.strAttrs.get("id"),x._1.strAttrs.get("featureArray"),x._1.strAttrs.get("model_s"),x._1.strAttrs.get("country_s"))
      }.toDF("score","features","id","features_value","model_s","country_s")
val featureMapRDD = sc.textFile(savePath+"/featureMap").map(x=>(x.split("\t").last,x.split("\t").head))
val featureMap = featureMapRDD.collectAsMap()

val d = udf((x:Seq[Int])=>x.map(a=>featureMap.get(a.toString).get))
val newJDF = resultDF.withColumn("newfeatures",d($"features")).drop("features")




// trainRowDF映射featureMap
val trainRowDF = sqlContext.read.parquet("CID/BaseDataUDFCross/Model/ver=1_0618_white_v2/trainRowDF")
val featureMapRDD = sc.textFile("CID/BaseDataUDFCross/Model/ver=1_0618_white_v2/featureMap").map(x=>(x.split("\t").last,x.split("\t").head))
val featureMap = featureMapRDD.collectAsMap()
val d = udf{
    (features:Row)=>
    val new_indices = features.getAs[Seq[Int]]("indices").map{
        item=>
        featureMap.get(item.toString).get
    }
    val size = features.getAs[Int]("size")
    val values = features.getAs[Seq[Double]]("values")
    (size,new_indices,values)
}
val new_t=trainRowDF.withColumn("new_features",d($"features")).drop("features")
















