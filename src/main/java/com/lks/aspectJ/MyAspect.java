package com.lks.aspectJ;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by likaisong on 2019/3/19.
 */
@Component("myAspect")
@Aspect
public class MyAspect {
    @Before(value = "execution(void com..UserServiceImpl.query(*))")
    public void before(){
        System.out.println("注解方式 before..........");
    }
}
