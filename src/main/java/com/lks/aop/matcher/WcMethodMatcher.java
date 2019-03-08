package com.lks.aop.matcher;

import org.springframework.aop.MethodMatcher;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * Created by likaisong on 2019/3/6.
 */
public class WcMethodMatcher implements MethodMatcher{
    @Override
    public boolean matches(Method method, @Nullable Class<?> aClass) {
        String methodName = method.getName();
        if ("wc".equals(methodName)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, @Nullable Class<?> aClass, Object... objects) {
        return false;
    }
}