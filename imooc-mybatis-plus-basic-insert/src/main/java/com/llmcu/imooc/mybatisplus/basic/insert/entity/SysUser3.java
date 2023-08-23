package com.llmcu.imooc.mybatisplus.basic.insert.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 仅主键不同
 */
@Data
public class SysUser3 {
    @TableId("id")
    private Long userId;
    private String name;
    private Integer age;
    private String email;
}
