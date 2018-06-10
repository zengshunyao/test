package javaBase.sockect.udp;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Title: TRS 内容协作平台（TRS WCM）<BR>
 * Description: Copyright: Copyright (c) 2004-2005 TRS信息技术有限公司<BR>
 * Company: TRS信息技术有限公司(www.trs.com.cn)<BR>
 *
 * @author zengshunyao
 * @version 1.0
 */
public class UDPServer {
	public static void main(String[] args) throws SocketException,
			UnknownHostException, IOException {
		System.out.println("服务器启动");
		// 初始化ServerBean对象
		ServerBean serverBean = new ServerBean();
		// 开启监听程序
		serverBean.listenClient();
	}
}
