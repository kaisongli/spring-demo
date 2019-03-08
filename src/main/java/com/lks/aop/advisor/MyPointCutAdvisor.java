package com.lks.aop.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * Created by likaisong on 2019/3/6.
 */
public class MyPointCutAdvisor implements PointcutAdvisor{
    // 次要业务以及次要业务与主要业务执行顺序
    private Advice advice;
    // 当前拦截对象和对象调用主要业务方法 Person 和 .eat() 方法
    private Pointcut pointcut;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
