val df = sc.textFile("MSLR-WEB10K/Fold1/test.txt").map{
    str=>
    val arr = str.split(" ")
    val label = arr.head
    val qid = arr(1).split(":").last
    val featureArr=arr.drop(2)
    
}.toDF















