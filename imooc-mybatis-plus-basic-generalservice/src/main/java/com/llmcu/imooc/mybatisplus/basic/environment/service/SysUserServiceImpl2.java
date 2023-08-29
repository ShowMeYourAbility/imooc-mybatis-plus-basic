package com.llmcu.imooc.mybatisplus.basic.environment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llmcu.imooc.mybatisplus.basic.environment.entity.SysUser2;
import com.llmcu.imooc.mybatisplus.basic.environment.mapper.SysUserMapper2;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl2 extends ServiceImpl<SysUserMapper2, SysUser2>  implements SysUserService2 {
}
