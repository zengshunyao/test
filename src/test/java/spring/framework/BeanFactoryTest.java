package spring.framework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class BeanFactoryTest {
    public static void main(String[] args) {
//        System.out.println(String.class.isAssignableFrom(Object.class));
//        System.out.println(Object.class.isAssignableFrom(String.class));
//        System.out.println(Number.class.isAssignableFrom(new Integer(1).getClass()));
//        System.out.println(Number.class.isInstance(4));

        //BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        //beanFactory.getBean("scheduler");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        beanFactory.getBean("schedulerFactoryBean");

        applicationContext.getBean("scheduler");
    }
}
