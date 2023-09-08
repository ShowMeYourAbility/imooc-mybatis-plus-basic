package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class SysUserMapperTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * (1, 'Jone', 18, 'test1@baomidou.com'),
     * (2, 'Jack', 20, 'test2@baomidou.com'),
     * (3, 'Tom', 28, 'test3@baomidou.com'),
     * (4, 'Sandy', 21, 'test4@baomidou.com'),
     * (5, 'Billie', 24, 'test5@baomidou.com');
     */
    @Test
    @Rollback
    public void select() {
        int insert = sysUserMapper.insert(new SysUser().setName("Jone").setAge(18).setEmail("test1@baomidou.com"));
        Assertions.assertEquals(1,insert);
        insert = sysUserMapper.insert(new SysUser().setName("Jack").setAge(20).setEmail("test2@baomidou.com"));
        Assertions.assertEquals(1,insert);
    }

}