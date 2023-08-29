# 使用说明
1. 先看WrapperTest.java，通过对比了解wrapper的生成方法
2. 接着在CommonTest.java中感受：其实BaseMapper没几个select的方法
3. SelectByMapTest.java中记录了几个不常用的select方法
4. @TableField(condition = SqlCondition.LIKE)与LambdaQueryWrapper<T> lambdaQuery(T entity)可以实现本该是相等条件，改为like条件
5. 条件构造器虽然多，但大多数都是见名知义的。常用但不直接的用法、特殊场景才用到的用法在MethodTest.java中都有例子
6. SysUser2.java是为了验证@TableField(condition = SqlCondition.LIKE)与LambdaQueryWrapper<T> lambdaQuery(T entity)配合的功能

# 结论
1. 图解wrapper的生成方法
![mybatis-plus queryWrapper选择.png](mybatis-plus%20queryWrapper选择.png)
2. mybatis-plus官网有完整的条件构造器示例

