package category.thread.ch03.pipeInputOutput.service;

import java.io.IOException;
import java.io.PipedInputStream;

public class ReadData {

	public void readMethod(PipedInputStream input) {
		try {
			System.out.println("read  begin:");
			byte[] byteArray = new byte[200];
			int readLength = input.read(byteArray);
			while (readLength != -1) {
				String newData = new String(byteArray, 0, readLength);
				System.out.println("从通道读取:"+newData);
				readLength = input.read(byteArray);
			}
			System.out.println();
			input.close();
			System.out.println("read  end:");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
