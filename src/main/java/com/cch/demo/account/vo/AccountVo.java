package com.cch.demo.account.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class AccountVo {
    @NotBlank(message = "统一社会信用代码不能为空")
    private String code;
    @NotBlank(message = "企业名称不能为空")
    private String name;

    @NotBlank(message = "专户号不能为空")
    private String specialAccountNo;

    @NotBlank(message = "企业ID不能为空")
    private String eid;
}
