package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser03;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser04;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class SysUserMapper04Test {
    @Autowired
    private SysUserMapper04 sysUserMapper;


    /**
     * Preparing: INSERT INTO sys_user04 ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
     * Parameters: 1701473915496792065(Long), Jone(String), 18(Integer), test1@baomidou.com(String)
     */
    @Test
    @Rollback
    public void select() {
        int insert = sysUserMapper.insert(new SysUser04().setName("Jone").setAge(18).setEmail("test1@baomidou.com"));
        Assertions.assertEquals(1,insert);
        insert = sysUserMapper.insert(new SysUser04().setName("Jack").setAge(20).setEmail("test2@baomidou.com"));
        Assertions.assertEquals(1,insert);
        List<SysUser04> userList = sysUserMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}