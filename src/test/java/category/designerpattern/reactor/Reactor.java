package category.designerpattern.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/20 19:47
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
//Reactor 1: Setup
public class Reactor implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    private Reactor(int port) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        /**
         Alternatively, use explicit SPI provider:
         */
//        SelectorProvider p = SelectorProvider.provider();
//        this.selector = p.openSelector();
//        this.serverSocketChannel = p.openServerSocketChannel();


        this.serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //非阻塞
        this.serverSocketChannel.configureBlocking(false);
        //向selector注册该channel
        SelectionKey selectionKey = this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        //利用selectionKey的attache功能绑定Acceptor 如果有事情，触发Acceptor
        selectionKey.attach(new Acceptor());
    }

    //Reactor 2: Dispatch Loop
    @Override
    public void run() {
        // normally in a new Thread
        try {
            while (!Thread.interrupted()) {
                //等待
                this.selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                //Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行。
                while (it.hasNext()) {
                    //来一个事件 第一次触发一个accepter线程
                    //以后触发SocketReadHandler
                    dispatch((SelectionKey) it.next());
                }
                selected.clear();
            }
        } catch (IOException ex) { /** ... */}
    }

    /**
     * 运行Acceptor或SocketReadHandler
     *
     * @param k
     */
    void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        if (r != null) {
            r.run();
        }
    }

    //Reactor 3: Acceptor
    class Acceptor implements Runnable { // inner
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    //调用Handler来处理channel
                    new Handler(selector, socketChannel);
                }
            } catch (IOException ex) { /* ... */ }
        }
    }
}

//Reactor 4: Handler setup
final class Handler implements Runnable {
    final static int MAXIN = 1024;
    final static int MAXOUT = 1024;
    final SocketChannel socketChannel;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(MAXIN);
    ByteBuffer output = ByteBuffer.allocate(MAXOUT);
    static final int READING = 0;
    static final int SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel c)
            throws IOException {
        this.socketChannel = c;
        this.socketChannel.configureBlocking(false);
        // Optionally try first read now
        this.sk = this.socketChannel.register(sel, 0);
        //将SelectionKey绑定为本Handler 下一步有事件触发时，将调用本类的run方法。
        //参看dispatch(SelectionKey key)
        this.sk.attach(this);
        //同时将SelectionKey标记为可读，以便读取。
        this.sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete() { /* ... */
        return false;
    }

    boolean outputIsComplete() { /* ... */
        return false;
    }

    void process() { /* ... */ }

    //Reactor 5: Request handling
    // class Handler continued
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) { /* ... */ }
    }

    void read() throws IOException {
        this.socketChannel.read(input);
        if (inputIsComplete()) {
            process();
            this.state = SENDING;
// Normally also do first write now
            this.sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        this.socketChannel.write(output);
        if (outputIsComplete()) {
            sk.cancel();
        }
    }
}