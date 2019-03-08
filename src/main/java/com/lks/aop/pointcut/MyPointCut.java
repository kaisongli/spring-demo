package com.lks.aop.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

/**
 * Created by likaisong on 2019/3/6.
 */
public class MyPointCut implements Pointcut{
    // 使用依赖注入，需要提供 setter() 方法
    private ClassFilter classFilter;

    private MethodMatcher methodMatcher;

    public void setClassFilter(ClassFilter classFilter) {
        this.classFilter = classFilter;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    @Override
    public ClassFilter getClassFilter() {
        return classFilter;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }
}
