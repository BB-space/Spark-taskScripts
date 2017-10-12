# coding=utf-8
from numpy import array
from pyspark.mllib.linalg import Vectors

# 创建稠密向量<1.0, 2.0, 3.0>
# 使用 numpy 创建数组（可以直接传给MLlib）
denseVec1 = array([1.0, 2.0, 3.0]) 
# 使用 mllib 的 Vectors 类来创建
denseVec2 = Vectors.dense([1.0, 2.0, 3.0]) 

# 创建稀疏向量<1.0, 0.0, 2.0, 0.0>
# 该方法只接收向量的维度(4)以及非零位的位置和对应的值
# 这些数据可以用一个dictionary来传递
sparseVec1 = Vectors.sparse(4,{0:1.0, 2:2.0})
# 或使用两个分别代表位置和值得list
sparseVec2 = Vectors.sparse(4, [0,2],[1.0,2.0])