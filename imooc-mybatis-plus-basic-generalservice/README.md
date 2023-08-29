# 使用说明
1. 基本用法参考SysUserServiceTest.java、批量用法参考SysUserServiceBatchTest.java、链式用法参考SysUserServiceChainTest.java
2. SysUser2.java是为了验证`removeBatchByIds`与`逻辑删除`的不同组合的表现，其中还有个参数涉及到是否`自动填充`

# 结论




1. 通用service的启用方法涉及的泛型看父类便知道了
2. 基本方法看看方法名、看源码、demo自测即可知道它的功能
3. 批量操作
   * 有一定性能提升
   * 批量保存、批量修改应该是见名知义的
   * saveOrUpdateBatch执行保存还是修改的依据是`id是否有值`和`id是否能查到数据`
   * removeBatchByIds()方法涉及到`逻辑删除`和`自动填充`功能的配合，具体效果见SysUserServiceBatchTest.java
4. 链式调用
   * 这里的链式调用确实符合链式的样式，但名称没用到chain
   * sysUserService.lambda即可看到idea提示的三个链式方法
   * delete方法用的是lambdaUpdate，有可能和逻辑删有关


# 注意

# 问题
1. SysUserServiceTest.java中的update3()方法得出结论：还是有办法更新字段为null的，其他`update`方法都不行。作为后续学update时的素材
2. 自动填充相关结论：
   1. 只要设置了@TableField(fill = FieldFill.UPDATE)之类自动填充功能，就会执行自动填充；
   2. 只不过MyMetaObjectHandler生效时，可以判断如果原来有值的话，是取传过来的值，还是当前时间