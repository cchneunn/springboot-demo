package com.cch.demo.account.mapper;

import com.cch.demo.account.entity.Account;
import com.cch.demo.account.entity.Afmmoniaccinfo;
import com.cch.demo.global.mapper.GlobalMapper;

import java.util.List;
import java.util.Map;


public interface AfmmoniaccinfoMapper {
    List<Map<String,Object>> getListByAfmmoniaccinfoAccsignstate(String MONIACCNO);
}
