SpringBoot报java.lang.NoSuchMethodError: org.springframework.util.CollectionUtils.lastElement(Ljava/util/Set;)Ljava/lang/Object;

原因： Spring中引入了重复的Jar包，导致调用冲突 
解决： 因为pom.xml中引入了spring-boot-starter-web ，同时pom.xml也引入了spring-core，spring-beans，这里去掉spring-core，spring-beans即可。 
