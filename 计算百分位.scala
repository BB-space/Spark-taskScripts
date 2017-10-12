计算百分位：
 注意需要用HiveContext初始化sqlContext


// 先注册df为table
df.registerTempTable(“table”)

// 列名是 score
// 1.计算1-99的百分位
var str = ""
for (i<-1 to 99){
  val t = i/100.0
  str = str + t.toString+","
}
str = str.stripSuffix(",")

val df_all = sqlContext.sql(s"SELECT percentile_approx(count,array($str)) from table")
// 2.计算某一个百分位
val df_99 = sqlContext.sql("SELECT percentile_approx(count,0.9999999) from table")