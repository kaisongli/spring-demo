package com.lks.processor.processor;

/**
 * Created by likaisong on 2019/3/4.
 */
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}

