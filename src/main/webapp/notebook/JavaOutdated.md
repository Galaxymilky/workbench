## Hibernate 和 MyBatis 相关
#### 什么是ORM？
答：对象关系映射（Object-Relational Mapping，简称ORM）是一种为了解决程序的面向对象模型与数据库的关系模型互不匹配问题的技术；简单的说，ORM是通过使用描述对象和数据库之间映射的元数据（在Java中可以用XML或者是注解），将程序中的对象自动持久化到关系数据库中或者将关系数据库表中的行转换成Java对象，其本质上就是将数据从一种形式转换到另外一种形式。
JPA、Hibernate、Mybatis都含有ORM模型。

#### 持久层设计要考虑的问题有哪些？你用过的持久层框架有哪些？
答：所谓"持久"就是将数据保存到可掉电式存储设备中以便今后使用，简单的说，就是将内存中的数据保存到关系型数据库、文件系统、消息队列等提供持久化支持的设备中。持久层就是系统中专注于实现数据持久化的相对独立的层面。

持久层设计的目标包括：
- 数据存储逻辑的分离，提供抽象化的数据访问接口。
- 数据访问底层实现的分离，可以在不修改代码的情况下切换底层实现。
- 资源管理和调度的分离，在数据访问层实现统一的资源调度（如缓存机制）。
- 数据抽象，提供更面向对象的数据操作。

持久层框架有：
- Hibernate
- MyBatis
- TopLink
- Guzz
- jOOQ
- Spring Data
- ActiveJDBC

#### Hibernate中SessionFactory是线程安全的吗？Session是线程安全的吗（两个线程能够共享同一个Session吗）？
答：SessionFactory对应Hibernate的一个数据存储的概念，它是线程安全的，可以被多个线程并发访问。SessionFactory一般只会在启动的时候构建。对于应用程序，最好将SessionFactory通过单例模式进行封装以便于访问。Session是一个轻量级非线程安全的对象（线程间不能共享session），它表示与数据库进行交互的一个工作单元。Session是由SessionFactory创建的，在任务完成之后它会被关闭。Session是持久层服务对外提供的主要接口。Session会延迟获取数据库连接（也就是在需要的时候才会获取）。为了避免创建太多的session，可以使用ThreadLocal将session和当前线程绑定在一起，这样可以让同一个线程获得的总是同一个session。Hibernate 3中SessionFactory的getCurrentSession()方法就可以做到。

#### Hibernate中Session的load和get方法的区别是什么？
答：主要有以下三项区别：
1. 如果没有找到符合条件的记录，get方法返回null，load方法抛出异常。
2. get方法直接返回实体类对象，load方法返回实体类对象的代理。
3. 在Hibernate3之前，get方法只在一级缓存中进行数据查找，如果没有找到对应的数据则越过二级缓存，直接发出SQL语句完成数据读取；load方法则可以从二级缓存中获取数据；从Hibernate 3开始，get方法不再是对二级缓存只写不读，它也是可以访问二级缓存的。

说明：对于load()方法Hibernate认为该数据在数据库中一定存在可以放心的使用代理来实现延迟加载，如果没有数据就抛出异常，而通过get()方法获取的数据可以不存在。

#### Session的save()、update()、merge()、lock()、saveOrUpdate()和persist()方法分别是做什么的？有什么区别？
答：Hibernate的对象有三种状态：瞬时态（transient）、持久态（persistent）和游离态（detached），如第135题中的图所示。瞬时态的实例可以通过调用save()、persist()或者saveOrUpdate()方法变成持久态；游离态的实例可以通过调用 update()、saveOrUpdate()、lock()或者replicate()变成持久态。save()和persist()将会引发SQL的INSERT语句，而update()或merge()会引发UPDATE语句。save()和update()的区别在于一个是将瞬时态对象变成持久态，一个是将游离态对象变为持久态。merge()方法可以完成save()和update()方法的功能，它的意图是将新的状态合并到已有的持久化对象上或创建新的持久化对象。对于persist()方法，按照官方文档的说明：① persist()方法把一个瞬时态的实例持久化，但是并不保证标识符被立刻填入到持久化实例中，标识符的填入可能被推迟到flush的时间；② persist()方法保证当它在一个事务外部被调用的时候并不触发一个INSERT语句，当需要封装一个长会话流程的时候，persist()方法是很有必要的；③ save()方法不保证第②条，它要返回标识符，所以它会立即执行INSERT语句，不管是在事务内部还是外部。至于lock()方法和update()方法的区别，update()方法是把一个已经更改过的脱管状态的对象变成持久状态；lock()方法是把一个没有更改过的脱管状态的对象变成持久状态。

#### 阐述Session加载实体对象的过程。
答：Session加载实体对象的步骤是：
- Session在调用数据库查询功能之前，首先会在一级缓存中通过实体类型和主键进行查找，如果一级缓存查找命中且数据状态合法，则直接返回；
- 如果一级缓存没有命中，接下来Session会在当前NonExists记录（相当于一个查询黑名单，如果出现重复的无效查询可以迅速做出判断，从而提升性能）中进行查找，如果NonExists中存在同样的查询条件，则返回null；
- 如果一级缓存查询失败则查询二级缓存，如果二级缓存命中则直接返回；
- 如果之前的查询都未命中，则发出SQL语句，如果查询未发现对应记录则将此次查询添加到Session的NonExists中加以记录，并返回null；
- 根据映射配置和SQL语句得到ResultSet，并创建对应的实体对象；
- 将对象纳入Session（一级缓存）的管理；
- 如果有对应的拦截器，则执行拦截器的onLoad方法；
- 如果开启并设置了要使用二级缓存，则将数据对象纳入二级缓存；
- 返回数据对象。

#### Query 接口的 list 方法和 iterate 方法有什么区别？
答：
- list()方法无法利用一级缓存和二级缓存（对缓存只写不读），它只能在开启查询缓存的前提下使用查询缓存；iterate()方法可以充分利用缓存，如果目标数据只读或者读取频繁，使用iterate()方法可以减少性能开销。
- list()方法不会引起N+1查询问题，而iterate()方法可能引起N+1查询问题

说明：关于N+1查询问题，可以参考CSDN上的一篇文章《什么是N+1查询》

#### Hibernate如何实现分页查询？
答：通过Hibernate实现分页查询，开发人员只需要提供HQL语句（调用Session的createQuery()方法）或查询条件（调用Session的createCriteria()方法）、设置查询起始行数（调用Query或Criteria接口的setFirstResult()方法）和最大查询行数（调用Query或Criteria接口的setMaxResults()方法），并调用Query或Criteria接口的list()方法，Hibernate会自动生成分页查询的SQL语句。

#### 锁机制有什么用？简述Hibernate的悲观锁和乐观锁机制。
答：有些业务逻辑在执行过程中要求对数据进行排他性的访问，于是需要通过一些机制保证在此过程中数据被锁住不会被外界修改，这就是所谓的锁机制。
Hibernate支持悲观锁和乐观锁两种锁机制。悲观锁，顾名思义悲观的认为在数据处理过程中极有可能存在修改数据的并发事务（包括本系统的其他事务或来自外部系统的事务），于是将处理的数据设置为锁定状态。悲观锁必须依赖数据库本身的锁机制才能真正保证数据访问的排他性，关于数据库的锁机制和事务隔离级别在《Java面试题大全（上）》中已经讨论过了。乐观锁，顾名思义，对并发事务持乐观态度（认为对数据的并发操作不会经常性的发生），通过更加宽松的锁机制来解决由于悲观锁排他性的数据访问对系统性能造成的严重影响。最常见的乐观锁是通过数据版本标识来实现的，读取数据时获得数据的版本号，更新数据时将此版本号加1，然后和数据库表对应记录的当前版本号进行比较，如果提交的数据版本号大于数据库中此记录的当前版本号则更新数据，否则认为是过期数据无法更新。Hibernate中通过Session的get()和load()方法从数据库中加载对象时可以通过参数指定使用悲观锁；而乐观锁可以通过给实体类加整型的版本字段再通过XML或@Version注解进行配置。

提示：使用乐观锁会增加了一个版本字段，很明显这需要额外的空间来存储这个版本字段，浪费了空间，但是乐观锁会让系统具有更好的并发性，这是对时间的节省。因此乐观锁也是典型的空间换时间的策略。

#### 阐述实体对象的三种状态以及转换关系。
答：最新的Hibernate文档中为Hibernate对象定义了四种状态（原来是三种状态，面试的时候基本上问的也是三种状态），分别是：瞬时态（new, or transient）、持久态（managed, or persistent）、游状态（detached）和移除态（removed，以前Hibernate文档中定义的三种状态中没有移除态），如下图所示，就以前的Hibernate文档中移除态被视为是瞬时态。

- 瞬时态：当new一个实体对象后，这个对象处于瞬时态，即这个对象只是一个保存临时数据的内存区域，如果没有变量引用这个对象，则会被JVM的垃圾回收机制回收。这个对象所保存的数据与数据库没有任何关系，除非通过Session的save()、saveOrUpdate()、persist()、merge()方法把瞬时态对象与数据库关联，并把数据插入或者更新到数据库，这个对象才转换为持久态对象。
- 持久态：持久态对象的实例在数据库中有对应的记录，并拥有一个持久化标识（ID）。对持久态对象进行delete操作后，数据库中对应的记录将被删除，那么持久态对象与数据库记录不再存在对应关系，持久态对象变成移除态（可以视为瞬时态）。持久态对象被修改变更后，不会马上同步到数据库，直到数据库事务提交。
- 游离态：当Session进行了close()、clear()、evict()或flush()后，实体对象从持久态变成游离态，对象虽然拥有持久和与数据库对应记录一致的标识值，但是因为对象已经从会话中清除掉，对象不在持久化管理之内，所以处于游离态（也叫脱管态）。游离态的对象与临时状态对象是十分相似的，只是它还含有持久化标识。

提示：关于这个问题，在Hibernate的官方文档中有更为详细的解读。

#### 如何理解Hibernate的延迟加载机制？在实际应用中，延迟加载与Session关闭的矛盾是如何处理的？
答：延迟加载就是并不是在读取的时候就把数据加载进来，而是等到使用时再加载。Hibernate使用了虚拟代理机制实现延迟加载，我们使用Session的load()方法加载数据或者一对多关联映射在使用延迟加载的情况下从一的一方加载多的一方，得到的都是虚拟代理，简单的说返回给用户的并不是实体本身，而是实体对象的代理。代理对象在用户调用getter方法时才会去数据库加载数据。但加载数据就需要数据库连接。而当我们把会话关闭时，数据库连接就同时关闭了。

延迟加载与session关闭的矛盾一般可以这样处理：
-  关闭延迟加载特性。这种方式操作起来比较简单，因为Hibernate的延迟加载特性是可以通过映射文件或者注解进行配置的，但这种解决方案存在明显的缺陷。首先，出现"no session or session was closed"通常说明系统中已经存在主外键关联，如果去掉延迟加载的话，每次查询的开销都会变得很大。
-  在session关闭之前先获取需要查询的数据，可以使用工具方法Hibernate.isInitialized()判断对象是否被加载，如果没有被加载则可以使用Hibernate.initialize()方法加载对象。
-  使用拦截器或过滤器延长Session的生命周期直到视图获得数据。Spring整合Hibernate提供的OpenSessionInViewFilter和OpenSessionInViewInterceptor就是这种做法。

#### 举一个多对多关联的例子，并说明如何实现多对多关联映射。
答：例如：商品和订单、学生和课程都是典型的多对多关系。可以在实体类上通过@ManyToMany注解配置多对多关联或者通过映射文件中的和标签配置多对多关联，但是实际项目开发中，很多时候都是将多对多关联映射转换成两个多对一关联映射来实现的。

#### 谈一下你对继承映射的理解。
答：继承关系的映射策略有三种：
- 每个继承结构一张表（table per class hierarchy），不管多少个子类都用一张表。
- 每个子类一张表（table per subclass），公共信息放一张表，特有信息放单独的表。
- 每个具体类一张表（table per concrete class），有多少个子类就有多少张表。
第一种方式属于单表策略，其优点在于查询子类对象的时候无需表连接，查询速度快，适合多态查询；缺点是可能导致表很大。后两种方式属于多表策略，其优点在于数据存储紧凑，其缺点是需要进行连接查询，不适合多态查询。

#### 简述Hibernate常见优化策略。
答：这个问题应当挑自己使用过的优化策略回答，常用的有：
- 制定合理的缓存策略（二级缓存、查询缓存）。
- 采用合理的Session管理机制。
- 尽量使用延迟加载特性。
- 设定合理的批处理参数。
- 如果可以，选用UUID作为主键生成器。
- 如果可以，选用基于版本号的乐观锁替代悲观锁。
- 在开发过程中, 开启hibernate.show_sql选项查看生成的SQL，从而了解底层的状况；开发完成后关闭此选项。
- 考虑数据库本身的优化，合理的索引、恰当的数据分区策略等都会对持久层的性能带来可观的提升，但这些需要专业的DBA（数据库管理员）提供支持。

#### 谈一谈Hibernate的一级缓存、二级缓存和查询缓存。
答：Hibernate的Session提供了一级缓存的功能，默认总是有效的，当应用程序保存持久化实体、修改持久化实体时，Session并不会立即把这种改变提交到数据库，而是缓存在当前的Session中，除非显示调用了Session的flush()方法或通过close()方法关闭Session。通过一级缓存，可以减少程序与数据库的交互，从而提高数据库访问性能。
SessionFactory级别的二级缓存是全局性的，所有的Session可以共享这个二级缓存。不过二级缓存默认是关闭的，需要显示开启并指定需要使用哪种二级缓存实现类（可以使用第三方提供的实现）。一旦开启了二级缓存并设置了需要使用二级缓存的实体类，SessionFactory就会缓存访问过的该实体类的每个对象，除非缓存的数据超出了指定的缓存空间。
一级缓存和二级缓存都是对整个实体进行缓存，不会缓存普通属性，如果希望对普通属性进行缓存，可以使用查询缓存。查询缓存是将HQL或SQL语句以及它们的查询结果作为键值对进行缓存，对于同样的查询可以直接从缓存中获取数据。查询缓存默认也是关闭的，需要显示开启。

#### Hibernate中DetachedCriteria类是做什么的？
答：DetachedCriteria和Criteria的用法基本上是一致的，但Criteria是由Session的createCriteria()方法创建的，也就意味着离开创建它的Session，Criteria就无法使用了。DetachedCriteria不需要Session就可以创建（使用DetachedCriteria.forClass()方法创建），所以通常也称其为离线的Criteria，在需要进行查询操作的时候再和Session绑定（调用其getExecutableCriteria(Session)方法），这也就意味着一个DetachedCriteria可以在需要的时候和不同的Session进行绑定。

#### @OneToMany注解的mappedBy属性有什么作用？
答：@OneToMany用来配置一对多关联映射，但通常情况下，一对多关联映射都由多的一方来维护关联关系，例如学生和班级，应该在学生类中添加班级属性来维持学生和班级的关联关系（在数据库中是由学生表中的外键班级编号来维护学生表和班级表的多对一关系），如果要使用双向关联，在班级类中添加一个容器属性来存放学生，并使用@OneToMany注解进行映射，此时mappedBy属性就非常重要。如果使用XML进行配置，可以用<set>标签的inverse="true"设置来达到同样的效果。

#### MyBatis中使用#和$书写占位符有什么区别？
答：#将传入的数据都当成一个字符串，会对传入的数据自动加上引号；$将传入的数据直接显示生成在SQL中。注意：使用$占位符可能会导致SQL注射攻击，能用#的地方就不要使用$，写order by子句的时候应该用$而不是#。

#### 解释一下MyBatis中命名空间（namespace）的作用。
答：在大型项目中，可能存在大量的SQL语句，这时候为每个SQL语句起一个唯一的标识（ID）就变得并不容易了。为了解决这个问题，在MyBatis中，可以为每个映射文件起一个唯一的命名空间，这样定义在这个映射文件中的每个SQL语句就成了定义在这个命名空间中的一个ID。只要我们能够保证每个命名空间中这个ID是唯一的，即使在不同映射文件中的语句ID相同，也不会再产生冲突了。

#### MyBatis中的动态SQL是什么意思？
答：对于一些复杂的查询，我们可能会指定多个查询条件，但是这些条件可能存在也可能不存在，例如在58同城上面找房子，我们可能会指定面积、楼层和所在位置来查找房源，也可能会指定面积、价格、户型和所在位置来查找房源，此时就需要根据用户指定的条件动态生成SQL语句。如果不使用持久层框架我们可能需要自己拼装SQL语句，还好MyBatis提供了动态SQL的功能来解决这个问题。MyBatis中用于实现动态SQL的元素主要有：
- if
- choose / when / otherwise
- trim
- where
- set
- foreach

下面是映射文件的片段。

    <select id="foo" parameterType="Blog" resultType="Blog">
        select * from t_blog where 1 = 1

        <if test="title != null">
        and title = #{title}
        </if>

        <if test="content != null">
        and content = #{content}
        </if>

        <if test="owner != null">
        and owner = #{owner}
        </if>
    </select>


当然也可以像下面这些书写。

    <select id="foo" parameterType="Blog" resultType="Blog">
        select * from t_blog where 1 = 1
        <choose>
            <when test="title != null">
            and title = #{title}
            </when>

            <when test="content != null">
            and content = #{content}
            </when>

            <otherwise>
            and owner = "owner1"
            </otherwise>
        </choose>
    </select>


再看看下面这个例子。

    <select id="bar" resultType="Blog">
        select * from t_blog where id in
        <foreach collection="array" index="index" item="item" open="(" separator="," close=")">
            #{item}
        </foreach>
    </select>


## web service 相关
#### 什么是Web Service？
答：从表面上看，Web Service就是一个应用程序，它向外界暴露出一个能够通过Web进行调用的API。这就是说，你能够用编程的方法透明的调用这个应用程序，不需要了解它的任何细节，跟你使用的编程语言也没有关系。例如可以创建一个提供天气预报的Web Service，那么无论你用哪种编程语言开发的应用都可以通过调用它的API并传入城市信息来获得该城市的天气预报。之所以称之为Web Service，是因为它基于HTTP协议传输数据，这使得运行在不同机器上的不同应用无须借助附加的、专门的第三方软件或硬件，就可相互交换数据或集成。


#### REST和SOAP比较
（www.ruanyifeng.com/blog/2014/05/restful_api.html）

RESTful API 是目前比较成熟的互联网应用程序的API设计理论，主要是为了解决不同前端设备与后端通信（前端设备层出不穷，手机、平板、PC等）。
REST（Representational State Transfer），REST采用简单的URL的方式来代表一个对象，例如一个URL就对应一个对象。

##### 协议：
API 与用户的通信协议，HTTPs协议

##### 域名：
尽量布置在专用域名下，若 API 简单，可以放在主域名下

##### 版本（Version）：
API 版本号放入 URL，或者存入 HTTP 头信息中；

##### 路径（Endpoint）API 的具体网址：
在 RESTful 架构中，每个网址代表一种资源，所以网址只能有名词，而且名词往往与数据库表名对应。
如用户信息：https://api.example.com/v1/appusers

##### HTTP动词
对资源的具体操作类型，由 HTTP 动词表示
- GET (SELECT)：去除资源；
- POST (CREATE)：新建一个资源；
- PUT (UPDATE)：更新资源，客户端提供改变后完整资源；
- PATCH (UPDATE)：更新资源，客户端提供改变的属性；
- DELETE (DELETE)：删除资源；
- HEAD：获取资源元数据；
- OPTIONS：获取信息，关于资源的哪些属性是客户端可以改变的。

##### 过滤信息（Filtering）
记录数量很多，服务器不可能将其返回给用户，API 提供参数，过滤返回结果。
- ?limit=10 ：指定返回记录的条数
- ?offset=10 ：指定返回记录的开始位置
- ?page=2&per_page=100 ：指定第几页，每页的记录数
- ?sortby=name&order=asc ：指定返回结果按照哪个属性排序，以及排序顺序。
- ?animal_type_id=1 ：指定筛选条件

##### 状态码（Status Codes）
服务器向用户返回的状态码和提示信息（https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html）
- 200 OK - GET ：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）；
- 201 CREATED - POST/PUT/PATCH ：用户新建或修改数据成功；
- 202 ACCEPTED - * ：表示一个请求已经进入后台排队（异步任务）；
- 204 NO CONTENT - DELETE ：用户删除数据成功；
- 400 INVALID REQUEST - POST/PUT/PATCH ：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
- 401 Unauthorized - * ：表示用户没有权限（令牌、用户名、密码错误）。
- 403 Forbiden - * ：表示用户得到授权（与401错误相对），但是访问是被禁止的。
- 404 Not FOUND - * ：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
- 406 Not Acceptable - GET ：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
- 410 Gone - GET ：用户请求的资源背永久删除，且不会再得到；
- 422 Unprocessable entity - POST/PUT/PATCH ：当创建一个对象时，发生一个验证错误；
- 500 INTERNAL SERVER ERROR - * ：服务器发生错误，用户无法判断请求是否成功；

##### 错误处理（Error Handling）
如果状态码是4xx，就应该向用户返回出错信息。一般来说，返回的信息中将error作为键名，出错信息作为键值即可。

    {
        error: "Invalid API key"
    }

##### 返回结果
针对不同操作，服务器向用户返回的结果应该符合以下规范。
- GET /collection：返回资源对象的列表（数组）
- GET /collection/resource：返回单个资源对象
- POST /collection：返回新生成的资源对象
- PUT /collection/resource：返回完整的资源对象
- PATCH /collection/resource：返回完整的资源对象
- DELETE /collection/resource：返回一个空文档

##### Hypermedia API
RESTful API最好做到Hypermedia，即返回结果中提供链接，连向其他API方法，使得用户不查文档，也知道下一步应该做什么。
比如，当用户向api.example.com的根目录发出请求，会得到这样一个文档。

    {"link": {
      "rel":   "collection https://www.example.com/zoos",
      "href":  "https://api.example.com/zoos",
      "title": "List of zoos",
      "type":  "application/vnd.yourformat+json"
    }}
上面代码表示，文档中有一个link属性，用户读取这个属性就知道下一步该调用什么API了。rel表示这个API与当前网址的关系（collection关系，并给出该collection的网址），href表示API的路径，title表示API的标题，type表示返回类型。
Hypermedia API的设计被称为HATEOAS。Github的API就是这种设计，访问api.github.com会得到一个所有可用API的网址列表。

    {
      "current_user_url": "https://api.github.com/user",
      "authorizations_url": "https://api.github.com/authorizations",
      // ...
    }
从上面可以看到，如果想获取当前用户的信息，应该去访问api.github.com/user，然后就得到了下面结果。

    {
      "message": "Requires authentication",
      "documentation_url": "https://developer.github.com/v3"
    }
上面代码表示，服务器给出了提示信息，以及文档的网址。

##### 其他
- API的身份认证应该使用 OAuth 2.0 框架。
- 服务器返回的数据格式，应该尽量使用JSON，避免使用XML。

REST的优点：
- 轻量级的解决方案，不必向SOAP那样要构建一个标准的SOAP XML。
- 可读性比较好:可以把URL的名字取得有实际意义。
- 不需要SDK支持：直接一个 HTTP 请求就可以，但是SOAP则可能需要使用到一些Webservice的类库（例如Apache的Axis）。
REST的缺点：
- 复杂的应用中，URL可能非常长，而且不容易解析。

Amazon、Yahoo和国内的阿里软件都提供了REST方式的Webservice调用。


SOAP的优点：
- 定义严格。必须符合SOAP的格式
- 某些时候使用比较方便
- 开发工具支持比较多一点。

Google基本上采用SOAP方式的Webservice。


#### 概念解释：SOAP、WSDL、UDDI。
- SOAP：简单对象访问协议（Simple Object Access Protocol），是Web Service中交换数据的一种协议规范。
- WSDL：Web服务描述语言（Web Service Description Language），它描述了Web服务的公共接口。这是一个基于XML的关于如何与Web服务通讯和使用的服务描述；也就是描述与目录中列出的Web服务进行交互时需要绑定的协议和信息格式。通常采用抽象语言描述该服务支持的操作和信息，使用的时候再将实际的网络协议和信息格式绑定给该服务。
- UDDI：统一描述、发现和集成（Universal Description, Discovery and Integration），它是一个基于XML的跨平台的描述规范，可以使世界范围内的企业在互联网上发布自己所提供的服务。简单的说，UDDI是访问各种WSDL的一个门面（可以参考设计模式中的门面模式）

#### Java规范中和Web Service相关的规范有哪些？
Java规范中和Web Service相关的有三个：
- JAX-WS(JSR 224)：这个规范是早期的基于SOAP的Web Service规范JAX-RPC的替代版本，它并不提供向下兼容性，因为RPC样式的WSDL以及相关的API已经在Java EE5中被移除了。WS-MetaData是JAX-WS的依赖规范，提供了基于注解配置Web Service和SOAP消息的相关API。
- JAXM(JSR 67)：定义了发送和接收消息所需的API,相当于Web Service的服务器端。
- JAX-RS(JSR 311 & JSR 339 & JSR 370)：是Java针对REST（Representation State Transfer）架构风格制定的一套Web Service规范。REST是一种软件架构模式，是一种风格，它不像SOAP那样本身承载着一种消息协议， (两种风格的Web Service均采用了HTTP做传输协议，因为HTTP协议能穿越防火墙，Java的远程方法调用（RMI）等是重量级协议，通常不能穿越防火墙），因此可以将REST视为基于HTTP协议的软件架构。REST中最重要的两个概念是资源定位和资源操作，而HTTP协议恰好完整的提供了这两个点。HTTP协议中的URI可以完成资源定位，而GET、POST、OPTION、DELETE方法可以完成资源操作。因此REST完全依赖HTTP协议就可以完成Web Service，而不像SOAP协议那样只利用了HTTP的传输特性，定位和操作都是由SOAP协议自身完成的，也正是由于SOAP消息的存在使得基于SOAP的Web Service显得笨重而逐渐被淘汰。

#### 介绍一下你了解的Java领域的Web Service框架
- Axis2（Axis的升级版本）
- Jersey（RESTful的Web Service框架）
- CXF（XFire的延续版本）
- Hessian、Turmeric、JBoss SOA等。