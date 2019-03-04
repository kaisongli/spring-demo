package com.lks.util;

import com.lks.processor.processor.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likaisong on 2019/3/3.
 */
public class BeanFactory {
    //存放bean集合
    private List<BeanDefined> beanDefinedList;

    //存放实例化对象
    private Map<String, Object> springIoc;

    //beanPostProcessor属性
    private BeanPostProcessor beanPostProcessor;

    public BeanFactory(){

    }

    public BeanFactory(List<BeanDefined> beanDefinedList) throws Exception {
        this.beanDefinedList = beanDefinedList;
        springIoc = new HashMap<>(16);
        for (BeanDefined bean: beanDefinedList){
            if ("singleton".equals(bean.getBeanScope())){
                String classPath = bean.getClassPath();
                Object instance = Class.forName(classPath).newInstance();
                //判断后置对象还是普通的bean
                isProcessor(instance, classPath);
                springIoc.put(bean.getBeanId(), instance);
            }
        }
    }

    /**
     * @param instance
     * @param classPath
     */
    private void isProcessor(Object instance, String classPath) {
        Class[] interfaces = instance.getClass().getInterfaces();
        if (interfaces == null){
            return;
        }
        for (Class c : interfaces){
            if (c == BeanPostProcessor.class){
                beanPostProcessor = (BeanPostProcessor) instance;
            }
        }
    }

    public List<BeanDefined> getBeanDefinedList() {
        return beanDefinedList;
    }

    public void setBeanDefinedList(List<BeanDefined> beanDefinedList) {
        this.beanDefinedList = beanDefinedList;
    }

    /**
     *  通过反射实例化对象
     * @param beanId
     * @return
     * @throws Exception
     */
    public Object getBean(String beanId) throws Exception {
        Object instance = null;
        for (BeanDefined bean : beanDefinedList){
            if (beanId.equals(bean.getBeanId())){
                String classPath = bean.getClassPath();
                instance = Class.forName(classPath).newInstance();
                return instance;
            }
        }
        return instance;
    }

    /**
     * 根据scope属性获取实例化对象
     * @param beanId
     * @return
     * @throws Exception
     */
    public Object getScopeBean(String beanId) throws Exception {
        Object instance = null;
        for (BeanDefined bean: beanDefinedList){
            if (beanId.equals(bean.getBeanId())){
                String classPath = bean.getClassPath();
                if ("singleton".equals(bean.getBeanScope())){
                    instance = springIoc.get(beanId);
                } else {
                    instance = Class.forName(classPath).newInstance();
                }
            }
        }
        return instance;
    }

    /**
     * 动态工厂
     * @param beanId
     * @return
     * @throws Exception
     */
    public Object getFactoryBean(String beanId) throws Exception{
        Object instance = null;
        for (BeanDefined bean: beanDefinedList){
            if (beanId.equals(bean.getBeanId())){
                String classPath = bean.getClassPath();
                if ("singleton".equals(bean.getBeanScope())){
                    instance = springIoc.get(beanId);
                } else {
                    if (bean.getFactoryBean() != null && bean.getFactoryMethod() != null){
                        //实例化自定义工厂
                        Object factoryObject = springIoc.get(bean.getFactoryBean());
                        //调用对象实例化方法
                        Method method = factoryObject.getClass().getDeclaredMethod(bean.getFactoryMethod(), null);
                        method.setAccessible(true);
                        instance = method.invoke(factoryObject, null);
                    } else {
                        instance = Class.forName(classPath).newInstance();
                    }
                }
            }
        }
        return instance;
    }

    public Object getBeanPostProcessor(String beanId) throws Exception{
        Object instance = null;
        for (BeanDefined bean : beanDefinedList){
            if (beanId.equals(beanId)){
                if ("singleton".equals(bean.getBeanScope())){
                    instance = springIoc.get(beanId);
                }else {
                    if (bean.getFactoryBean() != null && bean.getFactoryMethod() != null){
                        Object factory = springIoc.get(bean.getFactoryBean());
                        Method method = factory.getClass().getDeclaredMethod(bean.getFactoryMethod(), null);
                        method.setAccessible(true);
                        instance = method.invoke(factory, null);
                    } else {
                        instance = Class.forName(bean.getClassPath()).newInstance();
                    }
                }
            }
            // 当前实例对象的代理监控对象
            Object proxyObj = null;
            //判断beanPostProcessor属性
            if (beanPostProcessor != null){
                proxyObj = beanPostProcessor.postProcessBeforeInitialization(instance, beanId);

                proxyObj = beanPostProcessor.postProcessAfterInitialization(instance, beanId);
                return proxyObj;
            }else {
                return instance;
            }
        }
        return null;
    }
}
