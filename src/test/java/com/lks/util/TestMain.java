package com.lks.util;

import com.lks.bean.User;
import com.lks.processor.service.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Test
    public void testMyBeanFactory() throws Exception {
        //注册bean
        BeanDefined beanDefined = new BeanDefined();
        beanDefined.setBeanId("user");
        beanDefined.setClassPath("com.lks.bean.User");
        beanDefined.setBeanScope("prototype");
        beanDefined.setFactoryBean("myBeanFactory");
        beanDefined.setFactoryMethod("getInstance");

        //注册自定工厂，类似Spring中在配置文件中注册自定义工厂
        BeanDefined myBeanFactory = new BeanDefined();
        myBeanFactory.setBeanId("myBeanFactory");
        myBeanFactory.setClassPath("com.lks.util.MyBeanFactory");

        List<BeanDefined> beanDefineds = new ArrayList<BeanDefined>(16);
        beanDefineds.add(myBeanFactory);
        beanDefineds.add(beanDefined);

        //声明BeanFactory，类似于Spring中的ApplicationContext
        BeanFactory factory = new BeanFactory(beanDefineds);

        //实际调用
        User user = (User) factory.getFactoryBean("user");
        System.out.println(user);
        User user2 = (User) factory.getFactoryBean("user");
        System.out.println(user2);
    }

    @Test
    public void testBeanPostProcessor() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("spring_config.xml");
        BaseService service = (BaseService) factory.getBean("myService");
        System.out.println(service.doSomething());
    }

    @Test
    public void testMyBeanPostProcessor() throws Exception {
        //注册bean
        BeanDefined beanDefined = new BeanDefined();
        beanDefined.setBeanId("myService");
        beanDefined.setClassPath("com.lks.processor.impl.MyService");

        //注册BeanPostProcessor，类似Spring配置文件配置BeanPostProcessor
        BeanDefined beanObj1 = new BeanDefined();
        beanObj1.setClassPath("com.lks.processor.processor.MyBeanPostProcessor");
        List<BeanDefined> configuration = new ArrayList<BeanDefined>();
        configuration.add(beanDefined);
        configuration.add(beanObj1);
        // 2、声明一个BeanFactory，类似于Spring中的ApplicationContext
        BeanFactory factory = new BeanFactory(configuration);
        // 3、开发人员向BeanFactory索要实例对象
        BaseService b = (BaseService) factory.getBeanPostProcessor("myService");
        System.out.println("b =" + b);
        System.out.println("b方法 =" + b.doSomething());
    }

    @Test
    public void testPropertyMap() throws Exception {
        //注册bean
        BeanDefined beanObj = new BeanDefined();
        beanObj.setBeanId("user");
        beanObj.setClassPath("com.lks.bean.User");

        //设置propertyMap 类似Spring中依赖注入属性值
        Map<String, String> propertyMap = beanObj.getPropertyMap();
        propertyMap.put("name", "萧峰");
        propertyMap.put("age", "31");
        propertyMap.put("sex", "男");

        List<BeanDefined> beanDefineds = new ArrayList<>(16);
        beanDefineds.add(beanObj);

        //声明BeanFactory，类似于Spring中的ApplicationContext
        BeanFactory factory = new BeanFactory(beanDefineds);

        //实际调用
        User user = (User) factory.getPropertyBean("user");
        System.out.println(user.toString());
    }

    @Test
    public void testAdviceAop() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        com.lks.aop.joincut.BaseService personProxy = (com.lks.aop.joincut.BaseService) context.getBean("personProxy");
        personProxy.eat();
        personProxy.wc();
    }

    @Test
    public void testAdvisorAop() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        com.lks.aop.joincut.BaseService personProxy = (com.lks.aop.joincut.BaseService) context.getBean("personProxyAdisor");
        personProxy.eat();
        personProxy.wc();
    }

}
