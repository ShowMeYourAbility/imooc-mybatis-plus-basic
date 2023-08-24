package com.llmcu.imooc.mybatisplus.basic.insert.mapper;

import com.baomidou.mybatisplus.annotation.TableId;
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
     * 新增{@link TableId}后才能正常查询
     */
    @Test
    @Rollback
    public void select() {
        List<SysUser3> userList = sysUserMapper3.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }



    /**
     * 新增{@link TableId}后才能正常插入
     */
    @Test
    @Rollback
    public void insert() {
        // id为null时，也会insert此字段，普通字段为null时，不会insert此字段
        SysUser3 sysUser = new SysUser3().setName("dg").setEmail("2848421@qq.com");
        int insert = sysUserMapper3.insert(sysUser);
        Assertions.assertEquals(1, insert);
    }
}


