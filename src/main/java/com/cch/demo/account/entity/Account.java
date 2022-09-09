package com.cch.demo.account.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cch.demo.global.entity.GlobalEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("account")
@ApiModel(value="User对象", description="用户表")
public class Account extends GlobalEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID（主键）")
    @TableId(value = "code")
    private String code;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "specialAccountno")
    private String specialaccountno;

    public String getSpecialaccountno() {
        return specialaccountno;
    }

    public void setSpecialaccountno(String specialaccountno) {
        this.specialaccountno = specialaccountno;
    }

    @ApiModelProperty(value = "eid")
    private String eid;

    private String activation;

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
