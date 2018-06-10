package category.book.shenrufenxijavaweb.ch02.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/5/8 15:06
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class NioTest {
    public static void main(String[] args) {
    }

    /**
     * 完整从通道读取数据
     *
     * @throws IOException
     */
    @Test
    public void selector() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //创建一个选择器
        Selector selector = Selector.open();
        //创建一个通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);//设置为非阻塞
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册监听的事件

        while (true) {
            int readyChannels = selector.select();

            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();//获得所有key集合

            //获得准备就绪的通道个数
//            selector.select();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (SelectionKey.OP_ACCEPT == (key.readyOps() & SelectionKey.OP_ACCEPT)
                        || key.isAcceptable()) {
                    System.out.println("isAcceptable");
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel1.accept();//接收到服务端的请求
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                } else if (SelectionKey.OP_READ == (key.readyOps() & SelectionKey.OP_READ)
                        || key.isReadable()) {
                    System.out.println("isReadable");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    while (true) {
                        byteBuffer.clear();
                        int n = socketChannel.read(byteBuffer);//读取数据 写byteBuffer
                        if (n <= 0) {
                            break;
                        }
                        byteBuffer.flip();//切换模式 读byteBuffer
                        System.out.println("读取数据开始：");
                        while (byteBuffer.hasRemaining()) {
                            System.out.print((char) byteBuffer.get());//一次读取1个字节
                        }
                        System.out.println("读取数据结束！");
                        byteBuffer.clear();//使缓冲区准备好写入
                    }
                    //写数据到通道
                    System.out.println("写数据开始：");
                    byteBuffer.put("write over!".getBytes());
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        socketChannel.write(byteBuffer);
                    }
//                    socketChannel.write(byteBuffer);
                    byteBuffer.clear();
                    System.out.println("写数据结束！");

                    iterator.remove();
                }
            }
        }
    }

    /**
     * 文件通道
     *
     * @throws IOException
     */
    public void fileChannelTest() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        //创建容量为48字节的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);//读入缓冲区。
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);

            buf.flip(); //使缓冲区准备好读取
//            buf.rewind();
//            buf.reset();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());//一次读取1个字节
            }

            buf.clear();//使缓冲区准备好写入
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    /**
     * 散射读取
     */
    public void scatteringReads() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] bufferArray = {header, body};

        inChannel.read(bufferArray);
    }

    /**
     * 聚集写入
     */
    public void gatheringWrites() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        //write data into buffers
        ByteBuffer[] bufferArray = {header, body};

        inChannel.write(bufferArray);
    }

    /**
     * 将数据从一个源通道传输到 FileChannel
     */
    public void transferFromTest() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);
    }

    /**
     * 从一个FileChannel通道转换数据到另一个通道
     */
    public void transferToTest() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }

    /**
     * 将文件按照一定大小块映射为内存区域
     *
     * @throws IOException
     */
    public void mapTest() {
        int BUF_SIZE = 1024;
        String filename = "";
        long fileLength = new File(filename).length();
        int bufferCount = 1 + (int) (fileLength / BUF_SIZE);
        MappedByteBuffer[] mappedByteBuffer = new MappedByteBuffer[bufferCount];
        long remaining = fileLength;
        for (int i = 0; i < bufferCount; i++) {
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(filename, "r");
                mappedByteBuffer[i] = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,
                        i * BUF_SIZE, Math.min(remaining, BUF_SIZE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            remaining -= BUF_SIZE;
        }
    }

    /**
     * 完整选择器示例
     *
     * @throws IOException
     */
    @Test
    public void fullSelector() throws IOException {
        Selector selector = Selector.open();
        //创建一个通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);//设置非阻塞
        channel.socket().bind(new InetSocketAddress(8080));

        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(selectionKey);
        while (true) {
            int readyChannels = selector.select();

            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                    System.out.println("isAcceptable");
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                    System.out.println("isConnectable");
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                    System.out.println("isReadable");
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                    System.out.println("isWritable");
                }
                keyIterator.remove();
            }
        }
    }
}
