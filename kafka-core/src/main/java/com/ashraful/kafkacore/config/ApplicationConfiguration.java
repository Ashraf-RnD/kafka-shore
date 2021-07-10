package com.ashraful.kafkacore.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ApplicationConfiguration {

    public static final String KAFKA_LOCAL_URL = "localhost:9092";

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_LOCAL_URL);
        return new KafkaAdmin(configs);
    }

   /* @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("kafka-topic-1")
                .partitions(10)
                .replicas(1)
                .compact()
                .build();
    }*/

    @Bean
    public KafkaAdminClient kafkaAdminClient(){
//        Map<String, Object> config = new HashMap<>();
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_LOCAL_URL);

        return (KafkaAdminClient) KafkaAdminClient.create(config);
    }
}
