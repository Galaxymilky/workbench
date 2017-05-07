### MyBatis
 #### MyBatis中使用#和$书写占位符有什么区别？
 答：#将传入的数据都当成一个字符串，会对传入的数据自动加上引号；$将传入的数据直接显示生成在SQL中。注意：使用$占位符可能会导致SQL注射攻击，能用#的地方就不要使用$，写order by子句的时候应该用$而不是#。
 
 #### 解释一下MyBatis中命名空间（namespace）的作用。
 答：在大型项目中，可能存在大量的SQL语句，这时候为每个SQL语句起一个唯一的标识（ID）就变得并不容易了。为了解决这个问题，在MyBatis中，可以为每个映射文件起一个唯一的命名空间，这样定义在这个映射文件中的每个SQL语句就成了定义在这个命名空间中的一个ID。只要我们能够保证每个命名空间中这个ID是唯一的，即使在不同映射文件中的语句ID相同，也不会再产生冲突了。
 
 #### 除了常见 select|insert|update|delete 外，还有哪些标签？
 答：除了9个动态SQL标签，还有
 - resultMap：
 - parameterMap：
 - sql：sql 片段；
 - include：引入 sql 片段；
 - selectKey：不支持自增的主键生成策略标签；
 
 
 #### 动态SQL标签 
 答：在 XML 映射文件中，使用动态SQL标签，完成逻辑判断和动态拼接 SQL；
     使用 OGNL 从 SQL 参数对象中计算表达式的值，根据表达式的值动态拼接 SQL。
 对于一些复杂的查询，我们可能会指定多个查询条件，但是这些条件可能存在也可能不存在，例如在58同城上面找房子，我们可能会指定面积、楼层和所在位置来查找房源，也可能会指定面积、价格、户型和所在位置来查找房源，此时就需要根据用户指定的条件动态生成SQL语句。如果不使用持久层框架我们可能需要自己拼装SQL语句，还好MyBatis提供了动态SQL的功能来解决这个问题。MyBatis中用于实现动态SQL的元素主要有：
 - if
 - choose / when / otherwise
 - trim
 - where
 - set
 - foreach
 - bind
 
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
     
 #### 通常一个 XML 映射文件，对应一个 DAO 接口，DAO接口的工作原理是什么？DAO接口方法参数不同，能重载吗？
 答：映射文件 namespace 对应DAO接口的全限定名，映射文件 MappedStatement 的 id值对应接口的方法名，映射文件 sql 的参数对应接口的参数。
     Mapper 接口没有实现类，当调用接口方法，接口全限定名+方法名作为key值，可以唯一定位一个MappedStatement。
     如com.ssmdemo.dao.AppUserDao.queryByLoginName，可以唯一找到namespace为com.ssmdemo.dao.AppUserDao下 id = queryByLoginName 的 MappedStatement。
     MyBatis 中 CRUD 相关的标签都会被解析成为 MappedStatement。
     
 #### 如何分页，分页插件的工作原理？
 答：
 - 使用 RowBounds 对象进行分页，针对 ResultSet 结果集进行的内存分页；
 - 在sql内直接书写带有物理分页参数完成物理分页；
 - 分页插件；
 
 分页插件是使用 MyBatis 提供的插件接口，实现的自定义插件，在插件拦截方法内拦截待执行sql，重新编写，根据 dialect 方言，添加对应物理分页语句和参数。
 
     select * from app_user 拦截重写：select t.* from (select * from app_user) t limit 0, 10;
  
 #### 插件运行原理，如何编写
 答：可编写针对 ParameterHandler、ResultSetHandler、StatementHandler、Executor 四种接口插件，
     MyBatis 使用 JDK 动态代理，为需要拦截的接口生成代理对象以实现接口方法。每当执行这4中对象的方法，就进入拦截方法，
     即 InvocationHandler 的 invoke() 方法。
     实现 MyBatis 的 Interceptor 接口并复写 intercept() 方法，然后给插件编写注释，指定要拦截那个接口的哪个方法。
     
 #### 执行批量插入，并返回数据库主键列表
 答：
 
 #### MyBatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？
 答：使用 resultMap 标签，逐一定义列名和对象属性名之间的映射关系；
     使用 sql 的别名功能，将列别名书写为对象属性名。
     列名与属性名的映射关系，MyBatis 通过反射创建对象，同时使用反射给对象的属性逐一赋值并返回。
 
 #### 一对一、一对多关联查询，有哪些实现方式，区别
 答：多对一 == 一对一，selectList 改为 selectOne；
     多对多 == 一对多，selectOne 改为 selectlist；
     关联查询方式一：单独发送一个sql去查询关联对象；
     关联查询方式二：嵌套查询，使用join查询；
 
 #### MyBatis 是否支持延迟加载，原理是什么？
 答：仅支持 association 关联对象和 collection 关联集合对象的延迟加载，association 指的一对一，collection 指的是一对多；
     可以配置是否启用延迟加载：lazyLoadingEnable=true|false；
     原理：使用 CGLIB 创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，
 
 #### MyBatis 执行批处理
 答：使用BatchExecutor完成批处理。
 
 #### MyBatis 有哪些 Executor 执行器？区别是什么？
 答：三种基本：Simple、Reuse、Batch
 - Simple：每次 update 或 select，开启新的 Statement 对象，完成后关闭；
 - Reuse：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，
    而是放置于Map<String, Statement>内，供下一次使用。简言之，就是重复使用Statement对象。
 - Batch：将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，
    每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。
 
 #### MyBatis 指定 Executor 执行器；
 答：在Mybatis配置文件中，可以指定默认的ExecutorType执行器类型，也可以手动给DefaultSqlSessionFactory的创建SqlSession的方法传递ExecutorType类型参数。
 
 
 #### MyBatis 映射 Enum 枚举类；
 答：MyBatis 可以映射任何对象到表的一列上，映射方式为自定义一个 TypeHandler，实现TypeHandler的setParameter和getResult接口方法
     TypeHandler有两个作用，一是完成从JavaType到jdbcType，二是完成jdbcType到javaType的转换，体现为setParameter和getResult方法，
     分别代表设置sql问号占位符参数和获取列表查询结果。
 
 #### MyBatis 映射文件中，A标签 include 标签 B，描述解析过程；
 答：顺序解析XML映射文件，如果 B 未解析，先解析 B，然后返回解析 A；
 
 
 #### 简述 XML 映射文件 和 MyBatis 内部数据结构之间的映射关系；
 答：MyBatis 所有的 XML 配置信息都封装到 All-In-One 重量级对象 Configuration 内部，
     在XML映射文件中，parameterMap 标签会被解析为 parameterMap 对象，resultMap 解析为 ResultMap 对象，
     其每个子元素解析为 ResultMapping 对象，每个 SIUD 标签解析为 MappedStatement 对象，标签内 sql 解析为 BoundSql 对象。
 
 
 #### 为什么 MyBatis 是半自动 ORM 映射工具，与全自动有什么区别；
 答：Hibernate 全自动，使用 Hibernate 查询关联对象或者关联集合对象，可根据对象模型直接获取，所以是全自动；
     MyBatis 在查询关联对象时，需要手工编写sql，所以是半自动。
