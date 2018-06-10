package category.thread.ch03.pipeInputOutput.service;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.Random;

public class WriteData {

    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("write begin:");
            for (int i = 0; i < 300; i++) {
                String outData = new String(("当前时间：" + System.currentTimeMillis() + "---"
                        + new Random().nextInt(i + 1)).getBytes());
                out.write(outData.getBytes());
                System.out.println("写入到通道:" + outData);
            }
            System.out.println();
            out.close();
            System.out.println("write end:");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
