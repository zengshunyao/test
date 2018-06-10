package other.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(Java中典型的内存泄露问题和解决方法)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class MemoryLeak {

    public static void main(String[] args) {
        Map<Key, String> map = new HashMap<Key, String>(1000);
        System.out.println("Total memory is " + getTotalMemory() + "MB");
        System.out.println("Max memory is " + getMaxMemory() + "MB");
        int counter = 0;
        while (true) {
            // creates duplicate objects due to bad Key class
            map.put(new Key("dummyKey"), "value");
            counter++;
            if (counter % 100000 == 0) {
                System.out.println("map size: " + map.size());
                System.out.println("Free memory after count " + counter
                        + " is " + getFreeMemory() + "MB");
                sleep(1000);
            }
        }
    }

    // inner class key without hashcode() or equals() -- bad implementation
    static class Key {
        private String key;

        public Key(String key) {
            this.key = key;
        }
    }

    //delay for a given period in milli seconds
    public static void sleep(long sleepFor) {
        try {
            Thread.sleep(sleepFor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get available memory in MB
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }

    //get total memory in MB
    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory() / (1024 * 1024);
    }

    //get max memory in MB
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / (1024 * 1024);
    }
}
