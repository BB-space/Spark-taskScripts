# coding=utf-8
# ------- 缩放向量，控制特征的均值和标准差
from pyspark.mllib.feature import StandardScalar

vectors = [Vectors.dense([-2.0, 5.0, 1.0]), Vectors.dense([2.0, 0.0, 1.0])]
dataset = sc.parallelize(vectors)
scaler = StandardScalar(withMean = True, withStd = True)
model = scaler.fit(dataset)
result = model.transform(dataset)
# 得到 ([-0.7071, 0.7071, 0.0], [0.7071, -0.7071, 0.0])