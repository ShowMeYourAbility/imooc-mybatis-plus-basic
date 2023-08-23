package com.llmcu.imooc.mybatisplus.basic.insert.mapper;

import com.llmcu.imooc.mybatisplus.basic.insert.entity.SysUser2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class SysUserMapper2Test {
    @Autowired
    private SysUserMapper2 sysUserMapper2;

    /**
     * 新增{@link com.baomidou.mybatisplus.annotation.TableName}后才能正常查询
     */
    @Test
    @Rollback
    public void select() {
        List<SysUser2> userList = sysUserMapper2.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }



    /**
     * 新增{@link com.baomidou.mybatisplus.annotation.TableName}后才能正常插入
     */
    @Test
    @Rollback
    public void insert() {
        SysUser2 sysUser = new SysUser2().setId(433L).setName("dg").setAge(35).setEmail("2848421@qq.com");
        int insert = sysUserMapper2.insert(sysUser);
        Assertions.assertEquals(1, insert);
    }
}


