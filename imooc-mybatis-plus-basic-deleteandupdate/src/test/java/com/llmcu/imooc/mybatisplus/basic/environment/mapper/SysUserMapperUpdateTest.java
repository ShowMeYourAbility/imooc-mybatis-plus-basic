package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@Transactional
public class SysUserMapperUpdateTest {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserMapper2 sysUserMapper2;

    /**
     * 使用updateById，自动填充会取实体中的时间
     */
    @Test
    @Rollback
    public void updateById() {
        int i = sysUserMapper.updateById(new SysUser().setId(1L).setName("liuling"));
        Assertions.assertEquals(1,i);
        Assertions.assertEquals("liuling",sysUserMapper.selectById(1L).getName());

        int i1 = sysUserMapper2.updateById(new SysUser2().setId(2L).setName("liuling2"));
        Assertions.assertEquals(1,i1);
        Assertions.assertEquals("liuling2",sysUserMapper2.selectById(2L).getName());
    }

    /**
     * 使用updateByWrapper，自动填充会取实体中的时间
     */
    @Test
    @Rollback
    public void updateByWrapper() {
        int cnt = sysUserMapper.update(new SysUser().setName("liuling"), Wrappers.<SysUser>lambdaUpdate().eq(SysUser::getAge, 21));
        Assertions.assertEquals(1,cnt);
        Assertions.assertEquals("liuling",sysUserMapper.selectById(4L).getName());

        int cnt2 = sysUserMapper2.update(new SysUser2().setName("liuling2").setUpdateTime(new Date()), Wrappers.<SysUser2>lambdaUpdate().eq(SysUser2::getAge, 28));
        Assertions.assertEquals(1,cnt2);
        Assertions.assertEquals("liuling2",sysUserMapper2.selectById(3L).getName());

    }


    /**
     * 使用updateWrapper.set()方法不会自动填充
     */
    @Test
    @Rollback
    public void updateByWrapper2() {
        int cnt = sysUserMapper.update(null, Wrappers.<SysUser>lambdaUpdate().eq(SysUser::getAge, 21).set(SysUser::getName,"liuling"));
        Assertions.assertEquals(1,cnt);
        Assertions.assertEquals("liuling",sysUserMapper.selectById(4L).getName());

        int cnt2 = sysUserMapper2.update(null, Wrappers.<SysUser2>lambdaUpdate().eq(SysUser2::getAge, 28).set(SysUser2::getName,"liuling2"));
        Assertions.assertEquals(1,cnt2);
        Assertions.assertEquals("liuling2",sysUserMapper2.selectById(3L).getName());

    }

    /**
     * 链式调用不会自动填充
     */
    @Test
    @Rollback
    public void updateByChain() {
        boolean result = new LambdaUpdateChainWrapper<>(sysUserMapper).eq(SysUser::getAge, 21).set(SysUser::getName, "liuling").update();
        Assertions.assertEquals(true,result);

        boolean result2 = new LambdaUpdateChainWrapper<>(sysUserMapper2).eq(SysUser2::getAge, 21).set(SysUser2::getName, "liuling2").update();
        Assertions.assertEquals(true,result2);

    }


    /**
     * 另一种链式调用也不会自动填充
     */
    @Test
    @Rollback
    public void updateByChain2() {
        boolean result = new LambdaUpdateChainWrapper<>(SysUser.class).eq(SysUser::getAge, 21).set(SysUser::getName, "liuling").update();
        Assertions.assertEquals(true,result);

        boolean result2 = new LambdaUpdateChainWrapper<>(SysUser2.class).eq(SysUser2::getAge, 21).set(SysUser2::getName, "liuling2").update();
        Assertions.assertEquals(true,result2);


    }
}