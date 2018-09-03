package com.funi.distributedcomputer.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/8/21 9:54
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class RedisLock {
    /**
     * @param key     key
     * @param timeout 秒数seconds
     * @return
     */
    public String getLock(String key, int timeout) {
        try {
            Jedis jedis = RedisManager.getJedis();

            String value = UUID.randomUUID().toString();

            long end = System.currentTimeMillis() + timeout;

            while (System.currentTimeMillis() < end) {
                //阻塞
                if (1 == jedis.setnx(key, value)) {
                    jedis.expire(key, timeout);
                    //锁设置成功,redis操作成功
                    return value;
                }

                //检测过期时间
                if (jedis.ttl(key) == timeout) {
                    jedis.expire(key, timeout);
                }

                //睡1秒
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public boolean releaseLock(String key, String value) {
        try {
            Jedis jedis = RedisManager.getJedis();
            while (true) {
                jedis.watch(key);
                if (value.equals(jedis.get(key))) {
                    Transaction transaction = jedis.multi();
                    transaction.del(key);
                    List<Object> queue = transaction.exec();
                    if (queue == null) {
                        continue;
                    }
                    return false;
                }
                jedis.unwatch();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {

        RedisLock lock = new RedisLock();
        String lockKey = "lock:aaa";
        String lockId = lock.getLock(lockKey, 1000);
        if (lockId != null) {
            System.out.println("获得锁成功1");
        } else {
            System.out.println("失败1");
        }

        String lockId2 = lock.getLock(lockKey, 1000);
        if (lockId2 != null) {
            System.out.println("获得锁成功2");
        } else {
            System.out.println("失败2");
        }
    }
}
