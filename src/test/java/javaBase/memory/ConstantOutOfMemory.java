package javaBase.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(常量池内存溢出探究)
 *
 * @author ${user}
 * @VM args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class ConstantOutOfMemory {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            List<String> strings = new ArrayList<String>();
            int i = 0;
            while (true) {
                strings.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
