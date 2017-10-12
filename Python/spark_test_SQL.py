# coding=utf-8
from pyspark import SparkConf, SparkContext
# 这个是书中的例子要用HiveContext
from pyspark.sql import HiveContext, SQLContext, Row
# 这是官网的例子，要用到SturctType和StructField，这都是pyspark.sql.types中的
from pyspark.sql.types import *

conf = SparkConf().setMaster("local").setAppName("My App")
sc = SparkContext(conf = conf)
# 初始化SQL上下文环境
sqlCtx = SQLContext(sc)

# 这里用()，就是tuple
# 创建一个RDD
rdd = sc.parallelize([(1,"row1"),(2,"row2"),(3,"row3")])
# 规定 结构
dataFrame = StructType([StructField("field1",IntegerType(),False),
						StructField("field2",StringType(),False)])
# 使用指定的 结构 来结构化 RDD
df = sqlCtx.createDataFrame(rdd,dataFrame)

print (df.collect())
df.show()
'''
+------+------+
|field1|field2|
+------+------+
|     1|  row1|
|     2|  row2|
|     3|  row3|
+------+------+
'''

# 直接把之前的rdd保存
rdd.saveAsTextFile("save_test_1")
# df变成RDD再保存
rdd4Save = df.rdd
rdd4Save.saveAsTextFile("save_test_rddFromdf")

'''
RDD直接保存，如下：
(1, 'row1')
(2, 'row2')
(3, 'row3')

df变成RDD后在保存，如下：
Row(field1=1, field2=u'row1')
Row(field1=2, field2=u'row2')
Row(field1=3, field2=u'row3')
'''








