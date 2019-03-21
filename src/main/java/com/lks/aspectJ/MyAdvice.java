package com.lks.aspectJ;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by likaisong on 2019/3/19.
 */
public class MyAdvice {

    public void before(){
        System.out.println("xml方式 装配 before .....");
    }

    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("xml方式 装配 around 前置.....");
        try {
            joinPoint.proceed();
            System.out.println("xml方式 装配 around 后置.....");
        } catch (Throwable throwable) {
            System.out.println("xml方式 装配 around 异常.....");
            throwable.printStackTrace();
        }finally {
            System.out.println("xml方式 装配 around 最终.....");
        }
    }
}