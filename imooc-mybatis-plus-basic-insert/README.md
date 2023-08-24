# 使用说明
pojo、mapper类、mapper测试类对应关系

| pojo     | mapper类        | mapper测试类          |
|----------|----------------|--------------------|
| SysUser  | SysUserMapper  | SysUserMapperTest  |
| SysUser2 | SysUserMapper2 | SysUserMapper2Test |
| SysUser3 | SysUserMapper3 | SysUserMapper3Test |
| SysUser4 | SysUserMapper4 | SysUserMapper4Test |
| SysUser5 | SysUserMapper5 | SysUserMapper5Test |

## SysUserMapperTest
感受`int insert(T entity);`插入方法的使用：正常返回结果为1；插入失败抛异常
## SysUserMapper2Test
1. *表名与pojo类名不符合驼峰标识转换*时，你不在pojo类上使用`@TableName("system_user")`注解，我怎么知道`往哪个表`增删改查？
## SysUserMapper3Test
1. *表主键与pojo类属性不符合驼峰标识转换*时，你不在pojo类属性上使用`@TableId("id")`注解，我怎么知道增删改查的字段`表里是不是有`？
2. id为null时，也会insert此字段，普通字段为null时，不会insert此字段
## SysUserMapper4Test
1. *表字段与pojo类属性不符合驼峰标识转换*时，你不在pojo类属性上使用`@TableField("name")`注解，我怎么知道增删改查的字段`表里是不是有`？
## SysUserMapper5Test
1. pojo类多出来的属性，自然会出现*表字段与pojo类属性不符合驼峰标识转换*，你不在pojo类属性上使用`@TableField(exist = false)`注解，我怎么知道`不用`对这个字段增删改查？

# 结论
1. pojo类名与表名、pojo属性与表字段，`严格对应`的重要性：
    * 代码层执行相应的sql，肯定要知道正确的表名和字段
    * 不按驼峰标识，我根据pojo类名怎么知道表名
    * 不按驼峰标识，我根据pojo属性名怎么知道你要对哪个字段增删改查
2. 只要我们遵循分层规范，驼峰标识转换的问题应该不会有
3. @TableId与@TableField都有字段映射的共性，
   * 但主键字段有自动填充的特性，插入时`你不写，我可以帮你`雪花算法生成；
   * 普通字段有复杂使用的场景，所以功能多（@TableField属性多），而你只能做主键，自然@TableId属性少
4. `int insert(T entity);`
   * 入参方面：pojo对象属性为null，我自然不往这个字段上插入数据，但主键有可能为null我也要插入，因为我会雪花算法啊
   * 返回结果方面：正常返回1，否则抛异常
# 问题
1. 在`rateldata`项目中，不允许不同package下，两个相同类名映射同一个表，报错为别名不能相同，有机会可以进一步测试，进了解原理