# 使用说明
1. 既想使用wrapper，又想使用sql时，考虑使用自定义sql

# 亮点

# 注意
* 自定义sql
1. 不能使用<where></where>
2. 不能使用#{ }
3. xml方式：sql语句复制过去即可

* 分页插件开发步骤
1. 复制官网的Configuration类
2. 调用BaseMapper封装好的分页方法即可

* “加载更多”效果
  * 分页插件可以实现不查总记录数，只查特定页面记录

* 自定义SQL仿BaseMapper分页方法参数结构也能通过分页插件分页


