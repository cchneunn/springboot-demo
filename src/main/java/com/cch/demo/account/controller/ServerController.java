package com.cch.demo.account.controller;

import com.cch.demo.account.convert.AccountConverter;
import com.cch.demo.account.entity.Account;
import com.cch.demo.account.entity.ReturnBilling;
import com.cch.demo.account.service.AccountService;
import com.cch.demo.account.vo.AccountVo;
import com.cch.demo.global.entity.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api")
@Api(value = "用户接口")
public class ServerController {

    Logger logger  = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "添加用户")
    @PostMapping("/billing/receivable/callback")
    @ResponseBody
//构建住建局服务器，状态
    public CustomResponse addServerReturnBilling(@Valid @RequestBody ReturnBilling entity){

//        if(!StringUtils.hasLength(entity.getCode())){
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("code 不能为空").build();
//        }

        System.out.println("已经进入到住建局应收单据回调接口");
        try {
            System.out.println("已经进入到住建局应收单据回调接口的try里");
            Map<String, Integer> map = new HashMap();
            map.put("status", 1);
            return CustomResponse.<Map<String, Integer>>builder().status(200).message("成功").data(map).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return CustomResponse.<Map<String, Integer>>builder().status(500).message("失败").build();
        }

    }
    @PostMapping("/open-saas-biz-service/open/getToken")
    @ResponseBody
//构建住建局服务器，状态
    public CustomResponse addServerGetToken(@Valid ReturnBilling entity){

//        if(!StringUtils.hasLength(entity.getCode())){
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("code 不能为空").build();
//        }

        System.out.println("已经进入到住建局getToken接口");
        try {
            System.out.println("已经进入到住建局getToken接口的try里");
            String token = "woshiyigetoken";
            return CustomResponse.builder().status(200).message("获取token成功").data(token).build();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return CustomResponse.builder().status(500).message("获取token失败").build();
        }


    }
}
