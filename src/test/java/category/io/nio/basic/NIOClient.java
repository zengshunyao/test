package category.io.nio.basic;

/**
 * Created by Lenovo on 2015/6/14.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOClient {
    static int SIZE = 10;
    static InetSocketAddress remoteAddress = new InetSocketAddress("localhost", 12345);
    static CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();


    static class DownloadFile implements Runnable {
        int index;
        private String file = null;

        public DownloadFile(int index) {
            this.index = index;
            this.file = "E:\\ZengShunYao\\Study\\VMware-workstation-full-11.1.0.58002.1428919414" + index + ".exe";
        }


        @Override
        public void run() {
            long start = System.currentTimeMillis();
            try {
                File temp = new File(file);
                if (!temp.exists()) {
                    temp.createNewFile();
                }
                FileChannel fileChannel = new FileOutputStream(file).getChannel();
                Selector selector = Selector.open();
                SocketChannel client = SocketChannel.open();
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_CONNECT);
                client.connect(remoteAddress);

                ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
                int total = 0;
                while (true) {
                    boolean isExit = false;
                    int count=selector.select();
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey selectionKey = (SelectionKey) it.next();
                        it.remove();
                        if (selectionKey.isConnectable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            if (channel.isConnectionPending()) {
                                channel.finishConnect();
                            }
                            channel.write(encoder.encode(CharBuffer.wrap("Hello From " + index)));
                            channel.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            int num = channel.read(buffer);
                            if (count > 0) {
//                                System.out.println(buffer.toString());
                                buffer.flip();
                                fileChannel.write(buffer);
                                total += count;
                                buffer.clear();
                            } else {
                                channel.close();
                                isExit = true;
                                break;
                            }
                        }
                    }

                    if (isExit) {
                        fileChannel.close();
                        break;
                    }
                }

                double last = (System.currentTimeMillis() - start) * 1.0 / 1000;
                System.out.println("Thread " + index + " downloaded " + total + " bytes in " + last + "seconds.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println("begin:" + begin);
        // 新建10个线程
        ExecutorService pool = Executors.newFixedThreadPool(SIZE);
        for (int i = 0; i < SIZE; i++) {
            pool.execute(new DownloadFile(i));
        }
        pool.shutdown();
        //线程完毕
        System.out.println("end:" + (System.currentTimeMillis() - begin));
    }
}