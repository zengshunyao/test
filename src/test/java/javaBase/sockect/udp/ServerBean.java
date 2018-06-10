package javaBase.sockect.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerBean {
	// 描述UDP通讯的DatagramSocket对象
	// 用于发送DatagramPackect的socket
	private DatagramSocket ds;

	// 用来封装通讯字符串
	private byte buffer[];

	// 客户端的端口号
	private int clientport;

	// 服务器端的端口号
	private int serverport;

	// 通讯内容
	private String content;

	// 描述通讯地址
	private InetAddress ia;

	public ServerBean() throws SocketException, UnknownHostException {
		buffer = new byte[1024];
		clientport = 1985;
		serverport = 1986;
		content = "";
		ds = new DatagramSocket(serverport);
		ia = InetAddress.getByName("localhost");
	}

	/**
	 * @Title: listenClient
	 * @Description: TODO
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public void listenClient() throws IOException {
		// 在循环体里接受消息
		while (true) {
			// 初始化DatagramPacket类型的变量
			DatagramPacket datagramPacket = new DatagramPacket(buffer,
					buffer.length);

			// 接收消息，并把消息通过dp参数返回
			ds.receive(datagramPacket);

			content = new String(datagramPacket.getData(), 0,
					datagramPacket.getLength());
			// 打印消息
			print();
		}
	}

	/**
	 * @Title: print
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	private void print() {
		System.out.println(content);
	}
}
