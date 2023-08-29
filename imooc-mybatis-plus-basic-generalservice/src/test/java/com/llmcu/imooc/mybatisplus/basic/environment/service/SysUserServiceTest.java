package com.llmcu.imooc.mybatisplus.basic.environment.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.llmcu.imooc.mybatisplus.basic.environment.mapper.SysUserMapper;
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
public class SysUserServiceTest {
    @Autowired
    private SysUserService sysUserService;

    @Test
    @Rollback
    public void list() {
        List<SysUser> userList = sysUserService.list();
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    @Rollback
    public void getOne() {
        SysUser one = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getId, 1));
        Assertions.assertEquals(1,one.getId());
    }


    @Test
    @Rollback
    public void update() {
        boolean cnt = sysUserService.updateById(new SysUser().setId(1L).setName("liuling"));
        SysUser oneById = sysUserService.getById(1L);
        System.out.println(oneById);
        Assertions.assertEquals("liuling",oneById.getName());
    }

    @Test
    @Rollback
    public void update2() {
        boolean cnt = sysUserService.update(new SysUser().setName("liuling2"), Wrappers.<SysUser>lambdaUpdate().eq(SysUser::getId, 1));
        SysUser oneById = sysUserService.getById(1L);
        System.out.println(oneById);
        Assertions.assertEquals("liuling2",oneById.getName());
    }

    /**
     * 还是有办法更新字段为null的，其他update方法都不行
     */

    @Test
    @Rollback
    public void update3() {
        boolean cnt = sysUserService.update(Wrappers.<SysUser>lambdaUpdate().eq(SysUser::getId, 1).set(SysUser::getName, "liuling3").set(SysUser::getEmail, null));
        SysUser oneById = sysUserService.getById(1L);
        System.out.println(oneById);
        Assertions.assertEquals("liuling3",oneById.getName());
    }

    /**
     * updateBatchById看源码还是有一定性能提升的
     */
    @Test
    @Rollback
    public void update4() {
        boolean cnt = sysUserService.updateBatchById(Arrays.asList(new SysUser().setId(1L).setName("liuling4"), new SysUser().setId(9L).setName("liuling5")));
        Assertions.assertEquals(true,cnt);
        List<SysUser> list = sysUserService.list();
        list.forEach(System.out::println);
    }

}