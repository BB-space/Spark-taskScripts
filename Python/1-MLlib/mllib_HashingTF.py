# coding=utf-8
from pyspark.mllib.feature import HashingTF

# -------- 构建词频向量 
sentense = "hello hello world"
words = senetence.split()
tf = HashingTF(10000) # 创造一个向量，其尺寸为S=10000
tf.transform(words) # 得到SparseVector(10000,{3065:1.0,6861:2.0})

rdd = sc.wholeTextFiles("data").map(lambda (name,text):text.split())
tfVectors = tf.transform(rdd) # 对整个RDD进行转化操作














