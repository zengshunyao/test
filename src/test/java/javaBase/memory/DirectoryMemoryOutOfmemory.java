package javaBase.memory;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(直接内存溢出测试)
 *
 * @author ${user}
 * @VM args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class DirectoryMemoryOutOfmemory {/*

    private static final int ONE_MB = 1024 * 1024;
    private static int count = 1;

    /**
     * @param args
     * @Author YHJ create at 2011-11-12 下午09:05:54
     *//*
    public static void main(String[] args) {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            while (true) {
                unsafe.allocateMemory(ONE_MB);
                count++;
            }
        } catch (Exception e) {
            System.out.println("Exception:instance created " + count);
            e.printStackTrace();
        } catch (Error e) {
            System.out.println("Error:instance created " + count);
            e.printStackTrace();
        }

    }
*/
}
