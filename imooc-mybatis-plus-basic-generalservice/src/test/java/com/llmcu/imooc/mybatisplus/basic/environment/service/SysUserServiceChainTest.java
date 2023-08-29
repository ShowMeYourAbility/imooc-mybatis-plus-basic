package com.llmcu.imooc.mybatisplus.basic.environment.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class SysUserServiceChainTest {
    @Autowired
    private SysUserService sysUserService;

    @Test
    @Rollback
    public void lambdaQuery() {
        boolean exists = sysUserService.lambdaQuery().eq(SysUser::getId, 1).exists();
        Assertions.assertEquals(true,exists);
    }

    @Test
    @Rollback
    public void lambdaQuery2() {
        boolean exists = sysUserService.lambdaQuery(new SysUser().setId(1L)).gt(SysUser::getAge, 1).exists();
        Assertions.assertEquals(true,exists);
    }

    @Test
    @Rollback
    public void lambdaUpdate() {
        boolean result = sysUserService.lambdaUpdate().eq(SysUser::getId, 1L).gt(SysUser::getAge, 2).set(SysUser::getName, "liuling").update();
        Assertions.assertEquals(true,result);
    }


    /**
     * delete方法用的是lambdaUpdate，有可能和逻辑删有关
     */
    @Test
    @Rollback
    public void lambdaUpdate2() {
        boolean result = sysUserService.lambdaUpdate().eq(SysUser::getId, 1L).gt(SysUser::getAge, 2).remove();
        Assertions.assertEquals(true,result);
    }



}