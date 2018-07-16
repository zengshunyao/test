package other.md5;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/25 16:26
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class Test {

    public static void main(String[] args) {
        try {
            System.out.println(DigestUtils.md5Hex(
                    new FileInputStream("C:\\Users\\Lenovo\\Desktop\\CCSCRServer.rar")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
