package whu.zan.context;

import whu.zan.dao.BeanDefinition;
import whu.zan.dao.Constructor;
import whu.zan.service.Exception1;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MiniApplicationContext的抽象类
 */
public abstract class MiniApp implements MiniAppContext {

    /**
     * 容器解析的BeanDefinition
     */
    final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 容器中创建的Bean。key是Bean的name
     */
    final Map<String, Object> beans = new HashMap<>();

    /**
     * 根据名称获取Bean
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name){
        return beans.get(name);
        //TODO prototype模式需要动态创建Bean。在有注入的情况下很复杂，需要使用动态代理或CGLIB。
    }

    /**
     * 根据名称和类型获取Bean
     * @param name
     * @param requiredType
     * @return
     * @param <T>
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        Object bean = getBean(name);
        if (requiredType.isInstance(bean)) {
            return (T) bean;
        }
        return null;
    }

    /**
     * 根据类型获取Bean
     * @param requiredType
     * @return
     * @param <T>
     */
    @Override
    public <T> T getBean(Class<T> requiredType) {
        for (Object bean : beans.values()) {
            if(requiredType.isInstance(bean)){
                return (T)bean;
            }
        }
        return null;
    }

    /**
     * 创建Bean、注入依赖并调用初始化方法
     * @throws whu.zan.service.Exception1
     */
    public void createAndInitBeans() throws Exception1 {
        for (BeanDefinition bd : beanDefinitionMap.values()) {
            createBean(bd);
        }

    }

    /**
     * 根据beanDefinition创建Bean
     * @param beanDefinition
     * @throws Exception1
     */
    private void createBean(BeanDefinition beanDefinition) throws Exception1 {
        Object bean = beans.get(beanDefinition.getBeanName());
        if (bean != null) {
            return;
        }
        //递归调用，先创建被依赖的Bean
        for (String dependency : beanDefinition.getDependencies()) {
            if (beans.containsKey(dependency)) {
                continue;
            }
            //TODO 有循环依赖情况
            createBean(beanDefinitionMap.get(dependency));
        }
        //使用工厂方法创建
        if (beanDefinition.getFactoryMethodName() != null) {
            createBeanWithFactory(beanDefinition);
            return;
        }
        //异常情况
        Class<?> clazz = beanDefinition.getBeanClass();
        if (clazz == null) {
            throw new Exception1("Bean creation error: the Class of bean '" + beanDefinition.getBeanName() + "' is null");
        }
        if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
            throw new Exception1("Bean creation error: the Class of bean '" + beanDefinition.getBeanName() + "' is not a concrete class");
        }

    }

    /**
     * 使用工厂创建Bean
     * @param beanDefinition
     * @throws Exception1
     */
    private void createBeanWithFactory(BeanDefinition beanDefinition) throws Exception1 {
        String factoryBeanName = beanDefinition.getFactoryBeanName();
        Object factoryBean= (factoryBeanName!=null)? beans.get(factoryBeanName):null;
        Class<?> factoryClass=null;
        if(factoryBean!=null) {
            factoryClass=factoryBean.getClass();
        }else if(beanDefinition.getBeanClass()!=null){
            factoryClass= beanDefinition.getBeanClass();
        }else{
            throw new Exception1(
                    "Bean creation error: both class and factory-bean are missing in bean '"+beanDefinition.getBeanName());
        }

        String factoryMethodName=beanDefinition.getFactoryMethodName();
        try {
            //TODO 有参数的方法
            Method method = factoryClass.getMethod(factoryMethodName);
            Object bean=method.invoke(factoryBean);
            if(bean!=null){
                beans.put(beanDefinition.getBeanName(),bean);
            }
        } catch (Exception e) {
            throw new Exception1(
                    "Bean creation error for bean '"+beanDefinition.getBeanName()+"': "+e.getMessage());
        }
    }


    /**
     * 调用初始化方法
     * @param bd
     * @throws Exception1
     */
    private void invokeInitMethods(BeanDefinition bd) throws Exception1 {
        String name = bd.getInitMethodName();
        if(name==null) {return;}
        try {
            Method method = bd.getBeanClass().getMethod("name");
            Object bean=beans.get(bd.getBeanName());
            method.invoke(bean);
        } catch (Exception e) {
            throw new Exception1("invoke init methods of bean '"+bd.getBeanName()+" error:"+e);
        }
    }

    /**
     * 属性注入
     * @param bd BeanDefinition
     * @throws Exception1
     */


    /**
     * 将value字符串解析为具体值
     * @param valueStr
     * @param type
     * @return
     */
    private static Object convertPrimaryValue(String valueStr, Class<?> type) {
        Object value = (type == int.class||type == Integer.class) ? Integer.parseInt(valueStr)
                : (type == long.class||type == Long.class) ? Long.parseLong(valueStr)
                : (type == float.class||type == Float.class) ? Float.parseFloat(valueStr)
                : (type == String.class) ? valueStr : null;
        return value;
    }

}
