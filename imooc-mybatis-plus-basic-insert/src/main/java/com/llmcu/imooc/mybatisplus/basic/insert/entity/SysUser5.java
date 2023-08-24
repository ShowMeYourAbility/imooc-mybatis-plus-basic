package com.llmcu.imooc.mybatisplus.basic.insert.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 多了一个remark字段，少了一个name字段
 */
@Data
public class SysUser5 {
    private Long id;
    //    private String name;
    private Integer age;
    private String email;
    @TableField(exist = false)
    private String remark;
}
