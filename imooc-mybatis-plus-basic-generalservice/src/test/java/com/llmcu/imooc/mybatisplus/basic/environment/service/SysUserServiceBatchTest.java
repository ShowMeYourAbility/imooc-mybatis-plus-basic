package com.llmcu.imooc.mybatisplus.basic.environment.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser2;
import com.llmcu.imooc.mybatisplus.basic.environment.mapper.SysUserMapper;
import com.llmcu.imooc.mybatisplus.basic.environment.mapper.SysUserMapper2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
public class SysUserServiceBatchTest {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService2 sysUserService2;
    @Autowired
    private SysUserMapper2 sysUserMapper2;



    /**
     * saveOrUpdateBatch:看源码可知：
     * 1、没设置Id或设置了id但此id在数据库中没有记录都会插入
     * 2、设置了id且此id在数据库中有记录则执行修改
     * 3、批处理有一定性能提升
     */
    @Test
    @Rollback
    public void saveOrUpdateBatch() {
        boolean result = sysUserService.saveOrUpdateBatch(Arrays.asList(new SysUser().setId(1L).setName("liuling1"), new SysUser().setName("liuling2"),new SysUser().setId(9L).setName("liuling3")));
        List<SysUser> list = sysUserService.list();
        list.forEach(System.out::println);
    }


    /**
     * @TableLogic 逻辑删除字段没设置时，执行的是delete语句
     * DELETE FROM sys_user WHERE id=?
     */
    @Test
    @Rollback
    public void removeBatchByIds() {
        boolean result = sysUserService.removeBatchByIds(Arrays.asList(new SysUser().setId(1L).setName("liuling1"), new SysUser().setName("liuling2"), new SysUser().setId(9L).setName("liuling3")), false);
        List<SysUser> list = sysUserService.list();
        list.forEach(System.out::println);
    }

    /**
     * 如果是对象数组：
     * 参数useFill无论设置为真还是假，都会SET update_time=?，即使setUpdateTime(null)
     * 只不过启用MyMetaObjectHandler时，按MyMetaObjectHandler逻辑走，判断用当前时间还是传过来的时间（如果有的话）
     * UPDATE sys_user2 SET update_time=?, deleted=1 WHERE id=? AND deleted=0
     */
    @Test
    @Rollback
    public void removeBatchByIds3() {
//        boolean result = sysUserService2.removeBatchByIds(Arrays.asList(1L,2L), true);
        boolean result = sysUserService2.removeBatchByIds(Arrays.asList(new SysUser2().setId(1L).setUpdateTime(new Date()), new SysUser2().setName("liuling2"), new SysUser2().setId(9L).setName("liuling3")), false);
        List<SysUser2> list = sysUserService2.list();
        list.forEach(System.out::println);
    }




    /**
     * 如果是id数组：
     * 如果参数useFill为不启用自动填充，不会设置更新时间，不管MyMetaObjectHandler是否通过@Component生效
     * 如果参数useFill为启用自动填充，会设置更新时间，不管MyMetaObjectHandler不生效时设置为null,不管MyMetaObjectHandler生效时按不管MyMetaObjectHandler逻辑设置
     *
     */
    @Test
    @Rollback
    public void removeBatchByIds4() {
        boolean result = sysUserService2.removeBatchByIds(Arrays.asList(1L), true);
        List<SysUser2> list = sysUserService2.list();
        list.forEach(System.out::println);
    }


    /**
     * 用于验证只要写了@TableField(fill = FieldFill.UPDATE)，就会在更新时设置修改时间，不论MyMetaObjectHandler是否生效，只不过MyMetaObjectHandler可以选择肜传过来的时间还是当时时间
     */
    @Test
    @Rollback
    public void removeBatchByIds5() {
        sysUserMapper.updateById(new SysUser().setId(1L).setName("liuling1"));
        sysUserMapper.selectList(null).forEach(System.out::println);
        sysUserMapper2.updateById(new SysUser2().setId(1L).setName("liuling1"));
        sysUserMapper2.selectList(null).forEach(System.out::println);

    }
}