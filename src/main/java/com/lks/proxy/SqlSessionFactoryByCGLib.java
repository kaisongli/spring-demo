package com.lks.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by likaisong on 2019/3/18.
 */
public class SqlSessionFactoryByCGLib {
    public static MySqlSession getSqlSessionByCGLib(MySqlSession session){
        //创建增强器
        Enhancer enhancer = new Enhancer();
        //设置增强对象
        enhancer.setSuperclass(session.getClass());
        //设置回调方法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                init();
                Object obj = methodProxy.invokeSuper(o, objects);
                end();
                return obj;
            }
        });
        MySqlSession $Proxy = (MySqlSession) enhancer.create();
        return $Proxy;
    }

    private static void init(){
        System.out.println("连接数据库.........");
    }

    private static void end(){
        System.out.println("关闭数据库........");
    }
}
