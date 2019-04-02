package category.io.aio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private volatile boolean close = false;

    //线程池
    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup threadGroup;
    //服务器通道
    protected AsynchronousServerSocketChannel assc;

    public void setClose(boolean close) {
        this.close = close;
    }

    public Server(int port) {
        try {
            //创建一个缓存池
            executorService = Executors.newCachedThreadPool();
            //创建线程组
            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
            //创建服务器通道
            assc = AsynchronousServerSocketChannel.open(threadGroup);
            //进行绑定
            assc.bind(new InetSocketAddress(port));
            System.out.println("server start , port : " + port);

            //进行阻塞
            assc.accept(this, new ServerCompletionHandler());

            //一直阻塞 不让服务器停止
            //            Thread.sleep(Integer.MAX_VALUE);
            //为了让其一直阻塞
            while (!close) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8765);
        try {
            Thread.sleep(1 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            List<Runnable> noExecTasks = server.executorService.shutdownNow();
            System.out.println(noExecTasks.size());
            server.setClose(true);
        }
    }
}

/**
 * 干活的类
 */
class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {

    public void completed(AsynchronousSocketChannel asc, Server attachment) {

        //当有下一个客户端接入的时候 直接调用Server的accept方法，这样反复执行下去，
        //保证多个客户端都可以阻塞（没有递归上限），1.7以后AIO才实现了异步非阻塞
        attachment.assc.accept(attachment, this);
        read(asc);
    }

    private void read(final AsynchronousSocketChannel asc) {
        long startTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "==>开始" + startTime + "ms");
        //读取数据
        final ByteBuffer buf = ByteBuffer.allocate(1024);
        asc.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer resultSize, ByteBuffer attachment) {
                //进行读取之后,重置标识位
                attachment.flip();

                //获得读取的字节数
                System.out.println(Thread.currentThread().getName() + "==>" + "Server -> " + "收到客户端的数据长度为:" + resultSize);

                //获取读取的数据
                String resultData = new String(attachment.array()).trim();
                System.out.println(Thread.currentThread().getName() + "==>" + "Server -> " + "收到客户端的数据信息为:" + resultData);

                // 响应数据
                String response = "服务器响应, 收到了客户端发来的数据: " + resultData;
                write(asc, response);
                System.out.println(Thread.currentThread().getName() + "==>耗时" + (System.currentTimeMillis() - startTime) + "ms");
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private void write(AsynchronousSocketChannel asc, String response) {
        try {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.put(response.getBytes());
            buf.flip();
            asc.write(buf).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }
}