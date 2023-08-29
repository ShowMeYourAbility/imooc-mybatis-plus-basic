package com.llmcu.imooc.mybatisplus.basic.query.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.llmcu.imooc.mybatisplus.basic.query.entity.SysUser;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CommonTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    @Rollback
    public void selectCount() {
        Long cnt = sysUserMapper.selectCount(Wrappers.<SysUser>lambdaQuery().gt(SysUser::getId, 1));
        Assertions.assertEquals(4, cnt);
    }

    /**
     * 返回结果过多时会抛异常
     */
    @Test
    @Rollback
    public void selectOne() {
        Assertions.assertThrows(TooManyResultsException.class, () -> sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().gt(SysUser::getId, 1)));
    }

    @Test
    @Rollback
    public void selectList() {
        List<SysUser> sysUsers = sysUserMapper.selectList(Wrappers.<SysUser>lambdaQuery().gt(SysUser::getId, 1));
        Assertions.assertEquals(4,sysUsers.size());
    }

}
