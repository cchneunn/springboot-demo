package com.cch.demo.account.mapper;

import com.cch.demo.account.entity.Account;
import com.cch.demo.global.mapper.GlobalMapper;

import java.util.List;
import java.util.Map;


public interface AccountMapper extends GlobalMapper<Account> {
    List<Map<String,Object>> getListByAccountSpecialAccountno(String specialaccountno);
    void updateAccountActivation(String specialaccountno);
}
