package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser05;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class SysUserMapper05Test {
    @Autowired
    private SysUserMapper05 sysUserMapper;


    /**
     * Preparing: INSERT INTO sys_user05 ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
     * Parameters: 29243f57be67e9d109aaa50ba633f527(String), Jone(String), 18(Integer), test1@baomidou.com(String)
     */
    @Test
    @Rollback
    public void select() {
        int insert = sysUserMapper.insert(new SysUser05().setName("Jone").setAge(18).setEmail("test1@baomidou.com"));
        Assertions.assertEquals(1,insert);
        insert = sysUserMapper.insert(new SysUser05().setName("Jack").setAge(20).setEmail("test2@baomidou.com"));
        Assertions.assertEquals(1,insert);
        List<SysUser05> userList = sysUserMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}