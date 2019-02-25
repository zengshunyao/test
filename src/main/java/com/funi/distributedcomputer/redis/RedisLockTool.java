package com.funi.distributedcomputer.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**********************************************************************
 * &lt;p&gt;文件名：RedisTool.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：utf-8-demo
 * @author zengshunyao
 * @date 2019/1/10 12:15
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class RedisLockTool {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final int SPIN_TIMES = 100;

    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
//        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//        if (LOCK_SUCCESS.equals(result)) {
//            return true;
//        }
//        return false;
        //自旋
        String result = null;
        int count = 0;
        long end = System.currentTimeMillis() + expireTime;
        try {
            while (true) {
                if (System.currentTimeMillis() < end) {
                    result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                    if (LOCK_SUCCESS.equals(result)) {
                        return true;
                    }
                } else {
                    return false;
                }
                //睡1秒
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

//        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
//        if (RELEASE_SUCCESS.equals(result)) {
//            return true;
//        }
//        return false;

        // 自旋
        Object result = null;
        int count = 0;
        while (true) {
            if (count++ < SPIN_TIMES) {
                result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
                if (RELEASE_SUCCESS.equals(result)) {
                    return true;
                }
            } else {
                return false;
            }
        }
    }
}
