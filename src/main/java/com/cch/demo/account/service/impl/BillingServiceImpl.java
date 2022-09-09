package com.cch.demo.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cch.demo.account.entity.Billing;
import com.cch.demo.account.entity.Tranlog;
import com.cch.demo.account.mapper.BillingMapper;
import com.cch.demo.account.mapper.TranlogMapper;
import com.cch.demo.account.service.BillingService;
import com.cch.demo.global.service.impl.GlobalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BillingServiceImpl extends GlobalServiceImpl<BillingMapper, Billing> implements BillingService {

    @Override
    @Transactional
    public Billing addBilling(Billing billing) {
        // 比對
        super.save(billing);
        return billing;
    }

    @Resource
    private BillingMapper billingMapper;
        @Override
        public List<Map<String,Object>> getListByBillingCid(String cid) {

            return billingMapper.getListByBillingCid(cid);
        }
        public List<Map<String,Object>> getListByBillingCidBid(String cid,String bid){
            return billingMapper.getListByBillingCidBid(cid,bid);
        }
    @Resource
    private TranlogMapper tranlogMapper;
    @Override
        public List<Tranlog>getListByTranlogCid(String cid){
            String a  = cid;
            System.out.println("-----------------------------");
            return tranlogMapper.getListByTranlogCid(a);
        }

}
