package com.cch.demo.account.scheduled;
import com.cch.demo.account.mapper.BillingMapper;
import com.cch.demo.account.mapper.TranlogMapper;
import com.cch.demo.account.service.BillingService;
import com.cch.demo.account.service.ScheduledService;

import com.cch.demo.account.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledTask {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Resource
    ScheduledService scheduledService;
    @Resource
    private BillingMapper billingMapper;
    @Resource
    private TranlogMapper tranlogMapper;
    @Scheduled(cron="0/20 * * * * ? ")
    public void getToken() {
        log.info("==========发送请求给住建局,开始取token=========");
        try {
 //          String gettokenurl = "http://127.0.0.1:8082/api/open-saas-biz-service/open/getToken";
            String gettokenurl = "http://22.36.12.109:20089/api/open-saas-biz-service/open/getToken";
            Map<String,String> gettokenbody = new HashMap<String,String>();
            gettokenbody.put("appId","dlfcApponI4vHNN");
            gettokenbody.put("appSecret","8c038113a75f428cb402cbe44be51259");
            String result = HttpClientUtil.post(gettokenurl,gettokenbody);

            log.info("TOKEN:{}",result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    @Scheduled(cron="0/60 * * * * ? ")
    public void insertIntoTranlog() {
        try {
            log.info("==========开始每日导入Tranlog流水表=========");
            tranlogMapper.insertTranlog();
            log.info("==========开始每日导入Afmmoniaccinfo表=========");
            tranlogMapper.insertAfmmoniaccinfo();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
    @Scheduled(cron="0/60 * * * * ? ")
    public void scheduledTask() {
        log.info("==========入金核账开始=========");
        try {
//二期加上没有被取消的查询订单
            List<Map<String, Object>> checkBillingContract  = billingMapper.getListByBillingStatus();

            log.info("Billing应收单据表核账失败的记录：{}",checkBillingContract);
            if(!checkBillingContract.isEmpty()) {
                for (int i = 0; i < checkBillingContract.size(); i++) {
                    String checkBillingCno = checkBillingContract.get(i).get("CNO").toString();
                    String checkBillingPayamount = checkBillingContract.get(i).get("PAYMENTAMOUNT").toString();
                    String checkBillingBid = checkBillingContract.get(i).get("BID").toString();
                    log.info("第{}条应收单据核账开始,合同备案号：{},支付金额：{},账单号：{}",i,checkBillingCno,checkBillingPayamount,checkBillingBid);
                    scheduledService.checkdata(checkBillingCno,checkBillingPayamount,checkBillingBid);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}

