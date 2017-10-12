# coding=utf-8
# -------- 构件好词频向量后，使用IDF来计算逆文档频率，然后它们与词频相乘来计算TF-IDF。

from pyspark.mllib.feature import IDF

# 将若干文本文件读取为TF向量
rdd = sc.wholeTextFiles("data").map(lambda (name, text): text.split())
tf = HashingTF()
# 调用了cache()方法，因为被使用了两次（训练IDF模型 和 用IDF乘以TF向量）
tfVectors = tf.transform(rdd).cache()

# 计算IDF，然后计算TF-IDF向量
idf = IDF()
idfModel = idf.fit(tfVectors)
tfIdfVectors = idfModel.transform(tfVectors)

