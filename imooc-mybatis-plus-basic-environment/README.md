# 使用说明
1. 最简单的一个springboot集成mybatis-plus的项目
2. 后续项目可将此原始项目复制过去，以测试单一新功能
# 亮点
1. spring.sql.init相关配置实现spring项目启动时，初始化相关H2脚本，保证每次启动时都是`无污染`的初始环境
2. 单元测试方法上的`@Rollback`和单元测试类上的`@Transactional`，实现每个方法执行完后都会回滚，进一步保证`无污染`的初始环境
3. 父级根目录下的lombok.config实现new完对象后set的链式调用
   * 其实也只是使用了其中的lombok.accessors.chain=true，其他配置有待测试
# 注意
1. 高版本springboot中测试类不再有 @RunWith(SpringRunner.class)写法，因为`RunWith`这个类都没有了
2. mybatis-spring-boot-starter是无法通过spring-boot-starter-parent实现`版本管理`的
   * 因为mybatis-spring-boot-starter的作用是自动配置`SqlSessionFactory`和`SqlSessionTemplate`,这是mybatis公司做的`额外简化功能`
3. mybatis-plus-boot-starter也是无法通过spring-boot-starter-parent实现`版本管理`的
   * mybatis-spring-boot-starter也实现了自动配置`SqlSessionFactory`和`SqlSessionTemplate`
   * mybatis-spring-boot-starter有版本号你没想法，因为它是`第三方机构`，其实mybatis-spring-boot-starter相当于`第三方软件`
# 问题
mybatis-plus-boot-starter和mybatis-spring-boot-starter都实现了自动配置`SqlSessionFactory`和`SqlSessionTemplate`，
两部分的`SqlSessionFactory`和`SqlSessionTemplate`都会生效吗？（那就要去学习生效的原理了，参考达内入门课程学习原理）
都生效的话，会有什么影响？（毕竟重复总是没有必要的）

我记得曾经调试过，在项目启动时就会将XML中、BaseMapper中、BaseMapper继承类中所实现的dao层方法纳入管理。
1. 为明确此信息有必要学习下mybatis原理
2. 如何验证？在哪打断点？刘增辉的书好像有涉及
3. 是不是有这么一个猜想呢：SqlSessionFactory等作为一个spring容器中的一个bean,它为什么能实现不同的查询呢？不就是在项目启动时将这些实现纳入管理了嘛

@Mapper和@MapperScanner二者取其一即可，原理是什么呢？