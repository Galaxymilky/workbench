## 分布式集群

#### 什么是分布式？什么是集群？举例说明
- 集群：我的第一个初创公司，只有一个CEO和一个开发，后来又来了一个开发，这两个开发是集群的。
- 分布式：规模扩展到了20个开发，15个后台、4个前端、1个UI。后台、前端、UI这三块是分布式的。针对于开发应用来讲。

#### 什么是CAP理论？

在容忍网络分区的条件下，"强一致性"和"极致可用性"无法同时达到。

- C：数据一致性
- A：可用性
- P：分区容错性

CAP无法完全兼顾

#### 从多个层面聊聊你理解的微服务是什么？

- 开发：
- 维护：
- 生产：
- 架构：

## SpringCloud 相关

- [ ] Eureka 和 Zuul 有什么区别？都是管理服务调用的 
- [ ] 路由规则和服务实例的维护是如何做的；签名验证、登录冗余的问题
- [ ] Zuul 是如何解决上述的两个问题


#### 基础组成有哪些？

- 服务治理：Eureka

配置服务名对应的云主机实例如IP地址、端口等信息；

服务提供方：服务注册、服务检测、服务下线功能（失效踢出）

服务调用方：获取服务清单、服务调用

- 客户短负载均衡：Ribbon

从 Eureka 获取服务清单。发送请求前，通过负载均衡算法，从多个服务中选择一个进行访问。

默认策略是：轮询

- 服务端负载均衡：Nginx
- 服务容错保护：Hystrix

提供断路器、线程隔离、请求合并、请求缓存、仪表盘等功能

断路器，实现了请求失败快速返回的功能，
熔断关键参数：滑动窗口大小（20）、 熔断器开关间隔（5s）、错误率（50%）
每当20个请求中，有50%失败时，熔断器就会打开，此时再调用此服务，将会直接返回失败，不再调远程服务。
直到5s钟之后，重新检测该触发条件，判断是否把熔断器关闭，或者继续打开。

线程隔离，为每个依赖服务创建一个独立的线程池，降低了每个依赖服务异常情况下，互相的影响。

- 声明式服务调用：Feign

整合 Ribbon、Hystrix，并提供声明式服务调用。

调用远程方法和请求HTTP服务感觉就像调用本地服务一样；这个是微服务的核心，如果不支持声明式服务调用，开发效率会变低。

- API 网关服务：Zuul

动态路由、动态过滤器、默认过滤掉 HTTP 头信息和Cookies
Zuul 注册为 Eureka 下的应用，从 Eureka 中获取所有微服务清单，外界调用都需要经过 API 网关 Zuul，由它完成请求的鉴权、验证和拦截。


- 分布式配置中心：Config

- 消息总线：Bus
- 消息驱动：Stream
- 分布式服务追踪：Sleuth

## Spring 相关
#### 什么是IoC和DI？DI是如何实现的？
IoC（Inversion of Control）控制反转，DI（Dependency Injection）依赖注入，是对IoC更简单的诠释。控制反转是把传统上由程序代码直接操控的对象的调用权交给容器，通过容器来实现对象组件的装配和管理。所谓的"控制反转"就是对组件对象控制权的转移，从程序代码本身转移到了外部容器，由容器来创建对象并管理对象之间的依赖关系。
IoC体现了好莱坞原则 - "Don’t call me, we will call you"。依赖注入的基本原则是应用组件不应该负责查找资源或者其他依赖的协作对象。配置对象的工作应该由容器负责，查找资源的逻辑应该从应用组件的代码中抽取出来，交给容器来完成。DI是对IoC更准确的描述，即组件之间的依赖关系由容器在运行期决定，形象的来说，即由容器动态的将某种依赖关系注入到组件之中。

举个例子：一个类A需要用到接口B中的方法，那么就需要为类A和接口B建立关联或依赖关系，最原始的方法是在类A中创建一个接口B的实现类C的实例，但这种方法需要开发人员自行维护二者的依赖关系，也就是说当依赖关系发生变动的时候需要修改代码并重新构建整个系统。如果通过一个容器来管理这些对象以及对象的依赖关系，则只需要在类A中定义好用于关联接口B的方法（构造器或setter方法），将类A和接口B的实现类C放入容器中，通过对容器的配置来实现二者的关联。

依赖注入可以通过setter方法注入（设值注入）、构造器注入和接口注入三种方式来实现，Spring支持setter注入和构造器注入，通常使用构造器注入来注入必须的依赖关系，对于可选的依赖关系，则setter注入是更好的选择，setter注入需要类提供无参构造器或者无参的静态工厂方法来创建对象。


#### Spring中Bean的作用域有哪些？
在Spring的早期版本中，仅有两个作用域：singleton和prototype，前者表示Bean以单例的方式存在；后者表示每次从容器中调用Bean时，都会返回一个新的实例，prototype通常翻译为原型。

补充：设计模式中的创建型模式中也有一个原型模式，原型模式也是一个常用的模式，例如做一个室内设计软件，所有的素材都在工具箱中，而每次从工具箱中取出的都是素材对象的一个原型，可以通过对象克隆来实现原型模式。

Spring 2.x中针对WebApplicationContext新增了3个作用域，分别是：request（每次HTTP请求都会创建一个新的Bean）、session（同一个HttpSession共享同一个Bean，不同的HttpSession使用不同的Bean）和globalSession（同一个全局Session共享一个Bean）。

说明：单例模式和原型模式都是重要的设计模式。一般情况下，无状态或状态不可变的类适合使用单例模式。在传统开发中，由于DAO持有Connection这个非线程安全对象因而没有使用单例模式；但在Spring环境下，所有DAO类对可以采用单例模式，因为Spring利用AOP和Java API中的ThreadLocal对非线程安全的对象进行了特殊处理。

ThreadLocal为解决多线程程序的并发问题提供了一种新的思路。ThreadLocal，顾名思义是线程的一个本地化对象，当工作于多线程中的对象使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程分配一个独立的变量副本，所以每一个线程都可以独立的改变自己的副本，而不影响其他线程所对应的副本。从线程的角度看，这个变量就像是线程的本地变量。

ThreadLocal类非常简单好用，只有四个方法，能用上的也就是下面三个方法：
- void set(T value)：设置当前线程的线程局部变量的值。
- T get()：获得当前线程所对应的线程局部变量的值。
- void remove()：删除当前线程中线程局部变量的值。

ThreadLocal是如何做到为每一个线程维护一份独立的变量副本的呢？在ThreadLocal类中有一个Map，键为线程对象，值是其线程对应的变量的副本，


#### 解释一下什么叫AOP（面向切面编程）？
AOP（Aspect-Oriented Programming）指一种程序设计范型，该范型以一种称为切面（aspect）的语言构造为基础，切面是一种新的模块化机制，用来描述分散在对象、类或方法中的横切关注点（crosscutting concern）。

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
首先需要在Spring配置文件中增加如下配置：

    <context:component-scan base-package="org.example"/>

然后可以用@Component、@Controller、@Service、@Repository注解来标注需要由Spring IoC容器进行对象托管的类。这几个注解没有本质区别，只不过@Controller通常用于控制器，@Service通常用于业务逻辑类，@Repository通常用于仓储类（例如我们的DAO实现类），普通的类用@Component来标注。

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
- ispatcherServlet收到请求后，将根据请求的信息（包括URL、HTTP协议方法、请求头、请求参数、Cookie等）以及HandlerMapping的配置找到处理该请求的Handler（任何一个对象都可以作为请求的Handler）。
- 在这个地方Spring会通过HandlerAdapter对该处理器进行封装。
- andlerAdapter是一个适配器，它用统一的接口对各种Handler中的方法进行调用。
- andler完成对用户请求的处理后，会返回一个ModelAndView对象给DispatcherServlet，ModelAndView顾名思义，包含了数据模型以及相应的视图的信息。
- ModelAndView的视图是逻辑视图，DispatcherServlet还要借助ViewResolver完成从逻辑视图到真实视图对象的解析工作。
- 当得到真正的视图对象后，DispatcherServlet会利用视图对象对模型数据进行渲染。
- 客户端得到响应，可能是一个普通的HTML页面，也可以是XML或JSON字符串，还可以是一张图片或者一个PDF文件。

