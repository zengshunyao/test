package other.system;

import com.google.common.io.Files;

import java.io.File;

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
public final class SystemTest {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.io.tmpdir"));
        Files.createTempDir();
        Files.isDirectory().apply(new File(""));
        //new SystemTest.t();
        new SystemTest().new tt();
//        Runnable runnable = () -> {
//            System.out.println(Thread.currentThread().toString());
//        };
//        new Thread(runnable).start();
    }

    static class t {
        private t() {
            super();
            System.out.println("super:" + toString());
        }

        @Override
        public String toString() {
            return "t{}";
        }
    }

    final class tt extends t {
        public tt() {
            super();
            System.out.println("super:" + super.toString() + ";this:" + this.toString());
        }

        @Override
        public String toString() {
            return "tt{}";
        }
    }
}
