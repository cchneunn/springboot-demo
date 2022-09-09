package com.cch.demo.account.service;

import com.cch.demo.account.entity.Account;
import com.cch.demo.global.service.GlobalService;

import java.util.List;
import java.util.Map;

public interface AccountService extends GlobalService<Account> {
    Account addAccount(Account account);
    List<Map<String,Object>> getListByAccountSpecialAccountno(String specialaccountno);
    List<Map<String,Object>> getListByAfmmoniaccinfoAccsignstate(String MONIACCNO);
}
