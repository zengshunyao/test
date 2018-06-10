package sandbox;

import sandbox.util.ConstantParam;

import java.io.FilePermission;
import java.lang.reflect.ReflectPermission;
import java.security.Permission;
import java.security.SecurityPermission;
import java.util.PropertyPermission;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * 	[重写的安全管理器]
 * 	安全管理器用来限制客户端提交的Java源程序运行的功能,
 * 对程序读/写本地文件系统,修改系统属性连接网络,
 * 数据库等一切可能对本地计算机系统造成危害的操作进行屏蔽,
 * 如有这些操作将抛出SecurityException异常,并终止程序执行.
 *
 * [说明]:
 * 仅包内可见
 * 不允许提交的源程序执行exit(n)函数-即不允许源程序中途
 *  终止虚拟机的运行,但是调用源代码端可执行exit(n)函数.
 * @project_name：test
 * @author zengshunyao
 * @date 2018/3/6 0:02
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class SandboxSecurityManager extends SecurityManager {

    public static final int EXIT = ConstantParam.RANDOM.nextInt();

    /**
     * 重写强行退出检测
     * 防止用户自行终止虚拟机的运行,但是调用程序端可以执行退出
     */
    @Override
    public void checkExit(int status) {
        if (status != EXIT)
            throw new SecurityException("Exit On Client Is Not Allowed!");
    }

    /**
     * 策略权限查看
     * 当执行操作时调用,如果操作允许则返回,操作不允许抛出SecurityException
     */
    private void sandboxCheck(Permission perm) throws SecurityException {
        // 设置只读属性
        if (perm instanceof SecurityPermission) {
            if (perm.getName().startsWith("getProperty")) {
                return;
            }
        } else if (perm instanceof PropertyPermission) {
            if (perm.getActions().equals("read")) {
                return;
            }
        } else if (perm instanceof FilePermission) {
            if (perm.getActions().equals("read")) {
                return;
            }
        } else if (perm instanceof RuntimePermission || perm instanceof ReflectPermission) {
            return;
        }

        throw new SecurityException(perm.toString());
    }

    @Override
    public void checkPermission(Permission perm) {
        this.sandboxCheck(perm);
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        this.sandboxCheck(perm);
    }

}

