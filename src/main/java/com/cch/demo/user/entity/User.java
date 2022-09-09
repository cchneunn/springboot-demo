package com.cch.demo.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cch.demo.global.entity.GlobalEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user1")
@ApiModel(value="User对象", description="用户表")
public class User extends GlobalEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID（主键）")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "年龄")
    private Integer age;


}
