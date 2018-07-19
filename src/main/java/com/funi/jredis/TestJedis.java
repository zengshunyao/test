package com.funi.jredis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedis {
    /**
     * 测试jedis的普通方式
     */
    @Test
    public void test() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("jediskey", "zt100kg");
        jedis.close();
    }

    /**
     * 测试连接池
     */
    @Test
    public void test2() {
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();//通过连接池获取
        String value = jedis.get("jediskey");
        System.err.println(value);
        jedis.close();//放回连接池
        jedisPool.close();//关闭连接池
    }

    /**
     * 测试spring
     */
    @Test
    public void test3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        JedisPool jedisPool = applicationContext.getBean("jedisPool", JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get("jediskey");
        System.err.println(value);
        //jedis.close();//放回连接池
    }

    /**
     * 自定义bean
     */
    @Test
    public void test4() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        JedisHandler jedisHandler = applicationContext.getBean(JedisHandler.class);
        String value = jedisHandler.get("jediskey");
        System.err.println(value);
        //jedis.close();//放回连接池
    }
}
