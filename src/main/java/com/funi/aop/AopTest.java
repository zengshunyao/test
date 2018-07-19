package com.funi.aop;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AopTest {

    @Test
    public void test() {

        //读取配置文件
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("E:\\ZSY\\IdeaProjects\\test\\src\\test\\java\\com\\funi\\aop\\bean.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BeanFactory beanFactory = new BeanFactory(inputStream);

        ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) beanFactory.getBean("bean");

        Service service = (Service) proxyFactoryBean.getProxy();
        service.add();
    }
}
