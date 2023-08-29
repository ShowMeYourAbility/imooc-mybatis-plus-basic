package com.llmcu.imooc.mybatisplus.basic.query.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.llmcu.imooc.mybatisplus.basic.query.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
class MethodTest {
    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * SELECT id,name,age,email FROM sys_user WHERE (id < ? OR age > ?) ORDER BY id ASC
     */
    @Test
    @Rollback
    public void testOr() {
        Assertions.assertEquals(4, sysUserMapper.selectList(new QueryWrapper<SysUser>().lt("id", 4).or().gt("age", 22).orderByAsc("id")).size());
        Assertions.assertEquals(4, sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().lt(SysUser::getId, 4).or().gt(SysUser::getAge, 23).orderByAsc(SysUser::getId)).size());
    }

    /**
     * apply拼接sql
     * apply(String applySql)
     * apply(String applySql, Object... params)
     * apply(boolean condition, String applySql, Object... params)
     *
     */
    @Test
    @Rollback
    public void testApply() {
        Assertions.assertEquals(4, sysUserMapper.selectList(new QueryWrapper<SysUser>().lt("id", 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4)).size());
        Assertions.assertEquals(4, sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().lt(SysUser::getId, 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4)).size());
    }

    /**
     * 一系列逗号连接的数据（如："'1','2','3','4'"），额外首尾拼接括号后 放在in后面；
     * 字符串sql语句（如："select id from sys_user where id < 4"），额外首尾拼接括号后 放在in后面；
     *
     * inSql(R column, String inValue)
     * inSql(boolean condition, R column, String inValue)
     *
     * SELECT id,name,age,email FROM sys_user WHERE (id IN (select id from sys_user where id < 4) AND (id < ? OR CAST(SUBSTRING(email, 5, 1) AS INT) > ?))
     */
    @Test
    @Rollback
    public void testInSql() {
        Assertions.assertEquals(3, sysUserMapper.selectList(new QueryWrapper<SysUser>().inSql("id","select id from sys_user where id < 4").and(qw->qw.lt("id", 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4))).size());
        Assertions.assertEquals(3, sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().inSql(SysUser::getId,"select id from sys_user where id < 4").and(lqw->lqw.lt(SysUser::getId, 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4))).size());

        Assertions.assertEquals(3, sysUserMapper.selectList(new QueryWrapper<SysUser>().inSql("id","'1','2','3','4'").and(qw->qw.lt("id", 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4))).size());
        Assertions.assertEquals(3, sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().inSql(SysUser::getId,"1,2,3,4").and(lqw->lqw.lt(SysUser::getId, 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4))).size());
    }


    /**
     * and(qw->)、or(qw->)
     * and或or后跟括号，括号内有多部分逻辑表达式
     *
     * SELECT id,name,age,email FROM sys_user WHERE (id IN (select id from sys_user where id < 4) AND (id < ? OR CAST(SUBSTRING(email, 5, 1) AS INT) > ?))
     */
    @Test
    @Rollback
    public void testAnd() {
        Assertions.assertEquals(3, sysUserMapper.selectList(new QueryWrapper<SysUser>().inSql("id","select id from sys_user where id < 4").and(qw->qw.lt("id", 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4))).size());
        Assertions.assertEquals(3, sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().inSql(SysUser::getId,"select id from sys_user where id < 4").and(lqw->lqw.lt(SysUser::getId, 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4))).size());

    }


    /**
     * (qw->) and
     *
     * SELECT id,name,age,email FROM sys_user WHERE ((id < ? OR CAST(SUBSTRING(email, 5, 1) AS INT) > ?) AND id IN (select id from sys_user where id < 4))
     */
    @Test
    @Rollback
    public void testNested() {
        Assertions.assertEquals(3, sysUserMapper.selectList(new QueryWrapper<SysUser>().nested(qw->qw.lt("id", 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4)).inSql("id","select id from sys_user where id < 4")).size());
        Assertions.assertEquals(3, sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().nested(lqw->lqw.lt(SysUser::getId, 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4)).inSql(SysUser::getId,"select id from sys_user where id < 4")).size());

    }


    /**
     * last(String lastSql)
     * 无视优化规则直接拼接到 sql 的最后
     */
    @Test
    @Rollback
    public void testLast() {
        Assertions.assertEquals(1, sysUserMapper.selectList(new QueryWrapper<SysUser>().nested(qw->qw.lt("id", 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4)).last("limit 1")).size());
    }


    /**
     * select("id","name")
     * select(Class<T> entityClass, Predicate<TableFieldInfo> predicate)
     * 只查询“指定字段”或“符合条件表达式的字段”
     *
     */
    @Test
    @Rollback
    public void testSelect() {
        Assertions.assertEquals(3, sysUserMapper.selectList(new QueryWrapper<SysUser>().select("id","name").nested(qw->qw.lt("id", 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4)).inSql("id","select id from sys_user where id < 4")).size());
        Assertions.assertEquals(3, sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().select(SysUser::getAge,SysUser::getEmail).nested(lqw->lqw.lt(SysUser::getId, 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4)).inSql(SysUser::getId,"select id from sys_user where id < 4")).size());
        Assertions.assertEquals(3, sysUserMapper.selectList(new QueryWrapper<SysUser>().select(SysUser.class,tableFieldInfo -> !tableFieldInfo.getColumn().equals("id")&&!tableFieldInfo.getColumn().equals("age")  ).nested(qw->qw.lt("id", 4).or().apply("CAST(SUBSTRING(email, 5, 1) AS INT) > {0}",4)).inSql("id","select id from sys_user where id < 4")).size());
    }


    /**
     * allEqual除了要转map,其他还很达标的，毕竟可以实现 is null效果
     */
    @Test
    @Rollback
    public void allEq() {
        Map<SFunction<SysUser,?>,Object> map = new HashMap<>();
        map.put(SysUser::getName,"Tom");
        map.put(SysUser::getAge,28);
        map.put(SysUser::getEmail,null);
        Assertions.assertEquals(0,sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().allEq(map)).size());
        Assertions.assertEquals(1,sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().allEq(map,false)).size());

        Assertions.assertEquals(0,sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().allEq((k,v)->{
            System.out.println(v);
            return true;
        },map)).size());
    }



}