package com.cch.demo.account.controller;
import com.cch.demo.account.convert.BillingConverter;

import com.cch.demo.account.entity.Billing;
import com.cch.demo.account.entity.Tranlog;
import com.cch.demo.account.mapper.BillingMapper;
import com.cch.demo.account.service.BillingService;

import com.cch.demo.account.vo.BillingVo;
import com.cch.demo.global.entity.CustomResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/billing")
@Api(value = "用户接口")
@Slf4j
public class BillingController {

    Logger logger  = LoggerFactory.getLogger(this.getClass());

    @Resource

    BillingService billingService;

    @Resource

    BillingConverter billingConverter;


    @ApiOperation(value = "添加用户")
        @PostMapping("/receivable")
    @ResponseBody
//    public AccountVo addAccount(@RequestBody AccountVo entity) {
//        /*
//         * TODO
//         * 这里对传来的参数进行格式校验，转换等
//         */
//        Account accountDo = accountConverter.convert(entity);
//        Account account = accountService.save(accountDo);
//        return accountConverter.convert(account);
//
//    }
    public CustomResponse addBilling(@Valid @RequestBody BillingVo entity,BindingResult bindingResult){
        log.info("==========接到住建局应收单据接口请求=========");
        if(bindingResult.hasErrors()){
            List<String> list = new ArrayList<>();
            bindingResult.getAllErrors().forEach(objectError -> {
                list.add(objectError.getDefaultMessage());
            });
            String allErr = String.join("\n",list);
            return CustomResponse.<Map<String, Integer>>builder().status(500).message(allErr).build();
        }
//        if (entity.getCid() == null || "".equals(entity.getCid())){
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("合同ID不能为空").build();
//        } else if(entity.getCno() == null || "".equals(entity.getCno())) {
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("合同备案号不能为空").build();
//        } else if(entity.getBid() == null || "".equals(entity.getBid())) {
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("账单ID不能为空").build();
//        } else if(entity.getBno()== null || "".equals(entity.getBno())){
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("账单编号不能为空").build();
//        } else if(entity.getEid() == null || "".equals(entity.getEid())) {
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("企业ID不能为空").build();
//        } else if(entity.getPaymentAmount() == null || "".equals(entity.getPaymentAmount())) {
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("支付金额不能为空").build();
//        } else if(entity.getType()== null || "".equals(entity.getType())){
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("账单编号不能为空").build();
//        }else if(entity.getPayStartTime() == null || "".equals(entity.getPayStartTime())) {
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("应付款开始时间不能为空").build();
//        } else if(entity.getPayEndTime()== null || "".equals(entity.getPayEndTime())){
//            return CustomResponse.<Map<String, Integer>>builder().status(500).message("应付款支付逾期时间不能为空").build();
//        }
        Billing billingDo = billingConverter.convert(entity);
        try {
            //判断新传入的参数对象是否在库里已经存在
            //如果有查询窗口，下方情况还得排除billing表中的cancel字段有值得情况
            List<Map<String,Object>> temp = billingService.getListByBillingCidBid(entity.getCid(),entity.getBid());
            if(temp.isEmpty()){
                    //执行入库操作
            Billing billing = billingService.addBilling(billingDo);
            log.info("合同号：{}应收单据插库成功",entity.getCid());
                return CustomResponse.<Map<String, Integer>>builder().status(200).message("操作成功").build();
            }
            log.info("合同号：{}应收单据库里已存在",entity.getCid());
            log.info("接到住建局应收单据接口请求,返回操作成功");
            return CustomResponse.<Map<String, Integer>>builder().status(200).message("操作成功").build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            log.info("接到住建局应收单据接口请求,返回操作失敗");
            return CustomResponse.<Map<String, Integer>>builder().status(500).message("操作失败").build();
        }

//            System.out.println("-----------------------------------");
//            System.out.println(entity.getCreatetime());
//            String pattern = "yyyy-MM-dd HH:mm:ss";
//            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//            System.out.println(sdf.format(entity.getCreatetime()));

//            List<Billing> list=billingService.getListByBillingCid(cid);
//            System.out.println(list);
//            System.out.println("-----------------------------------");
//            List<Tranlog> list1=billingService.getListByTranlogCid(cid);
//            System.out.println(list1);
//            System.out.println(list1.get(0));
//            System.out.println(list1.get(0).getAccountnumber());


    }

//    @ApiOperation(value = "根据ID查询用户信息")
//    @GetMapping("/{accountId}")
//    @ResponseBody
//    public AccountVo getUserById(@PathVariable @ApiParam(value = "code", required = true) String accountId) {
//        Account account = accountService.get(accountId);
//        return accountConverter.convert(account);
//    }
}
