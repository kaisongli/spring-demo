package com.lks.dao;

/**
 * Created by likaisong on 2019/3/12.
 */
public interface AccountDao {
    void update(String name, double money);

    double queryMoney(String name);
}
