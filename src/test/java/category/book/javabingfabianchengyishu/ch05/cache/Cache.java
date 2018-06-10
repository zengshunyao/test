package category.book.javabingfabianchengyishu.ch05.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/12 16:29
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class Cache {

    static Map<String, Object> map = new HashMap<String, Object>();
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = reentrantReadWriteLock.readLock();
    static Lock writeLock = reentrantReadWriteLock.writeLock();

    // 获取一个key对应的value
    public static final Object get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }// 设置key对应的value，并返回旧的value

    public static final Object put(String key, Object value) {
        writeLock.lock();
        Object object = null;
        try {
            object = map.put(key, value);
            return object;
        } finally {
            writeLock.unlock();
            object = 1;
            //return object;
        }
    }

    // 清空所有的内容
    public static final void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
//        String s = "abc"; String[] arr = s.split("");
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Cache.put(null, null));
//        LockSupport.park();
//        HashMap map = new HashMap();
//        map.put("1", "123");
//        for (int i = 0; i < 10; i++) {
//            System.out.println(new Random().nextInt(3));
//        }
//        test();
        ConcurrentHashMap c=new ConcurrentHashMap();
        c.put("sb","sb");
    }

    public static void test() {
        final HashMap<String, String> map = new HashMap<String, String>(2);

        final String[] strings = new String[]{"A", "B", "C"};
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), strings[new Random().nextInt(3)]);
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
