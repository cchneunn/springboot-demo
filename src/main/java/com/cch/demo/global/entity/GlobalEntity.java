package com.cch.demo.global.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>数据库实体基类</b><br/>
 * <pre>
 * 公共属性:
 *   id, 数据库主键 UUID
 *   createTime, 数据插入时间
 *   createUserId, 数据创建人ID
 *   updateTime, 数据更新时间（每次UPDATE都会刷新）
 *   updateUserId  数据更新人ID（每次UPDATE都会刷新）
 * 以上属性由Mybatis-plus管理，在INSERT或UPDATE前自动填充
 * </pre>
 */
@Data
public class GlobalEntity implements Serializable {

    @TableField(fill = FieldFill.INSERT)
    private Date createtime;

//
//    @TableField(fill = FieldFill.INSERT)
//    private String createUserId;
//
//    @TableField(fill = FieldFill.UPDATE)
//    private Date updateTime;
//
//    @TableField(fill = FieldFill.UPDATE)
//    private String updateUserId;
//
//    @TableField(fill = FieldFill.INSERT)
//    @TableLogic
//    private Integer deleted;
//
//    @Version
//    @TableField(fill = FieldFill.INSERT)
//    private Integer version;
}
