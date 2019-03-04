package com.lks.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likaisong on 2019/3/3.
 */
public class BeanFactory {
    //存放bean集合
    private List<BeanDefined> beanDefinedList;

    private Map<String, Object> springIoc;

    public BeanFactory(){

    }

    public BeanFactory(List<BeanDefined> beanDefinedList) throws Exception {
        this.beanDefinedList = beanDefinedList;
        springIoc = new HashMap<String, Object>(16);
        for (BeanDefined bean: beanDefinedList){
            if ("singleton".equals(bean.getBeanScope())){
                String classPath = bean.getClassPath();
                Object instance = Class.forName(classPath).newInstance();
                springIoc.put(bean.getBeanId(), instance);
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
}
