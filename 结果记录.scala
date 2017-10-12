 //0701
// 非cross
+-----+---------------------------+-------------------+
|index|feature                    |params             |
+-----+---------------------------+-------------------+
|5    |android_id_s=unique        |-2.6779821795596237|
|14   |imei_s=unique              |-2.3496427726568903|
|7    |android_serial_s=unique    |-2.0811035657529384|
|15   |mac_addr_s=unique          |-1.2293903030796272|
|16   |imei_s=missing             |-1.21850952506747  |
|13   |android_serial_s=missing   |-1.1915061512939076|
|3    |mac_addr_s=missing         |-0.9938875176328718|
|10   |advertising_id_s=unique    |-0.9421668719861197|
|1    |android_id_s=missing       |-0.741374796211284 |
|4    |cpu_serial_s=unique        |-0.7151668904106192|
|9    |advertising_id_s=missing   |-0.6666020522496325|
|11   |cpu_serial_s=missing       |-0.6144768491212768|
|12   |advertising_id_s=duplicated|1.3389359598739692 |
|2    |cpu_serial_s=duplicated    |2.1342553629010923 |
|6    |mac_addr_s=duplicated      |2.2190189386895796 |
|17   |imei_s=duplicated          |2.542104872570528  |
|0    |android_id_s=duplicated    |2.6816014316863903 |
|8    |android_serial_s=duplicated|2.943792336910057  |
+-----+---------------------------+-------------------+





// 非cross
+-----+---------------------------+--------------------+
|index|feature                    |params              |
+-----+---------------------------+--------------------+
|5    |android_id_s=unique        |-2.567102621180588  |
|14   |imei_s=unique              |-1.7335486974777572 |
|7    |android_serial_s=unique    |-1.1597203202803081 |
|10   |advertising_id_s=unique    |-1.1016927494328137 |
|15   |mac_addr_s=unique          |-0.5859532190968568 |
|16   |imei_s=missing             |-0.48619646773341807|
|9    |advertising_id_s=missing   |-0.31589280673438186|
|3    |mac_addr_s=missing         |-0.31489349315807746|
|13   |android_serial_s=missing   |-0.3110625999271446 |
|4    |cpu_serial_s=unique        |-0.26428663704611394|
|11   |cpu_serial_s=missing       |-0.09468385066896444|
|1    |android_id_s=missing       |0.8539490076004431  |
|2    |cpu_serial_s=duplicated    |2.2184005847222315  |
|6    |mac_addr_s=duplicated      |2.288890700168479   |
|12   |advertising_id_s=duplicated|2.3564106254897372  |
|0    |android_id_s=duplicated    |2.573797854702414   |
|8    |android_serial_s=duplicated|2.8824787873996867  |
|17   |imei_s=duplicated          |3.075807607902312   |
+-----+---------------------------+--------------------+
// cross 的结果
+-----+--------------------------------------------------------+---------------------+
|index|feature                                                 |params               |
+-----+--------------------------------------------------------+---------------------+
|123  |android_serial_s=unique&&cpu_serial_s=duplicated        |-1.5807702167966897  |
|104  |cpu_serial_s=unique&&android_serial_s=duplicated        |-1.2362530554438875  |
|57   |android_id_s=missing&&advertising_id_s=unique           |-0.9169948331983957  |
|151  |mac_addr_s=duplicated&&imei_s=unique                    |-0.914532940095703   |
|30   |mac_addr_s=unique&&imei_s=duplicated                    |-0.8883774512489934  |
|22   |cpu_serial_s=unique&&imei_s=duplicated                  |-0.8190729054564068  |
|71   |android_id_s=unique                                     |-0.8094659559073998  |
|142  |android_id_s=missing&&android_serial_s=missing          |-0.7163600909800107  |
|109  |mac_addr_s=missing&&android_id_s=missing                |-0.5982407090095903  |
|93   |imei_s=unique&&android_id_s=missing                     |-0.4812294694714407  |
|31   |advertising_id_s=missing&&android_id_s=missing          |-0.471881552429869   |
|50   |imei_s=unique&&android_id_s=unique                      |-0.44721144909692856 |
|28   |android_id_s=unique&&android_serial_s=missing           |-0.43744199549718427 |
|125  |imei_s=unique                                           |-0.41514710294525    |
|66   |android_id_s=unique&&advertising_id_s=unique            |-0.3867251157897348  |
|69   |android_serial_s=duplicated&&mac_addr_s=unique          |-0.3798812817358324  |
|94   |advertising_id_s=unique                                 |-0.3550257212400108  |
|152  |cpu_serial_s=missing&&android_id_s=unique               |-0.3278983255626556  |
|72   |mac_addr_s=missing&&android_serial_s=missing            |-0.31809628250919103 |
|113  |imei_s=missing&&android_id_s=unique                     |-0.3039482483998624  |
|8    |advertising_id_s=missing&&android_id_s=unique           |-0.3000773154503331  |
|138  |mac_addr_s=missing&&android_id_s=unique                 |-0.2877712727702152  |
|58   |mac_addr_s=duplicated&&imei_s=missing                   |-0.2583558382465348  |
|118  |imei_s=unique&&advertising_id_s=unique                  |-0.25754817157497706 |
|75   |android_serial_s=unique&&android_id_s=unique            |-0.2551427078139863  |
|76   |android_serial_s=unique                                 |-0.253770955594161   |
|140  |imei_s=unique&&android_serial_s=missing                 |-0.2504735265142256  |
|128  |advertising_id_s=unique&&android_serial_s=missing       |-0.24118158727268776 |
|143  |cpu_serial_s=missing&&imei_s=unique                     |-0.23200259663104483 |
|23   |imei_s=missing&&mac_addr_s=unique                       |-0.22707009985213578 |
|61   |advertising_id_s=missing&&imei_s=unique                 |-0.2259942244265581  |
|97   |imei_s=missing&&advertising_id_s=unique                 |-0.22206832784482994 |
|10   |mac_addr_s=unique&&android_id_s=unique                  |-0.21422374223291485 |
|145  |android_serial_s=unique&&mac_addr_s=duplicated          |-0.20570786659065382 |
|84   |advertising_id_s=missing&&mac_addr_s=missing            |-0.20110497326992932 |
|122  |mac_addr_s=missing&&advertising_id_s=unique             |-0.18863571824671635 |
|116  |android_serial_s=unique&&advertising_id_s=unique        |-0.18676505101397908 |
|114  |cpu_serial_s=missing&&advertising_id_s=unique           |-0.1867511289416463  |
|146  |android_serial_s=unique&&imei_s=unique                  |-0.18404670749118218 |
|131  |mac_addr_s=unique                                       |-0.1773090955347638  |
|86   |mac_addr_s=unique&&android_serial_s=missing             |-0.1741383277346352  |
|20   |android_serial_s=unique&&advertising_id_s=missing       |-0.15079664405665733 |
|15   |mac_addr_s=unique&&advertising_id_s=unique              |-0.14807364921503305 |
|52   |imei_s=unique&&mac_addr_s=unique                        |-0.14557489211518226 |
|130  |advertising_id_s=missing&&mac_addr_s=unique             |-0.14405598309462309 |
|47   |cpu_serial_s=missing&&imei_s=missing                    |-0.1401002530703034  |
|51   |cpu_serial_s=unique&&android_id_s=missing               |-0.1375777541848197  |
|110  |cpu_serial_s=missing&&android_id_s=missing              |-0.13384647123797438 |
|29   |cpu_serial_s=missing&&mac_addr_s=unique                 |-0.13207439752972386 |
|24   |android_serial_s=unique&&cpu_serial_s=missing           |-0.13133234713913242 |
|53   |cpu_serial_s=duplicated&&android_serial_s=missing       |-0.1305106579262517  |
|7    |cpu_serial_s=unique&&imei_s=missing                     |-0.12173161769145699 |
|13   |android_serial_s=unique&&imei_s=missing                 |-0.11837337201072846 |
|5    |android_serial_s=unique&&mac_addr_s=unique              |-0.11075943231081209 |
|134  |imei_s=missing                                          |-0.10857651191513074 |
|133  |advertising_id_s=missing&&cpu_serial_s=unique           |-0.10327091585230298 |
|46   |android_serial_s=unique&&mac_addr_s=missing             |-0.09504737480268757 |
|56   |mac_addr_s=missing&&imei_s=unique                       |-0.09504324229524042 |
|59   |imei_s=missing&&android_serial_s=missing                |-0.09265114133134045 |
|149  |cpu_serial_s=unique&&android_id_s=unique                |-0.09126133808111515 |
|43   |cpu_serial_s=unique                                     |-0.09059000335167025 |
|44   |android_serial_s=unique&&cpu_serial_s=unique            |-0.09030608400810114 |
|111  |mac_addr_s=missing&&cpu_serial_s=unique                 |-0.0871179999341542  |
|108  |cpu_serial_s=unique&&advertising_id_s=unique            |-0.08659222122933187 |
|87   |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.0846331234588506  |
|106  |cpu_serial_s=unique&&imei_s=unique                      |-0.07952650567948343 |
|112  |android_serial_s=missing                                |-0.07807175333448073 |
|73   |cpu_serial_s=missing&&android_serial_s=missing          |-0.07788228841547129 |
|38   |mac_addr_s=missing                                      |-0.07383254871048757 |
|33   |cpu_serial_s=missing&&mac_addr_s=missing                |-0.07292419576991019 |
|21   |mac_addr_s=missing&&imei_s=missing                      |-0.06732504997504331 |
|0    |cpu_serial_s=unique&&android_serial_s=missing           |-0.06284934501676151 |
|70   |imei_s=unique&&android_serial_s=duplicated              |-0.04900217058023757 |
|19   |advertising_id_s=missing&&imei_s=missing                |-0.022599172427780985|
|65   |advertising_id_s=missing&&android_serial_s=missing      |-0.016580244341544814|
|98   |cpu_serial_s=missing                                    |0.00407256275282199  |
|64   |imei_s=missing&&android_serial_s=duplicated             |0.017130122821034537 |
|78   |advertising_id_s=missing                                |0.03847941697999798  |
|32   |advertising_id_s=missing&&cpu_serial_s=missing          |0.04182161471199817  |
|18   |android_id_s=missing                                    |0.08631202240216841  |
|96   |android_serial_s=unique&&android_id_s=missing           |0.13048318077581056  |
|91   |mac_addr_s=missing&&imei_s=duplicated                   |0.1846974423759871   |
|117  |mac_addr_s=unique&&android_id_s=missing                 |0.2006393669984859   |
|103  |mac_addr_s=unique&&cpu_serial_s=duplicated              |0.38808604953101294  |
|4    |mac_addr_s=duplicated&&advertising_id_s=duplicated      |0.40968296654964337  |
|39   |android_id_s=duplicated&&mac_addr_s=duplicated          |0.42466422242646507  |
|2    |mac_addr_s=missing&&android_serial_s=duplicated         |0.42817249669465957  |
|99   |android_id_s=duplicated&&cpu_serial_s=duplicated        |0.4592117695917209   |
|95   |advertising_id_s=missing&&cpu_serial_s=duplicated       |0.45923836974595994  |
|68   |cpu_serial_s=duplicated&&imei_s=duplicated              |0.4635491709764489   |
|16   |mac_addr_s=duplicated&&cpu_serial_s=duplicated          |0.5110079505265331   |
|88   |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.5121222549199903   |
|126  |mac_addr_s=duplicated&&android_serial_s=missing         |0.5184336078954933   |
|34   |cpu_serial_s=duplicated                                 |0.5211722960940456   |
|67   |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.532521421965942    |
|141  |advertising_id_s=duplicated&&imei_s=duplicated          |0.5382925618881307   |
|27   |android_id_s=duplicated&&android_serial_s=duplicated    |0.5407225125302609   |
|124  |advertising_id_s=duplicated&&android_serial_s=duplicated|0.5445788571796785   |
|54   |mac_addr_s=missing&&cpu_serial_s=duplicated             |0.5631157869237775   |
|127  |imei_s=missing&&android_id_s=missing                    |0.5702598008378069   |
|121  |android_id_s=duplicated&&advertising_id_s=duplicated    |0.5934783717320007   |
|102  |advertising_id_s=duplicated                             |0.6104765628444793   |
|107  |cpu_serial_s=missing&&advertising_id_s=duplicated       |0.6106864705145408   |
|74   |mac_addr_s=duplicated                                   |0.6425821792040515   |
|77   |android_serial_s=duplicated                             |0.6456813196229964   |
|137  |cpu_serial_s=missing&&mac_addr_s=duplicated             |0.6471643344461077   |
|81   |cpu_serial_s=missing&&android_serial_s=duplicated       |0.6541229679215858   |
|45   |android_id_s=duplicated&&imei_s=duplicated              |0.6716379160769644   |
|85   |android_serial_s=missing&&imei_s=duplicated             |0.6936839153569174   |
|6    |android_serial_s=duplicated&&imei_s=duplicated          |0.7005016287727354   |
|90   |android_serial_s=unique&&imei_s=duplicated              |0.7205541344688796   |
|139  |imei_s=duplicated                                       |0.7311615860739128   |
|135  |mac_addr_s=duplicated&&android_serial_s=duplicated      |0.7340196737415181   |
|37   |cpu_serial_s=missing&&imei_s=duplicated                 |0.7786990007948758   |
|115  |advertising_id_s=duplicated&&android_serial_s=missing   |0.7894876810589998   |
|11   |android_id_s=duplicated                                 |0.8131632623497135   |
|82   |advertising_id_s=missing&&mac_addr_s=duplicated         |0.8434547601683058   |
|17   |android_id_s=duplicated&&cpu_serial_s=missing           |0.8762706196125191   |
|80   |cpu_serial_s=duplicated&&advertising_id_s=unique        |0.9246836856191742   |
|144  |advertising_id_s=missing&&android_serial_s=duplicated   |0.9359809369798942   |
|25   |mac_addr_s=missing&&advertising_id_s=duplicated         |0.9466096835897354   |
|150  |mac_addr_s=duplicated&&imei_s=duplicated                |0.9525303864419697   |
|36   |imei_s=missing&&cpu_serial_s=duplicated                 |0.9782344914588368   |
|136  |android_serial_s=duplicated&&advertising_id_s=unique    |1.0486751555328364   |
|62   |advertising_id_s=missing&&imei_s=duplicated             |1.0691719855889734   |
|79   |mac_addr_s=duplicated&&advertising_id_s=unique          |1.1163502756990458   |
|48   |advertising_id_s=duplicated&&mac_addr_s=unique          |1.147491706402615    |
|35   |advertising_id_s=duplicated&&imei_s=missing             |1.2214873184457091   |
|147  |android_id_s=duplicated&&mac_addr_s=missing             |1.4395211447701128   |
|105  |android_id_s=duplicated&&advertising_id_s=missing       |1.4861549309920836   |
|60   |mac_addr_s=duplicated&&android_id_s=unique              |1.486426844696319    |
|92   |imei_s=unique&&advertising_id_s=duplicated              |1.4873605517781125   |
|41   |android_serial_s=duplicated&&android_id_s=missing       |1.4923384259290673   |
|9    |advertising_id_s=unique&&imei_s=duplicated              |1.5827830937298002   |
|49   |imei_s=unique&&cpu_serial_s=duplicated                  |1.6196717090314763   |
|63   |android_id_s=duplicated&&android_serial_s=missing       |1.6583789199608914   |
|119  |android_id_s=unique&&imei_s=duplicated                  |1.7069377717299057   |
|14   |android_id_s=duplicated&&advertising_id_s=unique        |1.7528664030479228   |
|3    |mac_addr_s=duplicated&&android_id_s=missing             |1.8020155526191854   |
|55   |cpu_serial_s=unique&&mac_addr_s=duplicated              |2.0086758906883264   |
|101  |android_id_s=duplicated&&imei_s=missing                 |2.0592030108331474   |
|129  |cpu_serial_s=unique&&advertising_id_s=duplicated        |2.1134635090157787   |
|132  |android_id_s=missing&&imei_s=duplicated                 |2.4955259438498865   |
|83   |android_id_s=duplicated&&cpu_serial_s=unique            |2.7746316622886678   |
|42   |android_id_s=duplicated&&mac_addr_s=unique              |2.862035559686348    |
|89   |android_id_s=duplicated&&imei_s=unique                  |3.1154032375557263   |
|1    |cpu_serial_s=duplicated&&android_id_s=unique            |3.207982989611844    |
|12   |android_serial_s=duplicated&&android_id_s=unique        |3.240121091025008    |
|26   |android_serial_s=unique&&advertising_id_s=duplicated    |3.2512305251594125   |
|100  |android_serial_s=unique&&android_id_s=duplicated        |3.5110079365865206   |
|120  |advertising_id_s=duplicated&&android_id_s=missing       |3.942818682611098    |
|148  |android_id_s=missing&&cpu_serial_s=duplicated           |4.319570220206021    |
|40   |advertising_id_s=duplicated&&android_id_s=unique        |7.427267681550404    |
+-----+--------------------------------------------------------+---------------------+

   
// ***************************各个DF示例*****************************
  // 6个ID列 的列名
  val allId_columnName = "allSixId"
  // 标志 join结果 的列名
  val join_result_columnName = "tag"
  // 标记 新数据中同一用户 的列名
  val joinedKey_user_columnName = "id"

resultWithIndexRDD
(List(android_id_s=9057e126dd9667f, imei_s=911468701728781, mac_addr_s=cc:1b:e0:d0:25:61, android_serial_s=0123456789ABCDEF, cpu_serial_s=0000000000000000, advertising_id_s=9c2ed067-85d5-40c9-96fc-655ebd7f90ed),2017-07-11-0)
(List(android_id_s=4d26152f9d64ec23, imei_s=358158077835589, mac_addr_s=BC:D1:1F:51:47:20, android_serial_s=3300c895af44b28d, cpu_serial_s=\N, advertising_id_s=3a96d886-599b-4ecf-a6d3-4a9920df01de),2017-07-11-1)
(List(android_id_s=55c296c2011d2332, imei_s=359592072295200, mac_addr_s=02:00:00:00:00:00, android_serial_s=52033b03eea28379, cpu_serial_s=\N, advertising_id_s=4dac6e00-7928-4228-bd2e-5fd49088de65),2017-07-11-2)
(List(android_id_s=5f5e2056ca3feeec, imei_s=356900063904602, mac_addr_s=02:00:00:00:00:00, android_serial_s=acd3c847, cpu_serial_s=0000000000000000, advertising_id_s=f8a39349-7f51-417a-9292-e0a59bacd5b8),2017-07-11-3)
(List(android_id_s=48808026a9ebecc2, imei_s=354772088366519, mac_addr_s=02:00:00:00:00:00, android_serial_s=52039933495d542b, cpu_serial_s=\N, advertising_id_s=6f242cc8-d7b7-480f-9b43-5d4cb1473de6),2017-07-11-4)
(List(android_id_s=91db4a370a78326e, imei_s=358656238879651, mac_addr_s=00:08:22:10:b1:fd, android_serial_s=JVV4QW8H99999999, cpu_serial_s=0000000000000000, advertising_id_s=197e09d4-b0bc-40c7-88a7-a4db901b5f80),2017-07-11-5)
(List(android_id_s=5f103f94043c564a, imei_s=352973057225056, mac_addr_s=40:45:da:bc:08:53, android_serial_s=30316143814479, cpu_serial_s=0000000000000000, advertising_id_s=539515b4-7972-47aa-9bf8-31d02951fc60),2017-07-11-6)
(List(android_id_s=70d38f8297f6e128, imei_s=359256073034790, mac_addr_s=02:00:00:00:00:00, android_serial_s=aaeaee1c, cpu_serial_s=0000aaea0000ee1c, advertising_id_s=aeee331d-ed87-4661-9ee4-9a5236365982),2017-07-11-7)
(List(android_id_s=e7f1b8a0acd07319, imei_s=["352700077210069","352701077210067"], mac_addr_s=[{"name":"rev_rmnet1","up":false,"mac":"b6050d6a9fb4"},{"name":"rev_rmnet0","up":false,"mac":"a28a321a8842"},{"name":"rev_rmnet3","up":false,"mac":"16db73781901"},{"name":"rev_rmnet2","up":false,"mac":"b23762d82f70"},{"name":"dummy0","up":true,"mac":"7a57b85323a0"}], android_serial_s=0a5fdc54, cpu_serial_s=00000a5f0000dc54, advertising_id_s=a9abf7f7-ddcc-4d48-b931-6f82106b59c1),2017-07-11-8)
(List(android_id_s=eebd8b8c6fe8f520, imei_s=867770022629638, mac_addr_s=dc:1a:c5:07:95:8e, android_serial_s=65DABULB99999999, cpu_serial_s=0000000000000000, advertising_id_s=422978e0-29f5-4035-9a85-7c3af6cb34fa),2017-07-11-9)
(List(android_id_s=6616dc6207a8f72d, imei_s=357062070729982, mac_addr_s=9c:5c:8e:a2:b1:32, android_serial_s=G1AXB707T243AJH, cpu_serial_s=0000000000000000, advertising_id_s=c59beafc-c7b0-42a2-aa59-6129c7f66a53),2017-07-11-10)
(List(android_id_s=e2913fd867362c00, imei_s=353441065512757, mac_addr_s=\N, android_serial_s=0123456789ABCDEF, cpu_serial_s=0000000000000000, advertising_id_s=cc58c988-bdcd-4758-9d7d-46bb639e42b7),2017-07-11-11)
(List(android_id_s=727b3d2e70f50c7c, imei_s=357575064109861, mac_addr_s=18:3A:2D:BB:F6:F2, android_serial_s=04ae3378, cpu_serial_s=000004ae00003378, advertising_id_s=\N),2017-07-11-12)
(List(android_id_s=5ff5198af7fef3cb, imei_s=869102028462962, mac_addr_s=04:56:04:2c:70:1a, android_serial_s=E6R8NF9SUOHAIRS4, cpu_serial_s=0000000000000000, advertising_id_s=33418ceb-c62c-416d-bc98-1baf950e580f),2017-07-11-13)
(List(android_id_s=58dca0f37550b747, imei_s=352256081285068, mac_addr_s=\N, android_serial_s=0123456789ABCDEF, cpu_serial_s=0000000000000000, advertising_id_s=893c33bd-4811-481a-a231-3e7715d02ae5),2017-07-11-14)
(List(android_id_s=a354583a5744061, imei_s=990004829859341, mac_addr_s=C0:BD:D1:AE:F8:0F, android_serial_s=90c807ae, cpu_serial_s=000090c8000007ae, advertising_id_s=b4eff84c-e270-491b-9f4d-d47a2e0929eb),2017-07-11-15)
(List(android_id_s=177eedd43500da74, imei_s=358225088221537, mac_addr_s=5C:99:60:F4:DA:AE, android_serial_s=32012322dd2854d5, cpu_serial_s=d55428dd22230132, advertising_id_s=913b5f09-8712-4c7a-aff7-50f4f17f60cf),2017-07-11-16)
(List(android_id_s=fd1bb71c13ea23a5, imei_s=867457029802638, mac_addr_s=bc:3a:ea:80:00:8a, android_serial_s=D6MB8SAMYDS8QCQC, cpu_serial_s=0000000000000000, advertising_id_s=47eb8c52-0706-4732-b69b-1cdbd98fadbc),2017-07-11-17)
(List(android_id_s=2e4dac844b58c440, imei_s=352974089795389, mac_addr_s=5c:af:06:07:f1:d8, android_serial_s=LGMS330112a2c7f, cpu_serial_s=0000000000000000, advertising_id_s=147f5220-ed11-45d9-9485-7f882b5bd810),2017-07-11-18)
(List(android_id_s=22ae331cbd87a86a, imei_s=356823079091042, mac_addr_s=BC:D1:1F:88:DE:3B, android_serial_s=19216d16, cpu_serial_s=0000192100006d16, advertising_id_s=0fd17f87-7a88-4bf3-8143-a62fcc8f68f9),2017-07-11-19)

resultRDD_hasIndex
List((android_id_s=dbef0c1ad04acb86,2017-07-11-0), (imei_s=911508351212022,2017-07-11-0), (mac_addr_s=\N,2017-07-11-0), (android_serial_s=84897858110394,2017-07-11-0), (cpu_serial_s=0000000000000000,2017-07-11-0), (advertising_id_s=75c38569-4266-48c2-b3b2-3980ba3b4ab1,2017-07-11-0))
List((android_id_s=634ad6b7d2e1a4c9,2017-07-11-1), (imei_s=869545026842371,2017-07-11-1), (mac_addr_s=10:2a:b3:3a:25:42,2017-07-11-1), (android_serial_s=b4234e76,2017-07-11-1), (cpu_serial_s=\N,2017-07-11-1), (advertising_id_s=7b7fd79a-1c1b-45eb-a087-0b131c1ed211,2017-07-11-1))
List((android_id_s=5028e804b0d20fe6,2017-07-11-2), (imei_s=355758088551852,2017-07-11-2), (mac_addr_s=02:00:00:00:00:00,2017-07-11-2), (android_serial_s=5210caabb2293493,2017-07-11-2), (cpu_serial_s=\N,2017-07-11-2), (advertising_id_s=\N,2017-07-11-2))
List((android_id_s=1a81b7619218218c,2017-07-11-3), (imei_s=352364071264463,2017-07-11-3), (mac_addr_s=24:da:9b:db:d8:b9,2017-07-11-3), (android_serial_s=0017696749,2017-07-11-3), (cpu_serial_s=0612c30a12000000,2017-07-11-3), (advertising_id_s=fd379802-3f16-42cf-8673-39b002d767f1,2017-07-11-3))
List((android_id_s=bc81d25519d40530,2017-07-11-4), (imei_s=911489204503026,2017-07-11-4), (mac_addr_s=00:0c:e7:c2:ed:e4,2017-07-11-4), (android_serial_s=0123456789ABCDEF,2017-07-11-4), (cpu_serial_s=0000000000000000,2017-07-11-4), (advertising_id_s=77045e40-a3c7-499e-917a-8175630b23f9,2017-07-11-4))
List((android_id_s=b704aa2e15049cb2,2017-07-11-5), (imei_s=911503455812397,2017-07-11-5), (mac_addr_s=00:90:4c:d2:53:b9,2017-07-11-5), (android_serial_s=160525001715,2017-07-11-5), (cpu_serial_s=0000000000000000,2017-07-11-5), (advertising_id_s=N/A,2017-07-11-5))
List((android_id_s=abb566d56f0e186b,2017-07-11-6), (imei_s=358683075498619,2017-07-11-6), (mac_addr_s=02:00:00:00:00:00,2017-07-11-6), (android_serial_s=42002209da5883ef,2017-07-11-6), (cpu_serial_s=ef8358da09220042,2017-07-11-6), (advertising_id_s=b08fd605-2d59-4e63-95fb-3598443b759d,2017-07-11-6))
List((android_id_s=c12521ca59482491,2017-07-11-7), (imei_s=353093081978429,2017-07-11-7), (mac_addr_s=02:00:00:00:00:00,2017-07-11-7), (android_serial_s=DURS5D7T4SZ55LMZ,2017-07-11-7), (cpu_serial_s=0000000000000000,2017-07-11-7), (advertising_id_s=3ebcdd3c-e7de-44cf-8abf-bfcdf8b4c2bd,2017-07-11-7))
List((android_id_s=af3ab7361a5e4d8c,2017-07-11-8), (imei_s=990007370939396,2017-07-11-8), (mac_addr_s=02:00:00:00:00:00,2017-07-11-8), (android_serial_s=fc7a5524,2017-07-11-8), (cpu_serial_s=0000fc7a00005524,2017-07-11-8), (advertising_id_s=44700506-23cc-4376-b6d9-2ba890abbddf,2017-07-11-8))
List((android_id_s=a0f8677ac5fcd866,2017-07-11-9), (imei_s=356813085163760,2017-07-11-9), (mac_addr_s=02:00:00:00:00:00,2017-07-11-9), (android_serial_s=H3AXJA005939PV7,2017-07-11-9), (cpu_serial_s=\N,2017-07-11-9), (advertising_id_s=94fc72a4-9e11-4773-b39b-e5832ae7b0b3,2017-07-11-9))
List((android_id_s=45eaef66ffd7d004,2017-07-11-10), (imei_s=352860088745030,2017-07-11-10), (mac_addr_s=5c:af:06:18:53:ae,2017-07-11-10), (android_serial_s=LGK430PZFM6STO,2017-07-11-10), (cpu_serial_s=0000000000000000,2017-07-11-10), (advertising_id_s=d7f14d54-e240-45ff-9300-e6fe5e5f7017,2017-07-11-10))
List((android_id_s=cb0de41c9f3ac6e2,2017-07-11-11), (imei_s=358008060681104,2017-07-11-11), (mac_addr_s=00:F4:6F:B2:A2:D2,2017-07-11-11), (android_serial_s=420380d3ea5ea100,2017-07-11-11), (cpu_serial_s=ea5ea100420380d3,2017-07-11-11), (advertising_id_s=3c39fc10-f669-4ec6-aa71-604f675414cf,2017-07-11-11))
List((android_id_s=92da908e66204f7e,2017-07-11-12), (imei_s=351617086021454,2017-07-11-12), (mac_addr_s=d0:13:fd:0e:51:c2,2017-07-11-12), (android_serial_s=LGK430CIFU5HW8,2017-07-11-12), (cpu_serial_s=0000000000000000,2017-07-11-12), (advertising_id_s=d4306db0-061d-4f6e-9c70-441a7507e237,2017-07-11-12))
List((android_id_s=69c4bbb82cad6864,2017-07-11-13), (imei_s=404534534634639,2017-07-11-13), (mac_addr_s=c4:9a:02:7e:86:0d,2017-07-11-13), (android_serial_s=065c84c803a28ce2,2017-07-11-13), (cpu_serial_s=0000000000000000,2017-07-11-13), (advertising_id_s=22e67257-9add-437d-b1fd-d553d36eec93,2017-07-11-13))
List((android_id_s=7e3f137f2789945a,2017-07-11-14), (imei_s=\N,2017-07-11-14), (mac_addr_s=C8:14:79:5A:B0:EE,2017-07-11-14), (android_serial_s=4100331f9609a000,2017-07-11-14), (cpu_serial_s=4100331f9609a000,2017-07-11-14), (advertising_id_s=75ef2344-cca5-4c8f-a106-9c293cf0a9c5,2017-07-11-14))
List((android_id_s=8c7ad448efd5d53f,2017-07-11-15), (imei_s=358042060002094,2017-07-11-15), (mac_addr_s=96:00:00:e9:49:0a,2017-07-11-15), (android_serial_s=0123456789ABCDEF,2017-07-11-15), (cpu_serial_s=0000000000000000,2017-07-11-15), (advertising_id_s=1d624d5e-58d5-45c9-905a-791e78a8c7b7,2017-07-11-15))
List((android_id_s=8054d84acc0cf8dd,2017-07-11-16), (imei_s=911510658105636,2017-07-11-16), (mac_addr_s=d8:6c:02:f0:a8:8f,2017-07-11-16), (android_serial_s=V027751702600015261,2017-07-11-16), (cpu_serial_s=0000000000000000,2017-07-11-16), (advertising_id_s=a473230a-2bbc-44e6-8faf-8e7360671f79,2017-07-11-16))
List((android_id_s=314c171ca02991ae,2017-07-11-17), (imei_s=356262066627023,2017-07-11-17), (mac_addr_s=00:08:22:18:63:fd,2017-07-11-17), (android_serial_s=GIZLINGIYPGQEYSS,2017-07-11-17), (cpu_serial_s=0000000000000000,2017-07-11-17), (advertising_id_s=4894af66-d729-44ae-bee4-f01dc3a1f12b,2017-07-11-17))
List((android_id_s=13994589a8812869,2017-07-11-18), (imei_s=861170030992910,2017-07-11-18), (mac_addr_s=02:00:00:00:00:00,2017-07-11-18), (android_serial_s=f892d65b,2017-07-11-18), (cpu_serial_s=\N,2017-07-11-18), (advertising_id_s=28a5f8d1-114b-430d-a2af-2f57d8b69b6c,2017-07-11-18))
List((android_id_s=a91976f14e0b1451,2017-07-11-19), (imei_s=356113071666102,2017-07-11-19), (mac_addr_s=02:00:00:00:00:00,2017-07-11-19), (android_serial_s=356113071666102,2017-07-11-19), (cpu_serial_s=0000000000000000,2017-07-11-19), (advertising_id_s=7d221c81-d515-4790-aaa5-5b91a550f67e,2017-07-11-19))  

flatRDD_new
(android_id_s=6a11931db6243eac,2017-07-11-0)
(imei_s=359692064048383,2017-07-11-0)
(mac_addr_s=40:45:da:97:bb:0e,2017-07-11-0)
(android_serial_s=CW824XA550X61E002421,2017-07-11-0)
(cpu_serial_s=0000000000000000,2017-07-11-0)
(advertising_id_s=a86910bb-f627-47fa-acda-367a042fee5c,2017-07-11-0)
(android_id_s=c7d1d7da63bf4f81,2017-07-11-1)
(imei_s=860857031062088,2017-07-11-1)
(mac_addr_s=dc:d9:16:e3:53:27,2017-07-11-1)
(android_serial_s=BHG7N16708004270,2017-07-11-1)
(cpu_serial_s=\N,2017-07-11-1)
(advertising_id_s=131de0e4-f1ce-4f33-9a1e-d9a76c2f1c34,2017-07-11-1)
(android_id_s=afc692ac842fe5b7,2017-07-11-2)
(imei_s=865823027665586,2017-07-11-2)
(mac_addr_s=00:27:15:77:23:c6,2017-07-11-2)
(android_serial_s=2015122700425784,2017-07-11-2)
(cpu_serial_s=0000000000000000,2017-07-11-2)
(advertising_id_s=66d2b90b-087f-4a56-963c-2ff81b837223,2017-07-11-2)
(android_id_s=e59fc2693389cad,2017-07-11-3)
(imei_s=354638080137671,2017-07-11-3)

df_result_new
+-----------------------------------------------------+------------+
|allSixId                                             |id          |
+-----------------------------------------------------+------------+
|android_id_s=6a11931db6243eac                        |2017-07-11-0|
|imei_s=359692064048383                               |2017-07-11-0|
|mac_addr_s=40:45:da:97:bb:0e                         |2017-07-11-0|
|android_serial_s=CW824XA550X61E002421                |2017-07-11-0|
|cpu_serial_s=0000000000000000                        |2017-07-11-0|
|advertising_id_s=a86910bb-f627-47fa-acda-367a042fee5c|2017-07-11-0|
|android_id_s=c7d1d7da63bf4f81                        |2017-07-11-1|
|imei_s=860857031062088                               |2017-07-11-1|
|mac_addr_s=dc:d9:16:e3:53:27                         |2017-07-11-1|
|android_serial_s=BHG7N16708004270                    |2017-07-11-1|
|cpu_serial_s=\N                                      |2017-07-11-1|
|advertising_id_s=131de0e4-f1ce-4f33-9a1e-d9a76c2f1c34|2017-07-11-1|
|android_id_s=afc692ac842fe5b7                        |2017-07-11-2|
|imei_s=865823027665586                               |2017-07-11-2|
|mac_addr_s=00:27:15:77:23:c6                         |2017-07-11-2|
|android_serial_s=2015122700425784                    |2017-07-11-2|
|cpu_serial_s=0000000000000000                        |2017-07-11-2|
|advertising_id_s=66d2b90b-087f-4a56-963c-2ff81b837223|2017-07-11-2|
|android_id_s=e59fc2693389cad                         |2017-07-11-3|
|imei_s=354638080137671                               |2017-07-11-3|
+-----------------------------------------------------+------------+



newDF
+-----------------------------------------------------+------------+
|allSixId                                             |id          |
+-----------------------------------------------------+------------+
|android_id_s=74f8eeb8b16232fa                        |2017-07-01-0|
|imei_s=861841033268439                               |2017-07-01-0|
|mac_addr_s=24:61:5a:42:66:0d                         |2017-07-01-0|
|android_serial_s=72c9a96                             |2017-07-01-0|
|cpu_serial_s=dirtyData                               |2017-07-01-0|
|advertising_id_s=e0e34378-109f-4c14-a808-bdabce8b7bda|2017-07-01-0|
|android_id_s=389fd1c004efcf12                        |2017-07-01-1|
|imei_s=354122072012440                               |2017-07-01-1|
|mac_addr_s=dirtyData                                 |2017-07-01-1|
|android_serial_s=ZY223TCKPV                          |2017-07-01-1|
|cpu_serial_s=13ca310100000000                        |2017-07-01-1|
|advertising_id_s=b2538140-9653-4d79-9bae-3d52445a038f|2017-07-01-1|
|android_id_s=7933693e8c3cf3ea                        |2017-07-01-2|
|imei_s=911500051498183                               |2017-07-01-2|
|mac_addr_s=40:c6:2a:6a:af:61                         |2017-07-01-2|
|android_serial_s=dirtyData                           |2017-07-01-2|
|cpu_serial_s=dirtyData                               |2017-07-01-2|
|advertising_id_s=01dc6f4d-7b62-47ff-9ab6-203558d325f7|2017-07-01-2|
|android_id_s=b07d647603cdb0a2                        |2017-07-01-3|
|imei_s=351507013256929                               |2017-07-01-3|
+-----------------------------------------------------+------------+

historyDF
+-----------------------------------------------------+---+
|allSixId                                             |tag|
+-----------------------------------------------------+---+
|mac_addr_s=d8:6c:85:62:82:64                         |1  |
|android_id_s=ceaa29e5b7cadacd                        |1  |
|android_id_s=199bbc4ff307c28c                        |1  |
|android_id_s=2b596f5b58a3d3da                        |1  |
|cpu_serial_s=430055adb0cc6000                        |1  |
|imei_s=352328063190465                               |1  |
|advertising_id_s=50b1b22c-065a-45ca-abb9-d4e15d0e5f37|1  |
|android_serial_s=3004d074ae5a8100                    |1  |
|imei_s=357091057828152                               |1  |
|android_serial_s=89d22680                            |1  |
|android_id_s=b040e9988678c603                        |1  |
|cpu_serial_s=21010231c53e234a                        |1  |
|android_id_s=5e4c80163a2db297                        |1  |
|mac_addr_s=A0:B4:A5:C7:C6:0D                         |1  |
|android_id_s=c434adb38a6e3c26                        |1  |
|advertising_id_s=25313da0-e133-4127-91ca-462759b2dd92|1  |
|mac_addr_s=5c:af:06:8c:46:9c                         |1  |
|advertising_id_s=bb18d5d5-44b5-4d15-b2cc-b6ef4c987483|1  |
|advertising_id_s=a73c4c4e-fc30-4cf3-a12e-d9c95ea73fe1|1  |
|android_serial_s=YT91118TCW                          |1  |
+-----------------------------------------------------+---+

joinedDF
+-----------------------------------------------------+--------------+----+
|allSixId                                             |id            |tag |
+-----------------------------------------------------+--------------+----+
|imei_s=861841033268439                               |2017-07-01-0  |1   |
|mac_addr_s=24:61:5a:42:66:0d                         |2017-07-01-0  |1   |
|android_id_s=74f8eeb8b16232fa                        |2017-07-01-0  |1   |
|advertising_id_s=e0e34378-109f-4c14-a808-bdabce8b7bda|2017-07-01-0  |1   |
|android_serial_s=72c9a96                             |2017-07-01-0  |1   |
|cpu_serial_s=dirtyData                               |2017-07-01-0  |null|
|android_serial_s=ZY223TCKPV                          |2017-07-01-1  |1   |
|mac_addr_s=dirtyData                                 |2017-07-01-1  |null|
|advertising_id_s=b2538140-9653-4d79-9bae-3d52445a038f|2017-07-01-1  |1   |
|imei_s=354122072012440                               |2017-07-01-1  |1   |
|cpu_serial_s=13ca310100000000                        |2017-07-01-1  |1   |
|android_id_s=389fd1c004efcf12                        |2017-07-01-1  |1   |
|imei_s=911508059189381                               |2017-07-01-10 |1   |
|cpu_serial_s=dirtyData                               |2017-07-01-10 |null|
|android_id_s=8ebb15b24ec2a6bd                        |2017-07-01-10 |1   |
|android_serial_s=dirtyData                           |2017-07-01-10 |null|
|advertising_id_s=dd8663b3-285a-4e7d-875e-8b5a83e8079d|2017-07-01-10 |1   |
|mac_addr_s=00:90:4c:52:33:2e                         |2017-07-01-10 |1   |
|android_id_s=51c5a5e290f140ab                        |2017-07-01-100|null|
|mac_addr_s=dirtyData                                 |2017-07-01-100|null|
+-----------------------------------------------------+--------------+----+


extractorDF
+--------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|base_record         |cid_feature                                                                                                                                                                               |
+--------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|[2017-07-01-253335] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-436548] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-141011] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-753848] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-830141] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-246206] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-174648] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-935828] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-112835] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-347788] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-66098]  |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-311417] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-103319] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-184811] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-926253] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-1111066]|[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-1063837]|[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-888877] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-778112] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
|[2017-07-01-210724] |[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]|
+--------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+

labeledDF
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|base_record           |cid_feature                                                                                                                                                                               |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|[2017-07-01-1000084,0]|[[advertising_id_s=unique,1.0], [android_id_s=unique,1.0], [imei_s=unique,1.0], [cpu_serial_s=missing,1.0], [android_serial_s=unique,1.0], [mac_addr_s=unique,1.0]]                       |
|[2017-07-01-1000246,1]|[[mac_addr_s=duplicated,1.0], [imei_s=missing,1.0], [android_serial_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0]]   |
|[2017-07-01-1000408,0]|[[mac_addr_s=unique,1.0], [android_serial_s=unique,1.0], [cpu_serial_s=missing,1.0], [imei_s=unique,1.0], [advertising_id_s=unique,1.0], [android_id_s=unique,1.0]]                       |
|[2017-07-01-1000697,1]|[[mac_addr_s=missing,1.0], [cpu_serial_s=missing,1.0], [imei_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [android_id_s=duplicated,1.0], [advertising_id_s=unique,1.0]]          |
|[2017-07-01-1000741,1]|[[android_serial_s=missing,1.0], [android_id_s=unique,1.0], [mac_addr_s=duplicated,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=unique,1.0], [imei_s=duplicated,1.0]]              |
|[2017-07-01-100082,0] |[[mac_addr_s=missing,1.0], [android_serial_s=unique,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=unique,1.0], [android_id_s=unique,1.0], [imei_s=unique,1.0]]                      |
|[2017-07-01-1000859,1]|[[advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [cpu_serial_s=missing,1.0], [mac_addr_s=duplicated,1.0]]   |
|[2017-07-01-1000903,0]|[[imei_s=unique,1.0], [android_serial_s=unique,1.0], [advertising_id_s=unique,1.0], [mac_addr_s=unique,1.0], [cpu_serial_s=unique,1.0], [android_id_s=unique,1.0]]                        |
|[2017-07-01-1001137,1]|[[mac_addr_s=missing,1.0], [advertising_id_s=duplicated,1.0], [imei_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0]]   |
|[2017-07-01-1001470,0]|[[advertising_id_s=unique,1.0], [mac_addr_s=missing,1.0], [android_serial_s=unique,1.0], [android_id_s=unique,1.0], [imei_s=unique,1.0], [cpu_serial_s=missing,1.0]]                      |
|[2017-07-01-1001588,0]|[[mac_addr_s=unique,1.0], [cpu_serial_s=unique,1.0], [android_serial_s=unique,1.0], [imei_s=unique,1.0], [advertising_id_s=unique,1.0], [android_id_s=unique,1.0]]                        |
|[2017-07-01-1001632,1]|[[android_id_s=duplicated,1.0], [imei_s=unique,1.0], [advertising_id_s=duplicated,1.0], [cpu_serial_s=missing,1.0], [android_serial_s=duplicated,1.0], [mac_addr_s=unique,1.0]]           |
|[2017-07-01-1002028,0]|[[mac_addr_s=missing,1.0], [imei_s=unique,1.0], [android_serial_s=unique,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=unique,1.0], [android_id_s=unique,1.0]]                      |
|[2017-07-01-1002361,0]|[[imei_s=unique,1.0], [android_serial_s=unique,1.0], [advertising_id_s=unique,1.0], [cpu_serial_s=missing,1.0], [android_id_s=unique,1.0], [mac_addr_s=unique,1.0]]                       |
|[2017-07-01-100244,1] |[[imei_s=missing,1.0], [android_serial_s=duplicated,1.0], [android_id_s=unique,1.0], [advertising_id_s=unique,1.0], [mac_addr_s=unique,1.0], [cpu_serial_s=duplicated,1.0]]               |
|[2017-07-01-1002479,1]|[[mac_addr_s=duplicated,1.0], [android_id_s=unique,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=missing,1.0], [android_serial_s=duplicated,1.0], [imei_s=duplicated,1.0]]          |
|[2017-07-01-1002523,0]|[[advertising_id_s=unique,1.0], [mac_addr_s=missing,1.0], [android_serial_s=unique,1.0], [cpu_serial_s=missing,1.0], [android_id_s=unique,1.0], [imei_s=unique,1.0]]                      |
|[2017-07-01-1002974,1]|[[android_serial_s=missing,1.0], [imei_s=duplicated,1.0], [android_id_s=duplicated,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]      |
|[2017-07-01-1003090,1]|[[mac_addr_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [advertising_id_s=duplicated,1.0], [cpu_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [android_id_s=duplicated,1.0]]|
|[2017-07-01-1003252,0]|[[cpu_serial_s=unique,1.0], [imei_s=unique,1.0], [advertising_id_s=unique,1.0], [mac_addr_s=unique,1.0], [android_id_s=unique,1.0], [android_serial_s=unique,1.0]]                        |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+

dupDF
+------------------------------------------------------------------------------------+------+
|cid_feature                                                                         |count |
+------------------------------------------------------------------------------------+------+
|[advertising_id_s, android_id_s, android_serial_s, imei_s, mac_addr_s]              |146911|
|[advertising_id_s, android_id_s, android_serial_s, imei_s]                          |85386 |
|[advertising_id_s, android_id_s, android_serial_s, cpu_serial_s, imei_s, mac_addr_s]|59439 |
|[advertising_id_s, android_id_s, imei_s, mac_addr_s]                                |52836 |
|[android_id_s, android_serial_s, imei_s, mac_addr_s]                                |25522 |
|[android_serial_s, imei_s, mac_addr_s]                                              |16726 |
|[advertising_id_s, android_id_s, imei_s]                                            |15618 |
|[android_id_s, android_serial_s, cpu_serial_s, imei_s, mac_addr_s]                  |14316 |
|[android_serial_s, imei_s]                                                          |11900 |
|[android_id_s, imei_s, mac_addr_s]                                                  |11621 |
|[advertising_id_s, android_id_s, android_serial_s, cpu_serial_s, imei_s]            |11042 |
|[android_id_s, android_serial_s, imei_s]                                            |8564  |
|[imei_s, mac_addr_s]                                                                |7646  |
|[advertising_id_s, android_id_s, android_serial_s]                                  |6624  |
|[android_id_s, imei_s]                                                              |6243  |
|[android_serial_s, cpu_serial_s, imei_s, mac_addr_s]                                |4478  |
|[advertising_id_s, android_id_s, android_serial_s, mac_addr_s]                      |3655  |
|[advertising_id_s, android_id_s, android_serial_s, cpu_serial_s, mac_addr_s]        |3371  |
|[advertising_id_s, android_id_s, android_serial_s, cpu_serial_s]                    |1544  |
|[android_id_s, android_serial_s, cpu_serial_s, mac_addr_s]                          |1284  |
+------------------------------------------------------------------------------------+------+



uniqueID
+-----------------------------------------------------+---+----------+
|allSixId                                             |tag|dt        |
+-----------------------------------------------------+---+----------+
|advertising_id_s=013cd6af-1e53-41c6-8909-c9d8a6cba169|1  |2017-07-09|
|advertising_id_s=09880339-6ceb-41aa-a4fc-c97d99382912|1  |2017-07-09|
|advertising_id_s=0ca914c6-d00d-4457-a297-afbd7b942dac|1  |2017-07-09|
|advertising_id_s=0d318f83-b7e9-4d00-a27e-66fdc74cb407|1  |2017-07-09|
|advertising_id_s=0fef2d6f-9e5c-4b6e-9bd7-d52c1e43a823|1  |2017-07-09|
|advertising_id_s=1269d8ba-749c-44f5-b656-8c45656eb014|1  |2017-07-09|
|advertising_id_s=1316391b-19c1-494d-bb34-11fc31376ea4|1  |2017-07-09|
|advertising_id_s=13879ced-16cc-4775-a432-5336d58d2940|1  |2017-07-09|
|advertising_id_s=13aa08d6-5598-42a9-aadf-e3aec81cb38b|1  |2017-07-09|
|advertising_id_s=164a6375-1c2c-4060-b3c9-90def50e9809|1  |2017-07-09|
|advertising_id_s=21725d6b-1234-4b10-bff6-a6525ba4d5db|1  |2017-07-09|
|advertising_id_s=23944b85-56c9-43d5-998b-f9f5e9672a56|1  |2017-07-09|
|advertising_id_s=242fc1fd-76c9-4fcc-8e72-5283a0de1a31|1  |2017-07-09|
|advertising_id_s=29fcef85-d840-4bb9-b299-5d3ab5589947|1  |2017-07-09|
|advertising_id_s=2b267eea-a3df-4dbb-bd22-35a880792ef5|1  |2017-07-09|
|advertising_id_s=2e79f8c2-71fc-4a6c-9ff7-ab89900bf09f|1  |2017-07-09|
|advertising_id_s=39b433e5-d2f8-43b7-a8fd-393f199c2ebb|1  |2017-07-09|
|advertising_id_s=3a466e8c-5353-49c6-8844-c8fe2bef4bc7|1  |2017-07-09|
|advertising_id_s=3b11f81f-6266-44b9-9b5b-cee990c5d73c|1  |2017-07-09|
|advertising_id_s=3bb25ac3-c539-4302-a897-3ee83d83e7b5|1  |2017-07-09|
+-----------------------------------------------------+---+----------+

aggregatedDF
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+----------+
|base_record           |features                                                                                                                                                                                  |dt        |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+----------+
|[2017-07-16-1000091,0]|[[android_serial_s=missing,1.0], [android_id_s=unique,1.0], [cpu_serial_s=missing,1.0], [mac_addr_s=unique,1.0], [imei_s=unique,1.0], [advertising_id_s=unique,1.0]]                      |2017-07-16|
|[2017-07-16-1000253,0]|[[advertising_id_s=unique,1.0], [android_id_s=unique,1.0], [mac_addr_s=unique,1.0], [imei_s=unique,1.0], [android_serial_s=unique,1.0], [cpu_serial_s=unique,1.0]]                        |2017-07-16|
|[2017-07-16-1000415,0]|[[mac_addr_s=unique,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=unique,1.0], [imei_s=unique,1.0], [android_id_s=unique,1.0], [android_serial_s=unique,1.0]]                       |2017-07-16|
|[2017-07-16-1000866,0]|[[imei_s=unique,1.0], [android_serial_s=missing,1.0], [mac_addr_s=unique,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=unique,1.0], [android_id_s=unique,1.0]]                      |2017-07-16|
|[2017-07-16-1000910,0]|[[mac_addr_s=missing,1.0], [android_serial_s=unique,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=unique,1.0], [imei_s=unique,1.0], [android_id_s=unique,1.0]]                      |2017-07-16|
|[2017-07-16-10011,1]  |[[mac_addr_s=duplicated,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0], [imei_s=duplicated,1.0], [android_serial_s=duplicated,1.0]]   |2017-07-16|
|[2017-07-16-1001144,1]|[[mac_addr_s=duplicated,1.0], [imei_s=duplicated,1.0], [cpu_serial_s=missing,1.0], [android_serial_s=duplicated,1.0], [advertising_id_s=duplicated,1.0], [android_id_s=duplicated,1.0]]   |2017-07-16|
|[2017-07-16-100127,0] |[[mac_addr_s=unique,1.0], [android_serial_s=unique,1.0], [android_id_s=unique,1.0], [imei_s=unique,1.0], [cpu_serial_s=unique,1.0], [advertising_id_s=unique,1.0]]                        |2017-07-16|
|[2017-07-16-1001306,1]|[[cpu_serial_s=duplicated,1.0], [advertising_id_s=duplicated,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0], [android_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0]]|2017-07-16|
|[2017-07-16-1001595,0]|[[android_serial_s=missing,1.0], [mac_addr_s=unique,1.0], [cpu_serial_s=missing,1.0], [android_id_s=unique,1.0], [advertising_id_s=missing,1.0], [imei_s=unique,1.0]]                     |2017-07-16|
|[2017-07-16-1001757,0]|[[mac_addr_s=missing,1.0], [advertising_id_s=unique,1.0], [cpu_serial_s=missing,1.0], [imei_s=unique,1.0], [android_id_s=unique,1.0], [android_serial_s=unique,1.0]]                      |2017-07-16|
|[2017-07-16-1001801,1]|[[mac_addr_s=missing,1.0], [imei_s=unique,1.0], [android_serial_s=missing,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=missing,1.0], [android_id_s=duplicated,1.0]]                |2017-07-16|
|[2017-07-16-1001919,1]|[[android_serial_s=missing,1.0], [advertising_id_s=unique,1.0], [cpu_serial_s=missing,1.0], [android_id_s=unique,1.0], [imei_s=duplicated,1.0], [mac_addr_s=duplicated,1.0]]              |2017-07-16|
|[2017-07-16-1002035,0]|[[mac_addr_s=missing,1.0], [cpu_serial_s=missing,1.0], [android_id_s=unique,1.0], [android_serial_s=unique,1.0], [imei_s=unique,1.0], [advertising_id_s=unique,1.0]]                      |2017-07-16|
|[2017-07-16-1002486,1]|[[mac_addr_s=missing,1.0], [advertising_id_s=duplicated,1.0], [cpu_serial_s=missing,1.0], [android_serial_s=duplicated,1.0], [imei_s=duplicated,1.0], [android_id_s=duplicated,1.0]]      |2017-07-16|
|[2017-07-16-1002530,1]|[[mac_addr_s=missing,1.0], [android_serial_s=missing,1.0], [cpu_serial_s=missing,1.0], [imei_s=duplicated,1.0], [advertising_id_s=missing,1.0], [android_id_s=duplicated,1.0]]            |2017-07-16|
|[2017-07-16-1002648,0]|[[mac_addr_s=missing,1.0], [android_serial_s=missing,1.0], [cpu_serial_s=missing,1.0], [imei_s=unique,1.0], [advertising_id_s=unique,1.0], [android_id_s=unique,1.0]]                     |2017-07-16|
|[2017-07-16-1002981,1]|[[advertising_id_s=duplicated,1.0], [mac_addr_s=missing,1.0], [android_serial_s=missing,1.0], [cpu_serial_s=missing,1.0], [imei_s=duplicated,1.0], [android_id_s=duplicated,1.0]]         |2017-07-16|
|[2017-07-16-1003377,0]|[[imei_s=unique,1.0], [android_id_s=unique,1.0], [cpu_serial_s=missing,1.0], [android_serial_s=unique,1.0], [advertising_id_s=missing,1.0], [mac_addr_s=unique,1.0]]                      |2017-07-16|
|[2017-07-16-1003421,1]|[[mac_addr_s=missing,1.0], [android_id_s=duplicated,1.0], [cpu_serial_s=missing,1.0], [advertising_id_s=duplicated,1.0], [android_serial_s=duplicated,1.0], [imei_s=unique,1.0]]          |2017-07-16|
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+----------+


PNCountsFromRDD
+-------------------------------+
|result                         |
+-------------------------------+
|Map(0 -> 9131252, 1 -> 8328912)|
+-------------------------------+


featureAndParamsDF
+-----+---------------------------+-------------------+
|index|feature                    |params             |
+-----+---------------------------+-------------------+
|15   |android_id_s=unique        |-2.6914715015823463|
|16   |imei_s=unique              |-2.296528190190276 |
|7    |android_serial_s=unique    |-1.999592316537245 |
|11   |imei_s=missing             |-1.4071722708719012|
|9    |advertising_id_s=unique    |-1.3634712672415275|
|8    |android_serial_s=missing   |-1.153451027867617 |
|17   |mac_addr_s=unique          |-1.1425474009995664|
|12   |advertising_id_s=missing   |-1.1274500305243433|
|14   |mac_addr_s=missing         |-1.072556046689999 |
|3    |cpu_serial_s=unique        |-0.9458146838305792|
|1    |android_id_s=missing       |-0.8641588823625385|
|13   |cpu_serial_s=missing       |-0.6452263768297493|
|5    |advertising_id_s=duplicated|1.9253068467446786 |
|4    |mac_addr_s=duplicated      |2.183394004616084  |
|2    |cpu_serial_s=duplicated    |2.308567862607905  |
|0    |imei_s=duplicated          |2.4846936110063447 |
|6    |android_id_s=duplicated    |2.694814247307943  |
|10   |android_serial_s=duplicated|2.9618434510344827 |
+-----+---------------------------+-------------------+

3492032

0720 featureAndParamsDF
+-----+---------------------------+-------------------+
|index|feature                    |params             |
+-----+---------------------------+-------------------+
|15   |android_id_s=unique        |-2.8508631855490147|
|13   |imei_s=unique              |-2.3120884883046626|
|1    |android_serial_s=unique    |-1.907276172189957 |
|4    |imei_s=missing             |-1.4230134182297711|
|6    |advertising_id_s=unique    |-1.3819701626245107|
|11   |mac_addr_s=missing         |-1.1612306013752356|
|14   |mac_addr_s=unique          |-1.1589318181152497|
|2    |advertising_id_s=missing   |-1.1072893818470688|
|16   |android_serial_s=missing   |-1.0869471819158187|
|12   |cpu_serial_s=unique        |-1.0781842752449544|
|0    |android_id_s=missing       |-1.0525912631809602|
|3    |cpu_serial_s=missing       |-0.6707466365925095|
|7    |advertising_id_s=duplicated|1.9294434388047048 |
|5    |mac_addr_s=duplicated      |2.2781759662556396 |
|10   |cpu_serial_s=duplicated    |2.4757134578957314 |
|8    |imei_s=duplicated          |2.499553463110779  |
|9    |android_id_s=duplicated    |2.8557297106155897 |
|17   |android_serial_s=duplicated|3.1198984435424895 |
+-----+---------------------------+-------------------+

// ***************************各个DF示例*****************************
+-----+---------------------------+-------------------+
|index|feature                    |params             |
+-----+---------------------------+-------------------+
|0    |advertising_id_s=unique    |-6.203745372153021 |
|3    |imei_s=unique              |-3.0671387734730753|
|5    |android_serial_s=unique    |-3.025165785917253 |
|4    |mac_addr_s=unique          |-2.5985474737131855|
|2    |android_id_s=unique        |-0.7831371005314914|
|8    |android_id_s=duplicated    |0.7831340494723987 |
|7    |mac_addr_s=duplicated      |2.5985445708047252 |
|9    |android_serial_s=duplicated|3.025162974945398  |
|1    |imei_s=duplicated          |3.0671358567737403 |
|6    |advertising_id_s=duplicated|6.203742502549923  |
+-----+---------------------------+-------------------+

// *******MINGLI
+----------------+--------------------+---------------+-------------+--------------+--------------------+---------------+---------+--------------+
|    android_id_s|ori_advertising_id_s|     ori_imei_s|ori_country_s|   ori_model_s|    advertising_id_s|         imei_s|country_s|       model_s|
+----------------+--------------------+---------------+-------------+--------------+--------------------+---------------+---------+--------------+
0701：1114111
安卓ID相同
android_id_s 44/1,099,032 = 4.003523100328289E-5  万分之四
imei        2223(四个不同)/1060947 = 0.0020952978801014564   72,285(adv anid 不同)
advertising_id_s  37/981473 = 3.769843897896325E-5
android_serial_s   2341/914599 = 0.002559591689910004
+--------------------+  
|    advertising_id_s|
+--------------------+
|1d7eb7cf-1d4c-428...|
|         flash+share|
|ec46bd96-3bf4-43f...|
|3b72b1df-71da-454...|
|b345c5ae-d538-461...|
|20e4b171-d08d-490...|
|38e952f4-57c8-4b9...|
|d6ca8481-07f6-461...|
|                 TCL|
|      Acer marketing|
|                 N/A|
|bb88d4c5-a3a0-416...|
|aa52d76a-40f8-40a...|
|                  \N|
|c91a955c-a625-4fa...|
|7dbd24ee-2d7d-4eb...|
|4895878e-19cd-469...|
|a5b9ded1-debf-4da...|
|7e0e9ea7-d11b-4c8...|
|flash share lates...|
|1c587d8b-d599-468...|
|feccb28b-3f8a-485...|
|2dddcabf-ec4a-457...|
|53e26356-40f2-475...|
|06e82984-3a80-693...|
|2988e1c7-7312-407...|
|d77fcc62-09ba-49f...|
|2cd334f5-4698-48b...|
|309e0592-0551-413...|
|eee461ea-d34e-480...|
|           test_term|
|         flash share|
|e6afecaa-f1b6-4fb...|
|     flash share apk|
|             KEYWORD|
|181cec6f-be83-4ef...|
|94b21a84-2ad7-4df...|
+--------------------+

0701一天中上报激活的用户里，重复出现的android_serial及其出现次数
(提取感兴趣的字段并去重后的结果）
+--------------------+------+   
|    android_serial_s| count|   
+--------------------+------+   
|    0123456789ABCDEF|173901|   
|             unknown|  5952|   
|              LS5002|   633|   
|     0123456789ABCDE|   612|   
|    H352C80123456789|   350|   
|        LenovoTAB2A8|   307|   
|     0123456789INTEX|   303|   
|01234567890123456...|   299|   
|      HT49Q0FMB00J7D|   220|   
|    0123456789AACDEF|   212|   
|            134699cc|   209|   
|                  Y6|   205|   
|    H533X60001234567|   179|   
|    ET2015A336240000|   172|   
|            Micromax|   168|   
|      00000000000000|   153|   
|                   F|   138|   
|                  \N|   135|   
|          0123456789|   116|   
|      12345678901234|   104|   

+--------------------+------+
|    advertising_id_s| count|
+--------------------+------+
|                  \N|124887|
|                 N/A|  7134|
|                 TCL|    46|
|37c74cae-ab8c-4a3...|    26|
|dbb0015c-1896-4d6...|    17|
|61e4129c-7718-47c...|     9|
|280f13da-03b5-4b1...|     8|
|3790a760-31e5-443...|     7|
|eba2a5bb-2e49-448...|     6|
|ba34c39c-2f7c-403...|     5|
|39a2d27f-bb95-447...|     5|
|e0bcb618-fe52-48a...|     5|
|15510c34-6d26-41b...|     4|
|2bd275e6-d35e-437...|     4|
|4d9df67c-7ba6-4b1...|     4|
|b809a3a1-c846-41d...|     4|
|             KEYWORD|     4|
|c7091895-a9a6-4b1...|     3|
|d4c8811c-5fb0-46c...|     3|
|f82df977-fc53-446...|     3|
+--------------------+------+

+----------------+-----+
|    android_id_s|count|
+----------------+-----+
|              \N|  140|
|2f95c9206ee13fc3|   90|
|1f2003e1e02dbd36|   87|
|a603292c3f3c9b9e|   83|
|        00000000|   75|
|ac0b17515c518145|   63|
|E80C36DB1A858791|   52|
|5bddbdcc907ffa60|   42|
|95f97e9c963fd67c|   35|
| 7e1e262f4f47538|   31|
|9982d996d8c4e7ff|   31|
|f7f18e88811825d9|   30|
|4ca031e098bf9284|   26|
|71a5de471a63770e|   25|
|         ZEUZ-HD|   18|
|e3d0eeb22847711a|   16|
|7a81041c677b7b7f|   16|
|1efd719a5011bcdb|   14|
|e78ffe4c50967b89|   13|
|ae5656c94a150dba|   13|
+----------------+-----+

+--------------------+-----+
|              imei_s|count|
+--------------------+-----+
|                  \N|33569|
|     012345678912345| 1095|
|     352273017386340|  950|
|     000000000000000|  358|
|     355543060656972|  196|
|     352005048247251|  192|
|     004999010640000|  174|
|                   0|  169|
|     868969010014527|  164|
|     359454784981884|  112|
|     353919025680130|   98|
|     353774052244889|   91|
|     861622010000056|   91|
|     353697060119399|   82|
|     357360072412323|   75|
|     352695081283484|   63|
|     358688000000158|   57|
|     302327803152356|   52|
|["\f\f\f\f\f\f\f\f"]|   48|
|        ["\b\b\b\b"]|   47|
+--------------------+-----+


0630 - 使用新标注规则的新模型
0 -> 21,026,282
1 -> 11,282,518
5 -> 3,035,030

percentile 0.97

+-----+---------------------------+--------------------+
|index|feature                    |params              |
+-----+---------------------------+--------------------+
|7    |android_id_s=unique        |-3.3669295817292353 |
|2    |advertising_id_s=unique    |-2.241788558542362  |
|17   |imei_s=unique              |-1.125864179536004  |
|15   |android_serial_s=unique    |-0.7505583044159055 |
|3    |cpu_serial_s=unique        |-0.5219103140423186 |
|11   |mac_addr_s=unique          |-0.2294527644760933 |
|0    |mac_addr_s=missing         |-0.15742051924718026|
|1    |android_serial_s=missing   |0.16580822789209554 |
|13   |cpu_serial_s=missing       |0.21752551386089913 |
|14   |cpu_serial_s=duplicated    |0.389435111661707   |
|4    |mac_addr_s=duplicated      |0.45454107479294115 |
|6    |imei_s=missing             |0.7956276263081885  |
|16   |android_serial_s=duplicated|0.7980357300830202  |
|8    |imei_s=duplicated          |1.0606189024390806  |
|10   |advertising_id_s=missing   |1.3917478395680354  |
|5    |advertising_id_s=duplicated|2.129941575689088   |
|12   |android_id_s=missing       |3.098268939130063   |
|9    |android_id_s=duplicated    |3.365322655175055   |
+-----+---------------------------+--------------------+


 使用了4种标记，missing被细分为了missing和white

+-----+---------------------------+--------------------+
|index|feature                    |params              |
+-----+---------------------------+--------------------+
|3    |android_id_s=unique        |-3.3797303112257358 |
|0    |advertising_id_s=unique    |-2.087540335258679  |
|12   |imei_s=unique              |-1.0915832623794337 |
|4    |android_serial_s=unique    |-0.7841258121446145 |
|22   |cpu_serial_s=unique        |-0.42904244304980604|
|14   |mac_addr_s=unique          |-0.30598480878202006|
|5    |mac_addr_s=white           |-0.14191259912784532|
|9    |android_serial_s=missing   |0.01650735299764783 |
|11   |cpu_serial_s=white         |0.05891451784350258 |
|1    |cpu_serial_s=missing       |0.07217282430247887 |
|20   |mac_addr_s=missing         |0.07506204716000968 |
|21   |imei_s=missing             |0.14983721318127755 |
|10   |android_serial_s=white     |0.24830485184998574 |
|19   |cpu_serial_s=duplicated    |0.3305161289415698  |
|15   |mac_addr_s=duplicated      |0.479403775544295   |
|8    |android_serial_s=duplicated|0.8149733907187874  |
|7    |imei_s=duplicated          |1.0804882227965633  |
|6    |advertising_id_s=white     |1.2898275554296728  |
|23   |advertising_id_s=missing   |1.4398021409016675  |
|2    |advertising_id_s=duplicated|1.9511419652791016  |
|17   |imei_s=white               |2.2564078089329165  |
|13   |android_id_s=duplicated    |3.378231724730135   |
|16   |android_id_s=missing       |3.671570980592566   |
|18   |android_id_s=white         |3.9270227195284444  |
+-----+---------------------------+--------------------+

+---------------------------+--------------------+--------+---------+---------+--------------------+--------------------+---------------------+
|feature                    |score               |count   |pos_count|neg_count|total_ratio         |pos_ratio           |neg_ratio            |
+---------------------------+--------------------+--------+---------+---------+--------------------+--------------------+---------------------+
|mac_addr_s=missing         |25.019445185521178  |2889531 |867000   |1613426  |0.07905825730766662 |0.07381039480218175 |0.07442337917381048  |
|imei_s=missing             |209.87225378625828  |1060864 |326045   |625589   |0.029025492054053217|0.027757220499743197|0.028856884266129913 |
|advertising_id_s=white     |326.7869119470587   |2442    |273      |0        |6.681370241237138E-5|2.32413353875382E-5 |0.0                  |
|android_id_s=missing       |596.1167844308984   |2783    |498      |0        |7.614354374022504E-5|4.239628213550924E-5|0.0                  |
|imei_s=white               |3740.9802692761555  |134082  |52133    |61445    |0.003668515498302858|0.00443824372805322 |0.0028343069550972803|
|android_id_s=white         |4154.862166184032   |22598   |3471     |0        |6.182866695801673E-4|2.954969784987E-4   |0.0                  |
|android_serial_s=missing   |58309.71520348901   |1509466 |304626   |1033402  |0.041299349764779926|0.02593375470243301 |0.04766829646043518  |
|cpu_serial_s=white         |115029.19512550408  |19224571|6905954  |10367794 |0.5259888475837449  |0.5879252493952783  |0.4782408762831126   |
|mac_addr_s=white           |147036.04488999178  |8413635 |2214791  |5934121  |0.23019906023600012 |0.18855201627949128 |0.2737264288825589   |
|cpu_serial_s=missing       |159589.57542215497  |9585378 |2464837  |6575281  |0.2622582281744847  |0.2098392065663498  |0.30330156513986495  |
|android_serial_s=white     |179239.72056222052  |5605865 |2248480  |2580419  |0.15337780338817703 |0.1914200651727908  |0.11902839154959996  |
|cpu_serial_s=unique        |1629780.1356741278  |5254754 |3303     |4653182  |0.14377132268886902 |2.811946182602149E-4|0.21463985850652573  |
|advertising_id_s=missing   |1852030.708342774   |4657186 |1547199  |0        |0.12742171969003366 |0.131717842015618   |0.0                  |
|cpu_serial_s=duplicated    |2641108.758652607   |2484686 |2372219  |82764    |0.06798160155290148 |0.20195434942011165 |0.003817700070496726 |
|mac_addr_s=unique          |4034796.799442842   |15833849|405630   |13287018 |0.4332178849829747  |0.0345325379972422  |0.6128975104549232   |
|android_serial_s=unique    |5723235.7322711395  |19998742|301891   |17661201 |0.547170350782061   |0.02570091568307434 |0.8146678302493456   |
|imei_s=unique              |6924902.865579629   |23402311|163801   |20463410 |0.6402928103668163  |0.013944886365619578|0.943926849833302    |
|advertising_id_s=unique    |7055570.412943526   |22039544|351078   |21679021 |0.6030071802294698  |0.029888357308374126|1.0                  |
|android_id_s=unique        |7593148.583560198   |24793760|15354    |21679021 |0.6783631868647654  |0.001307133566081544|1.0                  |
|mac_addr_s=duplicated      |8002753.744969134   |9412374 |8258892  |844456   |0.2575247974733586  |0.7031050509210848  |0.038952681488707445 |
|android_serial_s=duplicated|9685416.019039545   |9435316 |8891316  |403999   |0.2581524960649821  |0.7569452644417018  |0.01863548174061919  |
|advertising_id_s=duplicated|1.1787985569071442E7|9850217 |9847763  |0        |0.2695042863780842  |0.8383705593406203  |0.0                  |
|imei_s=duplicated          |1.216122461099077E7 |11952132|11204334 |528577   |0.3270131820808277  |0.953859649406584   |0.024381958945470832 |
|android_id_s=duplicated    |1.4037460983641166E7|11730248|11726990 |0        |0.32094238292191424 |0.9983549731732843  |0.0                  |
+---------------------------+--------------------+--------+---------+---------+--------------------+--------------------+---------------------+




 ------ >  cross 
+-----+--------------------------------------------------------+---------------------+
|index|feature                                                 |params               |
+-----+--------------------------------------------------------+---------------------+
|41   |android_id_s=unique&&imei_s=duplicated                  |-2.016020394940658   |
|63   |android_serial_s=duplicated&&android_id_s=unique        |-1.6509038312433981  |
|222  |cpu_serial_s=duplicated&&android_id_s=unique            |-1.2942822782397463  |
|184  |mac_addr_s=duplicated&&android_id_s=unique              |-1.0702555848785054  |
|81   |android_id_s=unique&&advertising_id_s=unique            |-0.7227672194103784  |
|56   |android_id_s=unique                                     |-0.7117991824301861  |
|16   |advertising_id_s=unique                                 |-0.5990192217962755  |
|243  |imei_s=white&&advertising_id_s=unique                   |-0.570074698351372   |
|162  |imei_s=missing&&android_id_s=unique                     |-0.5280801681259294  |
|38   |android_serial_s=white&&android_id_s=unique             |-0.5187215466172043  |
|246  |imei_s=unique&&android_id_s=unique                      |-0.5049460283796517  |
|200  |imei_s=unique&&advertising_id_s=unique                  |-0.4927159743576426  |
|69   |imei_s=unique                                           |-0.4736531770981081  |
|60   |android_id_s=unique&&android_serial_s=missing           |-0.45846537511237423 |
|119  |imei_s=missing&&advertising_id_s=unique                 |-0.43812900099707835 |
|197  |mac_addr_s=missing&&android_id_s=unique                 |-0.4200992244039169  |
|14   |cpu_serial_s=white&&android_id_s=unique                 |-0.41579957570217646 |
|116  |android_serial_s=unique&&imei_s=white                   |-0.40030644545120764 |
|58   |cpu_serial_s=unique&&android_serial_s=duplicated        |-0.38917738747479824 |
|228  |android_serial_s=unique&&android_id_s=unique            |-0.38466895476406787 |
|183  |android_serial_s=unique&&advertising_id_s=unique        |-0.38080154627204105 |
|26   |imei_s=white&&android_id_s=unique                       |-0.37273657842144253 |
|85   |android_serial_s=unique                                 |-0.37258022300892335 |
|0    |android_serial_s=white&&advertising_id_s=unique         |-0.36349747000868976 |
|241  |android_serial_s=unique&&imei_s=unique                  |-0.35278974365846844 |
|72   |cpu_serial_s=unique&&android_serial_s=white             |-0.3501149829015659  |
|107  |android_serial_s=unique&&imei_s=missing                 |-0.3472705562576842  |
|25   |android_serial_s=duplicated&&advertising_id_s=unique    |-0.345132196760889   |
|19   |advertising_id_s=unique&&android_serial_s=missing       |-0.3394071570428905  |
|53   |mac_addr_s=white&&android_id_s=unique                   |-0.32099837716224355 |
|227  |cpu_serial_s=white&&advertising_id_s=unique             |-0.31928704585464296 |
|213  |advertising_id_s=unique&&imei_s=duplicated              |-0.3185065293031847  |
|83   |imei_s=unique&&android_serial_s=white                   |-0.31635657106581105 |
|144  |mac_addr_s=missing&&advertising_id_s=unique             |-0.3011049399415313  |
|87   |mac_addr_s=unique&&android_id_s=unique                  |-0.29535400818375745 |
|73   |imei_s=unique&&android_serial_s=missing                 |-0.2928757184519573  |
|10   |mac_addr_s=white&&advertising_id_s=unique               |-0.2928511227590302  |
|92   |cpu_serial_s=unique&&imei_s=white                       |-0.2901301432886395  |
|210  |mac_addr_s=missing&&imei_s=unique                       |-0.28945564832631987 |
|42   |mac_addr_s=unique&&advertising_id_s=unique              |-0.28661222653115825 |
|171  |cpu_serial_s=white&&android_serial_s=missing            |-0.28472143833226843 |
|148  |cpu_serial_s=missing&&android_id_s=unique               |-0.28236298199262605 |
|77   |cpu_serial_s=unique&&imei_s=missing                     |-0.2738401065526269  |
|135  |mac_addr_s=duplicated&&advertising_id_s=unique          |-0.27356313582337405 |
|59   |cpu_serial_s=unique&&android_serial_s=missing           |-0.2647210962935634  |
|244  |imei_s=unique&&cpu_serial_s=white                       |-0.26380894358799056 |
|28   |imei_s=unique&&mac_addr_s=unique                        |-0.2603542910034927  |
|211  |imei_s=missing&&mac_addr_s=unique                       |-0.2601243195273384  |
|198  |mac_addr_s=missing&&cpu_serial_s=unique                 |-0.2570672404818508  |
|111  |cpu_serial_s=missing&&advertising_id_s=unique           |-0.25634529130597355 |
|43   |cpu_serial_s=unique&&imei_s=duplicated                  |-0.24849711227899668 |
|99   |mac_addr_s=unique                                       |-0.24711884143420457 |
|204  |imei_s=unique&&mac_addr_s=white                         |-0.24671045578708245 |
|216  |android_serial_s=unique&&mac_addr_s=white               |-0.24429150953964085 |
|161  |cpu_serial_s=missing&&imei_s=unique                     |-0.22988757605977442 |
|11   |android_serial_s=unique&&mac_addr_s=unique              |-0.2296718348806841  |
|248  |android_serial_s=unique&&cpu_serial_s=missing           |-0.22236162218495426 |
|1    |android_serial_s=unique&&cpu_serial_s=white             |-0.21242651364452164 |
|207  |android_serial_s=unique&&mac_addr_s=missing             |-0.20495625553074515 |
|123  |cpu_serial_s=unique&&android_id_s=unique                |-0.20422856934929842 |
|82   |cpu_serial_s=unique&&advertising_id_s=unique            |-0.2031925023689035  |
|57   |cpu_serial_s=unique                                     |-0.2026007212745187  |
|196  |mac_addr_s=duplicated&&imei_s=unique                    |-0.20185103637278737 |
|226  |android_serial_s=unique&&cpu_serial_s=unique            |-0.19810898858902604 |
|193  |cpu_serial_s=unique&&mac_addr_s=white                   |-0.19660664680042286 |
|133  |cpu_serial_s=unique&&imei_s=unique                      |-0.19309900287714152 |
|104  |mac_addr_s=unique&&android_serial_s=missing             |-0.19179108665166567 |
|80   |android_serial_s=white&&mac_addr_s=unique               |-0.18917165964388102 |
|163  |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.188519690779755   |
|51   |cpu_serial_s=white&&mac_addr_s=unique                   |-0.17375483590057392 |
|195  |cpu_serial_s=missing&&mac_addr_s=unique                 |-0.16324817493922628 |
|45   |android_serial_s=white&&imei_s=white                    |-0.1555215198098542  |
|182  |cpu_serial_s=duplicated&&advertising_id_s=unique        |-0.11025844497474263 |
|157  |imei_s=white&&mac_addr_s=white                          |-0.10833675532581938 |
|191  |cpu_serial_s=missing&&imei_s=white                      |-0.10769073226079742 |
|127  |mac_addr_s=white                                        |-0.06394524848551492 |
|33   |cpu_serial_s=missing&&mac_addr_s=white                  |-0.05589577990375002 |
|166  |android_serial_s=unique&&mac_addr_s=duplicated          |-0.05120705129208344 |
|118  |mac_addr_s=missing&&android_serial_s=missing            |-0.04315941755967603 |
|20   |imei_s=missing                                          |-0.03879767233604475 |
|152  |cpu_serial_s=missing                                    |-0.035031233436992555|
|44   |mac_addr_s=white&&cpu_serial_s=white                    |-0.029854140913109475|
|190  |imei_s=missing&&cpu_serial_s=white                      |-0.014963227173893206|
|30   |android_serial_s=white&&imei_s=missing                  |-0.01496279622053223 |
|178  |cpu_serial_s=missing&&imei_s=missing                    |-0.01247728576115844 |
|146  |imei_s=missing&&mac_addr_s=white                        |-0.006931023380131726|
|22   |cpu_serial_s=missing&&mac_addr_s=missing                |-0.006668374920373425|
|225  |android_id_s=white&&advertising_id_s=unique             |0.0                  |
|109  |android_id_s=missing&&advertising_id_s=unique           |0.0                  |
|113  |advertising_id_s=missing&&android_id_s=unique           |0.0                  |
|250  |advertising_id_s=missing&&android_id_s=white            |0.0                  |
|140  |cpu_serial_s=unique&&advertising_id_s=white             |0.0                  |
|230  |advertising_id_s=white&&android_id_s=unique             |0.0                  |
|252  |mac_addr_s=unique&&cpu_serial_s=duplicated              |0.003516155446669953 |
|66   |cpu_serial_s=unique&&mac_addr_s=duplicated              |0.0037467574439012273|
|6    |android_serial_s=missing                                |0.009194695156684045 |
|137  |mac_addr_s=missing&&imei_s=missing                      |0.009817299377777202 |
|143  |mac_addr_s=missing&&imei_s=white                        |0.012241486958144584 |
|62   |imei_s=white&&mac_addr_s=unique                         |0.014604629619984683 |
|141  |android_serial_s=white&&mac_addr_s=white                |0.021220476704549962 |
|169  |cpu_serial_s=missing&&android_serial_s=white            |0.0296400813327993   |
|167  |cpu_serial_s=white                                      |0.03458955393526524  |
|24   |imei_s=missing&&android_serial_s=missing                |0.034635429632943145 |
|155  |cpu_serial_s=missing&&android_serial_s=missing          |0.03792034291970247  |
|115  |mac_addr_s=missing                                      |0.04627873504910834  |
|15   |android_serial_s=white                                  |0.048081098234199364 |
|188  |android_serial_s=white&&cpu_serial_s=white              |0.04966080770374783  |
|29   |imei_s=white                                            |0.06083219215320038  |
|35   |mac_addr_s=missing&&cpu_serial_s=white                  |0.06581934817677625  |
|130  |mac_addr_s=missing&&android_serial_s=white              |0.07279285728143567  |
|202  |imei_s=white&&cpu_serial_s=white                        |0.11373681216779684  |
|34   |mac_addr_s=duplicated&&imei_s=white                     |0.17675021033479096  |
|142  |imei_s=unique&&cpu_serial_s=duplicated                  |0.18931044492684235  |
|187  |imei_s=white&&cpu_serial_s=duplicated                   |0.2594359514609579   |
|65   |imei_s=missing&&cpu_serial_s=duplicated                 |0.2750121362287129   |
|18   |mac_addr_s=duplicated&&android_serial_s=white           |0.28030930653070435  |
|168  |mac_addr_s=duplicated&&cpu_serial_s=white               |0.28309390738435647  |
|97   |cpu_serial_s=missing&&mac_addr_s=duplicated             |0.29864675888431225  |
|128  |android_serial_s=unique&&cpu_serial_s=duplicated        |0.3073953629661741   |
|215  |mac_addr_s=white&&cpu_serial_s=duplicated               |0.31376387646720016  |
|9    |mac_addr_s=duplicated&&android_serial_s=missing         |0.3162955238667519   |
|229  |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.32683669017196004  |
|221  |cpu_serial_s=duplicated&&android_serial_s=missing       |0.3268581043355739   |
|23   |mac_addr_s=duplicated&&imei_s=missing                   |0.32768361278831354  |
|218  |cpu_serial_s=duplicated                                 |0.32985236648547445  |
|132  |cpu_serial_s=duplicated&&imei_s=duplicated              |0.33190539034455824  |
|156  |android_serial_s=duplicated&&cpu_serial_s=white         |0.3336486195502792   |
|88   |mac_addr_s=duplicated&&cpu_serial_s=duplicated          |0.3351699024696673   |
|40   |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.33885589480614264  |
|122  |mac_addr_s=missing&&android_serial_s=duplicated         |0.3415740971700163   |
|114  |mac_addr_s=duplicated&&android_serial_s=duplicated      |0.34194753882005496  |
|5    |mac_addr_s=duplicated                                   |0.3558330541791989   |
|100  |mac_addr_s=missing&&cpu_serial_s=duplicated             |0.35690486104771857  |
|203  |android_id_s=white&&android_serial_s=duplicated         |0.37428202808675126  |
|17   |advertising_id_s=missing&&cpu_serial_s=duplicated       |0.38455871071644077  |
|96   |mac_addr_s=duplicated&&imei_s=duplicated                |0.386081396709401    |
|105  |android_serial_s=duplicated&&mac_addr_s=unique          |0.3876907958185493   |
|124  |android_id_s=duplicated&&cpu_serial_s=duplicated        |0.38841555796447125  |
|125  |advertising_id_s=white&&cpu_serial_s=duplicated         |0.3886075613300932   |
|138  |android_serial_s=unique&&imei_s=duplicated              |0.3886800354105389   |
|90   |cpu_serial_s=missing&&android_serial_s=duplicated       |0.3893313761446683   |
|189  |android_id_s=white&&imei_s=duplicated                   |0.3913241629168363   |
|205  |android_serial_s=white&&cpu_serial_s=duplicated         |0.3924430427492307   |
|37   |advertising_id_s=missing&&android_serial_s=duplicated   |0.3950518491278716   |
|186  |cpu_serial_s=white&&imei_s=duplicated                   |0.3982521640336647   |
|164  |advertising_id_s=white&&mac_addr_s=duplicated           |0.39855924570539075  |
|120  |android_serial_s=duplicated&&mac_addr_s=white           |0.39942546761424635  |
|240  |android_serial_s=duplicated&&imei_s=duplicated          |0.40055802227437093  |
|39   |mac_addr_s=duplicated&&advertising_id_s=duplicated      |0.4045441430587377   |
|67   |cpu_serial_s=missing&&imei_s=duplicated                 |0.40768278188620627  |
|54   |advertising_id_s=missing&&mac_addr_s=duplicated         |0.40925046250083624  |
|231  |mac_addr_s=white&&imei_s=duplicated                     |0.40944870395260197  |
|174  |android_id_s=white&&cpu_serial_s=duplicated             |0.41210609059680053  |
|153  |advertising_id_s=white&&android_serial_s=duplicated     |0.4189031892846951   |
|251  |android_serial_s=duplicated                             |0.42417987379603517  |
|139  |advertising_id_s=white&&imei_s=duplicated               |0.4309982258644917   |
|110  |mac_addr_s=missing&&imei_s=duplicated                   |0.44796357051233754  |
|47   |android_id_s=duplicated&&advertising_id_s=white         |0.4484832927172136   |
|84   |advertising_id_s=white                                  |0.4484832927172136   |
|27   |advertising_id_s=missing&&imei_s=duplicated             |0.44904149832988505  |
|4    |mac_addr_s=unique&&imei_s=duplicated                    |0.4522627099482247   |
|68   |advertising_id_s=duplicated&&android_serial_s=duplicated|0.45277774405883126  |
|3    |advertising_id_s=white&&cpu_serial_s=white              |0.45807009918517944  |
|64   |android_id_s=missing&&imei_s=duplicated                 |0.45893157034984244  |
|101  |android_serial_s=missing&&imei_s=duplicated             |0.4613396760518522   |
|214  |android_serial_s=white&&imei_s=duplicated               |0.46281913067533653  |
|173  |mac_addr_s=duplicated&&android_id_s=white               |0.46877177205685844  |
|145  |android_serial_s=duplicated&&android_id_s=missing       |0.47147315503823745  |
|36   |android_id_s=duplicated&&advertising_id_s=missing       |0.4723412832514664   |
|70   |advertising_id_s=missing                                |0.4723446464689462   |
|93   |advertising_id_s=duplicated&&cpu_serial_s=white         |0.472632796164921    |
|154  |mac_addr_s=duplicated&&android_id_s=missing             |0.4775816547173625   |
|170  |imei_s=unique&&android_serial_s=duplicated              |0.47823003282533816  |
|13   |cpu_serial_s=missing&&advertising_id_s=duplicated       |0.481415453213764    |
|55   |android_id_s=missing&&cpu_serial_s=duplicated           |0.4858805066300731   |
|254  |advertising_id_s=missing&&cpu_serial_s=white            |0.48658530238626696  |
|235  |advertising_id_s=missing&&android_id_s=missing          |0.491510220920231    |
|233  |imei_s=duplicated                                       |0.4959231797305072   |
|50   |mac_addr_s=missing&&advertising_id_s=duplicated         |0.5013973298811338   |
|98   |imei_s=missing&&android_serial_s=duplicated             |0.5025673275674044   |
|238  |advertising_id_s=missing&&cpu_serial_s=missing          |0.5061326914109727   |
|52   |advertising_id_s=duplicated&&imei_s=duplicated          |0.5064581327191086   |
|160  |android_id_s=duplicated&&mac_addr_s=duplicated          |0.5232614120475778   |
|121  |android_id_s=duplicated&&cpu_serial_s=missing           |0.5335167704394965   |
|79   |android_id_s=duplicated&&advertising_id_s=duplicated    |0.5364274774906244   |
|74   |cpu_serial_s=white&&android_id_s=missing                |0.5369281369746078   |
|208  |mac_addr_s=missing&&advertising_id_s=white              |0.5386546500261283   |
|149  |android_id_s=duplicated&&android_serial_s=duplicated    |0.5414740656754998   |
|175  |advertising_id_s=duplicated                             |0.5487603414154022   |
|48   |advertising_id_s=duplicated&&mac_addr_s=white           |0.5514451416462387   |
|151  |android_serial_s=white&&advertising_id_s=duplicated     |0.5645253826130999   |
|220  |advertising_id_s=white&&mac_addr_s=white                |0.564771191685152    |
|194  |advertising_id_s=missing&&mac_addr_s=missing            |0.5647826229580701   |
|249  |cpu_serial_s=missing&&advertising_id_s=white            |0.5688397747200756   |
|181  |advertising_id_s=duplicated&&android_serial_s=missing   |0.581117358058695    |
|172  |android_serial_s=white&&android_id_s=missing            |0.5844076629596603   |
|185  |android_serial_s=white&&android_id_s=white              |0.5876151755827407   |
|32   |mac_addr_s=white&&android_id_s=missing                  |0.6147335765250025   |
|91   |advertising_id_s=missing&&android_serial_s=white        |0.6161989308098615   |
|95   |android_id_s=duplicated&&mac_addr_s=white               |0.6174292945100089   |
|131  |android_id_s=duplicated&&cpu_serial_s=white             |0.6204837263554975   |
|61   |cpu_serial_s=missing&&android_id_s=missing              |0.6335120540909736   |
|212  |imei_s=white&&android_serial_s=duplicated               |0.639233051756374    |
|209  |advertising_id_s=missing&&mac_addr_s=white              |0.6392444229043013   |
|75   |advertising_id_s=missing&&android_serial_s=missing      |0.6423887143196013   |
|134  |android_id_s=duplicated&&imei_s=duplicated              |0.648486961887899    |
|78   |android_id_s=duplicated&&mac_addr_s=missing             |0.6485626107472487   |
|150  |android_id_s=missing                                    |0.6505908836364385   |
|102  |advertising_id_s=white&&android_serial_s=white          |0.6724185806178598   |
|112  |advertising_id_s=missing&&cpu_serial_s=unique           |0.6745163038186685   |
|71   |cpu_serial_s=missing&&android_id_s=white                |0.6793595880434539   |
|192  |android_id_s=white&&imei_s=missing                      |0.6835438891000071   |
|94   |advertising_id_s=missing&&imei_s=missing                |0.6975849792551296   |
|219  |android_id_s=duplicated                                 |0.711536718456789    |
|76   |advertising_id_s=duplicated&&android_id_s=missing       |0.7181306640584456   |
|236  |android_id_s=duplicated&&android_serial_s=white         |0.7462472253718134   |
|129  |android_id_s=white&&advertising_id_s=duplicated         |0.7465635255104187   |
|165  |android_id_s=white                                      |0.7465635255104187   |
|158  |android_id_s=missing&&android_serial_s=missing          |0.7496684823595893   |
|89   |android_id_s=white&&cpu_serial_s=white                  |0.7503936677044015   |
|224  |android_id_s=duplicated&&android_serial_s=missing       |0.7515463991428262   |
|255  |android_id_s=duplicated&&imei_s=white                   |0.7765048583728316   |
|86   |android_serial_s=unique&&advertising_id_s=duplicated    |0.7781404571215431   |
|179  |advertising_id_s=duplicated&&mac_addr_s=unique          |0.7865162222209527   |
|199  |advertising_id_s=duplicated&&imei_s=missing             |0.7881246008864189   |
|177  |imei_s=missing&&android_id_s=missing                    |0.7941694477368613   |
|31   |mac_addr_s=missing&&android_id_s=white                  |0.8055009207569617   |
|242  |advertising_id_s=white&&imei_s=unique                   |0.8056250934552203   |
|147  |advertising_id_s=missing&&mac_addr_s=unique             |0.8224095335576955   |
|176  |android_serial_s=unique&&advertising_id_s=white         |0.8293954434510512   |
|108  |advertising_id_s=missing&&imei_s=white                  |0.8312658903599405   |
|159  |android_serial_s=unique&&advertising_id_s=missing       |0.8747172607910091   |
|201  |android_id_s=white&&imei_s=white                        |0.9072663209816102   |
|239  |android_id_s=duplicated&&imei_s=missing                 |0.9134775684211489   |
|206  |advertising_id_s=duplicated&&imei_s=white               |0.9227707813362923   |
|106  |advertising_id_s=white&&imei_s=missing                  |0.9296345431286634   |
|8    |android_id_s=duplicated&&mac_addr_s=unique              |0.9652975043978724   |
|46   |android_id_s=white&&mac_addr_s=white                    |0.9784052940348548   |
|21   |mac_addr_s=missing&&android_id_s=missing                |1.0353682208980428   |
|103  |imei_s=unique&&advertising_id_s=duplicated              |1.0781274662841935   |
|49   |android_id_s=white&&mac_addr_s=unique                   |1.089898781072052    |
|247  |cpu_serial_s=unique&&advertising_id_s=duplicated        |1.0955639712474359   |
|126  |android_serial_s=unique&&android_id_s=duplicated        |1.1025246877179429   |
|12   |advertising_id_s=white&&mac_addr_s=unique               |1.104436152116666    |
|232  |cpu_serial_s=unique&&android_id_s=white                 |1.2050989257367422   |
|117  |advertising_id_s=missing&&imei_s=unique                 |1.2450790301414838   |
|7    |mac_addr_s=unique&&android_id_s=missing                 |1.2916651216380939   |
|2    |android_serial_s=unique&&android_id_s=white             |1.3079152348666712   |
|217  |cpu_serial_s=unique&&android_id_s=missing               |1.3331847476539178   |
|253  |android_serial_s=unique&&android_id_s=missing           |1.411619883082271    |
|234  |imei_s=unique&&android_id_s=missing                     |1.4233430543395778   |
|245  |imei_s=unique&&android_id_s=white                       |1.4650680331316968   |
|237  |android_id_s=duplicated&&imei_s=unique                  |1.6692231792893235   |
|223  |android_id_s=duplicated&&cpu_serial_s=unique            |2.4030312540192043   |
|180  |android_id_s=duplicated&&advertising_id_s=unique        |2.913513275705865    |
|136  |advertising_id_s=duplicated&&android_id_s=unique        |5.759592269651444    |
+-----+--------------------------------------------------------+---------------------+


========> cross ratio
+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+
|feature                                                 |score               |count   |pos_count|neg_count|total_ratio          |pos_ratio            |neg_ratio            |
+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+
|cpu_serial_s=white&&android_serial_s=missing            |1.1256475766444043  |18      |1        |7        |5.092826668756612E-7 |8.964825342342027E-8 |3.3123611972785075E-7|
|android_serial_s=unique&&advertising_id_s=white         |1.2400119115940067  |2175    |1        |0        |6.153832224747573E-5 |8.964825342342027E-8 |0.0                  |
|cpu_serial_s=missing&&advertising_id_s=white            |1.2400119115940067  |35      |1        |0        |9.9027185225823E-7   |8.964825342342027E-8 |0.0                  |
|imei_s=missing&&android_serial_s=missing                |2.2744637751550423  |11829   |3640     |6636     |3.3468359258178866E-4|3.2631964246124977E-4|3.1401184150200247E-4|
|advertising_id_s=white&&imei_s=unique                   |2.4800238231880134  |2270    |2        |0        |6.422620298931949E-5 |1.7929650684684054E-7|0.0                  |
|mac_addr_s=white&&android_id_s=missing                  |4.960047646376027   |12      |4        |0        |3.3952177791710745E-7|3.585930136936811E-7 |0.0                  |
|advertising_id_s=white&&mac_addr_s=unique               |7.4400714695640415  |1904    |6        |0        |5.387078876284772E-5 |5.378895205405215E-7 |0.0                  |
|advertising_id_s=white&&imei_s=missing                  |9.920095292752054   |31      |8        |0        |8.770979262858609E-7 |7.171860273873622E-7 |0.0                  |
|android_serial_s=white&&android_id_s=missing            |9.920095292752054   |17      |8        |0        |4.809891853825689E-7 |7.171860273873622E-7 |0.0                  |
|cpu_serial_s=missing&&mac_addr_s=missing                |13.39586638691473   |414820  |133814   |249659   |0.011736701992964543 |0.01199619138360156  |0.011813725487876498 |
|android_serial_s=unique&&android_id_s=missing           |16.120154850722088  |447     |13       |0        |1.2647186227412253E-5|1.1654272945044634E-6|0.0                  |
|cpu_serial_s=white&&android_id_s=missing                |19.840190585504107  |46      |16       |0        |1.3015001486822453E-6|1.4343720547747243E-6|0.0                  |
|imei_s=unique&&android_id_s=missing                     |22.320214408692124  |455     |18       |0        |1.2873534079356991E-5|1.6136685616215647E-6|0.0                  |
|imei_s=white&&mac_addr_s=white                          |23.67815858597053   |8186    |2228     |4921     |2.3161043950245346E-4|1.9973630862738035E-4|2.3285899216867907E-4|
|mac_addr_s=unique&&android_id_s=missing                 |27.28026205506815   |400     |22       |0        |1.1317392597236915E-5|1.972261575315246E-6 |0.0                  |
|mac_addr_s=missing&&advertising_id_s=white              |32.240309701444176  |201     |26       |0        |5.68698978011155E-6  |2.3308545890089268E-6|0.0                  |
|cpu_serial_s=unique&&android_id_s=missing               |33.48032161303817   |175     |27       |0        |4.951359261291151E-6 |2.420502842432347E-6 |0.0                  |
|advertising_id_s=white&&android_serial_s=white          |37.200357347820194  |153     |30       |0        |4.32890266844312E-6  |2.689447602702608E-6 |0.0                  |
|advertising_id_s=white&&mac_addr_s=white                |42.16040499419624   |246     |34       |0        |6.960196447300703E-6 |3.048040616396289E-6 |0.0                  |
|advertising_id_s=white&&cpu_serial_s=duplicated         |48.360464552166256  |41      |39       |0        |1.1600327412167839E-6|3.4962818835133904E-6|0.0                  |
|android_serial_s=white&&imei_s=white                    |55.238369282917816  |21915   |5824     |9470     |6.200516469211175E-4 |5.221114279379996E-4 |4.4811515054610663E-4|
|mac_addr_s=missing&&android_id_s=missing                |57.040547933324305  |311     |46       |0        |8.7992727443517E-6   |4.1238196574773326E-6|0.0                  |
|android_serial_s=white&&mac_addr_s=white                |61.085567240739046  |587700  |193002   |355798   |0.01662807907349034  |0.017302292207226958 |0.01683616413241855  |
|cpu_serial_s=missing&&imei_s=white                      |76.3817401906131    |5548    |1029     |2879     |1.56972235323676E-4  |9.224805277269945E-5 |1.3623268409949747E-4|
|advertising_id_s=missing&&android_id_s=missing          |94.2409052811445    |585     |76       |0        |1.6551686673458987E-5|6.81326726017994E-6  |0.0                  |
|android_id_s=missing&&cpu_serial_s=duplicated           |109.1210482202726   |130     |88       |0        |3.6781525941019976E-6|7.889046301260984E-6 |0.0                  |
|imei_s=missing&&android_id_s=missing                    |112.84108395505461  |337     |91       |0        |9.5349032631721E-6   |8.157991061531244E-6 |0.0                  |
|imei_s=missing                                          |142.21340411506273  |1014825 |307717   |602335   |0.02871293235622738  |0.027586291598694614 |0.028502158310896423 |
|android_id_s=missing&&android_serial_s=missing          |145.08139365649882  |358     |117      |0        |1.0129066374527039E-5|1.048884565054017E-5 |0.0                  |
|android_serial_s=duplicated&&android_id_s=missing       |145.08139365649882  |187     |117      |0        |5.290881039208258E-6 |1.048884565054017E-5 |0.0                  |
|cpu_serial_s=missing&&android_id_s=missing              |153.76147703765685  |658     |124      |0        |1.8617110822454727E-5|1.1116383424504113E-5|0.0                  |
|android_id_s=missing&&imei_s=duplicated                 |172.36165571156687  |209     |139      |0        |5.913337632056288E-6 |1.2461107225855417E-5|0.0                  |
|android_serial_s=white&&imei_s=missing                  |194.66853495995232  |176644  |55348    |95635    |0.004997873744865794 |0.004961851530479465 |0.004525395187167572 |
|advertising_id_s=duplicated&&android_id_s=missing       |221.96213217532718  |179     |179      |0        |5.064533187263519E-6 |1.6047037362792226E-5|0.0                  |
|mac_addr_s=duplicated&&android_id_s=missing             |226.92217982170328  |286     |183      |0        |8.091935707024395E-6 |1.6405630376485907E-5|0.0                  |
|advertising_id_s=white&&mac_addr_s=duplicated           |254.20244187677136  |255     |205      |0        |7.214837780738533E-6 |1.8377891951801156E-5|0.0                  |
|cpu_serial_s=missing&&android_serial_s=white            |256.96834626323164  |453144  |152455   |271082   |0.012821021377705811 |0.013667324475667536 |0.012827449972580748 |
|mac_addr_s=missing&&imei_s=missing                      |267.94062545887954  |25739   |8848     |12677    |7.282459201507024E-4 |7.932077462904226E-4 |5.998686128271377E-4 |
|imei_s=missing&&mac_addr_s=white                        |273.36721971930655  |311674  |99699    |204395   |0.008818342550878046 |0.008937841218061578 |0.009671858098824863 |
|cpu_serial_s=missing&&android_id_s=white                |276.5226562854635   |1903    |223      |0        |5.384249528135462E-5 |1.999156051342272E-5 |0.0                  |
|advertising_id_s=white&&cpu_serial_s=white              |286.44275157821556  |2516    |231      |0        |7.118639943662019E-5 |2.0708746540810082E-5|0.0                  |
|advertising_id_s=white&&android_serial_s=duplicated     |297.60285878256155  |278     |240      |0        |7.865587855079656E-6 |2.1515580821620864E-5|0.0                  |
|mac_addr_s=missing&&android_id_s=white                  |314.96302554487767  |4470    |254      |0        |1.2647186227412254E-4|2.2770656369548747E-5|0.0                  |
|android_id_s=missing                                    |316.2030374564717   |1009    |255      |0        |2.8548122826530117E-5|2.2860304622972166E-5|0.0                  |
|advertising_id_s=white&&imei_s=duplicated               |323.6431089260357   |303     |261      |0        |8.572924892406964E-6 |2.339819414351269E-5 |0.0                  |
|advertising_id_s=white                                  |336.0432280419759   |2606    |271      |0        |7.37328127709985E-5  |2.429467667774689E-5 |0.0                  |
|android_id_s=duplicated&&advertising_id_s=white         |336.0432280419759   |271     |271      |0        |7.66753348462801E-6  |2.429467667774689E-5 |0.0                  |
|android_id_s=white&&imei_s=missing                      |374.48359730139     |1811    |302      |0        |5.123949498399013E-5 |2.7073772533872922E-5|0.0                  |
|imei_s=missing&&cpu_serial_s=white                      |405.73430927378604  |416450  |137727   |239870   |0.011782820367798283 |0.012346984999247404 |0.011350515434159936 |
|cpu_serial_s=unique&&android_serial_s=duplicated        |426.8076716292093   |1721    |50       |1461     |4.8693081649611825E-5|4.482412671171013E-6 |6.913371013176999E-5 |
|imei_s=unique&&android_id_s=white                       |519.5649909578889   |13034   |419      |0        |3.687772377809649E-4 |3.7562618184413094E-5|0.0                  |
|cpu_serial_s=missing&&imei_s=missing                    |700.2979224725663   |139142  |38975    |90009    |0.003936811601911847 |0.003494040677177805 |0.004259175985797731 |
|android_id_s=white&&mac_addr_s=unique                   |721.6869325477121   |10930   |582      |0        |3.092477527194987E-4 |5.2175283492430595E-5|0.0                  |
|mac_addr_s=missing&&android_serial_s=missing            |914.4583209796679   |130339  |35544    |85218    |0.0036877440843281557|0.0031864575196820497|0.004032468521566855 |
|cpu_serial_s=duplicated&&advertising_id_s=unique        |967.5342074223697   |129373  |51199    |77978    |0.0036604125812058287|0.004589900927025694 |0.0036898757348769063|
|cpu_serial_s=unique&&mac_addr_s=duplicated              |968.5636375444694   |7078    |407      |4468     |2.002612620081072E-4 |3.648683914333205E-5 |2.1142328327771959E-4|
|cpu_serial_s=unique&&android_id_s=white                 |1092.4504941143202  |4067    |881      |0        |1.1506958923240634E-4|7.898011126603326E-5 |0.0                  |
|android_serial_s=white&&android_id_s=white              |1169.3312326331481  |5473    |943      |0        |1.548502242116941E-4 |8.45383029782853E-5  |0.0                  |
|android_id_s=white&&mac_addr_s=white                    |1243.7319473287885  |2631    |1003     |0        |7.44401498083258E-5  |8.991719818369053E-5 |0.0                  |
|mac_addr_s=missing&&imei_s=white                        |1288.7188027476707  |10737   |5164     |3952     |3.037871107913319E-4 |4.6294358067854225E-4|1.8700644930920944E-4|
|cpu_serial_s=unique&&advertising_id_s=duplicated        |1381.3732695157232  |1135    |1114     |0        |3.2113101494659745E-5|9.986815431369018E-5 |0.0                  |
|advertising_id_s=missing&&cpu_serial_s=unique           |1398.7334362780396  |584626  |1128     |0        |0.016541104911380573 |1.0112322986161806E-4|0.0                  |
|android_serial_s=unique&&android_id_s=white             |1438.4138174490479  |12887   |1160     |0        |3.646180960014803E-4 |1.0399197397116751E-4|0.0                  |
|android_id_s=white&&cpu_serial_s=white                  |1449.5739246533935  |15409   |1169     |0        |4.359742563270591E-4 |1.047988082519783E-4 |0.0                  |
|android_id_s=white&&cpu_serial_s=duplicated             |1459.494019946146   |2017    |1177     |0        |5.7067952171567145E-5|1.0551599427936565E-4|0.0                  |
|android_id_s=white&&imei_s=white                        |1651.695866243217   |3617    |1332     |0        |1.023375225605148E-4 |1.194114735599958E-4 |0.0                  |
|android_id_s=white&&android_serial_s=duplicated         |1670.2960449171271  |5036    |1347     |0        |1.4248597279921277E-4|1.207561973613471E-4 |0.0                  |
|android_id_s=white&&imei_s=duplicated                   |1732.2966404968274  |4934    |1397     |0        |1.3960003768691734E-4|1.252386100325181E-4 |0.0                  |
|android_serial_s=duplicated&&advertising_id_s=unique    |1866.4427378061137  |634543  |239389   |394988   |0.01795343062707126  |0.021460805738779155 |0.01869061320843776  |
|android_serial_s=missing                                |1945.910022638757   |448096  |157258   |250034   |0.012678195883128681 |0.014097905036860224 |0.011831470280004775 |
|mac_addr_s=duplicated&&android_id_s=white               |1997.659189577945   |5365    |1611     |0        |1.517945282104401E-4 |1.4442333626513005E-4|0.0                  |
|android_id_s=duplicated&&cpu_serial_s=unique            |2489.9439184807657  |2654    |2008     |0        |7.509089988266693E-5 |1.8001369287422788E-4|0.0                  |
|cpu_serial_s=unique&&imei_s=duplicated                  |2966.5853301177317  |17243   |1037     |12907    |4.878645013853903E-4 |9.296523880008682E-5 |6.107520853324813E-4 |
|imei_s=white                                            |3034.2380898622528  |138859  |51639    |65726    |0.003928804546649302 |0.0046293461585319995|0.0031101178864618166|
|cpu_serial_s=missing&&android_serial_s=missing          |3712.768898002828   |334804  |123099   |176475   |0.00947277077781327  |0.01103561034816961  |0.008350699175567494 |
|imei_s=white&&mac_addr_s=unique                         |3865.252002794661   |76750   |12371    |49814    |0.002171524704594833 |0.0011090385431011321|0.0023571708668747365|
|imei_s=white&&cpu_serial_s=white                        |4103.529730527072   |100054  |41482    |46299    |0.002830875997309856 |0.0037187888485103195|0.00219084301532568  |
|cpu_serial_s=unique&&imei_s=white                       |4139.659306575595   |22992   |849      |15683    |6.505237264891779E-4 |7.611136715648381E-5 |7.421108665274118E-4 |
|advertising_id_s=unique&&imei_s=duplicated              |4224.039732853143   |818473  |317222   |500969   |0.023157450678095725 |0.028438398247484224 |0.023705575380563094 |
|android_id_s=white                                      |4278.041094999325   |23396   |3450     |0        |6.619542930123872E-4 |3.092864743107999E-4 |0.0                  |
|android_id_s=white&&advertising_id_s=duplicated         |4278.041094999325   |3450    |3450     |0        |9.761251115116839E-5 |3.092864743107999E-4 |0.0                  |
|imei_s=unique&&cpu_serial_s=duplicated                  |4809.910326259597   |25812   |14929    |9748     |7.303113442996981E-4 |0.0013383587753582411|4.6126995644386984E-4|
|advertising_id_s=missing&&imei_s=white                  |7955.916424787148   |26459   |6416     |0        |7.486172268257288E-4 |5.751831939646644E-4 |0.0                  |
|imei_s=white&&cpu_serial_s=duplicated                   |8297.97736560918    |10265   |8279     |865      |2.9043258752659235E-4|7.421978900924963E-4 |4.093132050922727E-5 |
|mac_addr_s=unique&&cpu_serial_s=duplicated              |9103.300970374026   |68719   |35934    |27269    |0.0019442997547238089|0.003221420338517184 |0.0012903539641226803|
|cpu_serial_s=unique&&android_serial_s=white             |12362.741111270603  |52490   |868      |39786    |0.0014851248435724142|7.781468397152878E-5 |0.0018826514656417527|
|imei_s=white&&android_id_s=unique                       |12467.010242555069  |92139   |7550     |65726    |0.0026069330912920304|6.76844313346823E-4  |0.0031101178864618166|
|android_serial_s=unique&&advertising_id_s=missing       |15450.548418461321  |2038494 |12460    |0        |0.05767609226277967  |0.0011170172376558166|0.0                  |
|android_serial_s=unique&&imei_s=white                   |15946.93685995325   |66879   |1338     |52287    |0.001892239748776519 |1.1994936308053631E-4|0.0024741918560300187|
|mac_addr_s=missing&&cpu_serial_s=unique                 |16973.1584927513    |62048   |34       |49292    |0.0017555539396833903|3.048040616396289E-6 |0.002332470116232174 |
|advertising_id_s=duplicated&&android_id_s=unique        |17934.29227738412   |17478   |14463    |0        |4.94513469536267E-4  |0.0012965826892629272|0.0                  |
|android_serial_s=white&&cpu_serial_s=duplicated         |18329.323723572164  |33680   |25117    |6578     |9.529244566873483E-4 |0.0022516951812360467|3.1126731365282886E-4|
|imei_s=white&&advertising_id_s=unique                   |20050.059526461315  |68815   |1679     |65726    |0.0019470159289471458|1.5051941749792263E-4|0.0031101178864618166|
|mac_addr_s=missing&&android_serial_s=white              |23677.840747905168  |692968  |247543   |279010   |0.019606477283305175 |0.022191797597193724 |0.013202598537895375 |
|cpu_serial_s=unique&&android_serial_s=missing           |24825.792051970035  |77568   |82       |72251    |0.0021946687724561827|7.351156780720462E-6 |0.003418877269493849 |
|advertising_id_s=missing&&imei_s=unique                 |25038.320518906177  |2730423 |20192    |0        |0.07725317261881352  |0.001810177533125702 |0.0                  |
|mac_addr_s=white&&cpu_serial_s=white                    |25089.322299525174  |3790626 |1081587  |2563613  |0.10725000657823444  |0.09696238547547685  |0.1213087460862678   |
|mac_addr_s=duplicated&&imei_s=white                     |25269.216967834014  |43186   |31876    |7039     |0.0012218822917606836|0.002857627726124944 |3.330815781091916E-4 |
|android_serial_s=unique&&mac_addr_s=duplicated          |25694.169649514035  |433030  |65885    |286772   |0.012251926290953754 |0.005906475176802044 |0.013569892075227886 |
|android_serial_s=unique&&cpu_serial_s=duplicated        |26018.553417271698  |55091   |40232    |12940    |0.0015587161889359473|0.003606728531731044 |6.123136270397698E-4 |
|cpu_serial_s=duplicated&&android_id_s=unique            |26577.548088751057  |105239  |220      |77978    |0.0029775776988515395|1.9722615753152458E-5|0.0036898757348769063|
|advertising_id_s=missing&&android_serial_s=missing      |28645.51516973315   |63811   |23101    |0        |0.001805435347555712 |0.0020709643023344316|0.0                  |
|mac_addr_s=missing&&cpu_serial_s=duplicated             |29271.5167835237    |25749   |24773    |583      |7.285288549656333E-4 |0.0022208561820583903|2.7587236828762424E-5|
|mac_addr_s=duplicated&&advertising_id_s=unique          |37084.99003681414   |1068296 |251792   |815507   |0.03022581310514452  |0.022572713025989836 |0.03858933918441434  |
|cpu_serial_s=duplicated&&android_serial_s=missing       |39076.814404968274  |35706   |34076    |1301     |0.0010102470501923532|0.003054853883656469 |6.156259882370483E-5 |
|mac_addr_s=missing                                      |40477.69994268477   |2185465 |783633   |1024366  |0.06183441353130094  |0.07025132977495509  |0.04847243128873422  |
|mac_addr_s=unique&&android_serial_s=missing             |40714.36483770411   |187130  |8008     |152860   |0.0052945591918023595|7.179032134147495E-4 |0.0072332504659427515|
|android_serial_s=unique&&imei_s=duplicated              |45786.428872207536  |129852  |85496    |35397    |0.0036739651588410197|0.007664567074688739 |0.0016749664185723902|
|imei_s=white&&android_serial_s=duplicated               |45976.01564848236   |50065   |44477    |3969     |0.0014165131509516653|0.003987285367513463 |1.8781087988569136E-4|
|cpu_serial_s=white                                      |50849.86353586118   |20121814|6784157  |11224723 |0.5693161720164454   |0.6081878260002705   |0.5311476702199942   |
|android_id_s=duplicated&&imei_s=white                   |53010.509220643784  |43095   |42750    |0        |0.0012193075849448122|0.0038324628338512164|0.0                  |
|mac_addr_s=missing&&cpu_serial_s=white                  |53986.03271767753   |1682848 |625012   |724832   |0.047613628743687374 |0.056031234168678744 |0.03429864844779679  |
|advertising_id_s=duplicated&&imei_s=white               |53995.078678449434  |43583   |43544    |0        |0.0012331148039134412|0.003903643547069412 |0.0                  |
|advertising_id_s=missing&&imei_s=missing                |57279.87023226196   |148949  |46193    |0        |0.004214285774914603 |0.004141121770388052 |0.0                  |
|imei_s=unique&&android_serial_s=duplicated              |67321.72366600895   |177705  |117049   |44388    |0.005027893128729965 |0.010493238414957919 |0.0021004155546399767|
|imei_s=unique&&android_serial_s=missing                 |77467.57674413934   |274005  |2525     |236101   |0.007752555396514752 |2.2636183989413618E-4|0.011172168443409326 |
|advertising_id_s=unique&&android_serial_s=missing       |78375.63541494067   |255139  |5011     |250034   |0.007218770574666074 |4.4922739790475896E-4|0.011831470280004775 |
|cpu_serial_s=missing                                    |78513.35286550592   |7732304 |2109496  |5293592  |0.21877380012296346  |0.18911263200369136  |0.2504898390717704   |
|advertising_id_s=missing&&mac_addr_s=unique             |83998.40689137802   |2156967 |67740    |0        |0.061028105895710796 |0.006072772686902489 |0.0                  |
|android_id_s=unique&&android_serial_s=missing           |86146.75464772037   |290739  |142      |250034   |0.008226018515820159 |1.2730051986125677E-5|0.011831470280004775 |
|cpu_serial_s=unique&&imei_s=missing                     |86520.73241405883   |302075  |308      |251909   |0.008546753422025852 |2.7611662054413442E-5|0.011920194240646165 |
|android_serial_s=unique&&advertising_id_s=duplicated    |92692.1304035636    |76646   |74751    |0        |0.0021685821825195516|0.006701296591654088 |0.0                  |
|imei_s=missing&&mac_addr_s=unique                       |98924.41995502166   |466250  |17768    |364513   |0.01319183574615428  |0.0015928701668273314|0.01724855310147972  |
|advertising_id_s=missing&&mac_addr_s=white              |106040.85863187308  |365426  |85516    |0        |0.010339173768094742 |0.007666360039757208 |0.0                  |
|mac_addr_s=duplicated&&imei_s=unique                    |110041.86560345368  |717274  |48864    |517192   |0.020294178644476277 |0.0043805722552820075|0.024473238747755224 |
|android_serial_s=unique&&android_id_s=duplicated        |111044.30669515488  |90410   |89551    |0        |0.0025580136617904736|0.008028090742320708 |0.0                  |
|mac_addr_s=duplicated&&android_serial_s=missing         |113814.98315813846  |130626  |113706   |11955    |0.0036958643135166732|0.010193544303763425 |5.657039730494936E-4 |
|android_serial_s=duplicated&&android_id_s=unique        |114952.68053155467  |541770  |13816    |394988   |0.01532855946851261  |0.0012385802692979743|0.01869061320843776  |
|imei_s=missing&&cpu_serial_s=duplicated                 |117782.02313369288  |157158  |130707   |20547    |0.004446546964491398 |0.011717654260214993 |9.722726502925927E-4 |
|cpu_serial_s=missing&&mac_addr_s=white                  |122953.36237261865  |3654742 |838924   |2713823  |0.10340537513902709  |0.07520807135498943  |0.12841660002117072  |
|android_serial_s=white                                  |130215.99261280884  |5883666 |2217027  |2822076  |0.16646939508253633  |0.19875259834256517  |0.13353907197387058  |
|android_serial_s=white&&cpu_serial_s=white              |140140.4651334791   |5344352 |2038587  |2504630  |0.15121032440457075  |0.18275576400169005  |0.11851770322199526  |
|advertising_id_s=missing&&cpu_serial_s=missing          |152633.0661981063   |449809  |123090   |0        |0.012726662616926348 |0.0110348035138888   |0.0                  |
|imei_s=unique&&advertising_id_s=duplicated              |158211.87978836772  |130545  |127589   |0        |0.003693572541515733 |0.011438131006040768 |0.0                  |
|advertising_id_s=duplicated&&android_serial_s=missing   |160142.57833471958  |129146  |129146   |0        |0.0036539899609068964|0.011577713336621033 |0.0                  |
|android_id_s=unique&&imei_s=duplicated                  |162995.9571686395   |712640  |6225     |500969   |0.020163066651237287 |5.580603775607912E-4 |0.023705575380563094 |
|android_serial_s=unique&&imei_s=missing                 |164680.25265918634  |554215  |1029     |481582   |0.015680671845694143 |9.224805277269945E-5 |0.022788193287253972 |
|android_serial_s=missing&&imei_s=duplicated             |169729.73105167795  |162262  |151093   |7297     |0.004590956894032141 |0.013545223554504838 |3.452899950934467E-4 |
|android_serial_s=duplicated&&mac_addr_s=unique          |176673.2329751361   |307655  |232339   |56107    |0.008704631048757308 |0.02082878555214404  |0.0026549521385100744|
|mac_addr_s=duplicated&&imei_s=missing                   |178212.8176238554   |211162  |181402   |20750    |0.005974508139044354 |0.016262372467515284 |9.818784977647004E-4 |
|android_id_s=duplicated&&imei_s=unique                  |190666.71155051765  |155898  |153762   |0        |0.004410897177810101 |0.013784494742891947 |0.0                  |
|imei_s=missing&&advertising_id_s=unique                 |192040.23665746098  |614297  |9988     |602335   |0.017380600800762112 |8.954067551931216E-4 |0.028502158310896423 |
|android_id_s=duplicated&&android_serial_s=missing       |194680.63010834745  |156999  |156999   |0        |0.004442048300933996 |0.014074686139223557 |0.0                  |
|imei_s=missing&&android_id_s=unique                     |207499.00894067984  |705074  |360      |602335   |0.01994899817026055  |3.22733712324313E-5  |0.028502158310896423 |
|mac_addr_s=white                                        |209901.02848214135  |8737011 |2137915  |6316800  |0.24720045903344373  |0.19166034571773155  |0.2989074744424125   |
|android_serial_s=unique&&mac_addr_s=missing             |211199.9553944757   |824514  |4989     |634892   |0.023328371599795495 |4.472551363294437E-4 |0.03004273750375066  |
|mac_addr_s=unique&&imei_s=duplicated                    |225482.66305598264  |386446  |290337   |67073    |0.010933902749079542 |0.02602820495419557  |0.00317385717978659  |
|advertising_id_s=missing&&mac_addr_s=missing            |244671.71032425985  |573299  |197314   |0        |0.016220624646508315 |0.017688855475988745 |0.0                  |
|mac_addr_s=white&&cpu_serial_s=duplicated               |247172.30341357298  |227121  |216963   |8983     |0.006426043810192614 |0.01945035400750553  |4.250705805021833E-4 |
|imei_s=missing&&android_serial_s=duplicated             |263753.80897545896  |272137  |247700   |18482    |0.007699703173085656 |0.0222058723729812   |8.745579949728767E-4 |
|mac_addr_s=duplicated&&android_id_s=unique              |278663.78820805746  |1111201 |1867     |815507   |0.03143974492860564  |1.6737328914152565E-4|0.03858933918441434  |
|mac_addr_s=missing&&advertising_id_s=unique             |309083.9960997281   |1053743 |28428    |1024366  |0.02981405806897555  |0.0025485205483209913|0.04847243128873422  |
|advertising_id_s=duplicated&&imei_s=missing             |311897.71609941724  |251548  |251528   |0        |0.007117168682624379 |0.022549045887086054 |0.0                  |
|mac_addr_s=missing&&imei_s=unique                       |321261.5428916603   |1308953 |7410     |964916   |0.03703483748082763  |6.642935578675442E-4 |0.045659290243331256 |
|mac_addr_s=missing&&android_id_s=unique                 |353408.96848359227  |1397126 |295      |1024366  |0.039529558624518055 |2.644623475990898E-5 |0.04847243128873422  |
|cpu_serial_s=unique&&mac_addr_s=white                   |355245.76405663026  |1064522 |441      |1030381  |0.030119033505989587 |3.9534879759728336E-5|0.0487570577544718   |
|advertising_id_s=duplicated&&mac_addr_s=unique          |372888.9419830802   |302880  |300714   |0        |0.008569529674627792 |0.026958484879970403 |0.0                  |
|android_id_s=duplicated&&imei_s=missing                 |380639.01643054275  |307603  |306964   |0        |0.008703159787719668 |0.027518786463866778 |0.0                  |
|android_id_s=duplicated&&advertising_id_s=unique        |416188.91792403127  |338753  |335633   |0        |0.009584501736229491 |0.030088912241262814 |0.0                  |
|android_serial_s=white&&mac_addr_s=unique               |442920.05430293444  |2535592 |141149   |1875236  |0.07174072532603286  |0.012653761322462346 |0.08873512803056799  |
|android_id_s=duplicated&&mac_addr_s=unique              |463680.1341261702   |376194  |373932   |0        |0.01064383797681236  |0.03352235069912639  |0.0                  |
|advertising_id_s=missing&&cpu_serial_s=duplicated       |480314.89392020367  |415072  |387347   |0        |0.011743831950300801 |0.03472498201880157  |0.0                  |
|advertising_id_s=missing&&android_serial_s=white        |487917.4069501866   |1231972 |393478   |0        |0.03485677698200789  |0.03527461546054056  |0.0                  |
|mac_addr_s=missing&&android_serial_s=duplicated         |553685.5007388835   |537644  |495557   |25246    |0.01521182056387211  |0.04442581952174988  |0.0011946267255213314|
|cpu_serial_s=missing&&mac_addr_s=unique                 |684988.1494589319   |2496098 |60298    |2258354  |0.07062330256794468  |0.005405610384925395 |0.10686405941883866  |
|mac_addr_s=missing&&advertising_id_s=duplicated         |691759.2450613908   |558222  |557865   |0        |0.01579404382603696  |0.050011622896056346 |0.0                  |
|android_serial_s=white&&advertising_id_s=unique         |837683.1890592794   |2914490 |87588    |2822076  |0.08246106887680255  |0.007852111220850534 |0.13353907197387058  |
|imei_s=unique&&android_serial_s=white                   |841952.3588171743   |3347746 |31959    |2586845  |0.09471938949457373  |0.002865068531159088 |0.12240807144819886  |
|mac_addr_s=missing&&imei_s=duplicated                   |842590.6081139522   |840036  |762211   |42821    |0.023767543019531275 |0.06833088489011858  |0.0020262659832666137|
|android_id_s=duplicated&&mac_addr_s=missing             |970976.4472307477   |783558  |783038   |0        |0.022169583771764406 |0.07019798906416816  |0.0                  |
|android_serial_s=white&&android_id_s=unique             |974352.2827339341   |3660211 |372      |2822076  |0.10356011218931281  |3.334915027351234E-5 |0.13353907197387058  |
|cpu_serial_s=missing&&mac_addr_s=duplicated             |1164893.1913660092  |1166644 |1076460  |71756    |0.03300842042302716  |0.09650275888017498  |0.0033954541438845224|
|cpu_serial_s=unique&&mac_addr_s=unique                  |1189411.6400379082  |3992026 |2035     |3452527  |0.11294831375094323  |1.8243419571666025E-4|0.16337166381937676  |
|advertising_id_s=missing&&cpu_serial_s=white            |1198247.0703996092  |3070136 |966319   |0        |0.08686483609727638  |0.08662881059986605  |0.0                  |
|advertising_id_s=missing&&android_serial_s=duplicated   |1300580.293415816   |1185366 |1048845  |0        |0.03353813098354083  |0.09402712236188723  |0.0                  |
|mac_addr_s=duplicated&&android_serial_s=white           |1377166.2160674003  |2067406 |1635333  |312032   |0.058494113399707955 |0.14660474721568215  |0.014765181272988674 |
|advertising_id_s=missing&&mac_addr_s=duplicated         |1397882.7881066862  |1423951 |1127314  |0        |0.040288531265570256 |0.10106173115976959  |0.0                  |
|cpu_serial_s=unique&&imei_s=unique                      |1469220.5859817318  |4783364 |723      |4256169  |0.1353380208087239   |6.481568722513285E-5 |0.2013995577808524   |
|android_serial_s=unique&&cpu_serial_s=unique            |1524942.4526093598  |4993895 |1917     |4423170  |0.14129467576094612  |1.7185570181269666E-4|0.20930195252809108  |
|cpu_serial_s=unique                                     |1562502.1136983442  |5125674 |2917     |4536668  |0.14502316245862432  |2.6150395523611694E-4|0.21467261497335843  |
|cpu_serial_s=unique&&advertising_id_s=unique            |1566206.4152919562  |4539899 |675      |4536668  |0.1284495483370082   |6.051257106080868E-5 |0.21467261497335843  |
|cpu_serial_s=unique&&android_id_s=unique                |1567321.2718759247  |5118778 |1        |4536668  |0.14482805061024795  |8.964825342342027E-8 |0.21467261497335843  |
|android_serial_s=unique&&cpu_serial_s=missing           |1630740.3409238835  |5059495 |15291    |4793054  |0.14315072814689297  |0.0013708114430975194|0.2268046583722934   |
|cpu_serial_s=missing&&imei_s=unique                     |1718163.5197316085  |5470678 |34367    |5135953  |0.15478452674766713  |0.0030809415254026843|0.24303044897494488  |
|advertising_id_s=missing&&imei_s=duplicated             |1742319.656778242   |1613812 |1405083  |0        |0.04566035995533025  |0.12596323686493963  |0.0                  |
|cpu_serial_s=missing&&advertising_id_s=unique           |1760548.7219008196  |5337460 |41840    |5293592  |0.15101532573012036  |0.003750882923235904 |0.2504898390717704   |
|cpu_serial_s=missing&&android_id_s=unique               |1823231.531428928   |5623713 |3384     |5293592  |0.1591144196879625   |3.033696895848542E-4 |0.2504898390717704   |
|android_id_s=duplicated&&advertising_id_s=missing       |1832499.5230489161  |1477808 |1477808  |0        |0.041812333298343725 |0.13248290609515787  |0.0                  |
|advertising_id_s=missing                                |1832593.7639541973  |4519643 |1477884  |0        |0.1278764355758841   |0.13248971936241805  |0.0                  |
|android_serial_s=unique&&mac_addr_s=white               |1995112.8430705427  |6103337 |16197    |5852114  |0.1726846524556054   |0.001452032760699138 |0.2769187905092902   |
|imei_s=unique&&mac_addr_s=white                         |2013558.6194158313  |6282233 |33447    |5986935  |0.17774624312079365  |0.0029984651322531378|0.2832984454946943   |
|cpu_serial_s=white&&mac_addr_s=unique                   |2057437.703102626   |8903443 |287015   |7238138  |0.2519093997453021   |0.025730393456322966 |0.34250467788210087  |
|mac_addr_s=white&&advertising_id_s=unique               |2119149.5690410156  |6357073 |38591    |6316800  |0.17986372727573666  |0.0034596157478632115|0.2989074744424125   |
|cpu_serial_s=missing&&android_serial_s=duplicated       |2124438.5494116447  |1884861 |1818651  |52981    |0.053329279820551426 |0.16303888573675668  |0.002507031551328751 |
|android_serial_s=duplicated&&mac_addr_s=white           |2130914.6246772166  |2045973 |1928716  |108887   |0.057887699210866506 |0.17290602074980543  |0.005152472481258069 |
|android_serial_s=white&&advertising_id_s=duplicated     |2152575.1177052963  |1737051 |1735931  |0        |0.04914722032105745  |0.15562318221357135  |0.0                  |
|mac_addr_s=white&&android_id_s=unique                   |2179749.2425168417  |6598677 |1555     |6316800  |0.18669954557839374  |1.394030340734185E-4 |0.2989074744424125   |
|mac_addr_s=white&&imei_s=duplicated                     |2195701.172823208   |2134918 |2002541  |120549   |0.0604042629222696   |0.17952430305878944  |0.005704311856724668 |
|advertising_id_s=duplicated&&cpu_serial_s=duplicated    |2256266.153764698   |1819552 |1819552  |0        |0.05148146083771906  |0.16311965881309118  |0.0                  |
|android_serial_s=white&&imei_s=duplicated               |2323702.570113309   |2337361 |2123896  |130126   |0.06613208019617568  |0.19040356685298862  |0.006157490187958043 |
|mac_addr_s=duplicated&&cpu_serial_s=duplicated          |2353350.602132871   |2042449 |1980467  |41143    |0.05778799298208485  |0.17754540751272085  |0.0019468639534232803|
|cpu_serial_s=missing&&imei_s=duplicated                 |2364304.7690639165  |2116936 |2035125  |64751    |0.059895489538060814 |0.18244540174833818  |0.0030639814269282947|
|cpu_serial_s=missing&&advertising_id_s=duplicated       |2411283.7628687993  |1945000 |1944565  |0        |0.0550308215040645   |0.17432685591831323  |0.0                  |
|cpu_serial_s=duplicated&&imei_s=duplicated              |2492868.2761169104  |2170803 |2104222  |46818    |0.061419574505649215 |0.18863982711513624  |0.0022154018076312163|
|advertising_id_s=duplicated&&mac_addr_s=white           |2497103.747258309   |2014266 |2013774  |0        |0.05699059779316503  |0.18053132188949472  |0.0                  |
|android_serial_s=duplicated&&cpu_serial_s=duplicated    |2535394.998477149   |2239561 |2158712  |57159    |0.06336497770615125  |0.1935247604441784   |0.0027047321953606028|
|cpu_serial_s=duplicated                                 |2608880.681200573   |2364038 |2258137  |77978    |0.06688686540196691  |0.20243803804080196  |0.0036898757348769063|
|android_id_s=duplicated&&cpu_serial_s=missing           |2611173.683017754   |2106030 |2105765  |0        |0.059586920828897154 |0.18877815437016857  |0.0                  |
|android_id_s=duplicated&&mac_addr_s=white               |2647863.155457997   |2135691 |2135353  |0        |0.06042613378346376  |0.19143066689246074  |0.0                  |
|android_id_s=duplicated&&android_serial_s=white         |2747499.352566487   |2217965 |2215704  |0        |0.06275395167982643  |0.19863399370328597  |0.0                  |
|android_id_s=duplicated&&cpu_serial_s=duplicated        |2798275.3603224386  |2256652 |2256652  |0        |0.0638485415983497   |0.20230491038446818  |0.0                  |
|android_serial_s=unique&&cpu_serial_s=white             |2859793.703669357   |9682309 |33417    |8436699  |0.2739462305019009   |0.002995775684650435 |0.39921992001026263  |
|imei_s=unique&&cpu_serial_s=white                       |3479022.013453868   |12559446|104508   |10562061 |0.35535045296449197  |0.009368959668774804 |0.49979087170983755  |
|cpu_serial_s=white&&advertising_id_s=unique             |3492417.9609383624  |11470915|241919   |11224723 |0.3245521212613347   |0.021687615819940408 |0.5311476702199942   |
|android_serial_s=unique&&mac_addr_s=unique              |3756724.989823238   |12429909|3786     |10892085 |0.35168540025232126  |3.3940828746106914E-4|0.5154074244494181   |
|cpu_serial_s=white&&android_id_s=unique                 |3859970.1809818298  |13332036|10858    |11224723 |0.37720971383124013  |9.734007356714973E-4 |0.5311476702199942   |
|mac_addr_s=unique                                       |3877733.2992880847  |15460286|385282   |12976288 |0.4374253158189138   |0.03453985837548221  |0.6140307550844389   |
|imei_s=unique&&mac_addr_s=unique                        |4210464.767701884   |14530840|64806    |12494888 |0.41112805261908514  |0.005809744711358174 |0.5912511739362979   |
|mac_addr_s=duplicated&&cpu_serial_s=white               |4417770.56074242    |5744897 |4790543  |698140   |0.1625431369492214   |0.42946381289979196  |0.033035597803828816 |
|mac_addr_s=unique&&advertising_id_s=unique              |4455263.978867149   |12998535|16822    |12976288 |0.36777380945981236  |0.0015080629190887757|0.6140307550844389   |
|mac_addr_s=unique&&android_id_s=unique                  |4465279.545871529   |15072762|10746    |12976288 |0.4264609126967847   |9.633601312880742E-4 |0.6140307550844389   |
|android_serial_s=duplicated&&cpu_serial_s=white         |5167288.252945407   |5095135 |4712152  |283387   |0.1441591078273068   |0.42243619666567667  |0.013409715751616633 |
|android_serial_s=unique&&imei_s=unique                  |5901560.86409657    |19039844|2994     |17096597 |0.5387034738453642   |2.6840687074972027E-4|0.8090014929758305   |
|android_serial_s=unique                                 |5954203.181290693   |19790790|90857    |17665863 |0.5599503505986759   |0.008145171361291696 |0.8359388445376869   |
|android_serial_s=unique&&advertising_id_s=unique        |6097153.803334447   |17673475|3645     |17665863 |0.5000441378311292   |3.2676788372836685E-4|0.8359388445376869   |
|android_serial_s=unique&&android_id_s=unique            |6102962.30799522    |19687046|133      |17665863 |0.5570150716546566   |1.1923217705314895E-5|0.8359388445376869   |
|imei_s=unique                                           |6644883.6220369255  |22839300|154527   |19963931 |0.6462033118651827   |0.013853075656760864 |0.9446821484220786   |
|advertising_id_s=unique                                 |6760867.685600952   |21477647|335633   |21132961 |0.6076774079096691   |0.030088912241262814 |1.0                  |
|imei_s=unique&&advertising_id_s=unique                  |6885964.789818761   |19976062|6744     |19963931 |0.5651923405018641   |6.045878210875462E-4 |0.9446821484220786   |
|imei_s=unique&&android_id_s=unique                      |6896573.620941988   |22669913|328      |19963931 |0.6414107639155122   |2.9404627122881848E-5|0.9446821484220786   |
|advertising_id_s=duplicated&&cpu_serial_s=white         |6913919.535331765   |5578247 |5575688  |0        |0.15782802825839756  |0.4998506908339233   |0.0                  |
|mac_addr_s=duplicated&&android_serial_s=duplicated      |6978473.138328136   |6330006 |6032953  |204748   |0.17909790761216315  |0.5408436994355835   |0.009688561863148282 |
|cpu_serial_s=white&&imei_s=duplicated                   |7160583.938981958   |7045864 |6500440  |376493   |0.1993520226868452   |0.5827530924837381   |0.017815440060671102 |
|android_id_s=unique                                     |7277090.741616196   |24179766|14463    |21132961 |0.6841297618283021   |0.0012965826892629272|1.0                  |
|android_id_s=unique&&advertising_id_s=unique            |7300991.449658953   |21132961|0        |21132961 |0.5979250409477411   |0.0                  |1.0                  |
|mac_addr_s=duplicated                                   |7874890.60354306    |8961068 |7847877  |815507   |0.25353981161634154  |0.7035484661318312   |0.03858933918441434  |
|mac_addr_s=duplicated&&advertising_id_s=duplicated      |8021098.890931998   |6468566 |6468566  |0        |0.183018252407846    |0.57989564405412     |0.0                  |
|android_id_s=duplicated&&cpu_serial_s=white             |8397502.026672537   |6774323 |6772114  |0        |0.19166918242872943  |0.6071081920842923   |0.0                  |
|mac_addr_s=duplicated&&imei_s=duplicated                |8743780.099484302   |7989446 |7585735  |270526   |0.2260492425410602   |0.680047893682909    |0.01280114036078522  |
|advertising_id_s=duplicated&&android_serial_s=duplicated|9177440.9987912     |7401091 |7401091  |0        |0.20940263123719188  |0.6634948815777949   |0.0                  |
|android_serial_s=duplicated&&imei_s=duplicated          |9467456.518729398   |8721371 |8280339  |328149   |0.24675794898289177  |0.7423179291038303   |0.015527828778939212 |
|android_id_s=duplicated&&mac_addr_s=duplicated          |9726921.277116293   |7844216 |7844216  |0        |0.22194018022381842  |0.703220263876048    |0.0                  |
|android_serial_s=duplicated                             |9818028.180356795   |9221278 |8689565  |394988   |0.26090205843565906  |0.7790043252592829   |0.01869061320843776  |
|android_id_s=duplicated&&android_serial_s=duplicated    |1.075621672456122E7 |8674285 |8674285  |0        |0.24542572211330804  |0.7776344999469731   |0.0                  |
|advertising_id_s=duplicated&&imei_s=duplicated          |1.1058746150668543E7|8918258 |8918258  |0        |0.2523285676736222   |0.7995062532794451   |0.0                  |
|android_id_s=duplicated&&advertising_id_s=duplicated    |1.1560416529730216E7|9322827 |9322827  |0        |0.2637752331878011   |0.8357751575187049   |0.0                  |
|advertising_id_s=duplicated                             |1.158285082523478E7 |9343934 |9340919  |0        |0.2643724237016758   |0.8373970737196414   |0.0                  |
|imei_s=duplicated                                       |1.1982951018167341E7|11350846|10640824 |500969   |0.3211549512319406   |0.9539312865860126   |0.023705575380563094 |
|android_id_s=duplicated&&imei_s=duplicated              |1.3185124776729504E7|10633063|10633063 |0        |0.3008463712053844   |0.9532355264911934   |0.0                  |
|android_id_s=duplicated                                 |1.3809441013931205E7|11139659|11136539 |0        |0.31517973575585895  |0.9983712705318033   |0.0                  |
+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+

-------- > no cross
--> no chi2/chi2 yiyang
+-----+---------------------------+--------------------+
|index|feature                    |params              |
+-----+---------------------------+--------------------+
|6    |android_id_s=unique        |-3.3797303112259374 |
|3    |advertising_id_s=unique    |-2.0875403352588755 |
|8    |imei_s=unique              |-1.0915832623795647 |
|11   |android_serial_s=unique    |-0.7841258121446503 |
|7    |cpu_serial_s=unique        |-0.42904244304979566|
|12   |mac_addr_s=unique          |-0.30598480878222317|
|14   |mac_addr_s=white           |-0.14191259912813312|
|1    |android_serial_s=missing   |0.016507352997616975|
|18   |cpu_serial_s=white         |0.05891451784334254 |
|16   |cpu_serial_s=missing       |0.07217282430236151 |
|13   |mac_addr_s=missing         |0.07506204716020931 |
|4    |imei_s=missing             |0.14983721318098253 |
|2    |android_serial_s=white     |0.248304851849968   |
|20   |cpu_serial_s=duplicated    |0.33051612894104804 |
|0    |mac_addr_s=duplicated      |0.47940377554399743 |
|23   |android_serial_s=duplicated|0.8149733907185066  |
|22   |imei_s=duplicated          |1.0804882227960368  |
|10   |advertising_id_s=white     |1.2898275554303977  |
|9    |advertising_id_s=missing   |1.4398021409021233  |
|19   |advertising_id_s=duplicated|1.9511419652792428  |
|5    |imei_s=white               |2.2564078089324315  |
|21   |android_id_s=duplicated    |3.3782317247302087  |
|15   |android_id_s=missing       |3.671570980589432   |
|17   |android_id_s=white         |3.9270227195273364  |
+-----+---------------------------+--------------------+

-- > ratio
+---------------------------+--------------------+--------+---------+---------+--------------------+--------------------+---------------------+
|feature                    |score               |count   |pos_count|neg_count|total_ratio         |pos_ratio           |neg_ratio            |
+---------------------------+--------------------+--------+---------+---------+--------------------+--------------------+---------------------+
|mac_addr_s=missing         |25.019445185521178  |2889531 |867000   |1613426  |0.07905825730766662 |0.07381039480218175 |0.07442337917381048  |
|imei_s=missing             |209.87225378625828  |1060864 |326045   |625589   |0.029025492054053217|0.027757220499743197|0.028856884266129913 |
|advertising_id_s=white     |326.7869119470587   |2442    |273      |0        |6.681370241237138E-5|2.32413353875382E-5 |0.0                  |
|android_id_s=missing       |596.1167844308984   |2783    |498      |0        |7.614354374022504E-5|4.239628213550924E-5|0.0                  |
|imei_s=white               |3740.9802692761555  |134082  |52133    |61445    |0.003668515498302858|0.00443824372805322 |0.0028343069550972803|
|android_id_s=white         |4154.862166184032   |22598   |3471     |0        |6.182866695801673E-4|2.954969784987E-4   |0.0                  |
|android_serial_s=missing   |58309.71520348901   |1509466 |304626   |1033402  |0.041299349764779926|0.02593375470243301 |0.04766829646043518  |
|cpu_serial_s=white         |115029.19512550408  |19224571|6905954  |10367794 |0.5259888475837449  |0.5879252493952783  |0.4782408762831126   |
|mac_addr_s=white           |147036.04488999178  |8413635 |2214791  |5934121  |0.23019906023600012 |0.18855201627949128 |0.2737264288825589   |
|cpu_serial_s=missing       |159589.57542215497  |9585378 |2464837  |6575281  |0.2622582281744847  |0.2098392065663498  |0.30330156513986495  |
|android_serial_s=white     |179239.72056222052  |5605865 |2248480  |2580419  |0.15337780338817703 |0.1914200651727908  |0.11902839154959996  |
|cpu_serial_s=unique        |1629780.1356741278  |5254754 |3303     |4653182  |0.14377132268886902 |2.811946182602149E-4|0.21463985850652573  |
|advertising_id_s=missing   |1852030.708342774   |4657186 |1547199  |0        |0.12742171969003366 |0.131717842015618   |0.0                  |
|cpu_serial_s=duplicated    |2641108.758652607   |2484686 |2372219  |82764    |0.06798160155290148 |0.20195434942011165 |0.003817700070496726 |
|mac_addr_s=unique          |4034796.799442842   |15833849|405630   |13287018 |0.4332178849829747  |0.0345325379972422  |0.6128975104549232   |
|android_serial_s=unique    |5723235.7322711395  |19998742|301891   |17661201 |0.547170350782061   |0.02570091568307434 |0.8146678302493456   |
|imei_s=unique              |6924902.865579629   |23402311|163801   |20463410 |0.6402928103668163  |0.013944886365619578|0.943926849833302    |
|advertising_id_s=unique    |7055570.412943526   |22039544|351078   |21679021 |0.6030071802294698  |0.029888357308374126|1.0                  |
|android_id_s=unique        |7593148.583560198   |24793760|15354    |21679021 |0.6783631868647654  |0.001307133566081544|1.0                  |
|mac_addr_s=duplicated      |8002753.744969134   |9412374 |8258892  |844456   |0.2575247974733586  |0.7031050509210848  |0.038952681488707445 |
|android_serial_s=duplicated|9685416.019039545   |9435316 |8891316  |403999   |0.2581524960649821  |0.7569452644417018  |0.01863548174061919  |
|advertising_id_s=duplicated|1.1787985569071442E7|9850217 |9847763  |0        |0.2695042863780842  |0.8383705593406203  |0.0                  |
|imei_s=duplicated          |1.216122461099077E7 |11952132|11204334 |528577   |0.3270131820808277  |0.953859649406584   |0.024381958945470832 |
|android_id_s=duplicated    |1.4037460983641166E7|11730248|11726990 |0        |0.32094238292191424 |0.9983549731732843  |0.0                  |
+---------------------------+--------------------+--------+---------+---------+--------------------+--------------------+---------------------+
// *********   
// 注：以下的count都是select了id之后的count
// 78,522
jResultDF.select("original_id").distinct.count 
// cid 不同 4,507
jResultDF.drop("dt").drop("city_s").drop("original_city_s").distinct.filter("original_cid != client_id_s").select("original_id").distinct.count 
// cid + 机型 不同 12
jResultDF.drop("dt").drop("city_s").drop("original_city_s").distinct.filter("original_cid != client_id_s AND original_model_s != model_s").select("original_id").distinct.count
// cid + 机型 + 国家 不同 2
jResultDF.drop("dt").drop("city_s").drop("original_city_s").distinct.filter("original_cid != client_id_s AND original_country_s != country_s AND original_model_s != model_s").select("original_id").distinct.count 

// 针对cid不同的用户. 4392 种original_cid
idDF.join(installAppDF.toDF("original_cid","original_install_app"),Seq("original_cid"),"left_outer").join(installAppDF.toDF("client_id_s","install_app"),Seq("client_id_s"),"left_outer")
// 能同时取到ori_cid和cid在0702这一天对应的app列表 49

// app列表，能取到的中，不相同的有34
idDF.join(installAppDF.toDF("original_cid","original_install_app"),Seq("original_cid"),"inner").join(installAppDF.toDF("client_id_s","install_app"),Seq("client_id_s"),"inner").filter("original_install_app != install_app").count


HDFS
CID/analysis/submit/dt=2017-06-02/dupDF
CID/analysis/submit/dt=2017-06-02/extractorDF
CID/analysis/submit/dt=2017-06-10/featureRatioDF
CID/analysis/submit/dt=2017-06-02/transformedDF
CID/analysis/submit/dt=2017-06-02/joinedDF
CID/analysis/submit/dt=2017-06-02/labeledDF
CID/analysis/submit/dt=2017-06-30/newDF

CID/BaseDataCross
	CID/BaseDataCross/Model/ver=1_0630/featureAndParamsDF

CID/BaseData/FilterData
CID/BaseData/Model
	CID/BaseData/Model/ver=1_0602/aggregatedDF
	CID/BaseData/Model/ver=1_0602/aggregatedDF_idx
	CID/BaseData/Model/ver=1_0630/featureAndParamsDF
	CID/BaseData/Model/ver=1_0630/PNCountsFromRDD
	CID/BaseData/Model/ver=1_0602/featureMap
	CID/BaseData/Model/ver=1_0630/featureRatioDF
	CID/BaseData/Model/ver=1_0630/jResultDF
	CID/BaseData/Model/ver=1_0602/lrModel
	CID/BaseData/Model/ver=1_0602/predictResultDF_idx
	CID/BaseData/Model/ver=1_0602/trainRowDF
CID/BaseData/aggregatedDF
CID/BaseData/uniqueID
	CID/BaseData/uniqueID/dt=2017-06-01
	CID/BaseData/uniqueID/dt=2017-06-02
	CID/BaseData/uniqueID/dt=2017-06-03
	CID/BaseData/uniqueID/dt=2017-07-03
	CID/BaseData/uniqueID/dt=2017-07-04








+---+--------------------------------------------------------+----------------------+
|_1 |_2                                                      |_3                    |
+---+--------------------------------------------------------+----------------------+
|51 |android_serial_s=duplicated&&imei_s=duplicated          |-0.06366235162577456  |
|32 |cpu_serial_s=unique&&imei_s=unique                      |-0.0685844608763274   |
|31 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.08504116424534845  |
|48 |android_serial_s=unique&&cpu_serial_s=unique            |-0.09795093095598287  |
|42 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.110619329975039    |
|17 |cpu_serial_s=unique                                     |-0.1289970008551474   |
|2  |android_serial_s=duplicated&&mac_addr_s=duplicated      |-0.13756711085728443  |
|10 |advertising_id_s=unique&&cpu_serial_s=unique            |-0.14477494208447983  |
|30 |mac_addr_s=white                                        |-0.1479211419921043   |
|8  |imei_s=unique&&mac_addr_s=unique                        |-0.17205896662099412  |
|3  |android_serial_s=unique&&mac_addr_s=unique              |-0.1782770758899344   |
|28 |android_id_s=unique&&cpu_serial_s=unique                |-0.17902409360705293  |
|41 |android_id_s=unique&&mac_addr_s=unique                  |-0.30782755243180315  |
|52 |android_serial_s=unique&&imei_s=unique                  |-0.30918603382729315  |
|23 |advertising_id_s=unique&&mac_addr_s=unique              |-0.3420868027373311   |
|25 |android_serial_s=unique                                 |-0.38298984377595835  |
|20 |imei_s=unique                                           |-0.4090089455345103   |
|18 |advertising_id_s=unique&&android_serial_s=unique        |-0.430422679082154    |
|35 |android_id_s=unique&&android_serial_s=unique            |-0.4714412360733804   |
|15 |advertising_id_s=unique&&imei_s=unique                  |-0.596607248654283    |
|1  |android_serial_s=missing                                |-0.6982122159379904   |
|34 |android_id_s=unique&&imei_s=unique                      |-0.7492396913013845   |
|5  |advertising_id_s=unique                                 |-1.2022915308532707   |
|39 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-1.4236828245014642E-4|
|16 |android_id_s=unique                                     |-1.8085774941552892   |
|12 |advertising_id_s=unique&&android_id_s=unique            |-2.133537807374973    |
|49 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |-8.688665898702117E-4 |
|26 |mac_addr_s=unique                                       |0.018526987997933935  |
|6  |imei_s=missing                                          |0.019122491096400688  |
|38 |cpu_serial_s=missing                                    |0.019165307838939383  |
|44 |cpu_serial_s=white                                      |0.027864547685538504  |
|46 |cpu_serial_s=duplicated                                 |0.08195650540811132   |
|0  |mac_addr_s=duplicated                                   |0.09349584772861382   |
|27 |mac_addr_s=missing                                      |0.1425812462939821    |
|4  |android_serial_s=white                                  |0.1502585120407979    |
|13 |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.17712473299181014   |
|22 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.32014816323647527   |
|53 |android_serial_s=duplicated                             |0.36380117834004105   |
|50 |imei_s=duplicated                                       |0.39635149272168263   |
|54 |intercept_of_model                                      |0.4214283145706156    |
|29 |android_id_s=duplicated&&cpu_serial_s=duplicated        |0.5048518974193673    |
|14 |advertising_id_s=duplicated&&imei_s=duplicated          |0.5836416073108115    |
|11 |advertising_id_s=duplicated&&android_id_s=duplicated    |0.6343775910875648    |
|19 |advertising_id_s=duplicated&&android_serial_s=duplicated|0.6972384566145609    |
|36 |android_id_s=duplicated&&android_serial_s=duplicated    |0.9447476597653313    |
|40 |android_id_s=duplicated&&mac_addr_s=duplicated          |0.9602357261995637    |
|45 |advertising_id_s=duplicated                             |0.9937937079845114    |
|24 |advertising_id_s=white                                  |1.257050201581667     |
|33 |android_id_s=duplicated&&imei_s=duplicated              |1.2799651559240843    |
|21 |advertising_id_s=missing                                |1.4441644630649169    |
|9  |imei_s=white                                            |1.5679632956912746    |
|47 |android_id_s=duplicated                                 |1.806924764823496     |
|37 |android_id_s=missing                                    |2.9928904263721576    |
|43 |android_id_s=white                                      |3.857506075866875     |
|7  |imei_s=duplicated&&mac_addr_s=duplicated                |7.913329994068787E-4  |
+---+--------------------------------------------------------+----------------------+

+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+
|feature                                                 |score               |count   |pos_count|neg_count|total_ratio          |pos_ratio            |neg_ratio            |
+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+
|android_serial_s=missing                                |3.763804006484036   |57      |1        |15       |1.6127284451062605E-6|8.869344008239266E-8 |7.133721902409543E-7 |
|android_id_s=missing                                    |47.34576348062813   |161     |39       |0        |4.555250520387858E-6 |3.459044163213314E-6 |0.0                  |
|imei_s=missing                                          |152.82918363035807  |1009782 |309395   |596926   |0.028570248329057718 |0.027441306894291875 |0.028388693868784794 |
|advertising_id_s=white                                  |356.9142170078121   |2737    |294      |0        |7.743925884659359E-5 |2.607587138422344E-5 |0.0                  |
|imei_s=white                                            |3183.388999521306   |143847  |54093    |67570    |0.004069932432336846 |0.004797694254376866 |0.0032135039263054186|
|android_id_s=white                                      |4416.5099369878235  |24212   |3638     |0        |6.850417739107504E-4 |3.226667350197445E-4 |0.0                  |
|cpu_serial_s=white                                      |58783.11071049614   |20808699|7123973  |11514153 |0.588750540051828    |0.6318496724240831   |0.547591769625297    |
|mac_addr_s=missing                                      |59805.49226896632   |1864890 |700158   |798948   |0.05276423070165288  |0.06209942162120788  |0.037996485643242    |
|cpu_serial_s=missing                                    |105601.69377116107  |7045210 |1868192  |4916826  |0.19933351874994873  |0.1656963752144053   |0.23383512884357802  |
|android_serial_s=white                                  |140676.1545052193   |6080603 |2325826  |2887954  |0.17204142844734144  |0.206285508973071    |0.137345738019675    |
|mac_addr_s=white                                        |210579.30388192422  |9057521 |2257185  |6509268  |0.25626880278679476  |0.20019750255237548  |0.3095687180016904   |
|cpu_serial_s=unique&&mac_addr_s=unique                  |1196381.5927780839  |3973754 |2002     |3437030  |0.11243133525710146  |1.775642670449501E-4 |0.16345877460159114  |
|cpu_serial_s=unique&&imei_s=unique                      |1478359.792799917   |4763417 |711      |4238785  |0.13477365073338118  |6.306103589858118E-5 |0.20158875596070022  |
|android_serial_s=unique&&cpu_serial_s=unique            |1558962.9003850655  |5048243 |1932     |4475478  |0.1428323698931327   |1.713557262391826E-4 |0.2128454362156804   |
|cpu_serial_s=unique                                     |1572136.6567291315  |5103728 |2883     |4517710  |0.14440223371377692  |2.55703187757538E-4  |0.21485391183823077  |
|advertising_id_s=unique&&cpu_serial_s=unique            |1575821.5363658285  |4520905 |648      |4517710  |0.12791214194952838  |5.747334917339044E-5 |0.21485391183823077  |
|android_id_s=unique&&cpu_serial_s=unique                |1576891.0879538383  |5096890 |0        |4517710  |0.14420876288732715  |0.0                  |0.21485391183823077  |
|advertising_id_s=missing                                |1811220.6799089774  |4519508 |1491952  |0        |0.12787261595588253  |0.1323263553178059   |0.0                  |
|advertising_id_s=duplicated&&cpu_serial_s=duplicated    |2231793.096907397   |1838389 |1838389  |0        |0.05201442514860444  |0.16305304461962974  |0.0                  |
|cpu_serial_s=duplicated&&mac_addr_s=duplicated          |2324189.5376632595  |2060602 |1998165  |41325    |0.05830160455162896  |0.1772241277022341   |0.001965340384113829 |
|cpu_serial_s=duplicated&&imei_s=duplicated              |2462461.8033131068  |2190630 |2123531  |47067    |0.06198054936321276  |0.18834326951160335  |0.0022384192585380666|
|android_serial_s=duplicated&&cpu_serial_s=duplicated    |2543915.5364420773  |2294878 |2212507  |58159    |0.06493008822190464  |0.19623485703637433  |0.002765934214148244 |
|cpu_serial_s=duplicated                                 |2578273.881396518   |2386193 |2279743  |78203    |0.06751370748444636  |0.2021982491737541   |0.0037191896928942232|
|android_id_s=duplicated&&cpu_serial_s=duplicated        |2765767.115399469   |2278238 |2278238  |0        |0.06445928468985959  |0.20206476554643008  |0.0                  |
|android_serial_s=unique&&mac_addr_s=unique              |3809983.4890296957  |12473437|3800     |10933365 |0.3529169589147526   |3.370350723130921E-4 |0.5199705691169194   |
|mac_addr_s=unique                                       |3897195.734029315   |15383338|388051   |12907741 |0.43524818900498335  |0.034417578117412556 |0.613868231215531    |
|imei_s=unique&&mac_addr_s=unique                        |4230683.348359015   |14455326|65775    |12428952 |0.40899149865761575  |0.005833811021419377 |0.591097914042646    |
|advertising_id_s=unique&&mac_addr_s=unique              |4477842.408869209   |12929930|16731    |12907741 |0.3658327351619788   |0.0014839299460185115|0.613868231215531    |
|android_id_s=unique&&mac_addr_s=unique                  |4487737.9558655955  |14993018|10715    |12907741 |0.42420467730859956  |9.503502104828373E-4 |0.613868231215531    |
|android_serial_s=unique&&imei_s=unique                  |5988418.6468414385  |19116837|3010     |17170737 |0.5408818738659619   |2.669672546480019E-4 |0.8166084174494261   |
|android_serial_s=unique                                 |6042152.7822430255  |19869396|91798    |17740766 |0.5621743880049219   |0.008141880412683482 |0.8437179398648169   |
|advertising_id_s=unique&&android_serial_s=unique        |6186276.539251122   |17748420|3682     |17740766 |0.5021645928016291   |3.265692463833698E-4 |0.8437179398648169   |
|android_id_s=unique&&android_serial_s=unique            |6192130.285203679   |19764748|135      |17740766 |0.5592135317536329   |1.197361441112301E-5 |0.8437179398648169   |
|imei_s=unique                                           |6678912.73268059    |22724323|155866   |19862002 |0.6429502122435514   |0.013824291731882214 |0.9445999912873476   |
|advertising_id_s=unique                                 |6798831.575695357   |21372601|336610   |21026892 |0.6047052908527457   |0.029855098866134192 |1.0                  |
|advertising_id_s=unique&&imei_s=unique                  |6921747.92918472    |19874126|6676     |19862002 |0.5623082161723842   |5.921174059900534E-4 |0.9445999912873476   |
|android_id_s=unique&&imei_s=unique                      |6932282.852593274   |22553545|291      |19862002 |0.6381183080611241   |2.5809791063976263E-5|0.9445999912873476   |
|android_serial_s=duplicated&&mac_addr_s=duplicated      |6964794.005047016   |6446822 |6147657  |205693   |0.1824030389462602   |0.5452568477766018   |0.009782377728482174 |
|android_id_s=unique                                     |7315592.254697989   |24059532|14416    |21026892 |0.6807279233744616   |0.0012786046322277725|1.0                  |
|advertising_id_s=unique&&android_id_s=unique            |7339364.103089364   |21026892|0        |21026892 |0.5949239796592503   |0.0                  |1.0                  |
|mac_addr_s=duplicated                                   |7801564.1699300865  |9038081 |7929397  |810935   |0.255718777506569    |0.7032854977090041   |0.03856656513953655  |
|advertising_id_s=duplicated&&mac_addr_s=duplicated      |7937888.72967154    |6538656 |6538656  |0        |0.18500134252569686  |0.5799358941553773   |0.0                  |
|imei_s=duplicated&&mac_addr_s=duplicated                |8652100.324541261   |8068170 |7664376  |269757   |0.2282766185781224   |0.6797798735249283   |0.012829142794855274 |
|advertising_id_s=duplicated&&android_serial_s=duplicated|9163828.365396602   |7548496 |7548496  |0        |0.21357323187668117  |0.6695020776881806   |0.0                  |
|android_serial_s=duplicated&&imei_s=duplicated          |9451901.321935281   |8885923 |8441184  |330420   |0.2514136979495431   |0.7486776473284515   |0.01571416260662774  |
|android_id_s=duplicated&&mac_addr_s=duplicated          |9621836.713380974   |7925770 |7925770  |0        |0.22424762681350607  |0.7029638066018252   |0.0                  |
|android_serial_s=duplicated                             |9799894.282282429   |9393774 |8857166  |398157   |0.2657825708192915   |0.7855725219208054   |0.018935608743317844 |
|android_id_s=duplicated&&android_serial_s=duplicated    |1.0733914643910872E7|8841819 |8841819  |0        |0.25016584224177174  |0.784211343695861    |0.0                  |
|advertising_id_s=duplicated&&imei_s=duplicated          |1.0948359388635797E7|9018463 |9018463  |0        |0.25516371598663756  |0.7998785077257752   |0.0                  |
|advertising_id_s=duplicated&&android_id_s=duplicated    |1.1445355151855562E7|9427852 |9427852  |0        |0.26674675608161313  |0.8361886264676658   |0.0                  |
|advertising_id_s=duplicated                             |1.1467307804189416E7|9448984 |9445935  |0        |0.26734465393252516  |0.8377924699446757   |0.0                  |
|imei_s=duplicated                                       |1.1861826448012408E7|11465878|10755437 |500394   |0.324409606995054    |0.953936707119449    |0.02379781091756214  |
|android_id_s=duplicated&&imei_s=duplicated              |1.3047682681306202E7|10747733|10747733 |0        |0.3040907847281973   |0.9532534128570543   |0.0                  |
|android_id_s=duplicated                                 |1.3665563104637433E7|11259925|11256698 |0        |0.3185824796011072   |0.9983952695885893   |0.0                  |
+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+




+---+--------------------------------------------------------+---------------------+
|_1 |_2                                                      |_3                   |
+---+--------------------------------------------------------+---------------------+
|12 |advertising_id_s=unique&&android_id_s=unique            |-2.1328415075254914  |
|16 |android_id_s=unique                                     |-1.8082939400153533  |
|5  |advertising_id_s=unique                                 |-1.2032202860182655  |
|37 |android_id_s=missing                                    |-0.758244246805194   |
|34 |android_id_s=unique&&imei_s=unique                      |-0.7478641469212697  |
|1  |android_serial_s=missing                                |-0.6961678768214293  |
|15 |advertising_id_s=unique&&imei_s=unique                  |-0.5963246514609298  |
|35 |android_id_s=unique&&android_serial_s=unique            |-0.47089897099141714 |
|18 |advertising_id_s=unique&&android_serial_s=unique        |-0.429940076106613   |
|20 |imei_s=unique                                           |-0.40939996509899024 |
|25 |android_serial_s=unique                                 |-0.38296951402582013 |
|23 |advertising_id_s=unique&&mac_addr_s=unique              |-0.3405552531694388  |
|52 |android_serial_s=unique&&imei_s=unique                  |-0.30990566034990863 |
|41 |android_id_s=unique&&mac_addr_s=unique                  |-0.307199448840386   |
|3  |android_serial_s=unique&&mac_addr_s=unique              |-0.17875136935492872 |
|28 |android_id_s=unique&&cpu_serial_s=unique                |-0.17850765292142032 |
|8  |imei_s=unique&&mac_addr_s=unique                        |-0.17329022395091295 |
|30 |mac_addr_s=white                                        |-0.14686620340635226 |
|10 |advertising_id_s=unique&&cpu_serial_s=unique            |-0.1440483227158506  |
|2  |android_serial_s=duplicated&&mac_addr_s=duplicated      |-0.13802890990128996 |
|17 |cpu_serial_s=unique                                     |-0.12933600476054327 |
|42 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.1105428494171108  |
|48 |android_serial_s=unique&&cpu_serial_s=unique            |-0.09858266276179953 |
|31 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.08434454653491238 |
|32 |cpu_serial_s=unique&&imei_s=unique                      |-0.0689280821111014  |
|51 |android_serial_s=duplicated&&imei_s=duplicated          |-0.06389199927048307 |
|49 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |-0.001419314052640841|
|39 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-5.542274718796297E-4|
|7  |imei_s=duplicated&&mac_addr_s=duplicated                |1.7425935830348707E-4|
|26 |mac_addr_s=unique                                       |0.017258428188276925 |
|38 |cpu_serial_s=missing                                    |0.019815949045589868 |
|6  |imei_s=missing                                          |0.020097449748149577 |
|44 |cpu_serial_s=white                                      |0.027638160105602213 |
|46 |cpu_serial_s=duplicated                                 |0.08238089029691316  |
|0  |mac_addr_s=duplicated                                   |0.0937476934051311   |
|27 |mac_addr_s=missing                                      |0.14422975450402392  |
|4  |android_serial_s=white                                  |0.15160075739515524  |
|13 |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.17959165108124128  |
|22 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.3224204752610621   |
|53 |android_serial_s=duplicated                             |0.3631317114421932   |
|50 |imei_s=duplicated                                       |0.3966691444059289   |
|54 |intercept_of_model                                      |0.41718915080276164  |
|29 |android_id_s=duplicated&&cpu_serial_s=duplicated        |0.5052227406578211   |
|14 |advertising_id_s=duplicated&&imei_s=duplicated          |0.5841683364571292   |
|11 |advertising_id_s=duplicated&&android_id_s=duplicated    |0.635744209603027    |
|19 |advertising_id_s=duplicated&&android_serial_s=duplicated|0.6980854511940323   |
|36 |android_id_s=duplicated&&android_serial_s=duplicated    |0.9467347626271004   |
|40 |android_id_s=duplicated&&mac_addr_s=duplicated          |0.9612564362993945   |
|45 |advertising_id_s=duplicated                             |0.9950961079628126   |
|24 |advertising_id_s=white                                  |1.25192603385696     |
|33 |android_id_s=duplicated&&imei_s=duplicated              |1.280737102246222    |
|21 |advertising_id_s=missing                                |1.4453871439590709   |
|9  |imei_s=white                                            |1.5730669567044124   |
|47 |android_id_s=duplicated                                 |1.806705756685552    |
|43 |android_id_s=white                                      |3.8661761068493656   |
+---+--------------------------------------------------------+---------------------+
0 -> 20,345,950 
1 -> 10,888,087
5 ->  2,943,336


// whiteList_v2 训练的model ，使用18天数据
+---+--------------------------------------------------------+---------------------+
|_1 |_2                                                      |_3                   |
+---+--------------------------------------------------------+---------------------+
|8  |advertising_id_s=unique&&android_id_s=unique            |-3.2798851134159412  |
|38 |android_id_s=unique                                     |-2.4625077630730714  |1   1
|47 |android_id_s=white                                      |-2.0675022924149413  |
|3  |advertising_id_s=unique                                 |-1.3623400094144078  |
|32 |android_serial_s=missing                                |-1.0087827106609262  |
|22 |android_id_s=missing                                    |-0.9448879330906785  |
|21 |android_id_s=unique&&imei_s=unique                      |-0.8128588798039648  |1   1
|52 |android_serial_s=unique&&imei_s=unique                  |-0.5208623279674116  |   1
|54 |intercept_of_model                                      |-0.5160532953186279  |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.5000931582118472  |
|13 |android_serial_s=unique                                 |-0.4822179828558668  |   1
|37 |advertising_id_s=unique&&imei_s=unique                  |-0.43583583768079065 |
|45 |android_id_s=unique&&android_serial_s=unique            |-0.3716065313344272  |   1
|10 |imei_s=unique                                           |-0.34836547971348764 |1   1  
|5  |imei_s=unique&&mac_addr_s=unique                        |-0.27971421836170585 |
|18 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.2752289832513992  |
|26 |android_id_s=unique&&mac_addr_s=unique                  |-0.25676363750372083 |
|40 |advertising_id_s=unique&&android_serial_s=unique        |-0.2072505372384355  |
|16 |android_id_s=unique&&cpu_serial_s=unique                |-0.1878976228829235  |
|43 |advertising_id_s=unique&&mac_addr_s=unique              |-0.13536874011425146 |
|39 |cpu_serial_s=unique                                     |-0.11571859644307993 |
|27 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.1004516169626449  |
|30 |android_serial_s=unique&&cpu_serial_s=unique            |-0.0880888338686222  |
|4  |imei_s=duplicated&&mac_addr_s=duplicated                |-0.06563352486356108 |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |-0.06508689232002701 |
|51 |android_serial_s=duplicated&&imei_s=duplicated          |-0.024744137873050086|
|6  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.01900756238114695 |
|44 |mac_addr_s=white                                        |0.02365266962122187  |1   1
|19 |cpu_serial_s=unique&&imei_s=unique                      |0.036188151506431544 |
|14 |mac_addr_s=unique                                       |0.03990232127033712  |
|24 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |0.046300847204438424 |
|31 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.11023252330554949  |
|48 |cpu_serial_s=white                                      |0.21469005216611362  |1   1
|9  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.25805512410613946  |
|34 |imei_s=missing                                          |0.2743809699071965   |
|23 |cpu_serial_s=missing                                    |0.30631672428969414  |
|28 |cpu_serial_s=duplicated                                 |0.32551631125205477  |
|15 |mac_addr_s=missing                                      |0.34890354246617655  |
|0  |mac_addr_s=duplicated                                   |0.4315073213819976   |
|42 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.475375111565445    |
|33 |android_serial_s=white                                  |0.5036955161141266   |1
|7  |advertising_id_s=duplicated&&android_id_s=duplicated    |0.5315034676145803   |
|17 |android_id_s=duplicated&&cpu_serial_s=duplicated        |0.5713799016680402   |
|35 |imei_s=white                                            |0.6669999217427628   |
|50 |imei_s=duplicated                                       |0.8355588274633964   |
|53 |android_serial_s=duplicated                             |0.8497241084266651   |
|36 |advertising_id_s=duplicated&&imei_s=duplicated          |0.9096344727015309   |
|41 |advertising_id_s=duplicated&&android_serial_s=duplicated|1.0602109299449842   |
|46 |android_id_s=duplicated&&android_serial_s=duplicated    |1.1396116403229997   |
|25 |android_id_s=duplicated&&mac_addr_s=duplicated          |1.1816548927140278   |
|20 |android_id_s=duplicated&&imei_s=duplicated              |1.519893413614386    |
|49 |advertising_id_s=duplicated                             |1.6662156970127655   |
|11 |advertising_id_s=missing                                |1.905355925090083    |1   1
|12 |advertising_id_s=white                                  |1.9256734626097651   |
|29 |android_id_s=duplicated                                 |2.9807741490312116   |
+---+--------------------------------------------------------+---------------------+



+---+--------------------------------------------------------+---------------------+
|_1 |_2                                                      |_3                   |
+---+--------------------------------------------------------+---------------------+
|38 |android_id_s=unique                                     |-2.251418938680964   |
|32 |android_serial_s=missing                                |-1.5948812072271759  |
|54 |intercept_of_model                                      |-1.237844191675636   |
|21 |android_id_s=unique&&imei_s=unique                      |-1.1864734582442027  |
|8  |advertising_id_s=unique&&android_id_s=unique            |-1.0283792047616214  |
|3  |advertising_id_s=unique                                 |-0.9484281339789249  |
|26 |android_id_s=unique&&mac_addr_s=unique                  |-0.6662398646833266  |
|45 |android_id_s=unique&&android_serial_s=unique            |-0.6223923949991857  |
|10 |imei_s=unique                                           |-0.5756058953873961  |
|13 |android_serial_s=unique                                 |-0.39029997609330247 |
|37 |advertising_id_s=unique&&imei_s=unique                  |-0.3267169845176258  |
|52 |android_serial_s=unique&&imei_s=unique                  |-0.32357447734854733 |
|12 |advertising_id_s=white                                  |-0.24469325942373663 |
|5  |imei_s=unique&&mac_addr_s=unique                        |-0.2207451812775433  |
|16 |android_id_s=unique&&cpu_serial_s=unique                |-0.20450703990059516 |
|40 |advertising_id_s=unique&&android_serial_s=unique        |-0.19893703845889613 |
|14 |mac_addr_s=unique                                       |-0.1797813088666825  |
|39 |cpu_serial_s=unique                                     |-0.16632354544716554 |
|43 |advertising_id_s=unique&&mac_addr_s=unique              |-0.16291694402119275 |
|27 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.1499417025422373  |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.12285749634236447 |
|30 |android_serial_s=unique&&cpu_serial_s=unique            |-0.11201558815361255 |
|6  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.07731158937968712 |
|19 |cpu_serial_s=unique&&imei_s=unique                      |-0.04162560377400169 |
|18 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.010568749417568793|
|44 |mac_addr_s=white                                        |0.008225290670381299 |
|23 |cpu_serial_s=missing                                    |0.024889308158347022 |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.031209153756245715 |
|51 |android_serial_s=duplicated&&imei_s=duplicated          |0.051285476724056624 |
|48 |cpu_serial_s=white                                      |0.06889344316587993  |
|24 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |0.06989248964560742  |
|34 |imei_s=missing                                          |0.08021542998900308  |
|31 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.08210038048206877  |
|4  |imei_s=duplicated&&mac_addr_s=duplicated                |0.15469536457645025  |
|28 |cpu_serial_s=duplicated                                 |0.21657001955973582  |
|9  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.2286712213204028   |
|0  |mac_addr_s=duplicated                                   |0.2369701053796213   |
|33 |android_serial_s=white                                  |0.2789330108430423   |
|15 |mac_addr_s=missing                                      |0.3305392809396351   |
|47 |android_id_s=white                                      |0.3510433391106983   |
|42 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.362929207334651    |
|22 |android_id_s=missing                                    |0.3829741175698255   |
|53 |android_serial_s=duplicated                             |0.3890818972472175   |
|35 |imei_s=white                                            |0.4524837632342435   |
|36 |advertising_id_s=duplicated&&imei_s=duplicated          |0.5180941849998042   |
|11 |advertising_id_s=missing                                |0.5287711249262941   |
|41 |advertising_id_s=duplicated&&android_serial_s=duplicated|0.5382652238243669   |
|17 |android_id_s=duplicated&&cpu_serial_s=duplicated        |0.5700836737314239   |
|50 |imei_s=duplicated                                       |0.6489403367588011   |
|7  |advertising_id_s=duplicated&&android_id_s=duplicated    |0.9315564717689114   |
|25 |android_id_s=duplicated&&mac_addr_s=duplicated          |0.961687921762382    |
|49 |advertising_id_s=duplicated                             |1.0134339879885776   |
|46 |android_id_s=duplicated&&android_serial_s=duplicated    |1.0474413395735025   |
|20 |android_id_s=duplicated&&imei_s=duplicated              |1.3260060681445678   |
|29 |android_id_s=duplicated                                 |2.292859327282325    |
+---+--------------------------------------------------------+---------------------+


============================================================================================================================================================

+--------------------------------------------------------+-------------------+-------+---------+---------+---------------------+---------------------+---------------------+
|feature                                                 |score              |count  |pos_count|neg_count|total_ratio          |pos_ratio            |neg_ratio            |
+--------------------------------------------------------+-------------------+-------+---------+---------+---------------------+---------------------+---------------------+
|android_serial_s=missing                                |0.21539826605466159|568    |0        |2        |1.0850284036748842E-4|0.0                  |4.3461027627306044E-7|
|android_id_s=white                                      |2.987943298976952  |4570   |131      |915      |8.729894022525037E-4 |2.3585245358567745E-4|1.9883420139492515E-4|
|android_id_s=missing                                    |4.119360218931871  |587    |4        |10       |1.1213233678823187E-4|7.201601636203892E-6 |2.173051381365302E-6 |
|advertising_id_s=white                                  |39.43803677070967  |1317   |18       |640      |2.515814097957434E-4 |3.240720736291751E-5 |1.3907528840737934E-4|
|imei_s=missing                                          |93.01256916146778  |144540 |13722    |124619   |0.027610916455487283 |0.02470509441299745  |0.027080349009436257 |
|imei_s=white                                            |255.31704675781464 |24533  |2974     |17648    |0.004686443983689425 |0.005354390816517594 |0.003835001077833485 |
|cpu_serial_s=white                                      |3503.349023534852  |3063364|355796   |2635860  |0.5851825617597021   |0.6405752639387      |0.5727859214085546   |
|mac_addr_s=missing                                      |4131.092147473597  |288941 |37470    |212520   |0.055195280279264916 |0.06746100332713996  |0.0461816879567754   |
|android_serial_s=white                                  |8532.647475054844  |873447 |114672   |696555   |0.16685119790574235  |0.20645551570669318  |0.1513649804946908   |
|advertising_id_s=missing                                |9275.156874394197  |699306 |93132    |538398   |0.13358571705286418  |0.1676748908957352   |0.1169966517624316   |
|cpu_serial_s=missing                                    |9478.404784427603  |994876 |74867    |915514   |0.19004730953071375  |0.1347905774244192   |0.19894589623592732  |
|mac_addr_s=white                                        |17103.69245623963  |1266249|87188    |1169015  |0.24188664280368283  |0.15697331086433622  |0.25403296605867587  |
|cpu_serial_s=unique&&mac_addr_s=unique                  |89990.14111965882  |837984 |115      |837589   |0.1600768383494884   |2.0704604704086188E-4|0.1820123933466382   |
|advertising_id_s=unique&&cpu_serial_s=unique            |99134.79026986443  |920588 |0        |920479   |0.17585636057786166  |0.0                  |0.2000248162467752   |
|cpu_serial_s=unique&&imei_s=unique                      |105789.12561397535 |983435 |56       |983249   |0.18786177960704398  |1.0082242290685448E-4|0.2136650597676052   |
|android_serial_s=unique&&cpu_serial_s=unique            |111925.82353355008 |1041027|81       |1040668  |0.1988633563366996   |1.4583243313312882E-4|0.22614250349426662  |
|cpu_serial_s=unique                                     |112610.39752318026 |1048451|131      |1047902  |0.20028153430657325  |2.3585245358567745E-4|0.22771448886354628  |
|android_id_s=unique&&cpu_serial_s=unique                |112834.55078747302 |1047701|0        |1047683  |0.2001382647110176   |0.0                  |0.22766689903829437  |
|android_serial_s=unique&&mac_addr_s=unique              |277617.2931464398  |2583281|223      |2581628  |0.49347416543550326  |4.0148929121836696E-4|0.5610010291571342   |
|advertising_id_s=unique&&mac_addr_s=unique              |289244.02654198825 |2687265|19       |2686001  |0.51333782626785     |3.420760777196849E-5 |0.5836818183398583   |
|mac_addr_s=unique                                       |306773.17797609285 |3164927|16999    |3139211  |0.6045837483375951   |0.03060500655345749  |0.6821666799947151   |
|imei_s=unique&&mac_addr_s=unique                        |324287.80057131243 |3038983|1425     |3036033  |0.5805251537473787   |0.0025655705828976365|0.6597455704520643   |
|android_id_s=unique&&mac_addr_s=unique                  |337993.88214898173 |3144364|4        |3138386  |0.6006556780797137   |7.201601636203892E-6 |0.6819874032557525   |
|advertising_id_s=unique&&android_serial_s=unique        |371762.43703085516 |3452105|0        |3451861  |0.6594422495542406   |0.0                  |0.7501071314331013   |
|android_serial_s=unique&&imei_s=unique                  |403684.4101387356  |3754320|222      |3752160  |0.7171732106487133   |3.99688890809316E-4  |0.8153636471103632   |
|android_serial_s=unique                                 |416632.6024817162  |3878907|341      |3874475  |0.7409725827840377   |6.139365394863818E-4 |0.8419433250815329   |
|android_id_s=unique&&android_serial_s=unique            |417205.5477160723  |3875700|0        |3873806  |0.7403599619934417   |0.0                  |0.8417979479441196   |
|advertising_id_s=unique                                 |419203.3817337856  |4081451|9816     |4062786  |0.7796637787336725   |0.01767273041524435  |0.882864272949161    |
|advertising_id_s=unique&&imei_s=unique                  |422084.364728107   |3919635|1        |3919124  |0.7487526949010924   |1.800400409050973E-6 |0.8516457821941908   |
|advertising_id_s=unique&&android_id_s=unique            |437458.90817752696 |4061861|0        |4061861  |0.7759215768977586   |0.0                  |0.8826632656963848   |
|imei_s=unique                                           |469073.50575628725 |4495238|4811     |4439488  |0.8587079069152604   |0.00866172636794423  |0.9647235530954682   |
|android_id_s=unique&&imei_s=unique                      |478044.0340692802  |4487200|0        |4438699  |0.8571724389031585   |0.0                  |0.9645520993414786   |
|android_id_s=unique                                     |495435.2525031821  |4666609|41       |4600899  |0.8914442453952197   |7.381641677108989E-5 |0.9997989927472237   |
|advertising_id_s=duplicated&&cpu_serial_s=duplicated    |719085.2967510299  |97269  |97268    |0        |0.018580920386804985 |0.17512134698757004  |0.0                  |
|cpu_serial_s=duplicated&&mac_addr_s=duplicated          |818154.5390437982  |114821 |112529   |1519     |0.021933810975062303 |0.20259725763009692  |3.300865048293894E-4 |
|cpu_serial_s=duplicated&&imei_s=duplicated              |858828.7182451808  |120280 |118055   |1538     |0.022976622604580117 |0.21254627029051262  |3.342153024539835E-4 |
|cpu_serial_s=duplicated                                 |898516.7984733938  |128195 |124638   |2548     |0.024488594403010877 |0.22439830618329518  |5.53693491971879E-4  |
|android_serial_s=duplicated&&cpu_serial_s=duplicated    |901872.8429023667  |127692 |124573   |2114     |0.02439250826092488  |0.22428128015670684  |4.5938306202062486E-4|
|android_id_s=duplicated&&cpu_serial_s=duplicated        |921153.3809729313  |124654 |124601   |0        |0.023812170885860743 |0.22433169136816028  |0.0                  |
|android_serial_s=duplicated&&mac_addr_s=duplicated      |2360115.5662049605 |340786 |328515   |7644     |0.06509902985470935  |0.5914585403793804   |0.0016610804759156369|
|mac_addr_s=duplicated                                   |2438222.630929608  |514769 |413775   |81078    |0.09833432857945712  |0.7449606792550664   |0.017618665989833596 |
|advertising_id_s=duplicated&&mac_addr_s=duplicated      |2460709.17577083   |332866 |332851   |0        |0.06358610292564155  |0.5992650765530254   |0.0                  |
|advertising_id_s=duplicated&&android_serial_s=duplicated|2681954.2479000813 |363027 |362778   |0        |0.06934764195438067  |0.6531456595946938   |0.0                  |
|imei_s=duplicated&&mac_addr_s=duplicated                |2870737.3613729794 |420446 |400814   |10332    |0.08031617116399478  |0.7216256895533567   |0.0022451966872266303|
|android_serial_s=duplicated                             |2992032.314051827  |481964 |440419   |30792    |0.09206771646985244  |0.7929305477538204   |0.006691259813500038 |
|android_serial_s=duplicated&&imei_s=duplicated          |3033439.389128341  |441298 |423061   |10520    |0.08429944797269702  |0.7616791974535136   |0.0022860500531962976|
|android_id_s=duplicated&&mac_addr_s=duplicated          |3057982.8959029648 |417291 |413642   |0        |0.07971348373202396  |0.7447212260006626   |0.0                  |
|advertising_id_s=duplicated&&imei_s=duplicated          |3221209.077863794  |435959 |435721   |0        |0.0832795594784681   |0.784472266632099    |0.0                  |
|android_id_s=duplicated&&android_serial_s=duplicated    |3254986.8950375356 |443135 |440290   |0        |0.08465036296874469  |0.7926982961010529   |0.0                  |
|advertising_id_s=duplicated&&android_id_s=duplicated    |3343700.79437763   |452290 |452290   |0        |0.08639920716516081  |0.8143031010096645   |0.0                  |
|advertising_id_s=duplicated                             |3345001.931567952  |452812 |452466   |0        |0.08649892280366756  |0.8146199714816575   |0.0                  |
|imei_s=duplicated                                       |3769782.75563397   |570575 |533925   |20069    |0.10899473264556286  |0.9612787884025408   |0.004361096817262025 |
|android_id_s=duplicated&&imei_s=duplicated              |3946430.4193191812 |540833 |533819   |0        |0.1033132335642075   |0.9610879459591813   |0.0                  |
|android_id_s=duplicated                                 |4104910.4076653155 |563120 |555256   |0        |0.10757063286573958  |0.999683129528007    |0.0                  |
+--------------------------------------------------------+-------------------+-------+---------+---------+---------------------+---------------------+---------------------+



 =========  model 
Map(0 -> 4,601,824, 1 -> 555,432, 5 -> 77,630)


+---+--------------------------------------------------------+----------------------+
|_1 |_2                                                      |_3                    |
+---+--------------------------------------------------------+----------------------+
|38 |android_id_s=unique                                     |-2.5314504241464624   |
|54 |intercept_of_model                                      |-2.0276785690664876   |
|32 |android_serial_s=missing                                |-1.9555449524969903   |
|21 |android_id_s=unique&&imei_s=unique                      |-1.2157484619662393   |
|8  |advertising_id_s=unique&&android_id_s=unique            |-0.7340102226350019   |
|3  |advertising_id_s=unique                                 |-0.719501042565919    |
|10 |imei_s=unique                                           |-0.7001638752926361   |
|45 |android_id_s=unique&&android_serial_s=unique            |-0.4862090645723314   |
|26 |android_id_s=unique&&mac_addr_s=unique                  |-0.4304750528569099   |
|13 |android_serial_s=unique                                 |-0.3678464324361273   |
|37 |advertising_id_s=unique&&imei_s=unique                  |-0.31660910960348043  |
|52 |android_serial_s=unique&&imei_s=unique                  |-0.23019289535955592  |
|5  |imei_s=unique&&mac_addr_s=unique                        |-0.19311056082948608  |
|40 |advertising_id_s=unique&&android_serial_s=unique        |-0.18601351178101536  |
|14 |mac_addr_s=unique                                       |-0.17261163278839103  |
|43 |advertising_id_s=unique&&mac_addr_s=unique              |-0.14898690506970932  |
|12 |advertising_id_s=white                                  |-0.1384403645046877   |
|16 |android_id_s=unique&&cpu_serial_s=unique                |-0.13042311252243147  |
|39 |cpu_serial_s=unique                                     |-0.1114298145613999   |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.1053823092080112   |
|27 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.10232951375988482  |
|30 |android_serial_s=unique&&cpu_serial_s=unique            |-0.08469140255859876  |
|6  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.06522608028652838  |
|18 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.04182358648414212  |
|19 |cpu_serial_s=unique&&imei_s=unique                      |-0.0357941583111045   |
|23 |cpu_serial_s=missing                                    |-0.025221601367951083 |
|44 |mac_addr_s=white                                        |-0.0018988957925369582|
|34 |imei_s=missing                                          |0.047410410159693404  |
|24 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |0.05733751166161462   |
|48 |cpu_serial_s=white                                      |0.07566895994351508   |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.09749324474110709   |
|51 |android_serial_s=duplicated&&imei_s=duplicated          |0.1640604360704913    |
|4  |imei_s=duplicated&&mac_addr_s=duplicated                |0.1908753003023352    |
|31 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.20312599557318453   |
|28 |cpu_serial_s=duplicated                                 |0.20525425220366178   |
|9  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.2294417337859698    |
|33 |android_serial_s=white                                  |0.2557258308967662    |
|47 |android_id_s=white                                      |0.2747522252250917    |
|15 |mac_addr_s=missing                                      |0.31511472991308775   |
|0  |mac_addr_s=duplicated                                   |0.36175664917232087   |
|42 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.37637278952919767   |
|11 |advertising_id_s=missing                                |0.433780337403058     |
|35 |imei_s=white                                            |0.4467015326941567    |
|53 |android_serial_s=duplicated                             |0.4674535826009847    |
|17 |android_id_s=duplicated&&cpu_serial_s=duplicated        |0.48077308026269794   |
|36 |advertising_id_s=duplicated&&imei_s=duplicated          |0.5089097969364494    |
|41 |advertising_id_s=duplicated&&android_serial_s=duplicated|0.5423024326712651    |
|22 |android_id_s=missing                                    |0.6740381671841541    |
|50 |imei_s=duplicated                                       |0.880720103753667     |
|7  |advertising_id_s=duplicated&&android_id_s=duplicated    |0.8950044562530448    |
|25 |android_id_s=duplicated&&mac_addr_s=duplicated          |0.9866562904029841    |
|46 |android_id_s=duplicated&&android_serial_s=duplicated    |1.0029436691251703    |
|49 |advertising_id_s=duplicated                             |1.0174469240757904    |
|20 |android_id_s=duplicated&&imei_s=duplicated              |1.3928756706113912    |
|29 |android_id_s=duplicated                                 |2.536501218707851     |
+---+--------------------------------------------------------+----------------------+


verify
jR 21972.  6个model_s不一样
country_s && model_s same:


== ==== === 
+-----------------------------------------------+
|result                                         |
+-----------------------------------------------+
|Map(0 -> 44,132,210, 1 -> 24,699,241, 5 -> 1,405,513)|
+-----------------------------------------------+

+---+--------------------------------------------------------+---------------------+
|_1 |_2                                                      |_3                   |
+---+--------------------------------------------------------+---------------------+
|38 |android_id_s=unique                                     |-3.229136938117652   |
|8  |advertising_id_s=unique&&android_id_s=unique            |-1.8571144103315833  |
|21 |android_id_s=unique&&imei_s=unique                      |-1.6580944267501385  |
|3  |advertising_id_s=unique                                 |-1.6432436320057362  |
|26 |android_id_s=unique&&mac_addr_s=unique                  |-1.2942154683526306  |
|32 |android_serial_s=missing                                |-1.2651516896301775  |
|45 |android_id_s=unique&&android_serial_s=unique            |-0.8920271668114074  |
|13 |android_serial_s=unique                                 |-0.5523977364322404  |
|10 |imei_s=unique                                           |-0.48340157994142063 |
|16 |android_id_s=unique&&cpu_serial_s=unique                |-0.39186770089460254 |
|52 |android_serial_s=unique&&imei_s=unique                  |-0.36239396993383544 |
|39 |cpu_serial_s=unique                                     |-0.30315276896073373 |
|37 |advertising_id_s=unique&&imei_s=unique                  |-0.2628453356639325  |
|27 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.2529251956335086  |
|6  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.24523001513922948 |
|43 |advertising_id_s=unique&&mac_addr_s=unique              |-0.24263815370376127 |
|12 |advertising_id_s=white                                  |-0.23535365477185302 |
|14 |mac_addr_s=unique                                       |-0.16084104963992102 |
|40 |advertising_id_s=unique&&android_serial_s=unique        |-0.15414378504868306 |
|18 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.1353439886004397  |
|5  |imei_s=unique&&mac_addr_s=unique                        |-0.1319689552953061  |
|44 |mac_addr_s=white                                        |-0.08118544881289587 |
|24 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-0.07962473405916654 |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.04816363343869746 |
|30 |android_serial_s=unique&&cpu_serial_s=unique            |-0.041415004457116246|
|51 |android_serial_s=duplicated&&imei_s=duplicated          |-0.02537206008489165 |
|48 |cpu_serial_s=white                                      |0.02270136764753435  |
|23 |cpu_serial_s=missing                                    |0.08598370704690586  |
|4  |imei_s=duplicated&&mac_addr_s=duplicated                |0.09018264229765188  |
|19 |cpu_serial_s=unique&&imei_s=unique                      |0.09723519266903678  |
|22 |android_id_s=missing                                    |0.1206904240087647   |
|28 |cpu_serial_s=duplicated                                 |0.15340290599499595  |
|31 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.1622700414310343   |
|0  |mac_addr_s=duplicated                                   |0.17735373355991096  |
|34 |imei_s=missing                                          |0.21037971574540257  |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.21540549041493395  |
|47 |android_id_s=white                                      |0.2699003540890681   |
|15 |mac_addr_s=missing                                      |0.3302025059021608   |
|53 |android_serial_s=duplicated                             |0.34464381396625127  |
|9  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.4166240529423667   |
|50 |imei_s=duplicated                                       |0.44104764520951134  |
|33 |android_serial_s=white                                  |0.4492444730294933   |
|35 |imei_s=white                                            |0.5818584140575804   |
|11 |advertising_id_s=missing                                |0.649223871038069    |
|17 |android_id_s=duplicated&&cpu_serial_s=duplicated        |0.6934972345494907   |
|42 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.7858419822886554   |
|36 |advertising_id_s=duplicated&&imei_s=duplicated          |0.8177993109409933   |
|7  |advertising_id_s=duplicated&&android_id_s=duplicated    |0.8886884943316736   |
|41 |advertising_id_s=duplicated&&android_serial_s=duplicated|1.0159640473735403   |
|25 |android_id_s=duplicated&&mac_addr_s=duplicated          |1.176699860781958    |
|46 |android_id_s=duplicated&&android_serial_s=duplicated    |1.4146419133796284   |
|49 |advertising_id_s=duplicated                             |1.5546841427161022   |
|20 |android_id_s=duplicated&&imei_s=duplicated              |1.6219352483007319   |
|29 |android_id_s=duplicated                                 |3.206137880370585    |
+---+--------------------------------------------------------+---------------------+
|54 |intercept_of_model                                      |-0.6049358838160873  |
+---+--------------------------------------------------------+---------------------+
score = 0.696
advertising_id_s=duplicated
imei_s=duplicated
android_serial_s=duplicated
cpu_serial_s=duplicated
android_id_s=unique
mac_addr_s=white
advertising_id_s=duplicated&&android_serial_s=duplicated
advertising_id_s=duplicated&&cpu_serial_s=duplicated
advertising_id_s=duplicated&&imei_s=duplicated
android_serial_s=duplicated&&cpu_serial_s=duplicated
android_serial_s=duplicated&&imei_s=duplicated
cpu_serial_s=duplicated&&imei_s=duplicated

score=0.5
advertising_id_s=duplicated  					1.5546841427161022
imei_s=unique         							-0.48340157994142063
android_serial_s=unique                         -0.5523977364322404
android_id_s=white
cpu_serial_s=white
mac_addr_s=missing
android_serial_s=unique&&imei_s=unique

score=0.22
advertising_id_s=duplicated 								1.5546841427161022
advertising_id_s=duplicated&&android_serial_s=duplicated    1.0159640473735403
advertising_id_s=duplicated&&cpu_serial_s=duplicated	    0.4166240529423667
advertising_id_s=duplicated&&mac_addr_s=duplicated			0.7858419822886554
android_id_s=unique   										-3.229136938117652
android_id_s=unique&&imei_s=unique        					-1.6580944267501385
android_serial_s=duplicated         						0.34464381396625127
android_serial_s=duplicated&&cpu_serial_s=duplicated		0.1622700414310343
android_serial_s=duplicated&&mac_addr_s=duplicated          0.21540549041493395
cpu_serial_s=duplicated		       	                        0.15340290599499595
cpu_serial_s=duplicated&&mac_addr_s=duplicated              -0.07962473405916654
imei_s=unique                                               -0.48340157994142063
mac_addr_s=duplicated										0.17735373355991096


+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+
|feature                                                 |score               |count   |pos_count|neg_count|total_ratio          |pos_ratio            |neg_ratio            |
+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+
|android_serial_s=missing                                |5.469199789425203   |8481    |5        |33       |1.2074838542280956E-4|2.0243537038243402E-7|7.477531716630552E-7 |
|android_id_s=missing                                    |6.341888457899502   |8639    |54       |53       |1.2299791317859355E-4|2.1863020001302874E-6|1.2009369120649067E-6|
|android_id_s=white                                      |686.3569509874973   |61521   |5958     |5886     |8.759063105290258E-4 |2.4122198734770838E-4|1.3337197480026494E-4|
|advertising_id_s=white                                  |1314.9731098379943  |20643   |630      |6087     |2.9390507254840913E-4|2.5506856668186685E-5|1.3792647139130354E-4|
|imei_s=missing                                          |1764.6576657411392  |1969199 |641023   |1241589  |0.028036505108620585 |0.0259531456857318   |0.02813339735308973  |
|imei_s=white                                            |2907.6163624229994  |361073  |127310   |177757   |0.00514078313521638  |0.005154409400677535 |0.004027829107130597 |
|advertising_id_s=missing                                |52789.78737948011   |8299036 |2985881  |4299833  |0.11815766979905339  |0.1208895852305745   |0.09743072010216575  |
|mac_addr_s=missing                                      |76619.08626610076   |3506152 |1402964  |1693656  |0.049918900253148754 |0.05680190739464423  |0.03837686805170192  |
|cpu_serial_s=white                                      |77300.78880734865   |40865700|15278364 |24376390 |0.5818261165160841   |0.6185762550355293   |0.5523491798847145   |
|cpu_serial_s=missing                                    |132870.16302095065  |14438662|4314726  |10012785 |0.20557070205938857  |0.1746906311817436   |0.2268815679069777   |
|android_serial_s=white                                  |167907.82829952682  |11288272|4538520  |5899351  |0.16071696948632347  |0.1837513954376169   |0.1336744976061702   |
|mac_addr_s=white                                        |275660.7241251043   |18414918|5192330  |13017777 |0.2621827162119365   |0.21022224933956474  |0.29497224362885976  |
|cpu_serial_s=unique&&mac_addr_s=unique                  |2643609.991743467   |7387691 |2816     |7380041  |0.10518237946617397  |1.1401160059938684E-4|0.16722572923495108  |
|advertising_id_s=unique&&cpu_serial_s=unique            |3048581.6292508235  |8498808 |18       |8495821  |0.12100192713341083  |7.287673333767625E-7 |0.19250839692822996  |
|cpu_serial_s=unique&&imei_s=unique                      |3188870.3246198376  |8895328 |1207     |8892212  |0.12664738754938212  |4.886789841031957E-5 |0.2014902947303115   |
|android_serial_s=unique&&cpu_serial_s=unique            |3375652.0757423798  |9421809 |1940     |9416083  |0.13414316996959036  |7.85449237083844E-5  |0.21336078569371442  |
|cpu_serial_s=unique                                     |3405585.7453947766  |9516124 |3367     |9506022  |0.13548598142710155  |1.3631997841553106E-4|0.21539873031511453  |
|android_id_s=unique&&cpu_serial_s=unique                |3410065.4413773143  |9503449 |0        |9503116  |0.13530552089352837  |0.0                  |0.21533288271763412  |
|cpu_serial_s=duplicated&&mac_addr_s=duplicated          |4676293.99853736    |4525947 |4341981  |126858   |0.06443824935257737  |0.17579410638569826  |0.0028744991469949045|
|advertising_id_s=duplicated&&cpu_serial_s=duplicated    |4772660.0605103485  |4166062 |4166004  |0        |0.05931438038808169  |0.16866931255094034  |0.0                  |
|cpu_serial_s=duplicated&&imei_s=duplicated              |5033869.124265981   |4898033 |4694903  |147105   |0.06973583026737887  |0.19008288554292013  |0.0033332797065907193|
|cpu_serial_s=duplicated                                 |5299716.64727236    |5416478 |5102784  |237013   |0.07711719999742586  |0.20659679380431165  |0.005370521893193203 |
|android_serial_s=duplicated&&cpu_serial_s=duplicated    |5363878.014006855   |5381659 |5099223  |206014   |0.07662146387762432  |0.20645261933352527  |0.004668109754757353 |
|android_id_s=duplicated&&cpu_serial_s=duplicated        |5843497.997713582   |5105555 |5100727  |0        |0.07269042836190927  |0.20651351189293632  |0.0                  |
|mac_addr_s=unique                                       |8160844.840402509   |28818634|1122137  |27533419 |0.41030580422012547  |0.045432043842966674 |0.6238848904235704   |
|android_serial_s=unique&&mac_addr_s=unique              |8279773.573089419   |23114876|4589     |23094922 |0.3290984502120564   |1.8579518293699795E-4|0.5233121568124506   |
|advertising_id_s=unique&&mac_addr_s=unique              |8624923.960303312   |24091533|1126     |24040950 |0.34300362128408624  |4.558844541012414E-5 |0.5447483821906948   |
|imei_s=unique&&mac_addr_s=unique                        |8900669.274013706   |26864432|371322   |26462605 |0.38248281916057764  |0.015033741320229234 |0.599621115733837    |
|android_id_s=unique&&mac_addr_s=unique                  |9878230.451187514   |27628337|12       |27528550 |0.3933589299218571   |4.858448889178417E-7 |0.6237745628419696   |
|advertising_id_s=unique&&android_serial_s=unique        |1.1954411523752894E7|33320912|1        |33314368 |0.4744070657723759   |4.0487074076486805E-8|0.7548764949681877   |
|android_serial_s=unique&&imei_s=unique                  |1.2747967742724655E7|35569881|4397     |35545939 |0.5064268011356527   |1.7802166471431248E-4|0.8054420796058026   |
|android_serial_s=unique                                 |1.3172733982891245E7|36817945|10830    |36759072 |0.524196134103974    |4.384750122483521E-4 |0.8329306871330486   |
|android_id_s=unique&&android_serial_s=unique            |1.3189260031466557E7|36780602|6        |36755647 |0.5236644624901498   |2.4292244445892084E-7|0.8328530794175049   |
|advertising_id_s=unique&&imei_s=unique                  |1.331493763385604E7 |37137805|185      |37106702 |0.5287501464328669   |7.4901087041500586E-6|0.8408077003168434   |
|advertising_id_s=unique                                 |1.3626275510318438E7|40601876|412284   |39826290 |0.5780699177145527   |0.016692172848550284 |0.902431353426443    |
|imei_s=unique                                           |1.387983966350323E7 |42339527|578814   |41265181 |0.6028097541345893   |0.02343448529450763  |0.9350354536969709   |
|android_serial_s=duplicated&&mac_addr_s=duplicated      |1.4216787526564697E7|14536032|13670747 |626545   |0.20695700913268403  |0.5534885464699097   |0.014197000331503905 |
|advertising_id_s=unique&&android_id_s=unique            |1.4288997714919463E7|39820351|0        |39820351 |0.566942941895951    |0.0                  |0.9022967805147306   |
|android_id_s=unique&&imei_s=unique                      |1.4805965544889608E7|41708532|4        |41261047 |0.5938259518164822   |1.6194829630594722E-7|0.93494178061783     |
|mac_addr_s=duplicated                                   |1.5398394367516812E7|19497260|16981810 |1887358  |0.2775925793147893   |0.6875437994228244   |0.042765997895867894 |
|android_id_s=unique                                     |1.5826561370200764E7|45133832|4606     |44126271 |0.6425937203094371   |1.8648346319629822E-4|0.9998654270882876   |
|advertising_id_s=duplicated&&mac_addr_s=duplicated      |1.63914160184425E7  |14308714|14307892 |0        |0.20372056514287834  |0.5792846832823729   |0.0                  |
|imei_s=duplicated&&mac_addr_s=duplicated                |1.698570516688067E7 |17596159|16380131 |772993   |0.2505256206689116   |0.6631835771795579   |0.017515392952222424 |
|android_serial_s=duplicated&&imei_s=duplicated          |1.9354184603347592E7|20454749|18996023 |1055588  |0.2912248456524972   |0.7690933903596471   |0.02391876590816549  |
|android_id_s=duplicated&&mac_addr_s=duplicated          |1.9446224841946136E7|17123688|16974402 |0        |0.24379880656572797  |0.6872438711780657   |0.0                  |
|android_serial_s=duplicated                             |1.9785841364369158E7|22122266|20149886 |1473754  |0.31496614802427964  |0.8158099271147644   |0.03339406750760952  |
|advertising_id_s=duplicated&&android_serial_s=duplicated|2.0065935711276177E7|17526641|17515341 |0        |0.24953585693140154  |0.7091449085419265   |0.0                  |
|android_id_s=duplicated&&android_serial_s=duplicated    |2.3074728391576264E7|20291371|20141684 |0        |0.28889874852791186  |0.815477852133189    |0.0                  |
|advertising_id_s=duplicated&&imei_s=duplicated          |2.3079580094876666E7|20156141|20145919 |0        |0.28697340904427476  |0.815649314891903    |0.0                  |
|imei_s=duplicated                                       |2.3473248229907658E7|25567165|23352094 |1447683  |0.3640129576215737   |0.9454579596190831   |0.03280331984280869  |
|advertising_id_s=duplicated&&android_id_s=duplicated    |2.4390065825845324E7|21289828|21289828 |0        |0.3031142974801701   |0.861962843311663    |0.0                  |
|advertising_id_s=duplicated                             |2.440223002552503E7 |21315409|21300446 |0        |0.3034785074138455   |0.862392735064207    |0.0                  |
|android_id_s=duplicated&&imei_s=duplicated              |2.674481567825428E7 |23643086|23345264 |0        |0.3366188492999213   |0.9451814329031406   |0.0                  |
|android_id_s=duplicated                                 |2.828379544069022E7 |25032972|24688623 |0        |0.3564073754668553   |0.9995701082474558   |0.0                  |
+--------------------------------------------------------+--------------------+--------+---------+---------+---------------------+---------------------+---------------------+

jResult.rdd.filter(x=>x.getAs[String]("model_s") != x.getAs[String]("h_model_s") || x.getAs[String]("country_s") != x.getAs[String]("h_country_s"))
>> 观察机型不一样的是什么情况
val rdd = jR.rdd.filter(x=>x.getAs[String]("model_s") != x.getAs[String]("h_model_s"))
val df = sqlContext.createDataFrame(rdd,rdd.first.schema)
df.drop("id").drop("dt").drop("features_tag").drop("model_s").drop("country_s").drop("h_country_s").drop("h_model_s")
df.dropDuplicates(Seq("features_value")).count
jR.filter("model_s != h_model_s AND country_s!=h_country_s").dropDuplicates(Seq("features_value")).count
jR.dropDuplicates(Seq("features_value")).count
仅国家不同：2/72 ， cid相同，机型相同，模型认为是重复用户，只是国家不同，adv不同， 国家是CA SG ID
仅国家不同：4,380/261,225
仅机型不同：161(81)/261,225(108,047)
国家机型都不同:35(20)/261,225(108,047)
jR.filter("client_id_s != h_client_id_s").filter("model_s != h_model_s").dropDuplicates(Seq("features_value")).count

cid不同的：784(399）/261,225 ，这里面有34个 model_s不同
这个算的有一些问题，jR应该是根据features_value做一个distinct才是基数（所有unknown），相应的其他的也应该根据features_value去重


有问题的打分记录（对1L样本的打分）
+-------------------+-----+
|score              |count|
+-------------------+-----+
|0.22635591004356978|3    |
|0.2263559100435698 |1    |
|0.25797312834453895|6    |
|0.3673948194475125 |11   |
|0.36739481944751257|1    |
|0.3782889898236612 |2    |
|0.5434797031520052 |1    |
|0.6964525325669767 |12   |
|0.6964525325669768 |109  |
|0.6964525325669769 |27   |
|0.6978623870273294 |2    |
|0.7141004000664777 |14   |
|0.7332160334259048 |605  |
|0.7545211228888815 |114  |
|0.7545211228888817 |32   |
|0.7758857990995719 |1    |
|0.775885799099572  |11   |
|0.8167304462315925 |9    |
|0.8167304462315926 |2    |
|0.8210848160867541 |10   |
|0.8210848160867542 |29   |
|0.822585016166135  |1785 |
|0.8232313395541642 |4    |
|0.8316326545479222 |23   |
|0.8328802557601076 |2551 |
|0.8328802557601077 |517  |
|0.84150402976471   |983  |
|0.8506609634641163 |1    |
|0.857386238974969  |11   |
|0.861373657022631  |13   |
|0.8649504927060168 |4    |
|0.8680780373861556 |2    |
|0.8687587910815855 |2    |
|0.8733485704186518 |1    |
|0.8738131153572142 |20   |
|0.8847672696983104 |1    |
|0.8909815129175902 |214  |
|0.8910887010356416 |67   |
|0.8976506122833118 |12   |
|0.8995529702582319 |1277 |
|0.9007098188721294 |330  |
|0.9031020044029193 |1    |
|0.9062275044201766 |1    |
|0.9071229739290194 |13   |
|0.908922483299624  |1    |
|0.9124888791524952 |1    |
|0.9135805737170003 |1    |
|0.9202094238146291 |1    |
|0.9205438081919771 |4    |
|0.9216237407252685 |1    |
|0.9222550986179787 |45   |
|0.9266505751694837 |7    |
|0.9310964630924982 |21   |
|0.9341646948163037 |112  |
|0.9383184499879764 |30   |
|0.9388974183185115 |15   |
|0.9418810308748071 |4    |
|0.9421112531614326 |37   |
|0.9424286110921374 |772  |
|0.9429534831395999 |260  |
|0.9454675020430627 |4    |
|0.9455319466548389 |5    |
|0.9460975568620855 |23   |
|0.9463055549173893 |11   |
|0.9475511534132639 |5    |
|0.9500816910596496 |5041 |
|0.9506849583627638 |66   |
|0.952317322915001  |3    |
|0.952672404749498  |53   |
|0.9529988874170316 |452  |
|0.9536455694902083 |56   |
|0.9563640605031832 |5    |
|0.956627282212513  |115  |
|0.9570422640970079 |45   |
|0.9588672318390133 |3    |
|0.9591783936403315 |2    |
|0.9592143664073544 |105  |
|0.9616194149190546 |22   |
|0.9616317934488648 |1    |
|0.9619106753479596 |101  |
|0.9621903734472023 |62   |
|0.9630774628597842 |2    |
|0.963226860166267  |4    |
|0.9637587808977702 |230  |
|0.9638996559973853 |3    |
|0.964162668101298  |4    |
|0.9641719247012073 |1    |
|0.9643655469378499 |2    |
|0.9644264409031077 |1    |
|0.9652629617112114 |1    |
|0.9653806289898273 |171  |
|0.9668058438310936 |4    |
|0.9674344098767748 |553  |
|0.9689840854167383 |2    |
|0.9711120908725721 |21   |
|0.97197691186684   |2    |
|0.9725931436402727 |1163 |
|0.9725931436402729 |2220 |
|0.973087869843761  |69   |
|0.9742304665363902 |11   |
+-------------------+-----+
only showing top 100 rows


>>>>
schema
root
 |-- api: string (nullable = true)
 |-- baseTime: string (nullable = true)
 |-- message__adId: string (nullable = true)
 |-- message__advertisingId: string (nullable = true)
 |-- message__androidId: string (nullable = true)
 |-- message__androidSerial: string (nullable = true)
 |-- message__apiVersionHeader: string (nullable = true)
 |-- message__appid: string (nullable = true)
 |-- message__batteryLevel: string (nullable = true)
 |-- message__campaign: string (nullable = true)
 |-- message__channelId: string (nullable = true)
 |-- message__charging: string (nullable = true)
 |-- message__clientId: string (nullable = true)
 |-- message__clientTime: string (nullable = true)
 |-- message__connectedWifi: string (nullable = true)
 |-- message__contributor: string (nullable = true)
 |-- message__cpuFrequency: string (nullable = true)
 |-- message__cpuNum: string (nullable = true)
 |-- message__cpuSerial: string (nullable = true)
 |-- message__cpuType: string (nullable = true)
 |-- message__currency: string (nullable = true)
 |-- message__currentMccMnc: string (nullable = true)
 |-- message__dpi: string (nullable = true)
 |-- message__fakeIp: string (nullable = true)
 |-- message__gaid: string (nullable = true)
 |-- message__hardwareInfo: string (nullable = true)
 |-- message__height: string (nullable = true)
 |-- message__imei: string (nullable = true)
 |-- message__installNonMarketApps: string (nullable = true)
 |-- message__installSource: string (nullable = true)
 |-- message__installTime: string (nullable = true)
 |-- message__ip: string (nullable = true)
 |-- message__ipAddress: string (nullable = true)
 |-- message__isLimitAdTracking: string (nullable = true)
 |-- message__isProxy: string (nullable = true)
 |-- message__isRoaming: string (nullable = true)
 |-- message__isRoot: string (nullable = true)
 |-- message__isVpn: string (nullable = true)
 |-- message__isdefault: string (nullable = true)
 |-- message__l: string (nullable = true)
 |-- message__lastConnectTime: string (nullable = true)
 |-- message__lastConnectedIp: string (nullable = true)
 |-- message__lastResponseTime: string (nullable = true)
 |-- message__lastTransferRate: string (nullable = true)
 |-- message__localTimezone: string (nullable = true)
 |-- message__macAddr: string (nullable = true)
 |-- message__macInfo: string (nullable = true)
 |-- message__manufacturer: string (nullable = true)
 |-- message__mcc: string (nullable = true)
 |-- message__memory: string (nullable = true)
 |-- message__memoryFree: string (nullable = true)
 |-- message__model: string (nullable = true)
 |-- message__module: string (nullable = true)
 |-- message__net: string (nullable = true)
 |-- message__networkInterface: string (nullable = true)
 |-- message__networkOperator: string (nullable = true)
 |-- message__oldClientId: string (nullable = true)
 |-- message__openglesVersion: string (nullable = true)
 |-- message__os: string (nullable = true)
 |-- message__packageName: string (nullable = true)
 |-- message__phoneName: string (nullable = true)
 |-- message__pid: string (nullable = true)
 |-- message__productId: string (nullable = true)
 |-- message__ptr: string (nullable = true)
 |-- message__referer: string (nullable = true)
 |-- message__registerType: string (nullable = true)
 |-- message__registeredMccMnc: string (nullable = true)
 |-- message__romVersion: string (nullable = true)
 |-- message__sdk: string (nullable = true)
 |-- message__sh: string (nullable = true)
 |-- message__simCountryIso: string (nullable = true)
 |-- message__siteId: string (nullable = true)
 |-- message__storage: string (nullable = true)
 |-- message__storageFree: string (nullable = true)
 |-- message__time: string (nullable = true)
 |-- message__token: string (nullable = true)
 |-- message__updateTime: string (nullable = true)
 |-- message__userAgent: string (nullable = true)
 |-- message__versionCode: string (nullable = true)
 |-- message__versionName: string (nullable = true)
 |-- message__width: string (nullable = true)




>>>>>>>> puuid

+--------------------------------------------+
|result                                      |
+--------------------------------------------+
|Map(0 -> 8,813,354, 1 -> 3,500,761, 5 -> 10,1619)|
+--------------------------------------------+

 =======> lrModel intercept
Some(-0.45556919352151165)
=======> bestAggMetricScoreAndStd
(1.0,4.965068306494546E-17)

+---+--------------------------------------------------------+--------------------+
|_1 |_2                                                      |_3                  |
+---+--------------------------------------------------------+--------------------+
|27 |puuid=unique                                            |-2.086973755652263  |
|4  |advertising_id_s=unique&&puuid=unique                   |-1.676935146546613  |
|13 |advertising_id_s=white                                  |-1.5976418560755414 |
|38 |mac_addr_s=unique&&puuid=unique                         |-1.566367632943945  |
|3  |advertising_id_s=unique                                 |-1.4807878465234439 |
|39 |mac_addr_s=white                                        |-1.2912041931364864 |
|12 |imei_s=unique&&puuid=unique                             |-0.9841222642368495 |
|20 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-0.950640550725433  |
|36 |advertising_id_s=unique&&mac_addr_s=unique              |-0.8185453536240045 |
|9  |imei_s=unique                                           |-0.6742058008888985 |
|6  |imei_s=unique&&mac_addr_s=unique                        |-0.6087625586622152 |
|16 |android_serial_s=unique&&puuid=unique                   |-0.5358036870749027 |
|14 |android_serial_s=unique                                 |-0.5076780092880334 |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.4472799894982951 |
|11 |advertising_id_s=missing                                |-0.35878314023541796|
|31 |advertising_id_s=unique&&imei_s=unique                  |-0.3555458115055661 |
|33 |advertising_id_s=unique&&android_serial_s=unique        |-0.27616455435991066|
|47 |android_serial_s=unique&&imei_s=unique                  |-0.26791993488677224|
|10 |cpu_serial_s=unique&&puuid=unique                       |-0.12778729910529418|
|32 |cpu_serial_s=unique                                     |-0.12778729910529418|
|21 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.11463701956899464|
|15 |mac_addr_s=unique                                       |-0.11354125737990986|
|25 |android_serial_s=unique&&cpu_serial_s=unique            |-0.08936339679073188|
|7  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.05494886307012924|
|18 |cpu_serial_s=unique&&imei_s=unique                      |-0.04671064455091176|
|29 |imei_s=white                                            |-0.03303336451815751|
|19 |cpu_serial_s=missing                                    |-0.00751038297340604|
|43 |cpu_serial_s=white                                      |0.057538171532494194|
|5  |imei_s=duplicated&&mac_addr_s=duplicated                |0.11216045365308751 |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.20227711155748887 |
|0  |mac_addr_s=duplicated                                   |0.3007062603480967  |
|17 |cpu_serial_s=duplicated&&imei_s=duplicated              |0.3055390017572415  |
|28 |android_serial_s=white                                  |0.32083981493875    |
|46 |android_serial_s=duplicated&&imei_s=duplicated          |0.43156722954720556 |
|40 |cpu_serial_s=duplicated&&puuid=duplicated               |0.53074678594221    |
|48 |android_serial_s=duplicated                             |0.5999849233836608  |
|24 |cpu_serial_s=duplicated                                 |0.6251075115781312  |
|26 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.6351249214729744  |
|23 |mac_addr_s=duplicated&&puuid=duplicated                 |0.6827716023753156  |
|42 |android_serial_s=duplicated&&puuid=duplicated           |0.8209585869308138  |
|37 |advertising_id_s=duplicated&&puuid=duplicated           |0.8262395497906749  |
|35 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.9427933550960702  |
|45 |imei_s=duplicated                                       |0.9633175144174381  |
|30 |advertising_id_s=duplicated&&imei_s=duplicated          |1.1964755771134294  |
|34 |advertising_id_s=duplicated&&android_serial_s=duplicated|1.218267380528957   |
|41 |imei_s=duplicated&&puuid=duplicated                     |1.2587743730122716  |
|8  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |1.6502790445115239  |
|44 |advertising_id_s=duplicated                             |1.7985022858685913  |
|22 |puuid=duplicated                                        |2.0869888977984132  |
+---+--------------------------------------------------------+--------------------+
|49 |intercept_of_model                                      |-0.45556919352151165|
+---+--------------------------------------------------------+--------------------+





+---+--------------------------------------------------------+---------------------+
|_1 |_2                                                      |_3                   |
+---+--------------------------------------------------------+---------------------+
|28 |puuid=unique                                            |-1.1617294084391228  |
|40 |mac_addr_s=white                                        |-1.1531208093276077  |
|4  |advertising_id_s=unique&&puuid=unique                   |-1.1527009373858212  |
|3  |advertising_id_s=unique                                 |-1.071393177619614   |
|39 |mac_addr_s=unique&&puuid=unique                         |-0.9439622264544165  |
|37 |advertising_id_s=unique&&mac_addr_s=unique              |-0.7811426914820848  |
|12 |imei_s=unique&&puuid=unique                             |-0.7030341325502317  |
|13 |advertising_id_s=white                                  |-0.6691833127763877  |
|9  |imei_s=unique                                           |-0.5877963675880107  |
|6  |imei_s=unique&&mac_addr_s=unique                        |-0.5578572785351963  |
|20 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-0.5092571109586393  |
|16 |android_serial_s=unique&&puuid=unique                   |-0.44912972674106716 |
|14 |android_serial_s=unique                                 |-0.44450027032071365 |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.41413543213991005 |
|32 |advertising_id_s=unique&&imei_s=unique                  |-0.4050379194635532  |
|11 |advertising_id_s=missing                                |-0.37285995008245226 |
|48 |android_serial_s=unique&&imei_s=unique                  |-0.3221437698778969  |
|34 |advertising_id_s=unique&&android_serial_s=unique        |-0.3042404175273091  |
|27 |android_serial_s=missing                                |-0.1924224012699771  |
|10 |cpu_serial_s=unique&&puuid=unique                       |-0.18629560292515182 |
|33 |cpu_serial_s=unique                                     |-0.18629373421372905 |
|21 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.16743609293176437 |
|25 |android_serial_s=unique&&cpu_serial_s=unique            |-0.1511159501299969  |
|7  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.11010416999972626 |
|18 |cpu_serial_s=unique&&imei_s=unique                      |-0.08644762214485753 |
|15 |mac_addr_s=unique                                       |-0.06826597609615452 |
|19 |cpu_serial_s=missing                                    |-6.011542654399232E-4|
|44 |cpu_serial_s=white                                      |0.005488113311541662 |
|5  |imei_s=duplicated&&mac_addr_s=duplicated                |0.05062834848506794  |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.08294402345903953  |
|30 |imei_s=white                                            |0.09622961799522159  |
|0  |mac_addr_s=duplicated                                   |0.12559373855876593  |
|29 |android_serial_s=white                                  |0.20256973759274982  |
|17 |cpu_serial_s=duplicated&&imei_s=duplicated              |0.23085541893144002  |
|41 |cpu_serial_s=duplicated&&puuid=duplicated               |0.3130201110342516   |
|24 |cpu_serial_s=duplicated                                 |0.31829169736778656  |
|26 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.3195596235713465   |
|47 |android_serial_s=duplicated&&imei_s=duplicated          |0.33241956062535344  |
|23 |mac_addr_s=duplicated&&puuid=duplicated                 |0.39218253590656604  |
|49 |android_serial_s=duplicated                             |0.4323520168610828   |
|36 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.46183533417159955  |
|46 |imei_s=duplicated                                       |0.6027873788951338   |
|43 |android_serial_s=duplicated&&puuid=duplicated           |0.6671113236244008   |
|38 |advertising_id_s=duplicated&&puuid=duplicated           |0.7352007553267556   |
|35 |advertising_id_s=duplicated&&android_serial_s=duplicated|0.814981523988162    |
|8  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.8390955827054078   |
|42 |imei_s=duplicated&&puuid=duplicated                     |0.8612282102958838   |
|31 |advertising_id_s=duplicated&&imei_s=duplicated          |0.8844246374750404   |
|45 |advertising_id_s=duplicated                             |1.1212025441417797   |
|22 |puuid=duplicated                                        |1.1617111499923047   |
+---+--------------------------------------------------------+---------------------+
|50 |intercept_of_model                                      |0.14053956776891177  |
+---+--------------------------------------------------------+---------------------+




0915
+---+--------------------------------------------------------+---------------------+
|_1 |_2                                                      |_3                   |
+---+--------------------------------------------------------+---------------------+
|40 |mac_addr_s=white                                        |-1.1800582966901174  |
|28 |puuid=unique                                            |-1.1680661666118775  |
|4  |advertising_id_s=unique&&puuid=unique                   |-1.1658031774568316  |
|3  |advertising_id_s=unique                                 |-1.081108684193748   |
|39 |mac_addr_s=unique&&puuid=unique                         |-0.9458065044201461  |
|37 |advertising_id_s=unique&&mac_addr_s=unique              |-0.7822256321233959  |
|12 |imei_s=unique&&puuid=unique                             |-0.699954679213548   |
|13 |advertising_id_s=white                                  |-0.6354735443033844  |
|9  |imei_s=unique                                           |-0.587544001585958   |
|6  |imei_s=unique&&mac_addr_s=unique                        |-0.5585932323899889  |
|20 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-0.5200812883802525  |
|16 |android_serial_s=unique&&puuid=unique                   |-0.44660809985543964 |
|14 |android_serial_s=unique                                 |-0.4418562147350449  |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.41177198917983887 |
|32 |advertising_id_s=unique&&imei_s=unique                  |-0.3957770213645881  |
|11 |advertising_id_s=missing                                |-0.3576241259562814  |
|48 |android_serial_s=unique&&imei_s=unique                  |-0.3201407327236207  |
|34 |advertising_id_s=unique&&android_serial_s=unique        |-0.2982094707590443  |
|10 |cpu_serial_s=unique&&puuid=unique                       |-0.1939093733138926  |
|33 |cpu_serial_s=unique                                     |-0.19390771394237954 |
|21 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.17436004440017702 |
|25 |android_serial_s=unique&&cpu_serial_s=unique            |-0.15480524759152448 |
|7  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.11119605885008643 |
|18 |cpu_serial_s=unique&&imei_s=unique                      |-0.08772655474212016 |
|15 |mac_addr_s=unique                                       |-0.061221266078261664|
|27 |android_serial_s=missing                                |-0.009646930163114562|
|19 |cpu_serial_s=missing                                    |0.0012782794370024895|
|44 |cpu_serial_s=white                                      |0.0013874873468765188|
|5  |imei_s=duplicated&&mac_addr_s=duplicated                |0.042649065916731455 |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.07770275690789398  |
|0  |mac_addr_s=duplicated                                   |0.11770395284591621  |
|30 |imei_s=white                                            |0.11795129932366631  |
|29 |android_serial_s=white                                  |0.20246435266033022  |
|17 |cpu_serial_s=duplicated&&imei_s=duplicated              |0.22579346131335626  |
|24 |cpu_serial_s=duplicated                                 |0.313638516388794    |
|26 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.31469723422218593  |
|41 |cpu_serial_s=duplicated&&puuid=duplicated               |0.3155752905915033   |
|47 |android_serial_s=duplicated&&imei_s=duplicated          |0.32466103550080483  |
|23 |mac_addr_s=duplicated&&puuid=duplicated                 |0.39329412469236186  |
|49 |android_serial_s=duplicated                             |0.42550114574473935  |
|36 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |0.46636575090547217  |
|46 |imei_s=duplicated                                       |0.5972030729864798   |
|43 |android_serial_s=duplicated&&puuid=duplicated           |0.6739545945579921   |
|38 |advertising_id_s=duplicated&&puuid=duplicated           |0.7281259096698862   |
|35 |advertising_id_s=duplicated&&android_serial_s=duplicated|0.8234078851570585   |
|8  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.8411232603413505   |
|42 |imei_s=duplicated&&puuid=duplicated                     |0.8675788860592107   |
|31 |advertising_id_s=duplicated&&imei_s=duplicated          |0.8924313958585353   |
|45 |advertising_id_s=duplicated                             |1.1271153112238765   |
|22 |puuid=duplicated                                        |1.1680031498467147   |
+---+--------------------------------------------------------+---------------------+
|50 |intercept_of_model                                      |0.1664202218486412   |
+---+--------------------------------------------------------+---------------------+






0918
+---+--------------------------------------------------------+--------------------+
|_1 |_2                                                      |_3                  |
+---+--------------------------------------------------------+--------------------+
|4  |advertising_id_s=unique&&puuid=unique                   |-2.265637536670009  |
|29 |puuid=unique                                            |-1.7836225664801473 |
|3  |advertising_id_s=unique                                 |-1.7180742960438642 |
|13 |advertising_id_s=white                                  |-0.9120204312169147 |
|12 |imei_s=unique&&puuid=unique                             |-0.8793747468603211 |
|40 |mac_addr_s=unique&&puuid=unique                         |-0.7880010810738162 |
|9  |imei_s=unique                                           |-0.6906464591271465 |
|16 |android_serial_s=unique&&puuid=unique                   |-0.592813797599923  |
|14 |android_serial_s=unique                                 |-0.5846879293213499 |
|6  |imei_s=unique&&mac_addr_s=unique                        |-0.49811544577904926|
|15 |mac_addr_s=unique                                       |-0.4826123952042163 |
|49 |android_serial_s=unique&&imei_s=unique                  |-0.4308243799108104 |
|33 |advertising_id_s=unique&&imei_s=unique                  |-0.3346934586446934 |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.31167361855863457|
|10 |cpu_serial_s=unique&&puuid=unique                       |-0.2908629026700507 |
|34 |cpu_serial_s=unique                                     |-0.29077120740672446|
|18 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.26743503090206966|
|38 |advertising_id_s=unique&&mac_addr_s=unique              |-0.23415441389096983|
|22 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.21089398744507948|
|26 |android_serial_s=unique&&cpu_serial_s=unique            |-0.19737265578869617|
|35 |advertising_id_s=unique&&android_serial_s=unique        |-0.188989868581833  |
|19 |cpu_serial_s=unique&&imei_s=unique                      |-0.10217517614051987|
|17 |mac_addr_s=missing                                      |-0.10147687559435699|
|11 |advertising_id_s=missing                                |-0.07743977808312602|
|7  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.06263863784166354|
|21 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-0.05430037205214616|
|45 |cpu_serial_s=white                                      |0.011455536024552689|
|20 |cpu_serial_s=missing                                    |0.027891113925688875|
|41 |mac_addr_s=white                                        |0.05256906635195185 |
|27 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.18516112309700772 |
|25 |cpu_serial_s=duplicated                                 |0.19430608004971262 |
|0  |mac_addr_s=duplicated                                   |0.20212615510906054 |
|28 |android_serial_s=missing                                |0.22689102100710487 |
|5  |imei_s=duplicated&&mac_addr_s=duplicated                |0.2419218691808449  |
|31 |imei_s=white                                            |0.2628489454782599  |
|48 |android_serial_s=duplicated&&imei_s=duplicated          |0.2874609805431847  |
|30 |android_serial_s=white                                  |0.33616316074687974 |
|39 |advertising_id_s=duplicated&&puuid=duplicated           |0.3751617245298279  |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.4142973790018201  |
|50 |android_serial_s=duplicated                             |0.4886479476430604  |
|42 |cpu_serial_s=duplicated&&puuid=duplicated               |0.5371719653105415  |
|47 |imei_s=duplicated                                       |0.6337600668829039  |
|8  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.9165912316855258  |
|44 |android_serial_s=duplicated&&puuid=duplicated           |0.9479782171449906  |
|24 |mac_addr_s=duplicated&&puuid=duplicated                 |0.9997387548993801  |
|43 |imei_s=duplicated&&puuid=duplicated                     |1.3033328900997643  |
|32 |advertising_id_s=duplicated&&imei_s=duplicated          |1.389026211621284   |
|37 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |1.434872875098971   |
|36 |advertising_id_s=duplicated&&android_serial_s=duplicated|1.4402740086554324  |
|46 |advertising_id_s=duplicated                             |1.7007266460853747  |
|23 |puuid=duplicated                                        |1.7399831618675903  |
+---+--------------------------------------------------------+--------------------+
|51 |intercept_of_model                                      |-0.5267059322547673 |
+---+--------------------------------------------------------+--------------------+



df_nnn.select("cid","fit_day","installTime").take(100).map(x=>x.getAs[String]("cid")+","+x.getAs[String]("fit_day")+","+x.getAs[String]("installTime"))

0925
+---+--------------------------------------------------------+---------------------+
|_1 |_2                                                      |_3                   |
+---+--------------------------------------------------------+---------------------+
|4  |advertising_id_s=unique&&puuid=unique                   |-2.280864500902981   |
|29 |puuid=unique                                            |-1.7819132633786512  |
|3  |advertising_id_s=unique                                 |-1.7327488279071432  |
|13 |advertising_id_s=white                                  |-0.9138241592838404  |
|12 |imei_s=unique&&puuid=unique                             |-0.8802977700297845  |
|40 |mac_addr_s=unique&&puuid=unique                         |-0.7915645954849367  |
|9  |imei_s=unique                                           |-0.6910908948103381  |
|16 |android_serial_s=unique&&puuid=unique                   |-0.5952524218967348  |
|14 |android_serial_s=unique                                 |-0.5864134639230963  |
|6  |imei_s=unique&&mac_addr_s=unique                        |-0.5031105295797594  |
|15 |mac_addr_s=unique                                       |-0.48861686462428755 |
|49 |android_serial_s=unique&&imei_s=unique                  |-0.4325993121322087  |
|33 |advertising_id_s=unique&&imei_s=unique                  |-0.3321657160391912  |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.31365400019245576 |
|10 |cpu_serial_s=unique&&puuid=unique                       |-0.3017144934279712  |
|34 |cpu_serial_s=unique                                     |-0.3016325062882082  |
|18 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.2579619864987924  |
|38 |advertising_id_s=unique&&mac_addr_s=unique              |-0.2322244115592903  |
|22 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.2196313185181671  |
|26 |android_serial_s=unique&&cpu_serial_s=unique            |-0.20310722971450934 |
|35 |advertising_id_s=unique&&android_serial_s=unique        |-0.18742292489395998 |
|17 |mac_addr_s=missing                                      |-0.10736617136211629 |
|19 |cpu_serial_s=unique&&imei_s=unique                      |-0.10440794051637398 |
|11 |advertising_id_s=missing                                |-0.08752236428540695 |
|7  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.06275555670043655 |
|21 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-0.055914096788464304|
|45 |cpu_serial_s=white                                      |0.0010275589898335654|
|20 |cpu_serial_s=missing                                    |0.02854498501866472  |
|41 |mac_addr_s=white                                        |0.03220300119167444  |
|27 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.1755857951001336   |
|25 |cpu_serial_s=duplicated                                 |0.18610673365176789  |
|28 |android_serial_s=missing                                |0.1975640430790341   |
|0  |mac_addr_s=duplicated                                   |0.20435836388539375  |
|5  |imei_s=duplicated&&mac_addr_s=duplicated                |0.23498673763418537  |
|31 |imei_s=white                                            |0.26296075383197487  |
|48 |android_serial_s=duplicated&&imei_s=duplicated          |0.2800571708723833   |
|30 |android_serial_s=white                                  |0.32137082802269185  |
|39 |advertising_id_s=duplicated&&puuid=duplicated           |0.36665364712789417  |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.4060613587441923   |
|50 |android_serial_s=duplicated                             |0.4791760352630214   |
|42 |cpu_serial_s=duplicated&&puuid=duplicated               |0.5255824455433417   |
|47 |imei_s=duplicated                                       |0.6206354894000653   |
|8  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.8999037521070732   |
|44 |android_serial_s=duplicated&&puuid=duplicated           |0.9434921323030309   |
|24 |mac_addr_s=duplicated&&puuid=duplicated                 |0.9969327402762891   |
|43 |imei_s=duplicated&&puuid=duplicated                     |1.2904450098537263   |
|32 |advertising_id_s=duplicated&&imei_s=duplicated          |1.3928656567576676   |
|37 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |1.438431721615119    |
|36 |advertising_id_s=duplicated&&android_serial_s=duplicated|1.4426504100678232   |
|46 |advertising_id_s=duplicated                             |1.7011353881510343   |
|23 |puuid=duplicated                                        |1.7282632491963879   |
+---+--------------------------------------------------------+---------------------+
|51 |intercept_of_model                                      |-0.44294080353270204 |
+---+--------------------------------------------------------+---------------------+




>>>> 10.10
+---+--------------------------------------------------------+----------------------+
|_1 |_2                                                      |_3                    |
+---+--------------------------------------------------------+----------------------+
|4  |advertising_id_s=unique&&puuid=unique                   |-2.277179936791052    |
|30 |puuid=unique                                            |-1.7796513681587236   |
|3  |advertising_id_s=unique                                 |-1.7549974185578705   |
|13 |advertising_id_s=white                                  |-0.9271914117569053   |
|25 |puuid=missing                                           |-0.9253969274785014   |
|12 |imei_s=unique&&puuid=unique                             |-0.8715821231445345   |
|41 |mac_addr_s=unique&&puuid=unique                         |-0.7820013642161194   |
|9  |imei_s=unique                                           |-0.6985771493623502   |
|14 |android_serial_s=unique                                 |-0.5952154288673629   |
|16 |android_serial_s=unique&&puuid=unique                   |-0.584857778995877    |
|6  |imei_s=unique&&mac_addr_s=unique                        |-0.5157355569770612   |
|15 |mac_addr_s=unique                                       |-0.5001495777540105   |
|50 |android_serial_s=unique&&imei_s=unique                  |-0.43998973870259034  |
|34 |advertising_id_s=unique&&imei_s=unique                  |-0.3490366303823418   |
|2  |android_serial_s=unique&&mac_addr_s=unique              |-0.31908651022211854  |
|35 |cpu_serial_s=unique                                     |-0.30988109671142317  |
|10 |cpu_serial_s=unique&&puuid=unique                       |-0.3040060990894237   |
|18 |cpu_serial_s=duplicated&&imei_s=duplicated              |-0.24964572172413818  |
|39 |advertising_id_s=unique&&mac_addr_s=unique              |-0.24687235067733174  |
|22 |cpu_serial_s=unique&&mac_addr_s=unique                  |-0.22496731547907084  |
|27 |android_serial_s=unique&&cpu_serial_s=unique            |-0.2085212668728207   |
|36 |advertising_id_s=unique&&android_serial_s=unique        |-0.1980222163833496   |
|17 |mac_addr_s=missing                                      |-0.10875838869782428  |
|11 |advertising_id_s=missing                                |-0.1080615416781477   |
|19 |cpu_serial_s=unique&&imei_s=unique                      |-0.10713824869364363  |
|7  |advertising_id_s=unique&&cpu_serial_s=unique            |-0.06644219157649112  |
|21 |cpu_serial_s=duplicated&&mac_addr_s=duplicated          |-0.05816243501845365  |
|46 |cpu_serial_s=white                                      |-0.0035892989228132016|
|42 |mac_addr_s=white                                        |0.02242606455086675   |
|20 |cpu_serial_s=missing                                    |0.02641611127258467   |
|28 |android_serial_s=duplicated&&cpu_serial_s=duplicated    |0.17049611683153243   |
|26 |cpu_serial_s=duplicated                                 |0.1807671670127907    |
|29 |android_serial_s=missing                                |0.18483053648685      |
|0  |mac_addr_s=duplicated                                   |0.20815595117737243   |
|5  |imei_s=duplicated&&mac_addr_s=duplicated                |0.23084717113746114   |
|32 |imei_s=white                                            |0.25929848070021727   |
|49 |android_serial_s=duplicated&&imei_s=duplicated          |0.2754752154147363    |
|31 |android_serial_s=white                                  |0.3274049880925025    |
|40 |advertising_id_s=duplicated&&puuid=duplicated           |0.3585671574921511    |
|1  |android_serial_s=duplicated&&mac_addr_s=duplicated      |0.39915076285326473   |
|51 |android_serial_s=duplicated                             |0.47653187417048753   |
|43 |cpu_serial_s=duplicated&&puuid=duplicated               |0.5207131648694397    |
|48 |imei_s=duplicated                                       |0.6191172569559167    |
|8  |advertising_id_s=duplicated&&cpu_serial_s=duplicated    |0.881569875164803     |
|45 |android_serial_s=duplicated&&puuid=duplicated           |0.9484291731376093    |
|24 |mac_addr_s=duplicated&&puuid=duplicated                 |0.9966738711027008    |
|44 |imei_s=duplicated&&puuid=duplicated                     |1.2824496611155698    |
|33 |advertising_id_s=duplicated&&imei_s=duplicated          |1.395529294636752     |
|37 |advertising_id_s=duplicated&&android_serial_s=duplicated|1.4343518214837216    |
|38 |advertising_id_s=duplicated&&mac_addr_s=duplicated      |1.4414576216740767    |
|47 |advertising_id_s=duplicated                             |1.7143720905758717    |
|23 |puuid=duplicated                                        |1.7207770334561818    |
+---+--------------------------------------------------------+----------------------+
|52 |intercept_of_model                                      |-0.39705280567148976  |
+---+--------------------------------------------------------+----------------------+













