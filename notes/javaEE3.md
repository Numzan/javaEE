# 1、IOC、DI的相关要点

##### 1、IOC（控制反转）的定义

​		改变了以往由业务层需要通过new使用数据层对象的传统方法，简化成由外部提供对象。将类的对象存放于IOC容器中

##### 2、IOC容器的作用

​		负责对象的创建、初始化等，其中包括了数据层和业务层的类对象。将这些被管理的对象统称为Bean

##### 3、DI依赖注入

​		为了绑定Bean对象之间的关系，就要用到DI，业务层要用数据层的类对象，以前是自己new出来的，现在通过外部IOC容器来给注入进去，这种思想就是依赖注入

##### 4、在配置文件中添加依赖注入的配置

``<?xml version="1.0" encoding="UTF-8"?>`
`<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">`
    `<!--bean标签标示配置bean
    	id属性标示给bean起名字
    	class属性表示给bean定义类型
	-->`
    `<bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl"/>`

```xml
<bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
    <!--配置server与dao的关系-->
    <!--property标签表示配置当前bean的属性
    		name属性表示配置哪一个具体的属性
    		ref属性表示参照哪一个bean
	-->
    <property name="bookDao" ref="bookDao"/>
</bean>
```

`</beans>`

①name=“bookDao”中bookDao的作用是让Spring的IOC容器在获取到名称后，将首字母大写，前面加set找对应的setBookDao（）方法进行对象注入

②ref=“bookDao”中bookDao的作用是让Spring能在IOC容器中找到id为“bookDao的Bean对象给bookService进行注入

# 2、Bean的实例化

##### 1、构造方法实例化

在类中提供构造函数测试

`public class BookDaoImpl implements BookDao {`
    `public BookDaoImpl() {`
        `System.out.println("book dao constructor is running ....");`
    `}`
    `public void save() {`
        `System.out.println("book dao save ...");`
    `}`

`}`

##### 2、静态工厂实例化

在AppForInstanceOrder运行类，使用从IOC容器中获取bean的方法进行运行测试

`public class AppForInstanceOrder {`
    `public static void main(String[] args) {`
        `ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");`

        OrderDao orderDao = (OrderDao) ctx.getBean("orderDao");
    
        orderDao.save();
    
    }
`}`

##### 3、实例工厂实例化

创建一个工厂类OrderDaoFactory并提供一个普通方法，注意此处和静态工厂的工厂类不一样的地方是方法不是静态方法

`public class UserDaoFactory {`
    `public UserDao getUserDao(){`
        `return new UserDaoImpl();`
    `}`
`}`

编写AppForInstanceUser运行类，在类中通过工厂获取对象

`public class AppForInstanceUser {`
    `public static void main(String[] args) {`
        `//创建实例工厂对象`
        `UserDaoFactory userDaoFactory = new UserDaoFactory();`
        `//通过实例工厂对象创建对象`
        `UserDao userDao = userDaoFactory.getUserDao();`
        `userDao.save();`
`}`

# 3、Bean的生命周期

##### 1、初始化和注销方法

`public class BookDaoImpl implements BookDao {`
    `public void save() {`
        `System.out.println("book dao save ...");`
    `}`
    `//表示bean初始化对应的操作`
    `public void init(){`
        `System.out.println("init...");`
    `}`
    `//表示bean销毁前对应的操作`
    `public void destory(){`
        `System.out.println("destory...");`
    `}`
`}`

##### 2、配置生命周期

`<bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl" init-method="init" destroy-method="destory"/>`

# 疑问：无法显示destroy

#### 解决：close关闭容器

​		原因是Spring的IOC容器是运行在JVM中的，运行main方法后，JVM启动，Spring加载配置文件生成IOC容器，从容器获取bean对象，然后调方法执行。main方法执行完后，JVM退出，这个时候IOC容器中的bean还没有来得及销毁就已经结束了，所以没有调用destroy方法

​		需要将ApplicationContext更换成ClassPathXmlApplicationContext

`ClassPathXmlApplicationContext ctx = new` 
    `ClassPathXmlApplicationContext("applicationContext.xml");`

# 4、依赖注入

##### 1、setter注入

`public class BookDaoImpl implements BookDao {`

    private String databaseName;
    private int connectionNum;
    
    public void setConnectionNum(int connectionNum) {
        this.connectionNum = connectionNum;
    }
    
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    
    public void save() {
        System.out.println("book dao save ..."+databaseName+","+connectionNum);
    }
`}`

##### 2、构造器注入

`<?xml version="1.0" encoding="UTF-8"?>`
`<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">`

    <bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl"/>
    <bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
        <constructor-arg name="bookDao" ref="bookDao"/>
    </bean>
`</beans>`



​		



