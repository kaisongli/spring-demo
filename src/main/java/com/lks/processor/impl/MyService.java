package com.lks.processor.impl;

import com.lks.processor.service.BaseService;

/**
 * Created by likaisong on 2019/3/4.
 */
public class MyService implements BaseService{
    @Override
    public String doSomething() {
        return "doSomething";
    }

    @Override
    public String eat() {
        return "eat food";
    }

    @Override
    public void testProxy() {
        System.out.println("test proxy .....");
    }
}
