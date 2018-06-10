package javaBase.sockect.tcp;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ThreadClient {

	/**
	 * @throws java.net.UnknownHostException
	 * @Title: main
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getByName("localhost");
		for (int threadNo = 0; threadNo < 3; threadNo++) {
			new ClientThreadCode(address);
		}
	}

}
