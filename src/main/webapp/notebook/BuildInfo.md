## 问题列表
### MySQL 相关
#### 解决问题{mysql“Access denied for user 'root'@'IP地址'}
    grant all privileges on *.* to 'root'@'%' identified by 'root' with grant option;
    flush privileges;********
    
#### jdbc.url 缺少 serverTimezone=UTC
    java.sql.SQLException: The server time zone value '?��???????' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.


### BUILD 相关
#### 缺少 @Service 标签 和 Spring-service.xml
    严重: Context initialization failed
    org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'appUserController': Unsatisfied dependency expressed through field 'appUserService'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean found for dependency [com.ssmdemo.service.AppUserService]: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
    ***
    Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean found for dependency [com.ssmdemo.service.AppUserService]: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}

### Git 相关
#### 提交失败
21:21	Push failed
		Failed with error: The remote end hung up unexpectedly
		The remote end hung up unexpectedly
		RPC failed; curl 55 SSL_write() returned SYSCALL, errno = 10053
