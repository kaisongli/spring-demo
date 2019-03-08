package com.lks.aop.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * Created by likaisong on 2019/3/6.
 */
public class MyAfterAdvice implements AfterReturningAdvice{
    // 切面，对应代理模式中的次要业务
    @Override
    public void afterReturning(@Nullable Object o, Method method, Object[] objects, @Nullable Object o1) throws Throwable {
        System.out.println("....洗手....");
    }
}
