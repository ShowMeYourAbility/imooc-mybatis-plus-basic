package com.llmcu.imooc.mybatisplus.basic.environment.mapper;

import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liuling
* @description 针对表【SYS_USER】的数据库操作Mapper
* @createDate 2023-09-06 01:23:00
* @Entity com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}




