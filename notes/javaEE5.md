# 一、SpringData

​	对于数据访问层，无论是 SQL([关系型数据库](https://so.csdn.net/so/search?q=关系型数据库&spm=1001.2101.3001.7020)) 还是 NOSQL(非关系型数据库)，Spring Boot 底层都是采用 Spring Data 的方式进行统一处理。

​	Spring Boot 底层都是采用 Spring Data 的方式进行统一处理各种数据库，Spring Data 也是 Spring 中与 Spring Boot、Spring Cloud 等齐名的知名项目。

​	Sping Data 官网：https://spring.io/projects/spring-data



# 二、Spring JPA

#### 1、JPA简介

一、什么是JPA

 JPA是一套Java官方制定的ORM 方案，是Java的一个规范 。

#### 2、JPA优点

**简单易用**，帮助开发者提供了生产率
**便于维护**，减低了维护成本
学习成本相对比较低。
jPA存在的缺点：

将语言与数据库混在一起，导致数据改动以后，配置文件必须更新

对与多数据与大数据量处理很容易产生性能问题。

过度封装，导致错误查找相对与JDBC等传统开发技术而言更加困难

#### 3、JPA和JDBC的对比

相同处：
1.都跟数据库操作有关，JPA 是JDBC 的升华，升级版。
2.JDBC和JPA都是一组规范接口
3.都是由SUN官方推出的
不同处：
1.JDBC是由各个关系型数据库实现的， JPA 是由ORM框架实现
2.JDBC 使用SQL语句和数据库通信。 JPA用面向对象方式， 通过ORM框架来生成SQL，进行操作。
3.JPA在JDBC之上的， JPA也要依赖JDBC才能操作数据库。

#### 4、JPA注解

![627c56869c0c4412afe6373c57a9864a](C:\Users\ZAN\Desktop\627c56869c0c4412afe6373c57a9864a.png)



# 三、整合JDBC的相关步骤

#### 1、创建测试项目测试数据源

​	这一部分也就是新建一个springboot项目，引入相应的模块，springboot-data-jdbc

#### 2、相关配置

	<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-jdbc</artifactId>
`</dependency>`
`<dependency>`
    `<groupId>mysql</groupId>`
    `<artifactId>mysql-connector-java</artifactId>`
    `<scope>runtime</scope>`
`</dependency>`

#### 3、编写yaml配置文件连接数据库

spring:

​	datasource:

​		username: zan

​		password:5201314

​		url: jdbc:mysql://localhost:3306/springboot?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8

​		driver-class-name: com.mysql.cj.jdbc.Driver

#### 4、进行项目测试

# 四、作业时遇到的问题

##### 1、启动项目时，报错:Cannot determine embedded database driver class for database type NONE

原因：没有再application.properties配置文件中添加数据库相关配置

解决：加入相关配置（如username）

##### 2、在配置spring data web时出现unknown报错

原因：没有添加版本号

解决：添加版本号（如2.3.3RELEASE）