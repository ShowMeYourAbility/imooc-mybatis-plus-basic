package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
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
public class PageTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    @Rollback
    public void selectPage() {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = Wrappers.<SysUser>lambdaQuery()
                .gt(SysUser::getId,0)
                .isNotNull(SysUser::getName)
                .orderByAsc(SysUser::getId);
        // 3：当前第3页，每页2条
        IPage page = new Page(3,2);
        IPage iPage = sysUserMapper.selectPage(page, sysUserLambdaQueryWrapper);
        // 每页有2条
        Assertions.assertEquals(2, iPage.getSize());
        // 当前第3页
        Assertions.assertEquals(3, iPage.getCurrent());
        // 一共有5条
        Assertions.assertEquals(5, iPage.getTotal());
        // 一共有3页
        Assertions.assertEquals(3, iPage.getPages());
        // 当前页面取到记录条数
        Assertions.assertEquals(1, iPage.getRecords().size());
    }

    /**
     * 不查询总记录数
     */
    @Test
    @Rollback
    public void selectPage2() {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = Wrappers.<SysUser>lambdaQuery()
                .gt(SysUser::getId,0)
                .isNotNull(SysUser::getName)
                .orderByAsc(SysUser::getId);
        IPage page = new Page(3,2, false);
        IPage iPage = sysUserMapper.selectPage(page, sysUserLambdaQueryWrapper);
        Assertions.assertEquals(2, iPage.getSize());
        Assertions.assertEquals(3, iPage.getCurrent());
        // 不查总条数
        Assertions.assertEquals(0, iPage.getTotal());
        // 总页数也算不到
        Assertions.assertEquals(0, iPage.getPages());
        Assertions.assertEquals(1, iPage.getRecords().size());
    }


    /**
     * 自定义sql也可以用分页插件
     */
    @Test
    @Rollback
    public void selectPage3() {

        IPage page = new Page(3,2);
        IPage<SysUser> iPage = sysUserMapper.selectAllById(page, 0L);
        Assertions.assertEquals(2, iPage.getSize());
        Assertions.assertEquals(3, iPage.getCurrent());
        Assertions.assertEquals(5, iPage.getTotal());
        Assertions.assertEquals(3, iPage.getPages());
        Assertions.assertEquals(1, iPage.getRecords().size());
    }

}