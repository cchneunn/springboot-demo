package com.cch.demo.account.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cch.demo.global.entity.GlobalEntity;
import io.swagger.annotations.ApiModel;
import jdk.nashorn.internal.objects.Global;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("billing")
@ApiModel(value="User对象", description="用户表")
public class Billing extends GlobalEntity {
  private String cid;
  private String bid;
  private String eid;
  private String paystarttime;
  private String payendtime;
  private String status;
  private BigDecimal paymentamount;
  private Integer type;
  private String cancel;
  private String bno;
  private String cno;

  public String getBno() {
    return bno;
  }

  public void setBno(String bno) {
    this.bno = bno;
  }

  public String getCno() {
    return cno;
  }

  public void setCno(String cno) {
    this.cno = cno;
  }

  public BigDecimal getPaymentamount() {
    return paymentamount;
  }

  public void setPaymentamount(BigDecimal paymentamount) {
    this.paymentamount = paymentamount;
  }

  public String getPayendtime() {
    return payendtime;
  }

  public void setPayendtime(String payendtime) {
    this.payendtime = payendtime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPaystarttime() {
    return paystarttime;
  }

  public void setPaystarttime(String paystarttime) {
    this.paystarttime = paystarttime;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }



  public String getCid() {
    return cid;
  }
  public void setCid(String cid) {
    this.cid = cid;
  }

  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }


  public String getEid() {
    return eid;
  }

  public void setEid(String eid) {
    this.eid = eid;
  }


}
