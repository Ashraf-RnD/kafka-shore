package com.ashraful.kafkaproducer.controller;

import com.ashraful.kafkaproducer.model.PublishMessageRequest;
import com.ashraful.kafkaproducer.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kafka-producer",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public class KafkaMessageProducerController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/publishMessage")
    public Mono<String> publishMessage(@RequestBody PublishMessageRequest request){
        kafkaProducerService.sendToKafka(request);
        return Mono.empty();
    }
}
