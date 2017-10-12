记录：
1.cid:027f36261a32852bd88564c26fbcfe35 ，渠道是10946，结算是101606
2.0627-0831共结算有 21212个cid，但是在0801-0831的数据中，这些结算过的cid只找到了9851个，有64%的cid没有了
3. 原始数据
	allChannelDF.filter("channelId='101606'").groupBy("cid").count.count = 35407
   结算和原始join后的数据
   val joinedDF = allChannelDF.join(acceptedDF_101606.withColumnRenamed("clientId","cid").drop("channelId"),Seq("cid"),"left_outer")
		joinedDF.filter("channelId='101606'").groupBy("cid").count.count = 35407
		joinedDF.filter("channelId='101606' AND accept='1'").groupBy("cid").count.count = 9850
		可以用的记录：9065/9842(92%)
		预装比例：8484/9065(93%)




testDF.filter("accept is not null AND channelId != accept_channelId").select("cid","channelId","accept_channelId").distinct.rdd.map(x=> x.getAs[String]("cid")+","+x.getAs[String]("channelId")+","+x.getAs[String]("accept_channelId"))saveAsTextFile("false_accept_rdd.txt")

val pn = "com.apusapps.launcher"
val funnel_log = sc.textFile(s"/bzdata/funnel_log/20170[6-8][0-3][0-9]/${pn}_*")
val funnel_df = funnel_log.map{
        x=> (jsonParseObjectWithKey(x,"clientId"),
             jsonParseObjectWithKey(x,"channelId"),
             jsonParseObjectWithKey(x,"accept"),
             pn
             )
    }.toDF("cid","channelId","accept","pn")

val all = sqlContext.read.parquet("tmp/allChannelDF")

val infoDF_accept_101606_launcher = all.join(funnel_df.filter("channelId='101606' AND accept='1'").select("cid","accept"),Seq("cid"),"left_outer")
infoDF_accept_101606_launcher.write.mode("overwrite").parquet("tmp/infoDF_accept_101606_launcher")

// infoDF_accept_101606_launcher.filter("accept is not null").count - infoDF_accept_101606_launcher.filter("channelId = '101606'").count
// infoDF_accept_101606_launcher.filter("accept is not null AND channelId !='101606'").count




// 结算渠道 和 激活渠道 是否相同？
val verifyDF = all.join(funnel_df.filter("accept='1'").select("cid","channelId").withColumnRenamed("channelId","accept_channelId"),Seq("cid"),"left_outer")
verifyDF.filter("accept_channelId is not null AND accept_channelId != channelId").select("cid","channelId","accept_channelId").distinct.show
verifyDF.filter("accept_channelId is not null AND accept_channelId != channelId").groupBy("cid").count.count.toString + "/" + funnel_df.groupBy("cid").count.count.toString


激活时的渠道和结算时的渠道不一致。50多
// 结算渠道 和 激活渠道 是否相同？
val verifyDF = all.join(funnel_df.filter("accept='1'").select("cid","channelId").withColumnRenamed("channelId","accept_channelId"),Seq("cid"),"left_outer")
verifyDF.filter("accept_channelId is not null AND accept_channelId != channelId").select("cid","channelId","accept_channelId").distinct.show
verifyDF.filter("accept_channelId is not null AND accept_channelId != channelId").groupBy("cid").count.count.toString + "/" + funnel_df.groupBy("cid").count.count.toString
+--------------------------------+---------+----------------+
|cid                             |channelId|accept_channelId|
+--------------------------------+---------+----------------+
|bfd18cc8621574d6f8aef1494aab1fa0|100658   |uac_display     |
|72e446f8ae5c3f11361729ab47f0597f|100658   |100946          |
|cd59c50ca8140a64fd109a6e3b991524|101606   |100658          |
|cf4d2a49aae533f8a3e1a61fd1dcdae8|101606   |google-play     |
|4ae9fde69fe4560e481df180b1decbe0|100658   |100946          |
|ccff56ba51edae7ca2d992fd920f0a5c|100658   |wenyufb         |
|027f36261a32852bd88564c26fbcfe35|100946   |101606          |
|675131bedf361ba79bd6b857094100d8|100946   |100658          |
|2692f12dee2443b5c04b1088b831f533|101513   |100176          |
|caf8747106b2028260bbab7dd4c64b10|101606   |100946          |
|1a8e283c24353410080ce6259c964e4b|100946   |100412          |
|7709b1e0f54d1003b8d8fa82e25dad0b|100658   |100176          |
|dde626e25c9362943664b48530484f11|101606   |google-play     |
|b5355279270ef37bce08b615db4b1a3b|101606   |100946          |
|a32b9acbab238b6d36a63d8f8fb5bbbf|100946   |uac_display     |
|12d35a1addc782d8e50e8e70734d6e6d|100946   |100658          |
|728e0acbe3741affead3b288a4cfb57b|100658   |100946          |
|3af7675ca55016a8c9726236f9d24e81|100946   |google-play     |
|06951403612b0345daa75b9ab087c7b0|100658   |100946          |
|3de30de3cc715168af95329cee54179e|101606   |100412          |
|bb0733b00141834a900f5abf1e47580d|100946   |100658          |
|66f9ba5897dd16e85aff33f7aef93847|101606   |google-play     |
|6c9e7fd77b414541943dffd41791ea8f|100658   |100946          |
|4b32751b6dd246b308b442bb35657f28|100658   |wenyufb         |
|2bb86757c0df6020894158c67ff802e8|100946   |wenyufb         |
|5ef5fdfd8ed43fea26721604b141ab3a|100658   |100946          |
|b27594f432d632d2148a49a18505d64f|100658   |100946          |
|fa6a9db19a00e4afe0decf7569fc2901|100658   |wenyufb         |
|654f4bcbc40ca287d5df602ab4b400f4|100658   |fzwfb           |
|d9a6f075aacfafe635ec138e104dddbd|101513   |100412          |
|ffdb8dae3a08c532fa871a607bceb901|100658   |100946          |
|1d9b9c96e0e8e8c1a3f4315143b428da|100658   |100946          |
|5bec8ff9cc43c7a62d04a61864bb039b|101606   |google-play     |
|6b0c9fe5832a62b567c81d86927ed056|100658   |100946          |
|c241b1a83f14fc321dc2280f417e9150|101606   |wenyufb         |
|10d1b1e5689e5a5e9337e6bb18432969|101606   |google-play     |
|461fd1b1baa5329d464dc1e4578edf52|101513   |wenyufb         |
|6e2af29d32e36ebf80b4019282ade42e|100658   |101376          |
|213babd5bd664327e8d4e0a7f9208952|100658   |100946          |
|99ae95ba3752226f8e53b2709b6d2b3a|101513   |100946          |
|5fafd787db8542e9741a15e255710efb|101606   |google-play     |
|d46d97928c46fd67400048f03b29fab1|100658   |101500          |
|1ad675de5957990b03b5285baecfb205|100946   |100658          |
|7022a20f964f9c5a7c8c4211c7cae146|101606   |google-play     |
|dee0d151f63362d346179a8ef4f31d16|100658   |wenyufb         |
|4a2ca381cdd637504c269900d21b4b2b|100658   |100946          |
|6ff0a5f12e1c372a5882492afffcd110|100658   |100946          |
|cb70a89544ef6d319fcf47d044311334|101606   |101376          |
|ec76ad2d5c77be8213f489f72a130912|100658   |wenyufb         |
|716eb7e5244045cb9d25cbacf7cdeb24|100658   |google-play     |
|08c30ec4a172088c9d6698b119ad68ed|101606   |google-play     |
|915a3a01cefd2a69a17e47f0c326a978|101606   |google-play     |
|5606248aaab014a8cbe5e7d4522a4f7c|100946   |uac_search      |
|0d0e1fe9bfd69a52a7fc4e48b7d31952|101606   |google-play     |
|645425aff1a503f9a0f61ad46600d126|101606   |100946          |
|bb408bab7cb96915ebc3f5c053861b80|101606   |google-play     |
|9dec704cb9ae7030d9834ee052a4b8cc|100946   |100658          |
+--------------------------------+---------+----------------+



同一个cid对应两个accept（com.apusapps.launcher) 1W
funnel_df.groupBy("cid").agg(collect_list("accept") as "acceptArr").rdd.filter(_.getAs[Seq[String]]("acceptArr").length>1).filter(x=>x.getAs[Seq[String]]("acceptArr").head!=x.getAs[Seq[String]]("acceptArr").last).map(_.getAs[String]("cid")).take(200).foreach(println)
 12219/2826046 0.004
 10837da1d2dacdf01aaefcefc57cf087
// {"clientId":"10837da1d2dacdf01aaefcefc57cf087","channelId":"100946","imei":"869554029523848","cpuSerial":"","androidSerial":"b6bff2d31f5c3","androidId":"556bb98a28ca5adc","macAddr":"02:00:00:00:00:00","time":"1498702148","versionCode":235,"versionName":"3.1.7.4001","os":"6.0.1","model":"Redmi 4A","ipAddress":"180.214.232.84","post_pacific_time":"0","country":"ID","accept":0,"is_white":1,"channel_zj":0}
// {"clientId":"10837da1d2dacdf01aaefcefc57cf087","channelId":"100946","imei":"869554029523848","cpuSerial":"","androidSerial":"b6bff2d31f5c3","androidId":"556bb98a28ca5adc","macAddr":"02:00:00:00:00:00","time":"1498702148","versionCode":235,"versionName":"3.1.7.4001","os":"6.0.1","model":"Redmi 4A","ipAddress":"180.214.232.84","post_pacific_time":"0","country":"ID","accept":1,"is_white":1}
1183ed1c53ca151b152c540745de799c
134d4fb12cec60810129a119102ba979
13ee5e468f87a4627f1f68af6c9dcce8
1acbc6bb216620bdde1a0d3e164cb19d
1dc303c2dd85cc3fa24fb51201096a74
22392df4f5095e9497da7274bc60c428
23dc0b87d98478172cbbdafc222d6a41
251ade9394e55fe3872eb84a05383a0a
2b045b2eb986d9d7b08cfb96c14073c8
2c3b738841f68faba5aa4c9a1768da17
2da30bb480c6be70aa27cca7de96088f
30fc4d4f3e9c4b4aa67d875422396795
3a1504208a9daf4893e85d873b9e91dc
3c09f40b82a4d2b0de9bbff4e74f4341
3cc42bb143bad8b9b6df56657ccf80f6
3ff7efe5ad2fda631159c6861dfde274
445c081364a656d56d1c04e1184fbefc
58de35c2dec440e1ee265f6772aa6e87
62cdddeaf6eb2abaa15e503d2196cbd7
62f43e36ed5d9029390fd7c805b06860
64fca8b311c097a3e756fb9bfb3206f3
692b8b518d8479442e0f7723c8048bb0
6d227a918e161e173bb075c8e8343ad4
6d37f8994a7578fbdad40fd611109494
7583240fcbbe8b7ee51fea26ddd4b2fd
76f7a94607a7acb8ea6df2d91edd96be
7be5f33a60dff4e06e19aaee10c8070a
7da36c96f5e5b07d7e4b45c8ee570b30
82a54a678ac9352e42e8630c338f6841
8b8f7ed70049a346376c414b6c89597e
8bbb1173f120892bba7d2acfb2b30252
8c22bb40d1643548907135663e14cbe4
8cf29c121fc5690aee5f3d1d0d31370f
91a644741c15a20990c4afc893f109a5
93a48cc6aea39d11166a04d0c198ac69
9441189d98fca32c0c85dbf5d9e4de1d
a06d71fc60efcc7a4fed0c516157196f
a14c9630873550a93a4ee2631fb6f74b
a4fc556f9a7923cadc9747e97f37434c
b46f0d814dba2935dd0d03f3c31b6723
b4fd157e6bab725ce88faa2d101d69ec
b779a8985c3f96bc026de7bdd394175c
b91483fb2edd6d8048cbee0413d1cb98
bafcf82de2baf76f4b51a8ec820a0f09
bd1d426f78a6e22e8d2e1f6873440850
c20772c8aab13b5276c63b9d356d31d7
c8c0a7926abe4dcecaaf2a3f17084b95
c985b445cb05d02980c422296c313bb1
d572b0fb9006e6fc1d1d33575f51eb58
d6eca65fc1f6d85b66493ce4c93ed889
d7e4697476f45a71f236b67465f8826d
d813f14135a9e05d04fac098eac4603e
d8b7c16dcecff48bdc16268483c05b61
dae87867d0ba28add8c6444facd3fe32
e089e18fed4b862803ff615ff022055f
e11ef21df718b2d69a031ee479a12d0c
ec2dd751bcc80aff9a89f22e248e2ee3
ed67574755509e64c2622db4953482db
f4eb47177ddf73a964a9e6caf7026bd7
f57b011d55b16422763f3cac8132c8b4
010c2753596e6b6f13e07fecc9588402
04ac18bace0b19e689e0646d3dc3b5b4
094a468808961ea12917f73ba814da60
10bb69c3329af10e3f6e7c0e415db449
134c966401a1ae0a06c6c24b9146a6ee
1ad70c98875a91841eed27052e744dc9
1b4cdfd19d8c8da64edbe6018be39c64
1fd811dc38bf7405f1c64ef2fe0865e0
20015371a8ebb567b5acec0674b718f4
22c5bc8eb86695cd60e4652e006bfe97
291062402f2b78a01efc4ace91193bd2
3c95bf5ef47fb3db6025948e9097fd0c
41507cd1d171669dbc84f3eacc5eb26d
487c058d1e5a914f9e1f51396940c4bc
493dcba056df8a4dbe2873658a41e7a4
4e733358de75ef132dd6b05bbe23e2c8
527e39a69145b87061b9b03724b42223
52da2a6d4a8a338e0ce5d4dea68d6152
5ef8d96c6543385d778c422e03936d46
5efcd8172ffeae444fd268c5bddeac33
6035941e87521caf718820c61d4ccf2c
623b3697d28633df0613ad7cc71435a6
6863f242c3d089ca846bb968288b80ac
69085e4ebe48a4773294ba2d11c645bd
69fcfc0cae63f3b9fce2ed9e5e00d771
6b3e60773edaca0577374dc271bd97ff
6e8592009192260fe5c9578d3e3bc70f
70c5e3c6685564e494551e8d974ad376
713eb6cec95bda2c1d18ff03ba786e00
74b6e177f80a702cbcef665aa947fb99
7a87bfd3327f9efc6d9a0d6e10de178e
7ee8983f039768ed4aedd01c224309ff
8039ba06de04bef6485ad2080000cd6c
80f67f11309b6ecdee2a2b2d1dac03ef
843d562af76d32b7db234706f58ebdd5
90ef57b6a0cde8013397acd6247d40dd
939b31d31d0c9f0a4c55cc09bc2c80f7
9b6753fe85ac38b4079741a2c88077c5
9e0a4fa60f45c2ea8d04617e1b0f2e86
aabf65af94852eb11c2816ca3f828184
b4ffd2d47690d83973840a4259fd4ad5
bf8534f9cc758a4ababaddf2aa175bc4
c0d84ba93fd41c597372019b542c1fbc
c1b305e4ef4740672b08516641fad7a5
c34a8f18a567be9fd9d2757635c8f612
c4ebbf087fc6d3c4d8bee8293623e245
ca9e824295e87d1d0341af46e427e132
d3ea31d1b2edcb293b497ff1affe9b81
d91bec9fb4e35614b761fcd409bd5e3e
dd81e9dd20085e4f0198c6e703aee56d
dfe20498845eeeb3595aed15cf493929
ec5149af54e9231dae948271f31a92fa
ef7268f1e914b79c45828540f29ac13e
f07281691ac9aae5f90a4623a0bd3935
f46889302be8b822c256047b0bae8ea7
f502a4c59bf5c4349266c537dfa3d4a5
f6285f221eb8638f643dcf82374fe9c8
fbfcfc6575c484c437c6bf957c6a15b6
014b194e181c66cf8f36df3b95c89d7e
019c103f8d4f818c4487683dc564c26f
024910ed8ab88e373605d5c4ec32c31e
02ae9abde8bfbece26498753ba6d0ff1
0564f2bfb0890e42f24ff92d973abb8d
187b4aaab724f21ca87afdc4f8887908
1ae2161b402ea53f80fc5fa898bad2fd
1b1ae599d8e77f0ede24cba40d92872f
1e7ae6e07e56194f392a73263347ab7c
2aac5135c845c4f9ff7e2fc45b774c77
2eb3bd84e2adb3a8c84119885782677f
2ff89c7e91b8159960ad2d34430e165d
332d43fc3783cdcf66a570a53c12b175
3fc75206f9ac26fe53265d3f6b009b0e
46ac7c3882bea6f7ec7b2f186508e95f
48fba980120a08177ace8a9832f4e2df
4cc3f4614e2eecc81fcedc03f8eb27ea
4d60e566a7fa4fc6ea8642ddaf359a92
52e12949f8ad8779a98e9ac3d558977e
5346c8c2199e77961ec0a29631c94ffd
54435331507c18faa854a18dd8819b16
5a9781319149ea31a1ba3d2657097129
5bb4f9070081262de944b90526dbb404
5f3b65a92769a59dae12427e6857d71c
61cb3e63af65855da773c21907b045f6
64bfc167b72dcbc068e897e460312e10
666527c45cd4ede2f7b2b23327c08e12
75f00a47cbaa3b0999cdee66c4dba3fa
7a7726152c2960d4a97cdfa53d52d2e3
88a9c37daa4f11d384f116457f83cf66
8b736b19ad213981b1206c262810978d
8ff5d0723132a1c5d66d06d4cec77c7d
956b4a89efe79100f5092affa2e3904f
990f32eae52dbce7a8e486b63e822176
9f90a3a63dc0ddba267073411c463c20
9fa66af2f3fd6b1ea8d0d66d1d4faf76
9fbdd380579e7f0358078af39e37d4ac
a0be1e6a4742caa4b1ec31a9e66eaabd
b0f428127bcf9dc1f6b338775c398356
b55726e13b2aa055220b9f2462a6ec17
b6095c9699365a130de6f1420e8e50a5
ba7cfaf6220be744579c08bb8f452df7
bd71443b632212c3bba7bb075a42184e
bf7f2db4f58c449c8e760913ee3bcf51
c31df3a9b8612c3d0bbb7b417b886ce0
cd9891eb59b4dd2b19b89bc2e8066f7e
cf683c0394395c614d6f2f01c9484a8b
d22e3ea5f849849709b536c83d75a589
d2965e6892363bf367a285e53ebac248
d4f75af827b6978228db0b73a790a332
ec38f47eeea5c9d578674c7245fc6387
ee1ccf8a910935d0e5b7b0adb1c1870a
f0c3dac6372d9f02b4417b573ac233a9
f2d41327bdc979e510b966e4d215d1a0
fb6b2c732ce2a42c6953d12063494b16
0785d40e7cbf20a5fd7a670635303a7a
0bc82b29060a6190721e6999522418a3
15dbd8c03a0899b1f84a9297a5082e84
181d1a1421b3d2408c3287548e0f791f
18521bd0838a8ead0ad0ee744044931e
1f3fc66b74f75417a759dc39b8c3f1d3
25c2b30e7c0a3e3e3b262adc951931eb
2bd74c812e661b8c6649323d22a648e6
316f3584f0f841d097ee2fd1b46ceb05
31e08e6741d949e46874a0bafcf6af38
348cc0cade7fae7a7da7301afe1a2db5
3840ad43d996e1e5c9b301191531cd16
3a26192f3c9aace1be42473621f4d016
3b6141dd99d62a4351ec836bf6ab2a78
413d33d6f9443c341351fe1a8772e3c3
4c2ba6ef8fef81866b58c6df111c930f
4fe6689207cfe38f5fed2f3285be5b05
510ea0e060992dceff20890105f78a01
524fe7f8410ab8e82faaeb628e36e557
5e27d019b4b61a8045b08326608b7557
5f170ed7397b76237d1c666fdbde1628
6128d5dc17a7b642db7adeb22a5c2006
626923bfb630c34e6db23633b7191c8c
63761ad4c4691b47738f7819ff983e9b
691315e4de44396437851e5f3c73da49
6ed158d12e73c5aa67fddd2354daedbc



funnel_df.groupBy("cid").agg(collect_list("accept") as "acceptArr").rdd.filter(_.getAs[Seq[String]]("acceptArr").length>1).filter(x=>x.getAs[Seq[String]]("acceptArr").head!=x.getAs[Seq[String]]("acceptArr").last).map(_.getAs[String]("cid")).toDF("cid").join(funnel_df,Seq("cid"),"inner")




