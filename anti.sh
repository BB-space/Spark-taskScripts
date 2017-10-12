#!/bin/bash

year=2017
month=06
for day in {2..30};do
        dy=`echo $day | awk '{printf("%02d",$0)}'`
        dt=${year}${month}${dy}
        echo $dt
        # use cross
        /opt/spark1/bin/spark-submit --master yarn-client --driver-memory 4g --num-executors 16 --executor-memory 16g --executor-cores 4 --conf spark.port.maxRetries=100 --conf spark.driver.maxResultSize=2g --jars /data/ml-platform/lib/fastjson-1.2.7.jar,/data/ml-platform/lib/hadoop-xz-1.3.jar --class com.apus.dmp.user.driver.ZTScript apus-up-0.2-SNAPSHOT.jar -date $dt

done