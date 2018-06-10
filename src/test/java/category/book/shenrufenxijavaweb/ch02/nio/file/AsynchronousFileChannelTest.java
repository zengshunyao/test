package category.book.shenrufenxijavaweb.ch02.nio.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/5/9 1:40
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class AsynchronousFileChannelTest {

    public static void main(String[] args) {

    }

    //Reading Data Via a Future
    public void readingData() throws IOException {
        Path path = Paths.get("data/test.xml");

        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        Future<Integer> operation = fileChannel.read(buffer, position);

        while (!operation.isDone()) ;

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
    }

    //Reading Data Via a CompletionHandler
    public void readingDataViaaCompletionHandler() throws IOException {
        Path path = Paths.get("data/test.xml");

        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        fileChannel.read(buffer, position, buffer/*attachment*/, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    //Writing Data Via a Future
    public void writingData() throws IOException {
        Path path = Paths.get("data/test-write.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        buffer.put("test data".getBytes());
        buffer.flip();

        Future<Integer> operation = fileChannel.write(buffer, position);
        buffer.clear();

        while (!operation.isDone()) ;

        System.out.println("Write done");
    }

    //Writing Data Via a CompletionHandler
    public void writingDataViaaCompletionHandler() throws IOException {
        Path path = Paths.get("data/test-write.txt");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        buffer.put("test data".getBytes());
        buffer.flip();

        fileChannel.write(buffer, position, buffer/*attachment*/, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("bytes written: " + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("Write failed");
                exc.printStackTrace();
            }
        });
    }
}
