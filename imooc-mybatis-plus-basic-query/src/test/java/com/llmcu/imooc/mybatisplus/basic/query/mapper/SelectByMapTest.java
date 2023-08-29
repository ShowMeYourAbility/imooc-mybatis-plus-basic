package com.llmcu.imooc.mybatisplus.basic.query.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.llmcu.imooc.mybatisplus.basic.query.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
class SelectByMapTest {
    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 看源码，本质是{@link QueryWrapper#allEq(Map)}
     * <p>
     * Preparing: SELECT id,name,age,email FROM sys_user WHERE (name = ? AND age = ?)
     * Parameters: Jack(String), 20(Integer)
     */
    @Test
    @Rollback
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Jack");
        columnMap.put("age", 20);
        List<SysUser> sysUsers = sysUserMapper.selectByMap(columnMap);
        Assertions.assertEquals(1, sysUsers.size());
    }

    /**
     * selectMaps:返回结果是map对象组成的list
     */
    @Test
    @Rollback
    public void selectMaps() {
        List<Map<String, Object>> maps = sysUserMapper.selectMaps(Wrappers.<SysUser>lambdaQuery().lt(SysUser::getId, 2));
        Assertions.assertEquals(1, maps.size());
    }


    /**
     * 你要聚合，只能用selectMaps()返回map来接收
     * 只能用字段，不能用lambda表达式
     */
    @Test
    @Rollback
    public void testGroupBy() {
        List<Map<String, Object>> maps = sysUserMapper.selectMaps(Wrappers.<SysUser>query()
                .select("name","avg(age) age","max(age) max_age")
                .lt("id",5)
                .groupBy("name")
                .having("avg(age)>{0}",18));
        Assertions.assertEquals(3, maps.size());
    }

    /**
     * sql语句会查询全部字段（SELECT id,name,age,email FROM sys_user），
     * 但只返回第一个字段的值
     */
    @Test
    @Rollback
    public void selectObjs() {
        List<Object> objects = sysUserMapper.selectObjs(Wrappers.<SysUser>lambdaQuery());
        objects.forEach(System.out::println);
        Assertions.assertEquals(5,objects.size());

    }

}