package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser01;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class SysUserMapper01Test {
    @Autowired
    private SysUserMapper01 sysUserMapper;

    /**
     * type = IdType.AUTO，那我就认为你数据库表主键自增，那插入语句就为：INSERT INTO sys_user01  ( name, age, email )  VALUES  ( ?, ?, ? )
     * 数据库表不设置主键自增，主键为null就报错了
     */
    @Test
    @Rollback
    public void select() {
        int insert = sysUserMapper.insert(new SysUser01().setName("Jone").setAge(18).setEmail("test1@baomidou.com"));
        Assertions.assertEquals(1,insert);
        insert = sysUserMapper.insert(new SysUser01().setName("Jack").setAge(20).setEmail("test2@baomidou.com"));
        Assertions.assertEquals(1,insert);
        List<SysUser01> userList = sysUserMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}