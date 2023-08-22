package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class SysUserMapperTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void select() {
        List<SysUser> userList = sysUserMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}