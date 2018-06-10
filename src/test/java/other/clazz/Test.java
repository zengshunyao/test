package other.clazz;

import java.io.File;
import java.io.IOException;

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
public class Test {
    public static void main(String[] args) throws IOException {
//        System.out.println(SubClass.value);// 被动应用1
//        SubClass[] sca = new SubClass[10];// 被动引用2
//        System.out.println(SubClass.HELLOWORLD);// 被动应用3
        String s = "";
        for (int i = 0; i < 251; i++) {
            s += "a";
        }
        System.out.println(("D:\\"+s+".txt").length());
        File file = new File("D:\\"+s+".txt");
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
    }
}
