package com.cch.demo.account.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cch.demo.account.entity.Billing;
import com.cch.demo.account.entity.Tranlog;
import com.cch.demo.global.service.GlobalService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BillingService extends GlobalService<Billing> {
    @Transactional
    Billing addBilling(Billing billing);
    List<Map<String,Object>> getListByBillingCid(String cid);
    List<Tranlog> getListByTranlogCid(String cid);
    List<Map<String,Object>> getListByBillingCidBid(String cid,String bid);

}

