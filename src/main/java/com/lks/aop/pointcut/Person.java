package com.lks.aop.pointcut;

import com.lks.aop.joincut.BaseService;

/**
 * Created by likaisong on 2019/3/5.
 */
public class Person implements BaseService{
    //切入点
    @Override
    public void eat() {
        System.out.println("....吃饭....");
    }

    //切入点
    @Override
    public void wc() {
        System.out.println("....wc....");
    }
}
