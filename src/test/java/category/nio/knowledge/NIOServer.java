package category.nio.knowledge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private static final int BLOCK_SIZE = 1024 * 8;

    private final Selector selector;
    private String file = "E:\\ZengShunYao\\Study\\VMware-workstation-full-11.1.0.58002.1428919414.exe";
    private ByteBuffer buffer = ByteBuffer.allocate(BLOCK_SIZE);
    private CharsetDecoder charsetDecoder;

    public NIOServer(int port) throws IOException {
        selector = this.getSelector(port);
        Charset charset = Charset.forName("UTF-8");
        charsetDecoder = charset.newDecoder();
    }

    /**
     * 初始化serverSocketChannel 并注册
     */
    private void init(Selector selector, int port) throws IOException {
        // 1.打开服务器端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2.绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 3.非阻塞
        serverSocketChannel.configureBlocking(false);
        // 4.注册
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private Selector getSelector(int port) throws IOException {
        // 选择器
        Selector selector = Selector.open();
        init(selector, port);
        return selector;
    }

    /**
     * 监听、遍历key处理key
     */
    public void listen() {
        while (true) {
            try {
                if (selector.select() != 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    System.out.println("keyset size : " + selectionKeys.size());
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        handleKey(selectionKey);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            System.out.println("Accept.............");
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int count = channel.read(buffer);
            if (count > 0) {
                buffer.flip();
                CharBuffer charBuffer = charsetDecoder.decode(buffer);

                String fromClientString = charBuffer.toString();
                System.out.println("Read From Client : " + fromClientString);

                SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_WRITE);
                selectionKey.attach(new Handle(fromClientString));
            } else {
                channel.close();
            }
            buffer.clear();
        } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            Handle Handle = (Handle) key.attachment();
            ByteBuffer buffer = Handle.readBlock();
            System.out.println("Write to client : " + Handle.getClentName());
            if (buffer != null) {
                channel.write(buffer);
            } else {
                Handle.close();
                channel.close();
            }
        }
    }

    /***
     *
     */
    private class Handle {

        private FileChannel fileChannel;
        private ByteBuffer byteBuffer;
        private String clientName;

        public Handle(String clientName) throws FileNotFoundException {
            fileChannel = new FileInputStream(file).getChannel();
            byteBuffer = ByteBuffer.allocate(BLOCK_SIZE);
            this.clientName = clientName;
        }

        public ByteBuffer readBlock() {
            try {
                byteBuffer.clear();
                int count = fileChannel.read(byteBuffer);
                byteBuffer.flip();
                if (count < 0) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteBuffer;
        }

        public void close() {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getClentName() {
            return clientName;
        }
    }

    public static void main(String[] args) {
        int port = 12345;
        try {
            NIOServer server = new NIOServer(port);
            System.out.println("Listening on : " + port);
            while (true) {
                server.listen();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}