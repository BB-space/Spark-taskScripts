# coding=utf-8
from pyspark import SparkConf, SparkContext

conf = SparkConf().setMaster("local").setAppName("My App")
sc = SparkContext(conf = conf)

lines = sc.textFile("URL_data/0614/ID_URL.csv")
total = lines.count()
lines.saveAsTextFile("saveAsTextFile_test")

print "  total is:\n  ",total
for i in range(5):
	print "  剩余 %d"%(4-i)
	if i>=4:
		sc.stop()