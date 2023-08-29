# 使用说明
1. 基本用法、批量用法、链式用法

# 亮点
1. spring.sql.init相关配置实现spring项目启动时，初始化相关H2脚本，保证每次启动时都是`无污染`的初始环境
2. 单元测试方法上的`@Rollback`和单元测试类上的`@Transactional`，实现每个方法执行完后都会回滚，进一步保证`无污染`的初始环境
3. 父级根目录下的lombok.config实现new完对象后set的链式调用
   * 其实也只是使用了其中的lombok.accessors.chain=true，其他配置有待测试
# 注意

# 问题
