### AOP原理和拦截器

#### 原理
AOP 涉及安全、日志、事务管理、邮件等。无须预编译。

- Aspect：比如 J2EE 中的事务管理，通过 Spring 的 Advisor 或 拦截器实现；
- Join Point：程序执行过程中明确的点，如方法调用或异常抛出；
- Advice：在特定 Join Point ，AOP 框架执行的动作，各种类型的通知包；
- Pointcut：
- Introduction：
- Target Object：
- AOP Proxy：
- Weaving：组装 Aspect 创建一个被通知的对象，可选择编译时 或 运行时完成。Spring 在运行时；
