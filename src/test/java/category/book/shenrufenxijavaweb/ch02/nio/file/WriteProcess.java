package category.book.shenrufenxijavaweb.ch02.nio.file;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/5/8 15:53
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class WriteProcess {
    public static void main(String[] arg) throws Exception {
        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;

        try {
            //读写进程还必须使用相同的模式(此例中为rw)才可以成功加锁
            randomAccessFile = new RandomAccessFile("test.txt", "rw");
            channel = randomAccessFile.getChannel();
            FileLock lock = null;

            while (null == lock) {
                try {
                    //lock()方法和tryLock()的区别就是lock()是阻塞的，tryLock()是非阻塞的
                    lock = channel.lock();
                } catch (Exception e) {
                    System.out.println("Write Process : get lock failed");
                }
            }

            System.out.println("Write Process : get lock");

            for (int i = 0; i < 30; i++) {
                randomAccessFile.writeByte(i);
                System.out.println("Write Process : write " + i);

                Thread.sleep(100);
            }

            lock.release();
            System.out.println("Write Process : release lock");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != randomAccessFile) {
                randomAccessFile.close();
            }
            if (null != channel) {
                channel.close();
            }
        }
    }
}
