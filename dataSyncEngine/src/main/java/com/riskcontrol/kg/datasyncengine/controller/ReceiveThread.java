package com.riskcontrol.kg.datasyncengine.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ReceiveThread implements Runnable {

    private KafkaConsumer<String, String> consumer;

    public ReceiveThread(Properties properties, String topic){
        consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topic));
    }

    public void run(){
        try{
            while (!Thread.currentThread().isInterrupted()){
                ConsumerRecords<String,String> records = consumer.poll(Duration.ofSeconds(5));
                for(ConsumerRecord record:records){
                    if (record == null) {
                        handing(record);
                    }
                }
            }
        }finally {
            consumer.close();
        }


    }

    private void handing(ConsumerRecord record){
        System.out.println(Thread.currentThread().getName() + record.value());
    }
}
