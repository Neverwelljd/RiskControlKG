package com.riskcontrol.kg.datasyncengine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class YamlConfig {

    private Map<String, String> neo4jDatasource;

    private Map<String, String> kafkaConsumer;

    private String topic;

    private Properties kafkaConumserProperties = new Properties();
    public Properties getKafkaConsumerProperties(){
        Set kafkaConsumerSet = kafkaConsumer.keySet();
        Iterator<String> kafkaConsumerIterator = kafkaConsumerSet.iterator();

        while (kafkaConsumerIterator.hasNext()){
            String key =kafkaConsumerIterator.next();
            String value = kafkaConsumer.get(key);
            kafkaConumserProperties.put(key, value);

        }
        return kafkaConumserProperties;
    }

    public Map<String, String> getNeo4jDatasource() {
        return neo4jDatasource;
    }

    public void setNeo4jDatasource(Map<String, String> neo4jDatasource) {
        this.neo4jDatasource = neo4jDatasource;
    }

    public Map<String, String> getKafkaConsumer() {
        return kafkaConsumer;
    }

    public void setKafkaConsumer(Map<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getKafkaPartitions() {
        return kafkaPartitions;
    }

    public void setKafkaPartitions(int kafkaPartitions) {
        this.kafkaPartitions = kafkaPartitions;
    }

    private int kafkaPartitions;


}
