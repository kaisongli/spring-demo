package com.lks.util;

import com.lks.bean.User;

/**
 * Created by likaisong on 2019/3/4.
 */
public class MyBeanFactory {

    public User getInstance(){
        User user = new User();
        System.out.println("调用自定义的BeanFactory");
        return user;
    }
}
