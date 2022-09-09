package com.cch.demo.account.mapper;

import com.cch.demo.account.entity.Tranlog;
import com.cch.demo.global.mapper.GlobalMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface TranlogMapper extends GlobalMapper<Tranlog> {

      List<Tranlog> getListByTranlogCid(String cid);
      List<Map<String,Object>> getListGroupByTranlogCid(String cid);
      void updateTranlogStatus(String cid);
      void insertTranlog();
      void insertAfmmoniaccinfo();
}
