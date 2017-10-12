#!/bin/bash

year=2017
month=06
for day in {2..30};do
        dy=`echo $day | awk '{printf("%02d",$0)}'`
        dt=${year}-${month}-${dy}
        echo $dt
        # use cross
        /opt/spark1/bin/spark-submit --master yarn-client --driver-memory 4g --num-executors 32 --executor-memory 10g --executor-cores 4 --conf spark.port.maxRetries=100 --conf spark.driver.maxResultSize=2g --jars /data/ml-platform/spray-json_2.10-1.3.3.jar,/data/ml-platform/fastjson-1.2.7.jar,/data/ml-platform/spark-csv_2.11-1.5.0.jar,/data/ml-platform/commons-csv-1.2.jar --class com.apus.dmp.user.driver.UserIDDataGenerator apus-up-0.2-SNAPSHOT.jar -date $dt -savepath /user/zhoutong/CID/analysis/submitCross/dt=$dt -should_fullcross true

done



# /opt/spark1/bin/spark-submit --master yarn-client --driver-memory 4g --num-executors 16 --executor-memory 16g --executor-cores 8 --conf spark.port.maxRetries=100 --conf spark.driver.maxResultSize=2g --jars /data/ml-platform/spray-json_2.10-1.3.3.jar,/data/ml-platform/fastjson-1.2.7.jar,/data/ml-platform/spark-csv_2.11-1.5.0.jar,/data/ml-platform/commons-csv-1.2.jar --class com.apus.dmp.user.driver.UserIDDataGenerator apus-up-0.2-SNAPSHOT.jar -date 2017-07-06 -savepath /user/zhoutong/submit/dt=2017-07-




