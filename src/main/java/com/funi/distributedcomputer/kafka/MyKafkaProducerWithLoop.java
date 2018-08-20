package com.funi.distributedcomputer.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

public class MyKafkaProducerWithLoop implements Runnable {


    private final Producer<Integer, String> producer;

    private final String topic = MyKafkaProperties.TOPIC;

    public MyKafkaProducerWithLoop() {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", MyKafkaProperties.KAFKA_BROKER_LIST);
        properties.put("key.serializer", IntegerSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        properties.put("client.id", StringSerializer.class.getName());

        this.producer = new KafkaProducer<Integer, String>(properties);
    }

    public void sendMsg() {
        this.producer.send(new ProducerRecord<Integer, String>(this.topic, 1, "message"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("message send to [" + recordMetadata.partition() + "],offset[" + recordMetadata.offset() + "]");
            }
        });
    }

    @Override
    public void run() {
        int messageNo = 0;
        while (true) {
            String messageStr = "message" + messageNo;
            this.producer.send(new ProducerRecord<Integer, String>(this.topic, messageNo, messageStr), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("message send to [" + recordMetadata.partition() + "],offset[" + recordMetadata.offset() + "]");
                }
            });
            ++messageNo;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MyKafkaProducerWithLoop producer = new MyKafkaProducerWithLoop();
        new Thread(producer).start();

        System.in.read();
    }
}
