package com.llmcu.imooc.mybatisplus.basic.environment.entity;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
@KeySequence(dbType = DbType.H2,value = "SEQ_SYS_USER")
public class SysUser03 {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
