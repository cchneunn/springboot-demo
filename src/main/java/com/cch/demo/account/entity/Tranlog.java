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
@TableName("tranlog")
@ApiModel(value="User对象", description="用户表")
public class Tranlog extends GlobalEntity {

  private String accountnumber;
  private String contract;
  private String paytime;
  private String payamount;
  private String status;
  private String serialnumber;

  public String getSerialnumber() {
    return serialnumber;
  }

  public void setSerialnumber(String serialnumber) {
    this.serialnumber = serialnumber;
  }

  public String getAccountnumber() {
    return accountnumber;
  }

  public void setAccountnumber(String accountnumber) {
    this.accountnumber = accountnumber;
  }


  public String getContract() {
    return contract;
  }

  public void setContract(String contract) {
    this.contract = contract;
  }


  public String getPaytime() {
    return paytime;
  }

  public void setPaytime(String paytime) {
    this.paytime = paytime;
  }


  public String getPayamount() {
    return payamount;
  }

  public void setPayamount(String payamount) {
    this.payamount = payamount;
  }

}
