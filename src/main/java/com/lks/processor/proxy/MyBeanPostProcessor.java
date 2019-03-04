package com.lks.processor.proxy;

import com.lks.processor.impl.MyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by likaisong on 2019/3/4.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化之前。。。。postProcessBeforeInitialization");
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        System.out.println("初始化后。。。。postProcessAfterInitialization");
        //只代理自定义的service
        if (bean.getClass() == MyService.class) {
            Proxy proxy = (Proxy) Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println(method + "方法被拦截。。。。invoke");
                    String result = (String) method.invoke(bean, args);
                    return result.toUpperCase();
                }
            });
            return proxy;
        }
        return bean;
    }
}
