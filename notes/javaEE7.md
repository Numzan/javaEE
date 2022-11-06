# 一、Spring AOP

##### 1、简介

AOP(Aspect Oriented Programming)面向切面编程，一种编程范式，指导开发者如何组织程序结构。

* OOP(Object Oriented Programming)面向对象编程
* 作用:在不惊动原始设计的基础上为其进行功能增强，前面咱们有技术就可以实现这样的功能即代理模式。

##### 2、工作流程

——Spring容器启动

——读取所有切面配置

——初始化bean

——获取bean执行方法

##### 3、相关属于的解释

切面（ Aspect ）：切面用于组织多个Advice（增强处理）, Advice（增强处理） 放在切面中定义。

连接点（Joinpoint）：程序执行过程中明确的点，如方法的调用，或者异常的抛出。在SpringAOP中，连接点默认是方法的调用。 

增强处理（ Advice）: AOP 框架在特定的切入点执行的增强处理。处理有"around"、"before"和" after"等类型。 

切入点（ Pointcut ）：可以插入增强处理的连接点。简而言之，当某个连接点满足指定要求时，该连接点将被添加增强处理，该连接点也就变成了切入点。例如如下代码： 


# 二、AOP的实现

##### 1、静态实现

AOP 框架在编译阶段对程序进行修改，即实现对目标类的增强，生成静态的AOP 代理类（生成的＊.class 文件己经被改掉了，需要使用特定的编译器〉。以 AspectJ 为代表。

##### 2、动态实现

AOP 框架在运行阶段动态生成AOP 代理，即在运行时生成目标类的代理类，在内存中以 JDK 动态代理（实现与目标类实现相同的接口） 或 cglib动态代理（成为目标类的子类） 生成AOP 代理类），以实现对目标对象的增强。以 SpringAOP 为代表。

# 三、过程总结

spring aop过程总结：

Advisor：

        PointCut
    
                ClassFilter
    
                MethodMatcher
    
        Advice--->Method拦截器

被代理类--->找到所有匹配的Advisor---->利用proxyFactory生成代理对象

代理对象执行某个方法时---->依赖当前调用的方法---->找到所匹配的advisor----->执行对应的advice逻辑

##### @相关代码

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">　　　　 <!--开启注释扫描-->
    <context:component-scan base-package="com.study.day05"></context:component-scan>
　　 <!--开启Aspect生成代理对象-->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

</beans>
```

##### 注解

——实现和目标类相同的接口，就伪装成了和目标类一样的类（实现了同一接口），也就逃过了类型检查，到java运行期的时候，利用多态的后期绑定（所以spring采用运行时），伪装类（代理类）就变成了接口的真正实现。

——生成子类调用，这样也能逃过JVM的强类型检查，当然在这些重写的方法中，不仅实现了目标类的功能，还在这些功能之前，实现了一些其他的（写日志，安全检查，事物等）。