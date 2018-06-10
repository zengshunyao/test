package category.privilege;

import java.io.FilePermission;
import java.security.AccessController;
import java.security.Permission;

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
public class TestService {
    public void doService() {

        doFileOperation();

    }

    private void doFileOperation() {
        Permission perm = new FilePermission("C:/text.txt", "read");
        AccessController.checkPermission(perm);
        System.out.println("TestService has permission");
    }
}
