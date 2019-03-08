package com.lks.aop.filter;

import com.lks.aop.pointcut.Person;
import org.springframework.aop.ClassFilter;

/**
 * Created by likaisong on 2019/3/5.
 */
public class MyClassFilter implements ClassFilter{
    @Override
    public boolean matches(Class<?> aClass) {
        if (aClass == Person.class) {
            // 告诉顾问，当前类是需要我们提供织入服务的
            return true;
        }
        return false;
    }
}
