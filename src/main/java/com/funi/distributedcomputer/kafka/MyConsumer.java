package com.funi.distributedcomputer.kafka;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/8/20 10:11
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class MyConsumer extends ShutdownableThread {

    private final KafkaConsumer<Integer, String> consumer;

    public MyConsumer() {
        super("KafkaConsumerTest", false);
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, MyKafkaProperties.KAFKA_BROKER_LIST);
        //GroupId1 消息所属的分组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "demoGroup1");
        //是否自动提交消息
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //自动提交间隔时间
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //设置最开始的offset偏移量为当前group.id的最早消息
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //设置心跳时间
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        //对key和value设置方序列化对象
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        this.consumer = new KafkaConsumer<Integer, String>(properties);

        //消费指定分区
        TopicPartition p0 = new TopicPartition(MyKafkaProperties.TOPIC, 0);
        this.consumer.assign(Arrays.asList(p0));
    }

    @Override
    public void doWork() {
        //订阅发布 与消费制定分区互斥
//        consumer.subscribe(Collections.singleton(MyKafkaProperties.TOPIC));

        ConsumerRecords<Integer, String> records = consumer.poll(1000);
        for (ConsumerRecord<Integer, String> record : records) {
            System.out.println("[" + record.partition() + "] receiver message:["
                    + record.key() + "-->" + record.value() + "]");
        }
    }

    public static void main(String[] args) {
        MyConsumer consumer = new MyConsumer();
        System.out.println("---------start---------");
        consumer.start();
    }
}
