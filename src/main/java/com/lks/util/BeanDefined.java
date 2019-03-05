package com.lks.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by likaisong on 2019/3/3.
 */
public class BeanDefined {

    private String beanId;

    private String classPath;

    //和Spring一样，默认是单例
    private String beanScope = "singleton";

    //自定义工厂类
    private String factoryBean;

    //自定义工厂方法
    private String factoryMethod;

    //存放属性键值对
    private Map<String, String> propertyMap = new HashMap<>(16);

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Map<String, String> getPropertyMap() {

        return propertyMap;
    }

    public void setFactoryBean(String factoryBean) {
        this.factoryBean = factoryBean;
    }

    public void setFactoryMethod(String factoryMethod) {
        this.factoryMethod = factoryMethod;
    }

    public String getFactoryBean() {

        return factoryBean;
    }

    public String getFactoryMethod() {
        return factoryMethod;
    }

    public void setBeanScope(String beanScope) {
        this.beanScope = beanScope;
    }

    public String getBeanScope() {

        return beanScope;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getBeanId() {

        return beanId;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }
}
