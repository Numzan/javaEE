# 一、反射和注解

## 1、反射

##### 1.内容

当javac编译生成字节码文件后，类的加载器ClassLoader（体现反射）在堆内存的方法区得到Class对象，这个对象包含了完整的类的结构信息。

##### 2.疑难

//通过调用类的无参构造器创建对象（类名为User）

Class c1 = Class.forName("User");

c1.newInstance();➡

User user1 = (User)c1.newInstance();

//通过构造器创建对象

c1.getDeclaredConstructor(String.class,int.class,int.class);➡

Constructor constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);

User user2 = (User)constructor.newInstance("小张", 001, 18);

//通过反射获取一个方法

User user3 = (User)c1.newInstance();

c1.getDeclaredMethod("setName", String.class);➡

Method setName = c1.getDeclaredMethod("setName",String.class);

setName.invoke(user3, "小张");

//通过反射操作属性

User user4 = (User)c1.newInstance();

c1.getDeclaredField("name");➡

Field name = c1.getDeclaredField("name");

name.setAccessible(true);

name.set(user4, "小张");

①当调用Class对象的newInstance方法创建类的对象时，类必须有一个无参构造器，并且其访问权限足够

②invoke是激活的意思，使用方法为: 方法名.invoke(对象，"方法的值")

③不能直接操作私有属性，需要关闭程序的安全检测，属性或者方法的setAccessible（true）

## 2、注解

不是程序本身，可以对程序作出解释，但是可以被其他程序（比如编译器等）读取。

