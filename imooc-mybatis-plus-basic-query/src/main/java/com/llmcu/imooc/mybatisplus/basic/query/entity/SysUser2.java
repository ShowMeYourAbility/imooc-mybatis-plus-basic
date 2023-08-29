package com.llmcu.imooc.mybatisplus.basic.query.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class SysUser2 {
    private Long id;
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    private Integer age;
    private String email;
}
