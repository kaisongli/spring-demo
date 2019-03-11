package com.lks.util;

import com.lks.processor.impl.MyService;
import com.lks.processor.service.BaseService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by likaisong on 2019/3/11.
 */
public class MyProxy {
    public static BaseService getMyServiceProxyByCglib(BaseService baseService){
        //创建增强器
        Enhancer enhancer = new Enhancer();
        //设置增强类对象
        enhancer.setSuperclass(MyService.class);
        //设置回调方法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("增强之前。。。。。");
                Object obj =  methodProxy.invokeSuper(o, objects);
                System.out.println("增强之后。。。。。");
                return obj;
            }
        });
        BaseService service = (BaseService) enhancer.create();
        return service;
    }
}
