import org.apache.spark.sql.{Row, DataFrame, SQLContext}
import com.apus.ml.feature.extractor.{UserIDFeatureExtractor, UserIDLabel}
import com.apus.ml.pipeline.{FeatureTransformationStage, FeatureAggregationStage, FeatureExtractionStage, LabelStage}


// val savepath = s"/user/zhoutong/CID/analysis/submit/dt=*"
val savepath = s"/user/zhoutong/CID/Android8/analysis/submit/dt=*"

val joinedDF_fast = sqlContext.read.parquet(savepath+"/joinedDF")

val tableName = "tableName"
joinedDF_fast.registerTempTable(tableName)

val extractorDF_fast = sqlContext.read.parquet(savepath+"/extractorDF")

val cidLabel = new UserIDLabel(
      tableName = tableName,
      labelFieldName = "label"
    )
val ls = new LabelStage {
      override def inputs: DataFrame = extractorDF_fast
    }.add(cidLabel)
val labeledDF = ls.run()

labeledDF.repartition(128).write.mode("overwrite").parquet(s"tmp/labeledDF")
// labeledDF.repartition(128).write.mode("overwrite").parquet(s"tmp/f_a/$date")

val l =sqlContext.read.parquet(s"tmp/labeledDF")

val c0=l.count

val c10=l.rdd.filter{
    row=>
        row.getAs[Row]("base_record").getAs[Long]("label")==10L
}.count

val c11=l.rdd.filter{
    row=>
        row.getAs[Row]("base_record").getAs[Long]("label")==11L
}.count

val c21=l.rdd.filter{
    row=>
        row.getAs[Row]("base_record").getAs[Long]("label")==21L
}.count

val c20=l.rdd.filter{
    row=>
        row.getAs[Row]("base_record").getAs[Long]("label")==20L
}.count

val c111=l.rdd.filter{
    row=>
        row.getAs[Row]("base_record").getAs[Long]("label")==200L
}.count

val c222=l.rdd.filter{
    row=>
        row.getAs[Row]("base_record").getAs[Long]("label")==300L
}.count

val c333=l.rdd.filter{
    row=>
        row.getAs[Row]("base_record").getAs[Long]("label")==400L
}.count







