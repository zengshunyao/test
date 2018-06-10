package category.nio;// $Id$

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class MultiPortEcho {
    private int ports[];
    private ByteBuffer echoBuffer = ByteBuffer.allocate(1024);

    public MultiPortEcho(int ports[]) throws IOException {
        this.ports = ports;//端口数组
        go();
    }

    private void go() throws IOException {
        // Create a new selector
        Selector selector = Selector.open();

        // Open a listener on each port, and register each one
        // with the selector
        selector.selectedKeys();
        selector.select();
        for (int i = 0; i < ports.length; ++i) {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);//设置非阻塞
            ServerSocket ss = ssc.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            ss.bind(address);///绑定到端口
            SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Going to listen on " + ports[i]);
            System.out.println("key " + key);
        }

        while (true) {
            int num = selector.select();

            Set selectedKeys = selector.selectedKeys();
            Iterator it = selectedKeys.iterator();

            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT)
                        == SelectionKey.OP_ACCEPT) {
                    // Accept the new connection
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);

                    // Add the new connection to the selector
                    SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                    System.out.println("Got connection from " + sc);
                } else if ((key.readyOps() & SelectionKey.OP_READ)
                        == SelectionKey.OP_READ) {
                    // Read the data
                    SocketChannel sc = (SocketChannel) key.channel();

                    // Echo data
                    int bytesEchoed = 0;
                    while (true) {
                        echoBuffer.clear();
                        int r = sc.read(echoBuffer);
                        if (r <= 0) {
                            break;
                        }
                        echoBuffer.flip();
                        sc.write(echoBuffer);
                        bytesEchoed += r;
                    }
                    System.out.println("Echoed " + bytesEchoed + " from " + sc);
                    it.remove();
                }
            }

//System.out.println( "going to clear" );
//      selectedKeys.clear();
//System.out.println( "cleared" );
        }
    }

    static public void main(String args[]) throws Exception {
        args = new String[2];
        args[0] = new String("80");
        args[1] = new String("3306");
        System.out.println(1 << 2);
        if (args.length <= 0) {
            System.err.println("Usage: java MultiPortEcho port [port port ...]");
            System.exit(1);
        }

        int ports[] = new int[args.length];
        for (int i = 0; i < args.length; ++i) {
            ports[i] = Integer.parseInt(args[i]);
        }

        new MultiPortEcho(ports);
    }
}
