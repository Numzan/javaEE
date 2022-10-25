package whu.zan.context;


/**
 * Mini IOC容器的接口类。可以有XML、注解等多种不同实现。
 */
public interface MiniAppContext{


    Object getBean(String name);

    <T> T getBean(String name, Class<T> requiredType);

    <T> T getBean(Class<T> requiredType);


}
