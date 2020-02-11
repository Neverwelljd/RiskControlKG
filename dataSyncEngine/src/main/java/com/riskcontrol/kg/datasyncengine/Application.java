package com.riskcontrol.kg.datasyncengine;

import com.riskcontrol.kg.datasyncengine.config.YamlConfig;
import com.riskcontrol.kg.datasyncengine.config.YamlParser;
import com.riskcontrol.kg.datasyncengine.controller.ReceiveThread;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) {
        YamlConfig config = YamlParser.readValue("application.yml",YamlConfig.class);
        System.out.println(config.getTopic());
        // 用多线程的方式来进行消费者的启动，用partitions来控制线程数

        int kafkaPartitions = config.getKafkaPartitions();
        ExecutorService executorService = Executors.newFixedThreadPool(kafkaPartitions);
        Properties properties = config.getKafkaConsumerProperties();
        ReceiveThread thread = new ReceiveThread(properties, config.getTopic());

        for (int i = 0; i < kafkaPartitions; i++) {
            executorService.execute(thread);
        }

    }
}
