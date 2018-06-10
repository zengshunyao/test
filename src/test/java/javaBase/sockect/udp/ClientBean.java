package javaBase.sockect.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientBean {
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

	//
	public ClientBean() throws SocketException, UnknownHostException {
		buffer = new byte[1024];
		clientport = 1985;
		serverport = 1986;
		content = "";
		ds = new DatagramSocket(clientport);// 数据报文段
		ia = InetAddress.getByName("localhost");//

	}

	// 以下是各属性的Get和Set类型方法
	public DatagramSocket getDs() {
		return ds;
	}

	public void setDs(DatagramSocket ds) {
		this.ds = ds;
	}

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

	public int getClientport() {
		return clientport;
	}

	public void setClientport(int clientport) {
		this.clientport = clientport;
	}

	public int getServerport() {
		return serverport;
	}

	public void setServerport(int serverport) {
		this.serverport = serverport;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public InetAddress getIa() {
		return ia;
	}

	public void setIa(InetAddress ia) {
		this.ia = ia;
	}

	/**
	 * @Title: sendToServer
	 * @Description: 发送服务器
	 * @param @throws IOException
	 * @return void
	 * @throws
	 */
	public void sendToServer() throws IOException {
		buffer = content.getBytes();
		ds.send(new DatagramPacket(buffer, content.length(), ia, serverport));
	}
}
