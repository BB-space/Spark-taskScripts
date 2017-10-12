#!/bin/bash
# 卡在bash数组如何使用？
 year=2017
 month=04
 for day in {1..3};do
        dy=`echo $day | awk '{printf("%02d",$0)}'`
        dt=${year}${month}${dy}
        echo $dt
        # /opt/spark1/bin/spark-submit \
        # --master yarn-client \
        # --driver-memory 4g \
        # --num-executors 32 \
        # --executor-memory 8g \
        # --executor-cores 4 \
        # --conf spark.port.maxRetries=100 \
        # --conf spark.driver.maxResultSize=2g \
        # --jars /home/zhoutong/jars/fastjson-1.2.7.jar,/home/zhoutong/jars/hadoop-xz-1.3.jar \
        # --class com.apus.dmp.user.driver.ZTScript apus-up-0.2-SNAPSHOT.jar \
        # -modeInfoOrAnalysis true \
        # -date $dt \
        # -channel channelId='101606' \
        # -pn com.apusapps.launcher \
        # -savePathInfoDF anti_dog

        hdfs dfs -du -h -s anti_dog/infoDF/dt=$dt | awk '{print $1}'|if [[ $s -gt 10 ]]
        then  
            echo "done"
    	else 
            retry[$day]=$day
    	# /opt/spark1/bin/spark-submit \
        # --master yarn-client \
        # --driver-memory 4g \
        # --num-executors 32 \
        # --executor-memory 8g \
        # --executor-cores 4 \
        # --conf spark.port.maxRetries=100 \
        # --conf spark.driver.maxResultSize=2g \
        # --jars /home/zhoutong/jars/fastjson-1.2.7.jar,/home/zhoutong/jars/hadoop-xz-1.3.jar \
        # --class com.apus.dmp.user.driver.ZTScript apus-up-0.2-SNAPSHOT.jar \
        # -modeInfoOrAnalysis true \
        # -date $dt \
        # -channel channelId='101606' \
        # -pn com.apusapps.launcher \
        # -savePathInfoDF anti_dog
    	fi
 done

for retry_day in ${retry[@]};do
    echo $retry_day
done


