package com.llmcu.imooc.mybatisplus.basic.insert.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 仅表名不符合驼峰标识
 */
@Data
@TableName("system_user")
public class SysUser2 {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
