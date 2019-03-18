package com.lks.serviceImpl;

import com.lks.dao.AccountDao;
import com.lks.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by likaisong on 2019/3/12.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Resource
    private AccountDao accountDao;
    @Override
    public void transfer(String fromName, String toName, double money) {
        // 先查询from账户的钱
        double fromMoney = accountDao.queryMoney(fromName);
        System.out.println("操作前 husband'money: " + accountDao.queryMoney(fromName));
        // 对from账户进行扣钱操作
        accountDao.update(fromName, fromMoney - money);

        System.out.println("操作后 husband'money: " + accountDao.queryMoney(fromName));

        //手动制造异常
        System.out.println(1 / 0);
        // 先查询to账户的钱
        double toMoney = accountDao.queryMoney(toName);
        System.out.println("操作前 wife'money: " + accountDao.queryMoney(toName));
        // 对to账户进行加钱操作
        accountDao.update(toName, toMoney + money);

        System.out.println("操作后 wife'money: " + accountDao.queryMoney(toName));
    }
}
