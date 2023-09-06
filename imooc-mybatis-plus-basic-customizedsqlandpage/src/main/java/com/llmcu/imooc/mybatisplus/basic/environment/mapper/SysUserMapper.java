package com.llmcu.imooc.mybatisplus.basic.environment.mapper;
import java.util.Collection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.ResultMap;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * Java层手写sql与wrapper结合
     */
    @Select("select * from sys_user ${ew.customSqlSegment}")
    List<SysUser> selectAll(@Param(Constants.WRAPPER)Wrapper<SysUser> wrapper);


    /**
     * XML层手写sql与wrapper结合
     */
    List<SysUser> selectAll2(@Param(Constants.WRAPPER)Wrapper<SysUser> wrapper);

    /**
     * 自定义sql也可以使用分页插件，本质就是：和selectPage一样的参数形式
     */
    IPage<SysUser> selectAllById(IPage page, @Param("id") Long id);

}
