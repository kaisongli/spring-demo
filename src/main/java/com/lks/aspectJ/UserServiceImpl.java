package com.lks.aspectJ;

/**
 * Created by likaisong on 2019/3/19.
 */
public class UserServiceImpl implements UserService{
    @Override
    public void query(String sql) {
        System.out.println(".......query........" + sql);
    }
}
