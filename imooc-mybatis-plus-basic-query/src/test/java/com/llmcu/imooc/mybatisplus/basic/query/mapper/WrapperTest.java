package com.llmcu.imooc.mybatisplus.basic.query.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.llmcu.imooc.mybatisplus.basic.query.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class WrapperTest {
    @Autowired
    private SysUserMapper sysUserMapper;



    @Test
    @Rollback
    public void selectByMap1() {
        Assertions.assertEquals(2, sysUserMapper.selectList(new QueryWrapper<SysUser>().like("name", "a").lt("id",100)).size());
        Assertions.assertEquals(1, sysUserMapper.selectList(new QueryWrapper<SysUser>(new SysUser().setAge(20)).like("name", "a").lt("id",200)).size());
        Assertions.assertEquals(2, sysUserMapper.selectList(new QueryWrapper<SysUser>(SysUser.class).like("name", "a").lt("id",300)).size());
    }

    @Test
    @Rollback
    public void selectByMap2() {
        // Wrappers.<SysUser>query()可以使用链式拼接条件
        Assertions.assertEquals(2, sysUserMapper.selectList(Wrappers.<SysUser>query().like("name", "a").lt("id",100)).size());
        Assertions.assertEquals(1, sysUserMapper.selectList(Wrappers.query(new SysUser().setAge(20)).like("name", "a").lt("id",200)).size());
        Assertions.assertEquals(2, sysUserMapper.selectList(Wrappers.query(SysUser.class).like("name", "a").lt("id",300)).size());
    }


    @Test
    @Rollback
    public void selectByMap3() {
//        81 public static <T> LambdaQueryWrapper<T> lambdaQuery()
        Assertions.assertEquals(2,sysUserMapper.selectList(new LambdaQueryWrapper<SysUser>().like(SysUser::getName, "a").lt(SysUser::getId,100)).size());
//        92 public static <T> LambdaQueryWrapper<T> lambdaQuery(T entity)
        Assertions.assertEquals(1,sysUserMapper.selectList(new LambdaQueryWrapper<>(new SysUser().setAge(20)).like(SysUser::getName, "a").lt(SysUser::getId,200)).size());
//        104 public static <T> LambdaQueryWrapper<T> lambdaQuery(Class<T> entityClass)
        Assertions.assertEquals(2, sysUserMapper.selectList(new LambdaQueryWrapper<>(SysUser.class).like(SysUser::getName, "a").lt(SysUser::getId,300)).size());
    }

    @Test
    @Rollback
    public void selectByMap4() {
//        81 public static <T> LambdaQueryWrapper<T> lambdaQuery()
        Assertions.assertEquals(2,sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().like(SysUser::getName, "a").lt(SysUser::getId,100)).size());
//        92 public static <T> LambdaQueryWrapper<T> lambdaQuery(T entity)
        Assertions.assertEquals(1,sysUserMapper.selectList(Wrappers.lambdaQuery(new SysUser().setAge(20)).like(SysUser::getName, "a").lt(SysUser::getId,200)).size());
//        104 public static <T> LambdaQueryWrapper<T> lambdaQuery(Class<T> entityClass)
        Assertions.assertEquals(2, sysUserMapper.selectList(Wrappers.lambdaQuery(SysUser.class).like(SysUser::getName, "a").lt(SysUser::getId,300)).size());
    }


    /**
     * 所谓链式调用：wrappers及各种条件本应作为mapper的方法参数，链式则是wrapper写完条件后，直接链式执行相关操作
     */
    @Test
    @Rollback
    public void selectByMap5() {
        Assertions.assertEquals(2,new LambdaQueryChainWrapper<>(sysUserMapper).like(SysUser::getName, "a").lt(SysUser::getId,100).list().size());
    }
}