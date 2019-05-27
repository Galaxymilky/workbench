
##### cmd

###### Hadoop

$ start-all.sh

然后输入密码

$ hive

输入hive无法进入hive命令行模式



然后进入Hive命令模式

hive> select count(1) from t_user;

发现mapreduce无法使用，所有count和group by 和dml都无效；
发现是mapreduce程序问题，更换端口号，由9000改为8032，重启后可以


##### Problem

###### 1

19/05/24 12:49:35 WARN conf.HiveConf: HiveConf of name hive.metastore.local does not exist

只是WARN，不影响使用，将一下内容注释即可

!--    <property>-->
<!--        <name>hive.metastore.local</name>-->
<!--        <value>true</value>-->
<!--    </property>-->

###### 2

schematool -initSchema -dbType mysql

改成

schematool -dbType mysql -initSchema

###### 3 执行Hive数据初始化

$ schematool -dbType mysql -initSchema
-bash: /Users/dynamicniu/hadoop/hadoop-2.7.3/hive/bin/schematool: Permission denied

Solve

$ chmod u+x /Users/dynamicniu/hadoop/hadoop-2.7.3/hive/bin/schematool

###### 4

执行hadoop命令前，加一下日志

dynamicdeMBP:bin dynamicniu$ export HADOOP_ROOT_LOGGER=DEBUG,console
dynamicdeMBP:bin dynamicniu$ hadoop fs -ls /

###### 5 Hive 无法启动或者MapReduce无法使用

查看端口占用情况

dynamicdeMBP:bin dynamicniu$ lsof -i:8080

###### 6 Hive 无法启动

一，先开启 metastore

hive --service metastore &

二，先开启 hiveserver2

 hive --service hiveserver2 &