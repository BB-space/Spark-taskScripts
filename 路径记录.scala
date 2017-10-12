路径记录


ods_events: 查看未上报的改动的id 如 puuid
"/user/hive/warehouse/ods_events.db/ods_events_xal_hour"
"/user/hive/warehouse/ods_events.db/ods_events_xal_hour/dt=2017-09-28/pn=fastcleaner/hour=06/et=register/"
ods_events: XZ文件解析
/bzdata/rocket_clean_client_activate_request/

:xal打点日志路径
"/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-08-*/pn=launcher/*/et=filterregister"


:xallauncher原始日志
/bzdata/register/20170701-*




df.map(_.getAs[Row]("base_record")).map(_.getAs[Seq[String]]("allSixId")).map(_.filter(_.startsWith("mac_addr_s"))).map(_.mkString("")).filter(_.contains("N/A"))



:PUUID作业的路径
"/bzdata/rocket_clean_client_activate_request/20170825-00"

:反作弊路径
"/bzdata/query/20170816-01"
query日志的wiki
https://redmine.apuscn.com/projects/big-data/wiki/%E5%88%86%E7%B1%BB_Query




 hadoop fs -ls /user/hive/warehouse/ods_events.db/ods_events_info_hour/dt=2017-08-27/pn=acecamera/*/et=register
 hadoop fs -ls /user/hive/warehouse/ods_events.db/ods_events_info_hour/dt=2017-08-*/pn=rocketclean/*/et=register

 // you 
 hadoop fs -ls /user/hive/warehouse/ods_events.db/ods_events_info_hour/dt=2017-08-27/pn=fastcleaner/*/et=register
 hadoop fs -ls /user/hive/warehouse/ods_events.db/ods_events_info_hour/dt=2017-08-*/pn=turboc/*/et=register



/user/hive/warehouse/apuscommon.db/xal_basic/dt=2017-08-01/pn=launcher/hour=*/et=filterregister

 