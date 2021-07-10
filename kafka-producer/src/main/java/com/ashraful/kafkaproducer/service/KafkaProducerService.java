package com.ashraful.kafkaproducer.service;

import com.ashraful.kafkaproducer.model.PublishMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void sendToKafka(PublishMessageRequest data) {


        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(createRecord(data));
        future.addCallback(new KafkaSendCallback<String, Object>() {

            @Override
            public void onFailure(KafkaProducerException ex) {
                handleFailure(ex);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                handleSuccess(result);
            }
        });
    }

    private ProducerRecord<String, Object> createRecord(PublishMessageRequest data) {
        return new ProducerRecord(data.getTopicName(),1,System.currentTimeMillis(),"testKey",data.getMessage());
    }

    private void handleSuccess(SendResult<String, Object> result) {

        log.info("handleSuccess:: value: {}", result.getProducerRecord().value());
    }

    private void handleFailure(KafkaProducerException e) {
        log.error("handleFailure:: EXCEPTION: {}", e.getMessage());
    }

}
