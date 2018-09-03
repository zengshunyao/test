package com.funi.distributedcomputer.redis;

import redis.clients.jedis.Jedis;

import java.util.LinkedList;
import java.util.List;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/8/21 14:40
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class LuaDemo {


    public static void main(String[] args) {
        String lua = "local times=redis.call('incr',KEYS[1]);\n" +
                "if times == 1 then\n" +
                "    redis.call('expire',KEYS[1],ARGV[1]);\n" +
                "end\n" +
                "\n" +
                "if times >tonumber( ARGV[2] ) then \n" +
                "return 0;\n" +
                "end\n" +
                "\n" +
                "return 1;";

        Jedis jedis = null;

        try {
            jedis = RedisManager.getJedis();

            List<String> keys = new LinkedList<String>();
            keys.add("key");

            List<String> argvs = new LinkedList<String>();
            argvs.add("value");



            //1.第一种;存在sha
            String sha = null;
            if (!jedis.scriptExists(sha)) {
                sha = jedis.scriptLoad(lua);
            }
            jedis.evalsha(sha, keys, argvs);



            //2.第二种;不用sha
//            jedis.eval(lua, keys, argvs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
