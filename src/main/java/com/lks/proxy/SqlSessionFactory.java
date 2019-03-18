package com.lks.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by likaisong on 2019/3/18.
 */
public class SqlSessionFactory {
    public static MySqlSession getSqlSession(MySqlSession session){
        MySqlSession $Proxy = (MySqlSession) Proxy.newProxyInstance(session.getClass().getClassLoader(), session.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                init();
                Object obj = method.invoke(session, args);
                end();
                return obj;
            }
        });
        return $Proxy;
    }

    private static void init(){
        System.out.println("连接数据库.........");
    }

    private static void end(){
        System.out.println("关闭数据库........");
    }
}
