package com.llmcu.imooc.mybatisplus.basic.environment.service;

import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.llmcu.imooc.mybatisplus.basic.environment.mapper.SysUserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;

    @Test
    @Rollback
    public void select() {
        List<SysUser> userList = sysUserService.list();
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}