# 一、安装JDK和IDEA的学习

## 1、IDEA的学习

​	IDEA在业界被公认为最好的Java开发工具之一，在我看来，它在工具栏分类、整合能力和代码分析等方面的功能都值得称赞。但是本人没用过Eclipse，所以无法与其相比，但是也用过Sublime编辑器，个人感觉而言，IDEA的可选性还是更高一些，当然，选择合适自己并喜欢的工具就行~

​	安装完JDK后，就可以在IDEA新建项目中选择JDK了（我安装的是18），然后在项目结构图中，src目录为默认的source root，可以在这个目录下创建包和类；在外部库（我的IDEA已汉化）中，显示的是导入的JDK18版本。接着在src栏点右键新建一个包，再new一个java文件，就可以写helloWorld代码了。

​	IDEA中还有很多快捷键，比如很多软件都有的ctrl+z是撤销，然后还有ctrl+N是根据输入的类名查找类文件等等。

## 2、问题及解决方法

​	我之前安装过IDEA不过是2020版本的，用的是1.8版本的JDK，但是老师PPT上要求的是Java8以上，于是我去官网下载了最新的java18版本，但是在2020版本的IDEA上运行的时候报错了，查阅相关资料发现，2020版本的IDEA最高只能兼容java14。于是我将IDEA升级到了2022版，这才成功使用了JDK的18版本。

# 二、java的学习

### 1、注意类变量（static）、实例变量、局部变量的区分

（访问修饰符不能修饰局部变量）

### 2、熟悉java运算符、循环结构、条件语句（其中有嵌套方法）、数组（特别是多维数组的表示形式）相关知识

### 3、问题及解决方法

### --案例①

```
public class Demo{
    public void people(){
        int age ;//此处报错
        age = age + 7;
        System.out.println("小狗的年龄是: " + age);
    }

    public static void main(String[] args){
        Demo demo1 = new Demo();
        demo1.people();
    }
}
```

​	编译会出错，原因是在类的方法中，定义局部变量age的时候并未对其初始化，导致在接下来new对象的时候age值出错。

​	应在第三行的int age处补充age的初始值。

### --案例2

```
public class Case {
    public static void main(String[] args){
        char grade = c;//此处发生错误，后面已经改成正确的形式
        switch (grade){
            case 'a':
                System.out.println("高级");
                break;
            case 'b':
                System.out.println("中级");
                break;
            case 'c':
                System.out.println("低级");
                break;
            default:System.out.println("出错");
        }
    }
```

​	这次运行的时候报错了，第三行char grade的值赋错了，当是字符的时候应该加上单引号‘’，这里没有加上去，所以导致报错。

​	改成‘c’后就没有任何问题了。

​	这里需要注意每一个case后面都要接break中断，而break在很多循环分支里面都会用到。

# 三、Git的安装和使用

## 1、Git的学习

​	由于之前已经安装过Git了，直接进入学习阶段。

​	首先找到一个合适的位置，创建空目录，这样就建好了Git的仓库，之后会在这个目录下出现一个.git的目录，是Git用来跟踪管理版本的，不需要动他。（我的仓库目录名是learnlight）

​	

