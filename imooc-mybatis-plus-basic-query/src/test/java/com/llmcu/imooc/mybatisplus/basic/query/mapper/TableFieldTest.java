package com.llmcu.imooc.mybatisplus.basic.query.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.llmcu.imooc.mybatisplus.basic.query.entity.SysUser2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TableFieldTest {
    @Autowired
    private SysUserMapper2 sysUserMapper2;

    /**
     * Wrappers.lambdaQuery(sysUser2) 这种构造方法，可以与 @TableField(condition = SqlCondition.LIKE) 拼接出like效果如下：
     * SELECT id,name,age,email FROM sys_user2 WHERE name LIKE CONCAT('%',?,'%')
     */
    @Test
    @Rollback
    public void testTableField() {
        SysUser2 sysUser2 = new SysUser2().setName("a");
        Assertions.assertEquals(2, sysUserMapper2.selectList(Wrappers.lambdaQuery(sysUser2)).size());
    }

}
