# 一、Filter过滤器

Filter 过滤器它是 JavaWeb 的三大组件之一。
三大组件分别是：Servlet 程序、Listener 监听器、Filter 过滤器
Filter 过滤器它是 JavaEE 的规范。也就是接口
Filter 过滤器它的作用是：拦截请求，过滤响应。
拦截请求常见的应用场景有：
1.权限检查 2.日记操作 3.事务管理 ……等等

# 二、Spring Security

##### 1、关于安全的框架有：shiro、Spring Security，完成的工作是用户认证（Authentication）和用户授权（Authorization）。

认证、授权（vip1,vip2,vip3），原先权限控制的有：

功能权限
访问权限
菜单权限
原来的过滤器、拦截器，大量的原生代码。冗余性

##### 2、描述

Spring Security 是一个功能强大且高度可定制的身份验证和访问控制框架。 它是保护基于 Spring 的应用程序的事实标准。
Spring Security 是一个专注于为 Java 应用程序提供身份验证和授权的框架。 像所有 Spring 项目一样，Spring Security 的真正强大之处在于它可以轻松扩展以满足自定义需求

##### 3、JWT

- JWT内容是由三部分组成：HEADER、PAYLOAD、VERIFY SIGNATURE。

  ```
  每个信息用"."号分隔开，是base64编码的。
  
  HEADER：token类型和加密算法，此处的加密算法是签名的加密算法
  
  PAYLOAD：具体的业务数据，比如用户名等
  
  VERIFY SIGNATURE：数字签名密文信息
  ```