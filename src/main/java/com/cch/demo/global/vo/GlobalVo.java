package com.cch.demo.global.vo;

import lombok.Data;

import java.util.Date;


@Data
public class GlobalVo {

    private String id;

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;
}
