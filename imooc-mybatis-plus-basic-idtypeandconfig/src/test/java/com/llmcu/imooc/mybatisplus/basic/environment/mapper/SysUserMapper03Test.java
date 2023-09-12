package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser02;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser03;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class SysUserMapper03Test {
    @Autowired
    private SysUserMapper03 sysUserMapper;


    /**
     * Preparing: INSERT INTO sys_user02 ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
     * Parameters: 1701470299167850497(Long), Jone(String), 18(Integer), test1@baomidou.com(String)
     */
    @Test
    @Rollback
    public void select() {
        int insert = sysUserMapper.insert(new SysUser03().setName("Jone").setAge(18).setEmail("test1@baomidou.com"));
        Assertions.assertEquals(1,insert);
        insert = sysUserMapper.insert(new SysUser03().setName("Jack").setAge(20).setEmail("test2@baomidou.com"));
        Assertions.assertEquals(1,insert);
        List<SysUser03> userList = sysUserMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}