package category.privilege;

import sun.nio.cs.StreamEncoder;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;

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
public class ServiceCentreMain {
    public void loadService() {
        URL[] urls;
        try {
            urls = new URL[]{new URL("file:c:/TestService-1.0.jar")};
            URLClassLoader ll = new URLClassLoader(urls);
            final Class a = ll.loadClass("test.TestService");
            Object o = a.newInstance();
            Method m = a.getMethod("doService", null);
            m.invoke(o, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //ServiceCentreMain s = new ServiceCentreMain();
        //s.loadService();
        System.out.println(ServiceCentreMain.class.isAssignableFrom(Object.class));

        new PrintWriter(StreamEncoder.forOutputStreamWriter(
                new FileOutputStream(FileDescriptor.out),
                new Object(),
                Charset.defaultCharset()), true) {
            @Override
            public void close() {
                super.close();
            }
        };
    }
}
