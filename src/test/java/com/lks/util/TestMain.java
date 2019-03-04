package com.lks.util;

import com.lks.bean.User;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaisong on 2019/3/3.
 */
public class TestMain {
    @Test
    public void testBeanFactory() throws Exception {
        //注册bean
        BeanDefined beanDefined = new BeanDefined();
        beanDefined.setBeanId("user");
        beanDefined.setClassPath("com.lks.bean.User");
        List<BeanDefined> beanDefineds = new ArrayList<BeanDefined>(16);
        beanDefineds.add(beanDefined);

        //声明BeanFactory，类似于Spring中的ApplicationContext
        BeanFactory factory = new BeanFactory();
        factory.setBeanDefinedList(beanDefineds);

        //实际调用
        User user = (User) factory.getBean("user");
        System.out.println(user);
    }

    @Test
    public void testScopeBeanFactory() throws Exception {
        //注册bean
        BeanDefined beanDefined = new BeanDefined();
        beanDefined.setBeanId("user");
        beanDefined.setClassPath("com.lks.bean.User");
        beanDefined.setBeanScope("prototype");
        List<BeanDefined> beanDefineds = new ArrayList<BeanDefined>(16);
        beanDefineds.add(beanDefined);

        //声明BeanFactory，类似于Spring中的ApplicationContext
        BeanFactory factory = new BeanFactory(beanDefineds);

        //实际调用
        User user = (User) factory.getScopeBean("user");
        System.out.println(user);
        User user2 = (User) factory.getScopeBean("user");
        System.out.println(user2);
    }
}
