package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@SpringBootTest
@Transactional
public class SysUserMapperDeleteTest {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserMapper2 sysUserMapper2;

    /**
     * 如果id不存在，deleteById返回结果为0，表示影响了0条记录
     */
    @Test
    @Rollback
    public void deleteById() {
        int i = sysUserMapper.deleteById(new SysUser().setId(9L));
        Assertions.assertEquals(0,i);
        sysUserMapper.selectList(null).forEach(System.out::println);
    }


    /**
     * 只有deleteById会自动填充更新时间,自动填充内容由该方法入参实体获取，后续可由MyMetaObjectHandler覆盖
     */
    @Test
    @Rollback
    public void deleteById2() {
        int i = sysUserMapper2.deleteById(new SysUser2().setId(9L).setUpdateTime(new Date()));
        Assertions.assertEquals(0,i);
        sysUserMapper.selectList(null).forEach(System.out::println);
    }

    /**
     * DELETE FROM sys_user WHERE (name = ? AND id = ?)
     */
    @Test
    @Rollback
    public void deleteByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1L);
        map.put("name","Jone");
        int i = sysUserMapper.deleteByMap(map);
        Assertions.assertEquals(1,i);
    }

    /**
     * deleteByMap不会自动填充，因为map只会影响where中的条件，哪怕开启MyMetaObjectHandler
     * UPDATE sys_user2 SET deleted=1 WHERE deleted=0 AND (name = ? AND id = ?)
     */
    @Test
    @Rollback
    public void deleteByMap2() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1L);
        map.put("name","Jone");
        int i = sysUserMapper2.deleteByMap(map);
        Assertions.assertEquals(1,i);
    }

    @Test
    @Rollback
    public void deleteByQueryWrapper() {
        int delete = sysUserMapper.delete(Wrappers.<SysUser>lambdaQuery().between(SysUser::getId, 1L, 3L));
        Assertions.assertEquals(3,delete);
    }


    /**
     * deleteByQueryWrapper也不会自动填充，因为wrapper只会影响where中的条件，哪怕开启MyMetaObjectHandler
     * UPDATE sys_user2 SET deleted=1 WHERE deleted=0 AND (id BETWEEN ? AND ?)
     */
    @Test
    @Rollback
    public void deleteByQueryWrapper2() {
        int delete = sysUserMapper2.delete(Wrappers.<SysUser2>lambdaQuery().between(SysUser2::getId, 1L, 3L));
        Assertions.assertEquals(3,delete);
    }

    /**
     * 用updateWrapper也无防，只不过方法参数名为queryWrapper
     */
    @Test
    @Rollback
    public void deleteByUpdateWrapper() {
        int delete = sysUserMapper.delete(Wrappers.<SysUser>lambdaUpdate().between(SysUser::getId, 1L, 3L));
        Assertions.assertEquals(3,delete);
    }


    @Test
    @Rollback
    public void deleteByUpdateWrapper2() {
//        int delete = sysUserMapper2.delete(Wrappers.<SysUser2>lambdaUpdate().between(SysUser2::getId, 1L, 3L));
        int delete = sysUserMapper2.delete(Wrappers.lambdaUpdate(new SysUser2()).between(SysUser2::getId, 1L, 3L));
        Assertions.assertEquals(3,delete);
    }

    /**
     * DELETE FROM sys_user WHERE id IN ( ? , ? )
     */
    @Test
    @Rollback
    public void deleteBatchIds() {
        int i = sysUserMapper.deleteBatchIds(Arrays.asList(1L, 2L));
        Assertions.assertEquals(2,i);
    }

    /**
     * deleteBatchIds也不会自动填充，哪怕开启MyMetaObjectHandler
     */
    @Test
    @Rollback
    public void deleteBatchIds2() {
        int i = sysUserMapper2.deleteBatchIds(Arrays.asList(1L, 2L));
        Assertions.assertEquals(2,i);
    }
}