package com.cch.demo.account.service.impl;

import com.cch.demo.account.entity.Account;
import com.cch.demo.account.mapper.AccountMapper;
import com.cch.demo.account.mapper.AfmmoniaccinfoMapper;
import com.cch.demo.account.service.AccountService;
import com.cch.demo.global.service.impl.GlobalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl extends GlobalServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AfmmoniaccinfoMapper afmmoniaccinfoMapper;
    @Override
    @Transactional
    public Account addAccount(Account account) {

        // 比對
        super.save(account);
        return account;
    }
    public List<Map<String,Object>> getListByAccountSpecialAccountno(String specialaccountno){
        return accountMapper.getListByAccountSpecialAccountno(specialaccountno);
    }
    public List<Map<String,Object>> getListByAfmmoniaccinfoAccsignstate(String MONIACCNO){
        return afmmoniaccinfoMapper.getListByAfmmoniaccinfoAccsignstate(MONIACCNO);
    }
}
