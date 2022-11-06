# 一、MyBatisPlus相关知识

##### 1、说明

​	MyBatisPlus是基于MyBatis框架基础上开发的增强型工具，旨在简化开发、提高效率。

​	MP的特性:

- 无侵入：只做增强不做改变，不会对现有工程产生影响

- 强大的 CRUD 操作：内置通用 Mapper，少量配置即可实现单表CRUD 操作

- 支持 Lambda：编写查询条件无需担心字段写错

- 支持主键自动生成

- 内置分页插件

- ……

  

##### 2、原理

​	扫描实体类Entity，通过反射机制分析出需要操作的表名、字段名（实体类类名 >> 表名，实体类属性名 >> 字段名），生成相应的CRUD及其他常用的sql语句，注入到mybatis容器中。

# 二、通用接口

##### 1、 BaseMapper接口

MyBatis-Plus中的基本CRUD在内置的BaseMapper中都已得到了实现，我们可以直接使用，接口如下：

public interface BaseMapper<T> extends Mapper<T> {
	// 插入一条记录
	int insert(T entity);
	// 根据主键删除
	int deleteById(Serializable id);
	// 根据自定义条件删除 
	int deleteByMap(@Param("cm") Map<String, Object> columnMap);
需要使用时只需要继承该接口，即可获得BaseMapper所有封装好的CRUD等方法。注意BaseMapper<T> 泛型中必须指定实体类。

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

##### 2、IService接口

MyBatis-Plus中有一个接口 IService和其实现类 ServiceImpl，封装了常见的业务层逻辑。封装方法介绍

通用 IService接口，进一步封装 CRUD 采用 get 查询单行、remove 删除、list 查询集合、page 分页，前缀命名方式区分 Mapper 层避免混淆。

使用时流程如下：注意实现类继承的ServiceImpl<M extends BaseMapper<T>，T>，第一个为继承的mapper接口，第二个需指定为实体类。

// UserService继承IService模板提供的基础功能
public interface UserService extends IService<User> {
}
// 实现类
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}

# 三、常见注解

@TableName
@TableName可以用来设置当前实体类映射的表名。详细介绍
当数据库所有表有统一的前缀 t_ 时，可以通过在配置文件中设置全局的表名前缀进行处理。

mybatis-plus:
  configuration:



# 四、条件构造器

Wrapper：条件构造抽象类，最顶端父类。
AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件。
QueryWrapper ： Entity 对象封装操作类，不是用lambda语法。
UpdateWrapper ： Update 条件封装，用于Entity对象更新操作。
AbstractLambdaWrapper ： Lambda 语法使用 Wrapper统一处理解析 lambda 获取 column。
LambdaQueryWrapper ：看名称也能明白就是用于Lambda语法使用的查询Wrapper。
LambdaUpdateWrapper ： Lambda 更新封装Wrapper。



##### ——案例分析

查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序。

  @Autowired
  private UserMapper userMapper;
  @Test
  void test02(){
  	  // 查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序。
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
      queryWrapper.orderByDesc("age")
              .orderByAsc("id");
      for (User user : userMapper.selectList(queryWrapper)) {
          System.out.println(user);
      }
  }
删除邮箱为空的用户信息。

  @Autowired
  private UserMapper userMapper;
   @Test
   void test03(){
   		// 删除邮箱为空的用户信息。
       QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       queryWrapper.isNotNull("email");
       int delete = userMapper.delete(queryWrapper);
       System.out.println(delete);
   }

将（年龄大于20并且名字中包含“小”字的）或者邮箱为null的用户信息修改。

  @Autowired
  private UserMapper userMapper;
  @Test
  void test04(){
      // 将年龄大于20并且名字中包含“小”字的或者邮箱为null的用户信息修改。
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
      queryWrapper.gt("age",20)
              .like("u_name","小")
              // 默认and连接，这里需要改为or 
              .or()
              .isNotNull("email");
      User user = new User();
      user.setName("李白");
      user.setEmail("mybatis-plus@qq.com");
      // 第一个参数用来标明需要修改的内容，第二个参数用来标明参与修改的条件。
      int update = userMapper.update(user, queryWrapper);
      System.out.println(update);
  }



将（年龄大于20或者邮箱为空）并且名字中含有“小”的用户信息修改。注意优先级问题，可以上一题类比。

  @Autowired
  private UserMapper userMapper;
   @Test
   void test05(){
   		// 将（年龄大于20或者邮箱为空）并且名字中含有“小”的用户信息修改。
       QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       queryWrapper.like("u_name","小")
       			// lambda中的条件会优先执行。
               .and(i->i.gt("age",20).or().isNotNull("email"));
       User user = new User();
       user.setName("李白");
       user.setEmail("mybatis-plus@qq.com");
       int update = userMapper.update(user, queryWrapper);
       System.out.println(update);
   }

5. UpdateWrapper
   将（年龄大于20或者邮箱为空）并且名字中含有“小”的用户信息修改，注意与queryWrapper类比。

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
       */
        @Bean
        public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
        }

}

# 五、代码生成器

结合创建数据库的表，自动生成代码

添加依赖包，配置一个配置类后，点击运行就会生成代码文件