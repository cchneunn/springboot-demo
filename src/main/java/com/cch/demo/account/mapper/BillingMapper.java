package com.cch.demo.account.mapper;

import com.cch.demo.account.entity.Billing;
import com.cch.demo.global.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface BillingMapper extends GlobalMapper<Billing> {
//    Map<String,Object> findTranlogByCid(String cid);
      List<Map<String,Object>> getListByBillingCid(String cid);
      void updateBillingStatus(String cid);
      List<Map<String,Object>> getListByBillingStatus();
      List<Map<String,Object>> getListByBillingCidBid(String cid,String bid);

}
