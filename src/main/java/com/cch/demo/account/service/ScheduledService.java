package com.cch.demo.account.service;

import com.cch.demo.account.entity.Tranlog;
import com.cch.demo.account.mapper.BillingMapper;
import com.cch.demo.account.mapper.TranlogMapper;
import com.cch.demo.account.util.HttpClientUtil;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.*;

@Component
@Slf4j
public class ScheduledService {
//        public void printSay() {
//           System.out.println("This is a say method!"+new Date());
//
//        }
        @Resource
        private TranlogMapper tranlogMapper;
        @Resource
        private BillingMapper billingMapper;
        Map<String,Object> tranlogCheckdata;
        String contract;
        String checkTranlogPayamount;
        HttpClientUtil a = new HttpClientUtil();
        //接受应收单据接口，对账功能
        @Transactional
        public void checkdata(String checkBillingCno,String checkBillingPayamount,String checkBillingBid) throws Exception{
            Gson gson = new Gson();
            List<Map<String,Object>> ListtranlogCheckdata = tranlogMapper.getListGroupByTranlogCid(checkBillingCno);
            if(!ListtranlogCheckdata.isEmpty()){
                tranlogCheckdata = ListtranlogCheckdata.get(0);
                contract  = tranlogCheckdata.get("CONTRACT").toString();
                checkTranlogPayamount = tranlogCheckdata.get("PAYAMOUNT").toString();
                log.info("合同号：{}在流水表核账金额：{}",contract,checkTranlogPayamount);

            if(checkTranlogPayamount.equals(checkBillingPayamount)){
             log.info("合同号：{}核账成功",checkBillingCno);
             log.info("==========发送请求给住建局，应收单据回调接口开始==========");
//             String returnbillingurl = "http://127.0.0.1:8082/api/billing/receivable/callback";
             String returnbillingurl = "http://22.36.12.109:20089/api/bank-qyba-biz-service/billing/receivable/callback";
             Map<String,String> returnbillingheaders = new HashMap<String,String>();
             returnbillingheaders.put("x-app-auth","dlfcApponI4vHNN");
             returnbillingheaders.put("x-auth-token",a.TOKEN);
             log.info("POST请求头{}",returnbillingheaders);
                //根据合同号，从tranlog数据库中查询到支付时间，业务流水号
             List<Tranlog> tranlog = tranlogMapper.getListByTranlogCid(checkBillingCno);
             log.info("流水表取得的数据{}",tranlog);
             Map<String,String> returnbillingbody = new HashMap<String,String>();
             returnbillingbody.put("bid",checkBillingBid);
             returnbillingbody.put("payStatus","1");
             returnbillingbody.put("payTime",tranlog.get(0).getPaytime());
             returnbillingbody.put("serialNumber",tranlog.get(0).getSerialnumber());
             returnbillingbody.put("remark",null);
             log.info("POST请求体{}",returnbillingbody);
             //调用应收单据回调接口
             String result = HttpClientUtil.post(returnbillingurl,returnbillingheaders,returnbillingbody);
             Map<String,Object> returnMap = new HashMap<String,Object>();
             returnMap = gson.fromJson(result,returnMap.getClass());
             log.info(returnMap.toString());
             //住建局返回格式{"status": ,"message": ,"data" }
                String status = String.valueOf(returnMap.get("status"));
                log.info("住建局返回内容status字段：{}",status);
             if(status.equals("200.0")) {
                 billingMapper.updateBillingStatus(checkBillingCno);
                 log.info("应收单据表合同号：{}状态已修改", checkBillingCno);
                 tranlogMapper.updateTranlogStatus(checkBillingCno);
                 log.info("流水表合同号：{}状态已修改", checkBillingCno);
             }
            }else{
                log.info("合同号：{}核账失败",checkBillingCno);
            }
            }
        }

}
