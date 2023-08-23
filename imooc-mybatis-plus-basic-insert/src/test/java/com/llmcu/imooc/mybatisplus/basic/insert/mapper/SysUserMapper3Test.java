package com.llmcu.imooc.mybatisplus.basic.insert.mapper;

import com.llmcu.imooc.mybatisplus.basic.insert.entity.SysUser3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class SysUserMapper3Test {
    @Autowired
    private SysUserMapper3 sysUserMapper3;

    /**
     * 新增{@link com.baomidou.mybatisplus.annotation.TableName}后才能正常查询
     */
    @Test
    @Rollback
    public void select() {
        List<SysUser3> userList = sysUserMapper3.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }



    /**
     * 新增{@link com.baomidou.mybatisplus.annotation.TableName}后才能正常插入
     */
    @Test
    @Rollback
    public void insert() {
//        SysUser3 sysUser = new SysUser3().setUserId(433L).setName("dg").setAge(35).setEmail("2848421@qq.com");
        SysUser3 sysUser = new SysUser3().setName("dg").setAge(35).setEmail("2848421@qq.com");
        int insert = sysUserMapper3.insert(sysUser);
        Assertions.assertEquals(1, insert);
    }
}


