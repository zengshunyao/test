package category.jdk.java19;


import org.junit.Test;

/**********************************************************************
 * &lt;p&gt;文件名：FlowDemo.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/10/9 14:39
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class FlowDemo {
    @Test
    public void test() {
        /*
        // Create a publisher.
        //创建发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // Create a subscriber and register it with the publisher.
        //创建一个订阅者并注册到发布者
        MySubscriber<String> subscriber = new MySubscriber<>();
        publisher.subscribe(subscriber);

        // Publish several data items and then close the publisher.
        //发布一些列数据并关闭发布者
        System.out.println("Publishing data items...");
        String[] items = {"jan", "feb", "mar", "apr", "may", "jun",
                "jul", "aug", "sep", "oct", "nov", "dec"};

        Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
        publisher.close();

        try {
            synchronized ("A") {
                "A".wait();
            }
        } catch (InterruptedException ie) {
        }*/
    }
}
/*
class MySubscriber<T> implements Flow.Subscriber<T> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.println("Received: " + item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        synchronized ("A") {
            "A".notifyAll();
        }
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
        synchronized ("A") {
            "A".notifyAll();
        }
    }
}*/