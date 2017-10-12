import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
// SparkSQL 的import
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.SQLContext

// 创建SC
val conf = new SparkConf().setMaster("local").setAppName("My App")
val sc = new SparkContext(conf)
// 创建HiveCtx
val hiveCtx = new HiveContext(sc)
import hiveCtx._ // p144页所述，在创建HiveContext实例之后，通过这个导入必要的隐式转换支持
// Spark快速大数据分析 读者   2 - 164939616  1 - 218139230
//