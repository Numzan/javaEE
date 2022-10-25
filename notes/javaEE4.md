# SpringBoot中搭建RESTful API架构

## 一、为什么REST？

​	其宗旨是从资源的角度来观察整个网络，分布在各处的资源由 URI 确定，而客户端的应用通过 URI 来获取资源的表征。获得这些表征致使这些应用程序转变了其状态。随着不断获取资源的表征，客户端应用不断地在转变着其状态。

## 二、RESTful API 

​	Restful本质上是一个优雅的 URI 表达方式，基于 Restful 设计的软件可以更简洁，更有层次，更易于实现缓存等机制。

​	其不但简洁快速。 由于其面向资源接口设计以及操作抽象简化了开发者的不良设计，同时也最大限度的利用了 http 最初的应用协议设计理念。RESTful 架构遵循统一接口原则，不论什么样的资源，都是通过使用相同的接口进行资源的访问。接口应该使用标准的 HTTP 方法如 GET ，PUT 和 POST ，并遵循这些方法的语义。

## 三、设计规范

##### 1、HTTP请求动词（常用的以下五个）

| 动词   |                      说明                      |
| ------ | :--------------------------------------------: |
| GET    |         从服务器取出资源（一项或多项）         |
| POST   |              在服务器新建一个资源              |
| PUT    | 在服务器更新资源（客户端提供改变后的完整资源） |
| PATCH  |    在服务器更新资源（客户端提供改变的属性）    |
| DELETE |                从服务器删除资源                |

##### 2、HTTP响应状态码

| 响应状态码 | 说明                                 | 适用的HTTP请求动词 |
| ---------- | ------------------------------------ | ------------------ |
| 200        | OK-服务器成功返回用户请求的数据      | GET                |
| 201        | CREATED-用户新建或修改数据成功       | POST/PUT           |
| 400        | INVALID REQUEST-用户发出的请求有错误 | POST/PUT           |
| 401        | 用户没有权限                         | 所有请求           |
| 403        | 访问被禁止                           | 所有请求           |
| 404        | 服务器没有操作                       | 所有请求           |
| 500        | 服务器发生错误                       | 所有请求           |

## 四、Spring Boot实现RESTful API

（可以通过注解来实现）

| 应用场景 | 注解           | 说明                    |
| -------- | -------------- | ----------------------- |
| 处理请求 | Controller     | 处理HTTP请求            |
| 处理请求 | RestController | 衍生注解                |
| 路由请求 | RequestMapping | 设置请求方法            |
| 路由请求 | GetMapping     | GET                     |
| 路由请求 | PostMapping    | POST                    |
| 路由请求 | PutMapping     | PUT                     |
| 处理请求 | DeleteMapping  | DELETE                  |
| 处理请求 | PathVariable   | 处理请求url路径中的参数 |
| 处理请求 | RequestParam   | 处理问号后面的参数      |
| 处理请求 | RequestBody    | 请求参数以json格式提交  |
| 返回参数 | ResponseBody   | 响应数据以json格式返回  |

## 五、实践中遇到的问题及解决办法

——升级spring boot之后，项目启动报错，项目使用swagger 3.0 ,具体版本是knife4j-spring-boot-starter的3.0.3，查找解决方案，都说修改配置文件（但是修改配置后仍然无效）或者新增配置类，过滤空情况，但是仍然还是解决了很长时间。报错信息：Caused by: java.lang.NullPointerException: Cannot invoke “org.springframework.web.servlet.mvc.condition.PatternsRequestCondition.getPatterns()” because “this.condition” is null

——解决方案:
		新增一个配置类，注意配置类不能放到swagger的配置类下，测试无效。配置Bean可以放在启动类下或者重新新增一个配置Bean，代码如下:

```
package edu.whu.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class Import {

    @Bean
    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }

}
```

