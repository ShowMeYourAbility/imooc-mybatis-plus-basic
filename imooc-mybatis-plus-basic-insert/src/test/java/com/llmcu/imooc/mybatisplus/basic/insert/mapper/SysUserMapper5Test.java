package com.llmcu.imooc.mybatisplus.basic.insert.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.llmcu.imooc.mybatisplus.basic.insert.entity.SysUser3;
import com.llmcu.imooc.mybatisplus.basic.insert.entity.SysUser5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class SysUserMapper5Test {
    @Autowired
    private SysUserMapper5 sysUserMapper5;

    /**
     * 新增{@link TableField#exist()}后才能正常查询
     */
    @Test
    @Rollback
    public void select() {
        List<SysUser5> userList = sysUserMapper5.selectList(null);
        // 只挑选特定共通字段，不加@TableField(exist=false)也不会报错
//        List<SysUser5> userList = sysUserMapper5.selectList(new QueryWrapper<SysUser5>().select("id","age"));
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }



    /**
     * 新增{@link TableField#exist()}后才能正常插入
     */
    @Test
    @Rollback
    public void insert() {
        SysUser5 sysUser = new SysUser5().setAge(35).setEmail("2848421@qq.com");
        int insert = sysUserMapper5.insert(sysUser);
        Assertions.assertEquals(1, insert);
    }
}


