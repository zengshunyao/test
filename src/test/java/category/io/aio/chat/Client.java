package category.io.aio.chat;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class Client implements Runnable {

    private String name;
    private String mesg;
    private final AsynchronousSocketChannel asc;
    private volatile boolean completed = false;

    public Client(String name, String mg) throws Exception {
        this.name = name;
        this.mesg = mg;
        asc = AsynchronousSocketChannel.open();
    }

    public void connect(CompletionHandler<Void, Client> completionHandler) {
        asc.connect(new InetSocketAddress("127.0.0.1", 8765), this, completionHandler);
    }

    public void write(String request) {
        try {
            asc.write(ByteBuffer.wrap((name + "说：" + request).getBytes())).get();
            read();
            completed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read() {
        final ByteBuffer buf = ByteBuffer.allocate(1024);
        try {
            asc.read(buf).get();
            buf.flip();
            byte[] respByte = new byte[buf.remaining()];
            buf.get(respByte);
            System.out.println(Thread.currentThread().getName() + "==>" + new String(respByte, "utf-8").trim());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        this.connect(new CompletionHandler<Void, Client>() {
            @Override
            public void completed(Void result, final Client attachment) {
                attachment.write(attachment.mesg);
            }

            @Override
            public void failed(Throwable exc, final Client attachment) {
                exc.printStackTrace();
            }
        });
        try {
            //没完成就干等
//            Thread.sleep(1000);
            while (!completed) ;
            System.out.println(Thread.currentThread().getName() + "==>" + "等待了"
                    + (System.currentTimeMillis() - startTime) + "毫秒,执行完所有请求");
            countDownLatch.countDown();
            //等所有线程都执行完了才进行后边的执行
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "==>" + "---------------" + this.name + "----over---");
    }

    private final static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws Exception {
        Client c1 = new Client("c1", "aaa");
//        c1.connect();

        Client c2 = new Client("c2", "bbbb");
//        c2.connect();

        Client c3 = new Client("c3", "ccccccc");
//        c3.connect();


        new Thread(c1, "c1").start();
        new Thread(c2, "c2").start();
        new Thread(c3, "c3").start();

        Thread.sleep(1000);

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "==>" + "他们都完了");
//        c1.write("c1 aaa");
//        c2.write("c2 bbbb");
//        c3.write("c3 ccccc");
    }
}
