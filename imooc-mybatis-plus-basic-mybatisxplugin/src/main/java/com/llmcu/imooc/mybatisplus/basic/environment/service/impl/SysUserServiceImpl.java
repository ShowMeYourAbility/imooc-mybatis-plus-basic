package com.llmcu.imooc.mybatisplus.basic.environment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.llmcu.imooc.mybatisplus.basic.environment.service.SysUserService;
import com.llmcu.imooc.mybatisplus.basic.environment.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author liuling
* @description 针对表【SYS_USER】的数据库操作Service实现
* @createDate 2023-09-06 01:23:00
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




