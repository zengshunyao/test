package category.book.javabingfabianchengyishu.ch06.segment;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/16 19:28
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class MapTest {

    public static void main(String[] args) {
        ConcurrentHashMap c = new ConcurrentHashMap();
        c.put("sb", "sb");
        ConcurrentLinkedQueue<MapTest> concurrentLinkedQueue=new ConcurrentLinkedQueue();
        concurrentLinkedQueue.add(new MapTest());
        System.out.println(c.size());
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
