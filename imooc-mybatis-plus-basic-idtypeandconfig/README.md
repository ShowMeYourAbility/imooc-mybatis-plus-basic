# 使用说明
1. SysUser01用于测试@TableId(type = IdType.AUTO)
2. SysUser02用于测试@TableId(type = IdType.NONE)
3. SysUser03用于测试@TableId(type = IdType.INPUT
4. SysUser04用于测试@TableId(type = IdType.ASSIGN_ID)
5. SysUser05用于测试@TableId(type = IdType.ASSIGN_UUID)
6. SysUser用于测试不写type属性，其默认值的确为IdType.NONE

# 结论
1. type = IdType.AUTO:认为数据库表设置了id自增，插入语句为：INSERT INTO sys_user01  ( name, age, email )  VALUES  ( ?, ?, ? )，如果无表id自增，会因插入主键为null而失败
2. type = IdType.NONE：跟随全局策略，全局策略默认为IdType.ASSIGN_ID（数字型雪花算法id）插入语句为：INSERT INTO sys_user02 ( id, name, age, email ) VALUES ( ?, ?, ?, ? )，id值类似1701470299167850497（由mp生成）
3. type = IdType.INPUT：自己注册自动填充插件进行填充。那我就不注册了，毕竟数字型雪花算法已经ok了
4. type = IdType.ASSIGN_ID：数字型雪花算法id
5. type = IdType.ASSIGN_UUID：字符型雪花算法id
# 注意
1. 
# 问题
