package com.lks.util;

import com.lks.processor.processor.BeanPostProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

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
                //属性注入
                setValue(instance, Class.forName(bean.getClassPath()), bean.getPropertyMap());
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

    /**
     * 获取后置对象
     * @param beanId
     * @return
     * @throws Exception
     */
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

    /**
     * 新增属性注入
     * @param beanId
     * @return
     * @throws Exception
     */
    public Object getPropertyBean(String beanId) throws Exception{
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
                //属性值注入
                setValue(proxyObj, Class.forName(bean.getClassPath()), bean.getPropertyMap());
                return proxyObj;
            }else {
                //属性值注入
                setValue(instance, Class.forName(bean.getClassPath()), bean.getPropertyMap());
                return instance;
            }
        }
        return null;
    }

    private void setValue(Object instance, Class beanField, Map<String, String> propertyMap) throws Exception {
        //获取所有的方法
        Method[] methods = beanField.getDeclaredMethods();
        //对properMap进行解析，获取键值对，也就是需要注入的属性和属性值
        Set<String> propertySet = propertyMap.keySet();
        Iterator<String> propertyIterator = propertySet.iterator();
        while (propertyIterator.hasNext()){
            String propertyKey = propertyIterator.next();
            String propertyValue = propertyMap.get(propertyKey);
            //获取的属性
            Field field = beanField.getDeclaredField(propertyKey);
            //属性对应的set方法
            String methodName = "set" + field.getName();
            //对方法进行过滤
            for (int i = 0; i < methods.length; i ++){
                Method method = methods[i];
                if (methodName.equalsIgnoreCase(method.getName())){
                    //判断set方法的类型 也就是属性的类型
                    Class fieldType = field.getType();
                    if (fieldType == String.class){
                        method.invoke(instance, propertyValue);
                    } else if (fieldType == Integer.class){
                        method.invoke(instance, Integer.valueOf(propertyValue));
                        //这里只对List类型做判断
                    } else if (fieldType == List.class){
                        List<String> tmpList = new ArrayList<>(16);
                        String[] itemArray = propertyValue.split(",");
                        for (int j = 0; j < itemArray.length; j ++){
                            tmpList.add(itemArray[i]);
                        }
                    }else {
                        throw new RuntimeException("暂不该支持属性类型注入");
                    }
                }
            }
        }
    }
}
