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

##### 【1】创建版本库

①首先找到一个合适的位置，创建空目录，这样就建好了Git的仓库，之后会在这个目录下出现一个.git的目录，是Git用来跟踪管理版本的，不需要动他。（我的仓库目录名是javaEE）

```
$ mkdir javaEE
$ cd javaEE
$ pwd
```

②通过git init把这个目录变成git可以管理的仓库

③把文件添加到版本库：分别用git add和git commit将文件添加和提交到仓库

##### 【2】远程仓库

①创建SSH Key

②登录GitHub添加SSH Key

③添加远程库（GitHub上）并关联本地仓库：

```
$ git remote add origin git@github.com:Numzan/javeEE.git
```

④推送到远程库上：git push -u origin master

ps：从远程库克隆可以帮助我们快速从GitHub上获取相关知识，并作出修改。（通过git clone）

## 2、问题及解决方法

##### 【1】git 执行git pull –rebase报错误如下：

**error: Cannot pull with rebase: You have unstaged changes.**
**error: Additionally, your index contains uncommitted changes.**

原因：如果有未提交的更改，是不能git pull的

解决：
先执行git stash
再执行git pull –rebase
最后再执行git stash pop

git stash #可用来暂存当前正在进行的工作
git stash pop #从Git栈中读取最近一次保存的内容

##### 【2】git执行push的时候报错如下：

**! [rejected] master -> master (fetch first)**

分析原因，基本上可以确定是因为github上的远程库与本地库版本不一致

①温柔型方案：
通过git pull 先将本地库更新到与远程库一致的版本，但要注意本地库后来做的修改可能被覆盖，最好使用git fetch(不会自动合并)，查看更新情况再有选择合并，或者先将本地库修改过的文件备份，git pull后再重新修改；
再运行git push即可成功。
②暴力型方案：
git提供了一种强制上传的方式：git push -f ，它会忽略版本不一致等问题，强制将本地库上传的远程库，但是一定要谨慎使用，因为-f会用本地库覆盖掉远程库，如果远程库上有重要更新，或者有其他同伴做的修改，也都会被覆盖，所以一定要在确定无严重后果的前提下使用此操作。





​	

