
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