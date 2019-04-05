第一站：（深圳）

1、多个线程同时读写，读线程的数量远远大于写线程，你认为应该如何解决并发的问题？你会选择加什么样的锁？

2、JAVA的AQS是否了解，它是干嘛的？

3、除了synchronized关键字之外，你是怎么来保障线程安全的？

4、什么时候需要加volatile关键字？它能保证线程安全吗？

5、线程池内的线程如果全部忙，提交一个新的任务，会发生什么？队列全部塞满了之后，还是忙，再提交会发生什么？

答：考察对线程池和队列的理解

6、Tomcat本身的参数你一般会怎么调整？

7、synchronized关键字锁住的是什么东西？在字节码中是怎么表示的？在内存中的对象上表现为什么？

8、wait/notify/notifyAll方法需不需要被包含在synchronized块中？这是为什 么？

9、ExecutorService你一般是怎么用的？是每个service放一个还是一个项目里面放一个？有什么好处？


Other
1、POST和GET的区别

答：GET请求参数在url中，POST请求封装在报文中；GET进行一次TCP，POST进行两次。

2、HttpURLConnection 的两个方法 addRequestProperty 和 setRequestProperty 有什么不一样

答：addRequestProperty 不管 key 是否存在，直接添加，setRequestProperty 是有则覆盖，无则添加。

Spring

你有没有用过Spring的AOP? 是用来干嘛的? 大概会怎么使用？

如果一个接口有2个不同的实现, 那么怎么来Autowire一个指定的实现？
答：使用@Qualifier注解。

Spring的声明式事务 @Transaction 注解一般写在什么位置? 抛出了异常会自动回滚吗？有没有办法控制不触发回滚?


如果想在某个 Beans 生成并装配完毕后执行自己的逻辑，可以用什么方式实现？

SpringBoot没有放到web容器⾥为什么能跑HTTP服务？
SpringBoot中如果你想使⽤⾃定义的配置⽂件⽽不仅仅是 application.properties，应该怎么弄？
SpringMVC中RequestMapping可以指定GET, POST⽅法么？怎么指定？
SpringMVC如果希望把输出的Object(例如XXResult或者XXResponse)这 种包装为JSON输出, 应该怎么处理?
怎样拦截SpringMVC的异常，然后做⾃定义的处理，⽐如打⽇志或者包装 成JSON
1.struts1和struts2的区别
.struts2和springMVC的区别
spring框架中需要引用哪些jar包，以及这些jar包的用途
springMVC的原理
springMVC注解的意思
spring中beanFactory和ApplicationContext的联系和区别
spring注入的几种方式
spring如何实现事物管理的
springIOC和AOP的原理
hibernate中的1级和2级缓存的使用方式以及区别原理
spring中循环注入的方式


MySQL

如果有很多数据插⼊MYSQL 你会选择什么⽅式?
如果查询很慢，你会想到的第⼀个⽅式是什么？索引是⼲嘛的?
如果建了⼀个单列索引，查询的时候查出2列，会⽤到这个单列索引吗？
如果建了⼀个包含多个列的索引，查询的时候只⽤了第⼀列，能不能⽤上 这个索引？查三列呢？
接上题，如果where条件后⾯带有⼀个 i + 5 < 100 会使⽤到这个索引吗？
怎么看是否⽤到了某个索引？
like %aaa%会使⽤索引吗? like aaa%呢?
drop、truncate、delete的区别？
平时你们是怎么监控数据库的? 慢SQL是怎么排查的？
你们数据库是否⽀持emoji表情，如果不⽀持，如何操作?
你们的数据库单表数据量是多少？⼀般多⼤的时候开始出现查询性能急 剧下降？
查询死掉了，想要找出执⾏的查询进程⽤什么命令？找出来之后⼀般你 会⼲嘛？
读写分离是怎么做的？你认为中间件会怎么来操作？这样操作跟事务有 什么关系？ 14. 分库分表有没有做过？线上的迁移过程是怎么样的？如何确定数据是正 确的？
MySQL常用命令
数据库中事物的特征？
JDBC的使用？
InnodB与MyISAM的区别
MySQL为什么使用B+树作为索引？


JVM

1) 你知道哪些或者你们线上使用什么GC策略? 它有什么优势，适用于什么场景？
2) JAVA类加载器包括几种？它们之间的父子关系是怎么样的？双亲委派机制是什么意思？有什么好处？
3) 如何自定义一个类加载器？你使用过哪些或者你在什么场景下需要⼀个自定义的类加载器吗？
4) 堆内存设置的参数是什么？
5) Perm Space中保存什么数据? 会引起OutOfMemory吗？
6) 做GC时，一个对象在内存各个Space中被移动的顺序是什么？
7) 你有没有遇到过OutOfMemory问题？你是怎么来处理这个问题的？处理过程中有哪些收获？
8) 1.8之后Perm Space有哪些变动? MetaSpace大小默认是⽆限的么? 还是你们会通过什么方式来指定大小?
9) Jstack是干什么的？Jstat呢？如果线上程序周期性地出现卡顿，你怀疑可能是GC导致的，你会怎么来排查这个问题？线程日志一般你会看其中的什么部分？
10) StackOverFlow异常有没有遇到过？一般你猜测会在什么情况下被触发？如何指定一个线程的堆栈大小？一般你们写多少？


多线程

1) 什么是线程？
2) 线程和进程有什么区别？
3) 如何在Java中实现线程？
4) 用Runnable还是Thread？
6) Thread 类中的start() 和 run() 方法有什么区别？
7) Java中CyclicBarrier 和 CountDownLatch有什么不同？
8) Java中的volatile 变量是什么？
9) Java中的同步集合与并发集合有什么区别？
10) 如何避免死锁？
11) Java中活锁和死锁有什么区别？
12) Java中synchronized 和 ReentrantLock 有什么不同？
13) Java中ConcurrentHashMap的并发度是什么？
14) 如何在Java中创建Immutable对象？
15) 单例模式的双检锁是什么？
16) 写出3条你遵循的多线程最佳实践
17) 如何避免死锁？
18) 常用的线程池模式以及不同线程池的使用场景


Netty

1) BIO、NIO和AIO的区别？
2) NIO的组成？
3) Netty的特点？
4) Netty的线程模型？
5) TCP 粘包/拆包的原因及解决方法？
6) 了解哪几种序列化协议？
7) 如何选择序列化协议？
8) Netty的零拷贝实现？
9) Netty的高性能表现在哪些方面？
10) NIOEventLoopGroup源码？


Redis

1) Redis与Memorycache的区别？
2) Redis的五种数据结构？
3) 渐进式rehash过程？
4) rehash源码？
5) 持久化机制
6) reaof源码？
7) 事务与事件
8) 主从复制
9) 启动过程
10) 集群
11) Redis的6种数据淘汰策略
12) redis的并发竞争问题？


Hadoop

1) HDFS的特点？
2) 客户端从HDFS中读写数据过程？
3) HDFS的文件目录结构？
4) NameNode的内存结构？
5) NameNode的重启优化？
6) Git的使用？
7) Maven的使用

好了，到这里面试题差不多已经写完了，如果以上的面试题题目你已经全部理解，全部能回答出来了，那么我可以在这里告诉你，你牛批，我服。
如果以上题目还不会的，还回答不上来的，那么你可以跟着我的脚步，我来介绍一下我总结出来的几大体系，如果你想学习这些内容，我可以向大家推荐一下学习交流群：251981998 大家想学习的可以加群，群里面有里面有Java工程化、高性能及分布式、高性能、深入浅出。性能调优、Spring，MyBatis，Netty源码分析等知识点的讲解，但记得备注好信息哦。（记住：加群：251981998请备注好信息）