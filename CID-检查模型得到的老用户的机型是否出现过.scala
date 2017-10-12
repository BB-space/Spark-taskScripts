
val j2 = sqlContext.read.parquet("CID/BaseDataUDFCross/Model/ver=1_0610_white_v2_label_v2/jResultDF_android_id_s_android_serial_s_imei_s_095")
val j2 = sqlContext.read.parquet("CID/BaseDataUDFCross/Model/ver=1_0610_white_v2_label_v2/jResultDF_android_id_s_imei_s_mac_addr_s_095")
val j2 = sqlContext.read.parquet("CID/BaseDataUDFCross/Model/ver=1_0610_white_v2_label_v2/jResultDF_android_id_s_android_serial_s_imei_s")
val j2 = sqlContext.read.parquet("CID/BaseDataUDFCross/Model/ver=1_0610_white_v2_label_v2/jResultDF_android_id_s_imei_s_mac_addr_s")


val d1=j2.select("features_value","model_s") // 2363509
val d2=j2.groupBy("features_value").agg(collect_list("h_model_s") as "h_model_s") // 348760
val d3=d1.join(d2,Seq("features_value"),"left_outer")
d3.count
d3.select("h_model_s","model_s").rdd.filter(r=> !r.getAs[Seq[String]]("h_model_s").contains(r.getAs[String]("model_s"))).count
d3.select("h_model_s","model_s").rdd.filter(x=>x.getAs[Seq[String]]("h_model_s").distinct.length>1)
// 1234755
// id 有             330874 （即这么多个用户）
// features_value 有 330874
// 也就是，存在features_value完全相同的用户
// val d_test=j2.groupBy("features_value").agg(collect_list("score") as "score") 
// d_test.rdd.filter(x=>x.getAs[Seq[String]]("score").distinct.length>1).count  255 相同的features_value居然有不同的打分，待会看看
// 
// val mkStringUDF = udf((x:Seq[String])=>x.mkString("\u0394"))
// val j2_mkString = j2.withColumn("features_tag",mkStringUDF($"features_tag"))
// val d_test2 = j2_mkString.groupBy("features_value").agg(collect_list("features_tag") as "features_tag")
// d_test2.rdd.filter(x=>x.getAs[Seq[String]]("features_tag").distinct.length>1).count  241

// val d_test3=j2.groupBy("features_tag").agg(collect_list("score") as "score") 
// d_test3.rdd.map(x=>(x.getAs[String]("features_tag"),x.getAs[Seq[String]]("score").distinct)).take(23).foreach(println)
// d_test3.rdd.filter(x=>x.getAs[Seq[String]]("score").distinct.length>1).count  23
predict:0701
history:0601-0630
            android_id_s_android_serial_s_imei_s          android_id_s_imei_s_mac_addr_s
阈值 0.95                                                              19/8879
阈值 0.90			    0/4                                            19/8943
阈值 0.70               0/4                                            19/9675                    
阈值 0.50              0/4                                             19/9698 
阈值 0.40 				                                              19/9802
阈值 0.30															 1034/23199
阈值 0.20              0/4                                            1034/24126  
			android_serial_s_imei_s_mac_addr_s010           android_id_s_imei_s_mac_addr_s         
阈值 0.10              0/4                                            1034/24137                                