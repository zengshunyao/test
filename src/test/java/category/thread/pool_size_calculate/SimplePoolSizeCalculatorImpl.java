package category.thread.pool_size_calculate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/18 22:07
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class SimplePoolSizeCalculatorImpl extends PoolSizeCalculator {

    public SimplePoolSizeCalculatorImpl() {
        super();
    }

    @Override
    protected Runnable createTask() {
        return new AsyncIOTask();
    }

    @Override
    protected BlockingQueue createWorkQueue() {
        return new LinkedBlockingQueue(1000);
    }

    @Override
    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }

    /**
     * 调用测试
     *
     * @param args
     */
    public static void main(String[] args) {
        PoolSizeCalculator poolSizeCalculator = new SimplePoolSizeCalculatorImpl();
        poolSizeCalculator.calculateBoundaries(new BigDecimal(1.0), new BigDecimal(100000));
    }


    /**
     * 自定义的异步IO任务 请求网络数据
     * 指定期望CPU利用率为1.0（即100%），任务队列总大小不超过100,000字节
     *
     * @author Will
     */

    class AsyncIOTask implements Runnable {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String getURL = "http://baidu.com";
                URL getUrl = new URL(getURL);
                connection = (HttpURLConnection) getUrl.openConnection();
                connection.connect();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    // empty loop
                }
            } catch (IOException e) {
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                    }
                }
                connection.disconnect();
            }
        }
    }
}
