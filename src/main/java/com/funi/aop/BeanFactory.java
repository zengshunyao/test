package com.funi.aop;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class BeanFactory {

    public Properties properties = new Properties();

    public BeanFactory(InputStream inputStream) {
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name) {
        String className = properties.getProperty(name);
        Object bean = null;
        try {
            //获取ProxyFactoryBean的class的对象
            Class clazz = Class.forName(className);
            bean = clazz.getConstructor(null).newInstance(null);

            //根据对象实例化target,advice对象
            Object target = Class.forName(properties.getProperty(name + ".target")).getConstructor(null).newInstance(null);
            Object advice = Class.forName(properties.getProperty(name + ".advice")).getConstructor(null).newInstance(null);

            //通过内省实现对ProxyFactoryBean的属性赋值
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String propertyName = propertyDescriptor.getName();
                Method writeMethod = propertyDescriptor.getWriteMethod();
                //写入对象
                if ("target".equals(propertyName)) {
                    writeMethod.invoke(bean, target);
                } else if ("advice".equals(propertyName)) {
                    writeMethod.invoke(bean, advice);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
