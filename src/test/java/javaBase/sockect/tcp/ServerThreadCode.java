package javaBase.sockect.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreadCode extends Thread {

	// 客户端的socket
	private Socket clientSocket;

	// IO句柄
	private BufferedReader in;
	private PrintWriter out;

	// 默认的构造函数
	public ServerThreadCode() {
	}

	public ServerThreadCode(Socket s) throws IOException {
		this.clientSocket = s;
		// 初始化sin和sout的句柄
		this.in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				clientSocket.getOutputStream())), true);

		// 开启线程
		start();
	}

	// 线程执行的主体函数
	public void run() {
		try {
			// 用循环来监听通讯内容
			while (true) {
				String str = in.readLine();
				// 如果接收到的是byebye，退出本次通讯
				if (str.equals("byebye")) {
					break;
				}
				System.out.println("In Server reveived the info: " + str);
				out.println(str);
			}
			System.out.println("closing the server socket!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("close the Server socket and the io.");
			try {
				clientSocket.close();
				// 此处不能关闭BufferReader和PrintWriter,
				// 因为他们的初始化在ServerThreadCode构造函数里面
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
