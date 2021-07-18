package com.ashraful.kafkaconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "kafka-topic-1", groupId = "foo")
    public void listen(String data) {
        log.info("KafkaConsumer:: listener:: data: {}", data);
    }
}
