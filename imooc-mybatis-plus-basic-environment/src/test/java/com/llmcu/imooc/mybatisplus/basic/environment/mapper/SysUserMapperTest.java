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

    @Test
    @Rollback
    public void select() {
        List<SysUser> userList = sysUserMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        SysUser user = new SysUser().setName("liuling").setAge(1);
        System.out.println(user);
        userList.forEach(System.out::println);
    }

}