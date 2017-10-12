# coding=utf-8
from pyspark import SparkConf, SparkContext

conf = SparkConf().setMaster("local").setAppName("My App")
sc = SparkContext(conf = conf)

lines = sc.textFile("/Users/zac/Spark/spark-2.1.1-bin-hadoop2.7/README.md")
total = lines.count()
first_word = lines.first()
pythonLines = lines.filter(lambda l:"Python" in l)
javaLines = lines.filter(lambda j:"Java" in j)
unionRDD = javaLines.union(pythonLines)

print "  total is:\n  ",total
print "  first_word is:\n ",first_word
print "  the whole pythonLines is: \n  pythonLines:",pythonLines.collect()
print "  the whole javaLines is: \n  javaLines",javaLines.collect()
print "  the first line of pythonLines is: \n  ",pythonLines.first()
print "  the whole unionRDD is: \n  unionRDD",unionRDD.collect()
print "  end--  "
for i in range(5):
	print "  剩余 %d"%(4-i)
	if i>=4:
		sc.stop()

# 在终端使用 spark-submit spark_test.py 输出结果如下
'''
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
17/07/05 11:45:07 INFO SparkContext: Running Spark version 2.1.1
17/07/05 11:45:07 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
17/07/05 11:45:08 INFO SecurityManager: Changing view acls to: zac
17/07/05 11:45:08 INFO SecurityManager: Changing modify acls to: zac
17/07/05 11:45:08 INFO SecurityManager: Changing view acls groups to: 
17/07/05 11:45:08 INFO SecurityManager: Changing modify acls groups to: 
17/07/05 11:45:08 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users  with view permissions: Set(zac); groups with view permissions: Set(); users  with modify permissions: Set(zac); groups with modify permissions: Set()
17/07/05 11:45:08 INFO Utils: Successfully started service 'sparkDriver' on port 63103.
17/07/05 11:45:08 INFO SparkEnv: Registering MapOutputTracker
17/07/05 11:45:08 INFO SparkEnv: Registering BlockManagerMaster
17/07/05 11:45:08 INFO BlockManagerMasterEndpoint: Using org.apache.spark.storage.DefaultTopologyMapper for getting topology information
17/07/05 11:45:08 INFO BlockManagerMasterEndpoint: BlockManagerMasterEndpoint up
17/07/05 11:45:08 INFO DiskBlockManager: Created local directory at /private/var/folders/zy/690m3fw13ylfsgp2zdly27km0000gn/T/blockmgr-b81f5290-d3cd-43ab-89fb-7d94edd12dd5
17/07/05 11:45:08 INFO MemoryStore: MemoryStore started with capacity 366.3 MB
17/07/05 11:45:08 INFO SparkEnv: Registering OutputCommitCoordinator
17/07/05 11:45:09 INFO Utils: Successfully started service 'SparkUI' on port 4040.
17/07/05 11:45:09 INFO SparkUI: Bound SparkUI to 0.0.0.0, and started at http://192.168.221.189:4040
17/07/05 11:45:09 INFO SparkContext: Added file file:/Users/zac/Desktop/spark_test.py at file:/Users/zac/Desktop/spark_test.py with timestamp 1499226309450
17/07/05 11:45:09 INFO Utils: Copying /Users/zac/Desktop/spark_test.py to /private/var/folders/zy/690m3fw13ylfsgp2zdly27km0000gn/T/spark-6100a871-c982-4640-8624-4af8dc74680b/userFiles-0f42bd77-8835-4953-8408-3a8344172708/spark_test.py
17/07/05 11:45:09 INFO Executor: Starting executor ID driver on host localhost
17/07/05 11:45:09 INFO Utils: Successfully started service 'org.apache.spark.network.netty.NettyBlockTransferService' on port 63104.
17/07/05 11:45:09 INFO NettyBlockTransferService: Server created on 192.168.221.189:63104
17/07/05 11:45:09 INFO BlockManager: Using org.apache.spark.storage.RandomBlockReplicationPolicy for block replication policy
17/07/05 11:45:09 INFO BlockManagerMaster: Registering BlockManager BlockManagerId(driver, 192.168.221.189, 63104, None)
17/07/05 11:45:09 INFO BlockManagerMasterEndpoint: Registering block manager 192.168.221.189:63104 with 366.3 MB RAM, BlockManagerId(driver, 192.168.221.189, 63104, None)
17/07/05 11:45:09 INFO BlockManagerMaster: Registered BlockManager BlockManagerId(driver, 192.168.221.189, 63104, None)
17/07/05 11:45:09 INFO BlockManager: Initialized BlockManager: BlockManagerId(driver, 192.168.221.189, 63104, None)
17/07/05 11:45:10 INFO MemoryStore: Block broadcast_0 stored as values in memory (estimated size 236.5 KB, free 366.1 MB)
17/07/05 11:45:10 INFO MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 22.9 KB, free 366.0 MB)
17/07/05 11:45:10 INFO BlockManagerInfo: Added broadcast_0_piece0 in memory on 192.168.221.189:63104 (size: 22.9 KB, free: 366.3 MB)
17/07/05 11:45:10 INFO SparkContext: Created broadcast 0 from textFile at NativeMethodAccessorImpl.java:0
17/07/05 11:45:10 INFO FileInputFormat: Total input paths to process : 1
17/07/05 11:45:10 INFO SparkContext: Starting job: count at /Users/zac/Desktop/spark_test.py:8
17/07/05 11:45:10 INFO DAGScheduler: Got job 0 (count at /Users/zac/Desktop/spark_test.py:8) with 1 output partitions
17/07/05 11:45:10 INFO DAGScheduler: Final stage: ResultStage 0 (count at /Users/zac/Desktop/spark_test.py:8)
17/07/05 11:45:10 INFO DAGScheduler: Parents of final stage: List()
17/07/05 11:45:10 INFO DAGScheduler: Missing parents: List()
17/07/05 11:45:10 INFO DAGScheduler: Submitting ResultStage 0 (PythonRDD[2] at count at /Users/zac/Desktop/spark_test.py:8), which has no missing parents
17/07/05 11:45:10 INFO MemoryStore: Block broadcast_1 stored as values in memory (estimated size 6.4 KB, free 366.0 MB)
17/07/05 11:45:10 INFO MemoryStore: Block broadcast_1_piece0 stored as bytes in memory (estimated size 3.8 KB, free 366.0 MB)
17/07/05 11:45:10 INFO BlockManagerInfo: Added broadcast_1_piece0 in memory on 192.168.221.189:63104 (size: 3.8 KB, free: 366.3 MB)
17/07/05 11:45:10 INFO SparkContext: Created broadcast 1 from broadcast at DAGScheduler.scala:996
17/07/05 11:45:10 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 0 (PythonRDD[2] at count at /Users/zac/Desktop/spark_test.py:8)
17/07/05 11:45:10 INFO TaskSchedulerImpl: Adding task set 0.0 with 1 tasks
17/07/05 11:45:11 INFO TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0, localhost, executor driver, partition 0, PROCESS_LOCAL, 6115 bytes)
17/07/05 11:45:11 INFO Executor: Running task 0.0 in stage 0.0 (TID 0)
17/07/05 11:45:11 INFO Executor: Fetching file:/Users/zac/Desktop/spark_test.py with timestamp 1499226309450
17/07/05 11:45:11 INFO Utils: /Users/zac/Desktop/spark_test.py has been previously copied to /private/var/folders/zy/690m3fw13ylfsgp2zdly27km0000gn/T/spark-6100a871-c982-4640-8624-4af8dc74680b/userFiles-0f42bd77-8835-4953-8408-3a8344172708/spark_test.py
17/07/05 11:45:11 INFO HadoopRDD: Input split: file:/Users/zac/Spark/spark-2.1.1-bin-hadoop2.7/README.md:0+3817
17/07/05 11:45:11 INFO deprecation: mapred.tip.id is deprecated. Instead, use mapreduce.task.id
17/07/05 11:45:11 INFO deprecation: mapred.task.id is deprecated. Instead, use mapreduce.task.attempt.id
17/07/05 11:45:11 INFO deprecation: mapred.task.is.map is deprecated. Instead, use mapreduce.task.ismap
17/07/05 11:45:11 INFO deprecation: mapred.task.partition is deprecated. Instead, use mapreduce.task.partition
17/07/05 11:45:11 INFO deprecation: mapred.job.id is deprecated. Instead, use mapreduce.job.id
17/07/05 11:45:11 INFO PythonRunner: Times: total = 266, boot = 251, init = 15, finish = 0
17/07/05 11:45:11 INFO Executor: Finished task 0.0 in stage 0.0 (TID 0). 1652 bytes result sent to driver
17/07/05 11:45:11 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 493 ms on localhost (executor driver) (1/1)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool 
17/07/05 11:45:11 INFO DAGScheduler: ResultStage 0 (count at /Users/zac/Desktop/spark_test.py:8) finished in 0.518 s
17/07/05 11:45:11 INFO DAGScheduler: Job 0 finished: count at /Users/zac/Desktop/spark_test.py:8, took 0.634252 s
17/07/05 11:45:11 INFO SparkContext: Starting job: runJob at PythonRDD.scala:441
17/07/05 11:45:11 INFO DAGScheduler: Got job 1 (runJob at PythonRDD.scala:441) with 1 output partitions
17/07/05 11:45:11 INFO DAGScheduler: Final stage: ResultStage 1 (runJob at PythonRDD.scala:441)
17/07/05 11:45:11 INFO DAGScheduler: Parents of final stage: List()
17/07/05 11:45:11 INFO DAGScheduler: Missing parents: List()
17/07/05 11:45:11 INFO DAGScheduler: Submitting ResultStage 1 (PythonRDD[3] at RDD at PythonRDD.scala:48), which has no missing parents
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_2 stored as values in memory (estimated size 5.4 KB, free 366.0 MB)
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_2_piece0 stored as bytes in memory (estimated size 3.3 KB, free 366.0 MB)
17/07/05 11:45:11 INFO BlockManagerInfo: Added broadcast_2_piece0 in memory on 192.168.221.189:63104 (size: 3.3 KB, free: 366.3 MB)
17/07/05 11:45:11 INFO SparkContext: Created broadcast 2 from broadcast at DAGScheduler.scala:996
17/07/05 11:45:11 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 1 (PythonRDD[3] at RDD at PythonRDD.scala:48)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Adding task set 1.0 with 1 tasks
17/07/05 11:45:11 INFO TaskSetManager: Starting task 0.0 in stage 1.0 (TID 1, localhost, executor driver, partition 0, PROCESS_LOCAL, 5968 bytes)
17/07/05 11:45:11 INFO Executor: Running task 0.0 in stage 1.0 (TID 1)
17/07/05 11:45:11 INFO HadoopRDD: Input split: file:/Users/zac/Spark/spark-2.1.1-bin-hadoop2.7/README.md:0+3817
17/07/05 11:45:11 INFO PythonRunner: Times: total = 3, boot = -81, init = 84, finish = 0
17/07/05 11:45:11 INFO Executor: Finished task 0.0 in stage 1.0 (TID 1). 1671 bytes result sent to driver
17/07/05 11:45:11 INFO TaskSetManager: Finished task 0.0 in stage 1.0 (TID 1) in 25 ms on localhost (executor driver) (1/1)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Removed TaskSet 1.0, whose tasks have all completed, from pool 
17/07/05 11:45:11 INFO DAGScheduler: ResultStage 1 (runJob at PythonRDD.scala:441) finished in 0.025 s
17/07/05 11:45:11 INFO DAGScheduler: Job 1 finished: runJob at PythonRDD.scala:441, took 0.035589 s
  total is:
   104
  first_word is:
  # Apache Spark
  the whole pythonLines is: 
  pythonLines:17/07/05 11:45:11 INFO SparkContext: Starting job: collect at /Users/zac/Desktop/spark_test.py:16
17/07/05 11:45:11 INFO DAGScheduler: Got job 2 (collect at /Users/zac/Desktop/spark_test.py:16) with 1 output partitions
17/07/05 11:45:11 INFO DAGScheduler: Final stage: ResultStage 2 (collect at /Users/zac/Desktop/spark_test.py:16)
17/07/05 11:45:11 INFO DAGScheduler: Parents of final stage: List()
17/07/05 11:45:11 INFO DAGScheduler: Missing parents: List()
17/07/05 11:45:11 INFO DAGScheduler: Submitting ResultStage 2 (PythonRDD[5] at RDD at PythonRDD.scala:48), which has no missing parents
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_3 stored as values in memory (estimated size 5.6 KB, free 366.0 MB)
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_3_piece0 stored as bytes in memory (estimated size 3.4 KB, free 366.0 MB)
17/07/05 11:45:11 INFO BlockManagerInfo: Added broadcast_3_piece0 in memory on 192.168.221.189:63104 (size: 3.4 KB, free: 366.3 MB)
17/07/05 11:45:11 INFO SparkContext: Created broadcast 3 from broadcast at DAGScheduler.scala:996
17/07/05 11:45:11 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 2 (PythonRDD[5] at RDD at PythonRDD.scala:48)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Adding task set 2.0 with 1 tasks
17/07/05 11:45:11 INFO BlockManagerInfo: Removed broadcast_2_piece0 on 192.168.221.189:63104 in memory (size: 3.3 KB, free: 366.3 MB)
17/07/05 11:45:11 INFO TaskSetManager: Starting task 0.0 in stage 2.0 (TID 2, localhost, executor driver, partition 0, PROCESS_LOCAL, 6118 bytes)
17/07/05 11:45:11 INFO Executor: Running task 0.0 in stage 2.0 (TID 2)
17/07/05 11:45:11 INFO BlockManagerInfo: Removed broadcast_1_piece0 on 192.168.221.189:63104 in memory (size: 3.8 KB, free: 366.3 MB)
17/07/05 11:45:11 INFO HadoopRDD: Input split: file:/Users/zac/Spark/spark-2.1.1-bin-hadoop2.7/README.md:0+3817
17/07/05 11:45:11 INFO PythonRunner: Times: total = 8, boot = 3, init = 5, finish = 0
17/07/05 11:45:11 INFO Executor: Finished task 0.0 in stage 2.0 (TID 2). 1860 bytes result sent to driver
17/07/05 11:45:11 INFO TaskSetManager: Finished task 0.0 in stage 2.0 (TID 2) in 27 ms on localhost (executor driver) (1/1)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Removed TaskSet 2.0, whose tasks have all completed, from pool 
17/07/05 11:45:11 INFO DAGScheduler: ResultStage 2 (collect at /Users/zac/Desktop/spark_test.py:16) finished in 0.028 s
17/07/05 11:45:11 INFO DAGScheduler: Job 2 finished: collect at /Users/zac/Desktop/spark_test.py:16, took 0.038643 s
 [u'high-level APIs in Scala, Java, Python, and R, and an optimized engine that', u'## Interactive Python Shell', u'Alternatively, if you prefer Python, you can use the Python shell:']
  the whole javaLines is: 
  javaLines17/07/05 11:45:11 INFO SparkContext: Starting job: collect at /Users/zac/Desktop/spark_test.py:17
17/07/05 11:45:11 INFO DAGScheduler: Got job 3 (collect at /Users/zac/Desktop/spark_test.py:17) with 1 output partitions
17/07/05 11:45:11 INFO DAGScheduler: Final stage: ResultStage 3 (collect at /Users/zac/Desktop/spark_test.py:17)
17/07/05 11:45:11 INFO DAGScheduler: Parents of final stage: List()
17/07/05 11:45:11 INFO DAGScheduler: Missing parents: List()
17/07/05 11:45:11 INFO DAGScheduler: Submitting ResultStage 3 (PythonRDD[4] at RDD at PythonRDD.scala:48), which has no missing parents
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_4 stored as values in memory (estimated size 5.6 KB, free 366.0 MB)
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_4_piece0 stored as bytes in memory (estimated size 3.4 KB, free 366.0 MB)
17/07/05 11:45:11 INFO BlockManagerInfo: Added broadcast_4_piece0 in memory on 192.168.221.189:63104 (size: 3.4 KB, free: 366.3 MB)
17/07/05 11:45:11 INFO SparkContext: Created broadcast 4 from broadcast at DAGScheduler.scala:996
17/07/05 11:45:11 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 3 (PythonRDD[4] at RDD at PythonRDD.scala:48)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Adding task set 3.0 with 1 tasks
17/07/05 11:45:11 INFO TaskSetManager: Starting task 0.0 in stage 3.0 (TID 3, localhost, executor driver, partition 0, PROCESS_LOCAL, 6119 bytes)
17/07/05 11:45:11 INFO Executor: Running task 0.0 in stage 3.0 (TID 3)
17/07/05 11:45:11 INFO HadoopRDD: Input split: file:/Users/zac/Spark/spark-2.1.1-bin-hadoop2.7/README.md:0+3817
17/07/05 11:45:11 INFO PythonRunner: Times: total = 3, boot = -11, init = 13, finish = 1
17/07/05 11:45:11 INFO Executor: Finished task 0.0 in stage 3.0 (TID 3). 1732 bytes result sent to driver
17/07/05 11:45:11 INFO TaskSetManager: Finished task 0.0 in stage 3.0 (TID 3) in 14 ms on localhost (executor driver) (1/1)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Removed TaskSet 3.0, whose tasks have all completed, from pool 
17/07/05 11:45:11 INFO DAGScheduler: ResultStage 3 (collect at /Users/zac/Desktop/spark_test.py:17) finished in 0.015 s
17/07/05 11:45:11 INFO DAGScheduler: Job 3 finished: collect at /Users/zac/Desktop/spark_test.py:17, took 0.026351 s
 [u'high-level APIs in Scala, Java, Python, and R, and an optimized engine that']
  the first line of pythonLines is: 
  17/07/05 11:45:11 INFO SparkContext: Starting job: runJob at PythonRDD.scala:441
17/07/05 11:45:11 INFO DAGScheduler: Got job 4 (runJob at PythonRDD.scala:441) with 1 output partitions
17/07/05 11:45:11 INFO DAGScheduler: Final stage: ResultStage 4 (runJob at PythonRDD.scala:441)
17/07/05 11:45:11 INFO DAGScheduler: Parents of final stage: List()
17/07/05 11:45:11 INFO DAGScheduler: Missing parents: List()
17/07/05 11:45:11 INFO DAGScheduler: Submitting ResultStage 4 (PythonRDD[7] at RDD at PythonRDD.scala:48), which has no missing parents
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_5 stored as values in memory (estimated size 6.0 KB, free 366.0 MB)
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_5_piece0 stored as bytes in memory (estimated size 3.7 KB, free 366.0 MB)
17/07/05 11:45:11 INFO BlockManagerInfo: Added broadcast_5_piece0 in memory on 192.168.221.189:63104 (size: 3.7 KB, free: 366.3 MB)
17/07/05 11:45:11 INFO SparkContext: Created broadcast 5 from broadcast at DAGScheduler.scala:996
17/07/05 11:45:11 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 4 (PythonRDD[7] at RDD at PythonRDD.scala:48)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Adding task set 4.0 with 1 tasks
17/07/05 11:45:11 INFO TaskSetManager: Starting task 0.0 in stage 4.0 (TID 4, localhost, executor driver, partition 0, PROCESS_LOCAL, 5968 bytes)
17/07/05 11:45:11 INFO Executor: Running task 0.0 in stage 4.0 (TID 4)
17/07/05 11:45:11 INFO HadoopRDD: Input split: file:/Users/zac/Spark/spark-2.1.1-bin-hadoop2.7/README.md:0+3817
17/07/05 11:45:11 INFO PythonRunner: Times: total = 3, boot = -33, init = 36, finish = 0
17/07/05 11:45:11 INFO Executor: Finished task 0.0 in stage 4.0 (TID 4). 1732 bytes result sent to driver
17/07/05 11:45:11 INFO TaskSetManager: Finished task 0.0 in stage 4.0 (TID 4) in 15 ms on localhost (executor driver) (1/1)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Removed TaskSet 4.0, whose tasks have all completed, from pool 
17/07/05 11:45:11 INFO DAGScheduler: ResultStage 4 (runJob at PythonRDD.scala:441) finished in 0.021 s
17/07/05 11:45:11 INFO DAGScheduler: Job 4 finished: runJob at PythonRDD.scala:441, took 0.032602 s
 high-level APIs in Scala, Java, Python, and R, and an optimized engine that
  the whole unionRDD is: 
  unionRDD17/07/05 11:45:11 INFO SparkContext: Starting job: collect at /Users/zac/Desktop/spark_test.py:19
17/07/05 11:45:11 INFO DAGScheduler: Got job 5 (collect at /Users/zac/Desktop/spark_test.py:19) with 2 output partitions
17/07/05 11:45:11 INFO DAGScheduler: Final stage: ResultStage 5 (collect at /Users/zac/Desktop/spark_test.py:19)
17/07/05 11:45:11 INFO DAGScheduler: Parents of final stage: List()
17/07/05 11:45:11 INFO DAGScheduler: Missing parents: List()
17/07/05 11:45:11 INFO DAGScheduler: Submitting ResultStage 5 (UnionRDD[6] at union at NativeMethodAccessorImpl.java:0), which has no missing parents
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_6 stored as values in memory (estimated size 7.2 KB, free 366.0 MB)
17/07/05 11:45:11 INFO MemoryStore: Block broadcast_6_piece0 stored as bytes in memory (estimated size 3.8 KB, free 366.0 MB)
17/07/05 11:45:11 INFO BlockManagerInfo: Added broadcast_6_piece0 in memory on 192.168.221.189:63104 (size: 3.8 KB, free: 366.3 MB)
17/07/05 11:45:11 INFO SparkContext: Created broadcast 6 from broadcast at DAGScheduler.scala:996
17/07/05 11:45:11 INFO DAGScheduler: Submitting 2 missing tasks from ResultStage 5 (UnionRDD[6] at union at NativeMethodAccessorImpl.java:0)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Adding task set 5.0 with 2 tasks
17/07/05 11:45:11 INFO TaskSetManager: Starting task 0.0 in stage 5.0 (TID 5, localhost, executor driver, partition 0, PROCESS_LOCAL, 6228 bytes)
17/07/05 11:45:11 INFO Executor: Running task 0.0 in stage 5.0 (TID 5)
17/07/05 11:45:11 INFO HadoopRDD: Input split: file:/Users/zac/Spark/spark-2.1.1-bin-hadoop2.7/README.md:0+3817
17/07/05 11:45:11 INFO PythonRunner: Times: total = 11, boot = 4, init = 6, finish = 1
17/07/05 11:45:11 INFO Executor: Finished task 0.0 in stage 5.0 (TID 5). 1819 bytes result sent to driver
17/07/05 11:45:11 INFO TaskSetManager: Starting task 1.0 in stage 5.0 (TID 6, localhost, executor driver, partition 1, PROCESS_LOCAL, 6228 bytes)
17/07/05 11:45:11 INFO TaskSetManager: Finished task 0.0 in stage 5.0 (TID 5) in 32 ms on localhost (executor driver) (1/2)
17/07/05 11:45:11 INFO Executor: Running task 1.0 in stage 5.0 (TID 6)
17/07/05 11:45:11 INFO HadoopRDD: Input split: file:/Users/zac/Spark/spark-2.1.1-bin-hadoop2.7/README.md:0+3817
17/07/05 11:45:11 INFO PythonRunner: Times: total = 6, boot = 6, init = 0, finish = 0
17/07/05 11:45:11 INFO Executor: Finished task 1.0 in stage 5.0 (TID 6). 1947 bytes result sent to driver
17/07/05 11:45:11 INFO TaskSetManager: Finished task 1.0 in stage 5.0 (TID 6) in 20 ms on localhost (executor driver) (2/2)
17/07/05 11:45:11 INFO TaskSchedulerImpl: Removed TaskSet 5.0, whose tasks have all completed, from pool 
17/07/05 11:45:11 INFO DAGScheduler: ResultStage 5 (collect at /Users/zac/Desktop/spark_test.py:19) finished in 0.054 s
17/07/05 11:45:11 INFO DAGScheduler: Job 5 finished: collect at /Users/zac/Desktop/spark_test.py:19, took 0.077066 s
 [u'high-level APIs in Scala, Java, Python, and R, and an optimized engine that', u'high-level APIs in Scala, Java, Python, and R, and an optimized engine that', u'## Interactive Python Shell', u'Alternatively, if you prefer Python, you can use the Python shell:']
  end--  
  剩余 4
  剩余 3
  剩余 2
  剩余 1
  剩余 0
17/07/05 11:45:11 INFO SparkUI: Stopped Spark web UI at http://192.168.221.189:4040
17/07/05 11:45:11 INFO MapOutputTrackerMasterEndpoint: MapOutputTrackerMasterEndpoint stopped!
17/07/05 11:45:11 INFO MemoryStore: MemoryStore cleared
17/07/05 11:45:11 INFO BlockManager: BlockManager stopped
17/07/05 11:45:11 INFO BlockManagerMaster: BlockManagerMaster stopped
17/07/05 11:45:11 INFO OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
17/07/05 11:45:11 INFO SparkContext: Successfully stopped SparkContext
17/07/05 11:45:12 INFO ShutdownHookManager: Shutdown hook called
17/07/05 11:45:12 INFO ShutdownHookManager: Deleting directory /private/var/folders/zy/690m3fw13ylfsgp2zdly27km0000gn/T/spark-6100a871-c982-4640-8624-4af8dc74680b/pyspark-45d74185-95a2-471c-9cf6-8025224644ad
17/07/05 11:45:12 INFO ShutdownHookManager: Deleting directory /private/var/folders/zy/690m3fw13ylfsgp2zdly27km0000gn/T/spark-6100a871-c982-4640-8624-4af8dc74680b
'''



-----记录-----
>>> rdd.collect()
[[1, 2], [3, 4], [3, 6]]

>>> df.collect()
[Row(f1=1, f2=2), Row(f1=3, f2=4), Row(f1=3, f2=6)]

>>> rdd0 = df.rdd
>>> rdd0.collect()
[Row(f1=1, f2=2), Row(f1=3, f2=4), Row(f1=3, f2=6)] 












