package javaBase.sockect.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UDPClient implements Runnable {
	public static String content;
	public static ClientBean client;

	@Override
	public void run() {
		client.setContent(content);
		try {
			client.sendToServer();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		client = new ClientBean();
		System.out.println("客户端启动......");
		while (true) {
			content = bufferedReader.readLine();
			if (content == null || content.equalsIgnoreCase("end")
					|| content.equalsIgnoreCase("")) {
				break;
			}
			// 开启新线程,发送消息
			new Thread(new UDPClient()).start();
		}
	}
}
