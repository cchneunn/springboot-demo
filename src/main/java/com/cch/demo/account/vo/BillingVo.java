package com.cch.demo.account.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
public class BillingVo {
    @NotBlank(message = "合同ID不能为空")
    private String cid;
    @NotBlank(message = "合同备案号不能为空")
    private String cno;
    @NotBlank(message = "账单ID不能为空")
    private String bid;
    @NotBlank(message = "账单编号不能为空")
    private String bno;
    @NotBlank(message = "企业ID不能为空")
    private String eid;
    @NotNull(message = "支付金额不能为空")
    private BigDecimal paymentAmount;
    @NotNull(message = "账单类型不能为空")
    private Integer type;
    @NotBlank(message = "应付款支付开始时间不能为空")
    private String payStartTime;
    @NotBlank(message = "应付款支付逾期时间不能为空")
    private String payEndTime;
    private Date createtime;

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}
