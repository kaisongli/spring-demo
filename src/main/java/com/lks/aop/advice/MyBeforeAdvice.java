package com.lks.aop.advice;


import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * Created by likaisong on 2019/3/5.
 */
public class MyBeforeAdvice implements MethodBeforeAdvice{
    // 切面，对应代理模式中的次要业务
    @Override
    public void before(Method method, Object[] objects, @Nullable Object o) throws Throwable {
        System.out.println("....洗手....");
    }
}
