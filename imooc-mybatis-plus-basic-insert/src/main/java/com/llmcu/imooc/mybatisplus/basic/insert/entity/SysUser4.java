package com.llmcu.imooc.mybatisplus.basic.insert.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 仅普通字段不符合驼峰标识转换
 */
@Data
public class SysUser4 {
    private Long id;
    @TableField("name")
    private String userName;
    private Integer age;
    private String email;
}
