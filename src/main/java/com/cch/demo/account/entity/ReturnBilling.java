package com.cch.demo.account.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cch.demo.global.entity.GlobalEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("returnbilling")
@ApiModel(value="User对象", description="用户表")
public class ReturnBilling extends GlobalEntity {

  private String bid;
  private String paystatus;
  private java.sql.Date paytime;
  private String serialnumber;
  private String remark;


  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }


  public String getPaystatus() {
    return paystatus;
  }

  public void setPaystatus(String paystatus) {
    this.paystatus = paystatus;
  }


  public java.sql.Date getPaytime() {
    return paytime;
  }

  public void setPaytime(java.sql.Date paytime) {
    this.paytime = paytime;
  }


  public String getSerialnumber() {
    return serialnumber;
  }

  public void setSerialnumber(String serialnumber) {
    this.serialnumber = serialnumber;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


}
