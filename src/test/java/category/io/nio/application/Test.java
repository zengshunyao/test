package category.io.nio.application;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Lenovo on 2015/6/11.
 */
public class Test {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);

            SelectionKey selectionKey = channel.register(selector,
                    SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            int interestSet = selectionKey.interestOps();

            boolean isInterestedInAccept1 = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
            boolean isInterestedInConnect2 = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
            boolean isInterestedInRead3 = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
            boolean isInterestedInWrite4 = (interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;

            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0) continue;
                Set selectedKeys = selector.selectedKeys();
                Iterator keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = (SelectionKey) keyIterator.next();
                    if (key.isAcceptable()) {
                        // a connection was accepted by a ServerSocketChannel.
                    } else if (key.isConnectable()) {
                        // a connection was established with a remote server.
                    } else if (key.isReadable()) {
                        // a channel is ready for reading
                    } else if (key.isWritable()) {
                        // a channel is ready for writing
                    }
                    keyIterator.remove();
                }
            }
            //selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void pipe() {
        Pipe pipe = null;
        try {
            pipe = Pipe.open();
            Pipe.SinkChannel sinkChannel = pipe.sink();
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();
            while (buf.hasRemaining()) {
                sinkChannel.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
