package com.ashraful.kafkacore.controller;


import com.ashraful.kafkacore.model.CreateTopicRequest;
import com.ashraful.kafkacore.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final KafkaService kafkaService;

    @PostMapping("/createTopic")
    public Mono<Map<String,String>> createNewTopic(@RequestBody CreateTopicRequest request){
        return kafkaService.createTopic(request);
    }

    @GetMapping("/getAllTopics")
    public Mono<List<String>> getAllTopics(){
        return Mono.just(kafkaService.getAllTopics());
    }

}
