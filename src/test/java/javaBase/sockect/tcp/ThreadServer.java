package javaBase.sockect.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServer {
	// 端口号

	static final int portNo = 3333;

	public static void main(String[] args) throws IOException {
		// 服务器端的socket
		ServerSocket serverSocket = new ServerSocket(portNo);
		System.out.println("The Server is start: " + serverSocket);
		try {
			for (;;) {
				// 阻塞,直到有客户端连接
				Socket socket = serverSocket.accept();
				// 通过构造函数，启动线程
				new ServerThreadCode(socket);
			}
		} finally {
			serverSocket.close();
		}
	}
}
