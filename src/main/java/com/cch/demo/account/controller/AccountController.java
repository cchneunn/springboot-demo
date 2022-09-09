package com.cch.demo.account.controller;

import com.cch.demo.account.convert.AccountConverter;
import com.cch.demo.account.convert.BillingConverter;
import com.cch.demo.account.entity.Account;
import com.cch.demo.account.entity.Billing;
import com.cch.demo.account.mapper.AccountMapper;
import com.cch.demo.account.service.AccountService;
import com.cch.demo.account.service.BillingService;
import com.cch.demo.account.vo.AccountVo;
import com.cch.demo.global.entity.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/account")
@Api(value = "用户接口")
@Slf4j
public class AccountController {

    Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Resource
    AccountService accountService;
    @Resource
    AccountMapper accountMapper;
    @Resource
    AccountConverter accountConverter;

    @ApiOperation(value = "添加用户")
    @PostMapping("/activation")
    @ResponseBody
    public CustomResponse<Map<String, Integer>> addAccount(@Valid @RequestBody AccountVo entity , BindingResult bindingResult){
        log.info("==========接到住建局查询激活状态请求=========");
        if(bindingResult.hasErrors()){

            List<String> list = new ArrayList<>();
            bindingResult.getAllErrors().forEach(objectError -> {
                list.add(objectError.getDefaultMessage());
            });
            String allErr = String.join("\n", list);
            return CustomResponse.<Map<String, Integer>>builder().status(500).message(allErr).build();
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).distinct().collect(Collectors.joining("\n"))).build();
        }
//        if (entity.getCode() == null || "".equals(entity.getCode())){
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("统一社会信用代码不能为空").build();
//        } else if(entity.getName() == null || "".equals(entity.getName())) {
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("企业名称不能为空").build();
//        } else if(entity.getSpecialAccountNo() == null || "".equals(entity.getSpecialAccountNo())) {
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("专户号不能为空").build();
//        } else if(entity.getEid()== null || "".equals(entity.getEid())){
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("企业ID不能为空").build();
//        }
        Account accountDo = accountConverter.convert(entity);

        try {

            List<Map<String,Object>> temp = accountService.getListByAccountSpecialAccountno(entity.getSpecialAccountNo());
            if(temp.isEmpty()){
                //执行入库操作
                Account account = accountService.addAccount(accountDo);
                log.info("专户号：{}插库成功",entity.getSpecialAccountNo());
            }
            else{log.info("专户号：{}数据库已存在",entity.getSpecialAccountNo());}
            String Specialaccountno = entity.getSpecialAccountNo();
            List<Map<String,Object>> SignMessage = accountService.getListByAfmmoniaccinfoAccsignstate(Specialaccountno);
            if(!SignMessage.isEmpty()){
                String signstatus= SignMessage.get(0).get("ACCSIGNSTATE").toString();
                if(signstatus.equals("01")) {
                    log.info("专户号：{}签约成功",entity.getSpecialAccountNo());
                    accountMapper.updateAccountActivation(Specialaccountno);
                    Map<String, Integer> map = new HashMap();
                    //需要查库判断此账户是否为激活状态
                    map.put("status", 1);
                    log.info("接到住建局查询激活状态请求,返回激活成功");
                    return CustomResponse.<Map<String, Integer>>builder().status(200).message("激活成功").data(map).build();
                }
                log.info("总行监管账户信息表无此用户");
                log.info("接到住建局查询激活状态请求,返回激活失败");
                return CustomResponse.<Map<String, Integer>>builder().status(500).message("激活失败，激活表中有此用户，未激活状态").build();
            }
            log.info("接到住建局查询激活状态请求,返回激活失败");
            return CustomResponse.<Map<String, Integer>>builder().status(500).message("激活失败，激活表中无此用户").build();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            log.info("接到住建局查询激活状态请求,返回激活失败");
            return CustomResponse.<Map<String, Integer>>builder().status(500).message("激活失败，有异常").build();

        }

    }

}
