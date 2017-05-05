## Spring 相关
#### 什么是IoC和DI？DI是如何实现的？
答：IoC（Inversion of Control）控制反转，DI（Dependency Injection）依赖注入，是对IoC更简单的诠释。
控制反转是把传统上由程序代码直接操控的对象的调用权交给容器，通过容器来实现对象组件的装配和管理。
所谓的"控制反转"就是对组件对象控制权的转移，从程序代码本身转移到了外部容器，由容器来创建对象并管理对象之间的依赖关系。
IoC体现了好莱坞原则 - "Don’t call me, we will call you"。
依赖注入的基本原则是应用组件不应该负责查找资源或者其他依赖的协作对象。
配置对象的工作应该由容器负责，查找资源的逻辑应该从应用组件的代码中抽取出来，交给容器来完成。
DI是对IoC更准确的描述，即组件之间的依赖关系由容器在运行期决定，形象的来说，即由容器动态的将某种依赖关系注入到组件之中。

举个例子：一个类A需要用到接口B中的方法，那么就需要为类A和接口B建立关联或依赖关系，
最原始的方法是在类A中创建一个接口B的实现类C的实例，但这种方法需要开发人员自行维护二者的依赖关系，
也就是说当依赖关系发生变动的时候需要修改代码并重新构建整个系统。如果通过一个容器来管理这些对象以及对象的依赖关系，
则只需要在类A中定义好用于关联接口B的方法（构造器或setter方法），将类A和接口B的实现类C放入容器中，通过对容器的配置来实现二者的关联。

DI可以通过setter方法、构造器、接口三种方式来实现注入，Spring支持setter和构造器注入，
通常使用构造器注入来处理必须的依赖关系，使用setter注入来处理可选的依赖关系，
setter注入需要类提供无参构造器或者无参的静态工厂方法来创建对象。


#### Spring中Bean的作用域有哪些？
答：早期版本的Spring，两个作用域：singleton单例和prototype原型，
单例表示Bean以单例的方式存在；原型表示每次从容器中调用Bean时，都会返回一个新的实例。
无状态Bean使用单例，有状态Bean使用原型。

补充：设计模式中的创建型模式中也有一个原型模式，原型模式也是一个常用的模式，
例如室内设计软件，所有素材都在工具箱中，从工具箱中取出的都是素材对象的一个原型，可以通过对象克隆来实现原型模式。

Spring 2.x中针对WebApplicationContext新增了3个作用域，分别是：
- request（每次HTTP请求都会创建一个新的Bean）；
- session（同一个HttpSession共享同一个Bean，不同的HttpSession使用不同的Bean）；
- globalSession（同一个全局Session共享一个Bean）。
此三种作用域，需要在容器级增加配置（web.xml）：
  <listener>
    <listener-class>>org.springframework.web.context.request.RequestContextListener</listener-class>
  </listener>
基于LocalThread将HTTP Request 对象绑定到为该请求提供服务的线程上，使得request 和 session作用域的bean能够在调用链中被访问到；
  <bean id="loginAction" class="com.founder.loginAction" scope="request"/>
针对每次HTTP请求，Spring容器根据loginAction定义创建一个新的loginAction实例，且该实例仅在当前HTTP Request内有效，因此可以放心
根据需要修改实例的内部状态，当处理请求结束，Request作用域的bean实例将被销毁。

Web相关作用域的Bean，注入singleton或prototype的Bean中，需要
  <aop:scoped-proxy/>

说明：单例模式和原型模式都是重要的设计模式。一般情况下，无状态或状态不可变的类适合使用单例模式。
在传统开发中，由于DAO持有Connection这个非线程安全对象因而没有使用单例模式；
但在Spring环境下，所有DAO类对可以采用单例模式，因为Spring利用AOP和Java API中的ThreadLocal对非线程安全的对象进行了特殊处理。

ThreadLocal为解决多线程程序的并发问题提供了一种新的思路。ThreadLocal，顾名思义是线程的一个本地化对象，
当工作于多线程中的对象使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程分配一个独立的变量副本，
所以每一个线程都可以独立的改变自己的副本，而不影响其他线程所对应的副本。从线程的角度看，这个变量就像是线程的本地变量。

ThreadLocal类非常简单好用，只有四个方法，能用上的也就是下面三个方法：
- void set(T value)：设置当前线程的线程局部变量的值。
- T get()：获得当前线程所对应的线程局部变量的值。
- void remove()：删除当前线程中线程局部变量的值。

ThreadLocal是如何做到为每一个线程维护一份独立的变量副本的呢？
在ThreadLocal类中有一个Map，键为线程对象，值是其线程对应的变量的副本，


#### 解释一下什么叫AOP（面向切面编程）？
AOP（Aspect Oriented Programming）指一种程序设计范型，该范型以一种称为切面（aspect）的语言构造为基础，
切面是一种新的模块化机制，用来描述分散在对象、类或方法中的横切关注点（crosscutting concern）。
##### AOP 概念
- Aspect：
- JoinPoint：
- Advice：
- PointCut：
- Introduction：
- TargetObject：包含JoinPoint 对象，
- AOP Proxy：AOP 框架创建的对象，包含Advice。Spring 中为JDK动态代理或CGLIB代理；
- Weaving：组装Aspect 创建被通知对象，编译或运行期完成，Spring 在运行期；

##### 通知类型
- Around 通知：包围JoinPoint ，在方法调用前后执行；
- Before 通知：在JoinPoint 之前执行；
- Throws 通知：在方法抛出异常时执行；
- After returning 通知：在JoinPoint 正常完成后执行；


#### Spring中自动装配的方式有哪些？
- no：不进行自动装配，手动设置Bean的依赖关系。
- byName：根据Bean的名字进行自动装配。
- byType：根据Bean的类型进行自动装配。
- constructor：类似于byType，不过是应用于构造器的参数，如果正好有一个Bean与构造器的参数类型相同则可以自动装配，否则会导致错误。
- autodetect：如果有默认的构造器，则通过constructor的方式进行自动装配，否则使用byType的方式进行自动装配。

说明：自动装配没有自定义装配方式那么精确，而且不能自动装配简单属性（基本类型、字符串等），在使用时应注意。


#### Spring中的自动装配有哪些限制？
- 如果使用了构造器注入或者setter注入，那么将覆盖自动装配的依赖关系。
- 基本数据类型的值、字符串字面量、类字面量无法使用自动装配来注入。
- 优先考虑使用显式的装配来进行更精确的依赖注入而不是使用自动装配。


#### Spring中如何使用注解来配置Bean？有哪些相关的注解？
答：首先需要在Spring配置文件中增加如下配置：

    <context:component-scan base-package="org.example"/>

然后可以用@Component、@Controller、@Service、@Repository注解来标注需要由Spring IoC容器进行对象托管的类。
这几个注解没有本质区别，通常情况，@Controller用于控制器，@Service用于业务逻辑，@Repository用于DAO的实现类，
普通的类用@Component来标注。


#### 阐述Spring框架中Bean的生命周期？
- Spring IoC容器找到关于Bean的定义并实例化该Bean。
- Spring IoC容器对Bean进行依赖注入。
- 如果Bean实现了BeanNameAware接口，则将该Bean的id传给setBeanName方法。
- 如果Bean实现了BeanFactoryAware接口，则将BeanFactory对象传给setBeanFactory方法。
- 如果Bean实现了BeanPostProcessor接口，则调用其postProcessBeforeInitialization方法。
- 如果Bean实现了InitializingBean接口，则调用其afterPropertySet方法。
- 如果有和Bean关联的BeanPostProcessors对象，则这些对象的postProcessAfterInitialization方法被调用。
- 当销毁Bean实例时，如果Bean实现了DisposableBean接口，则调用其destroy方法。


#### 依赖注入时如何注入集合属性？
可以在定义Bean属性时，通过<list> / <set> / <map> / <props>分别为其注入列表、集合、映射和键值都是字符串的映射属性。


#### Spring IoC容器配置Bean的方式？
- 基于XML文件进行配置。
- 基于注解进行配置。
- 基于Java程序进行配置（Spring 3+）


#### 在Web项目中如何获得Spring的IoC容器？

    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);


#### 如何在Web项目中配置Spring MVC？
要使用Spring MVC需要在Web项目配置文件中配置其前端控制器DispatcherServlet，如下所示：

    <web-app>
        <servlet>
            <servlet-name>example</servlet-name>
            <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
            </servlet-class>
            <load-on-startup>1</load-on-startup>
        </servlet>

        <servlet-mapping>
            <servlet-name>example</servlet-name>
            <url-pattern>\*.html</url-pattern>
        </servlet-mapping>
    </web-app>



#### Spring MVC的工作原理是怎样的？
- 客户端的所有请求都交给前端控制器DispatcherServlet来处理，它会负责调用系统的其他模块来真正处理用户的请求。
- DispatcherServlet收到请求后，将根据请求的信息（包括URL、HTTP协议方法、请求头、请求参数、Cookie等）
  以及HandlerMapping的配置找到处理该请求的Handler（任何一个对象都可以作为请求的Handler）。
- 在这个地方Spring会通过HandlerAdapter对该处理器进行封装。
- HandlerAdapter是一个适配器，它用统一的接口对各种Handler中的方法进行调用。
- Handler完成对用户请求的处理后，会返回一个ModelAndView对象给DispatcherServlet，包含了数据模型以及相应的视图的信息。
- ModelAndView的视图是逻辑视图，DispatcherServlet还要借助ViewResolver完成从逻辑视图到真实视图对象的解析工作。
- 当得到真正的视图对象后，DispatcherServlet会利用视图对象对模型数据进行渲染。
- 客户端得到响应，可能是一个普通的HTML页面，也可以是XML或JSON字符串，还可以是一张图片或者一个PDF文件。
