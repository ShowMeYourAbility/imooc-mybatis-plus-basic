package com.llmcu.imooc.mybatisplus.basic.insert.mapper;

import com.llmcu.imooc.mybatisplus.basic.insert.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class SysUserMapperTest {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 成功时返回结果为：1
     */
    @Test
    @Rollback
    public void insert() {
        insertAndAssert();
    }

    /**
     * 使用{@link Rollback},在方法执行后执行回滚，不影响数据库数据
     */
    @Test
    @Rollback
    public void insert2() {
        insertAndAssert();
    }

    private void insertAndAssert() {
        SysUser sysUser = new SysUser().setId(433L).setName("dg").setAge(35).setEmail("2848421@qq.com");
        int insert = sysUserMapper.insert(sysUser);
        Assertions.assertEquals(1, insert);

        List<SysUser> userList = sysUserMapper.selectList(null);
        Assertions.assertEquals(6, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * 插入失败（如主键冲突引起）抛异常，所以正常返回结果只能成功时的1
     */
    @Test
    public void insert3() {
        SysUser sysUser = new SysUser().setId(3L).setName("dg").setAge(35).setEmail("2848421@qq.com");
        Assertions.assertThrows(DuplicateKeyException.class, () -> sysUserMapper.insert(sysUser));
    }
}


