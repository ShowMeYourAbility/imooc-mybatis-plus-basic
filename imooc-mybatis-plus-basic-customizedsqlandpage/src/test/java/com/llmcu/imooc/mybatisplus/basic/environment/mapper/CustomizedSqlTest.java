package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
public class CustomizedSqlTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    @Rollback
    public void selectAll() {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = Wrappers.<SysUser>lambdaQuery()
                .lt(SysUser::getId,3)
                .isNotNull(SysUser::getName);
        List<SysUser> sysUsers = sysUserMapper.selectAll(sysUserLambdaQueryWrapper);
        Assertions.assertEquals(2,sysUsers.size());
    }

    @Test
    @Rollback
    public void selectAll2() {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = Wrappers.<SysUser>lambdaQuery()
                .lt(SysUser::getId,3)
                .isNotNull(SysUser::getName);
        List<SysUser> sysUsers = sysUserMapper.selectAll2(sysUserLambdaQueryWrapper);
        Assertions.assertEquals(2,sysUsers.size());
    }

}