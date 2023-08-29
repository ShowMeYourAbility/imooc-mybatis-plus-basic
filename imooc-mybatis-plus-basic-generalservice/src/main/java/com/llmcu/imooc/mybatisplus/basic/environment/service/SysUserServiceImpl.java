package com.llmcu.imooc.mybatisplus.basic.environment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser;
import com.llmcu.imooc.mybatisplus.basic.environment.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{
}
