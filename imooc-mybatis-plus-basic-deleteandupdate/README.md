# 使用说明
1. SysUser2是为了验证逻辑删除及自动填充


# 结论
1. 只有deleteById会自动填充更新时间,自动填充内容由该方法入参实体获取，后续可由MyMetaObjectHandler覆盖
2. update
   * 使用updateById，自动填充会取实体中的时间
   * 使用updateByWrapper，自动填充会取实体中的时间
   * 使用updateWrapper.set()方法不会自动填充
   * 链式调用不会自动填充
3. MyMetaObjectHandler只会影响自动填充的逻辑，不会使原来不自动填充变得自动填充
4. 自动填充与否和update()方法有关

