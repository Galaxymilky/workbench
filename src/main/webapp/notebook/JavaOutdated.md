## web service 相关
#### 什么是Web Service？
答：从表面上看，Web Service就是一个应用程序，它向外界暴露出一个能够通过Web进行调用的API。这就是说，你能够用编程的方法透明的调用这个应用程序，不需要了解它的任何细节，跟你使用的编程语言也没有关系。例如可以创建一个提供天气预报的Web Service，那么无论你用哪种编程语言开发的应用都可以通过调用它的API并传入城市信息来获得该城市的天气预报。之所以称之为Web Service，是因为它基于HTTP协议传输数据，这使得运行在不同机器上的不同应用无须借助附加的、专门的第三方软件或硬件，就可相互交换数据或集成。


#### REST和SOAP比较
 REST（Representational State Transfer），REST采用简单的URL的方式来代表一个对象，例如一个URL就对应一个对象。

REST的优点：
- 轻量级的解决方案，不必向SOAP那样要构建一个标准的SOAP XML。
- 可读性比较好:可以把URL的名字取得有实际意义。
- 不需要SDK支持：直接一个Http请求就可以，但是SOAP则可能需要使用到一些Webservice的类库（例如Apache的Axis）。
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
