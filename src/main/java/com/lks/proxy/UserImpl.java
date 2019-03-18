package com.lks.proxy;

/**
 * Created by likaisong on 2019/3/18.
 */
public class UserImpl implements MySqlSession {
    @Override
    public void update(String sql) {
        System.out.println(".....update......" + sql);
    }
}
