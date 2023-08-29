package com.llmcu.imooc.mybatisplus.basic.environment.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class SysUser2 {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableLogic
    private Boolean deleted;
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}
