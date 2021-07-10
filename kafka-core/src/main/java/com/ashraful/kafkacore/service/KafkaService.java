package com.ashraful.kafkacore.service;


import com.ashraful.kafkacore.model.CreateTopicRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaAdminClient kafkaAdminClient;

    public Mono<Map<String,String>> createTopic(CreateTopicRequest request){
        var topic = TopicBuilder.name(request.getTopicName())
                .replicas(request.getReplicas())
                .partitions(request.getPartitions())
                .compact()
                .build();

        log.info("KafkaService:: createDefaultTopic:: topic: {}",topic.toString());

        var topics = kafkaAdminClient.createTopics(List.of(topic));
        log.info("KafkaService:: createDefaultTopic:: topics: {}",topics.values());

        Map<String,String> topicMap = new HashMap<>();
        topicMap.put("topicName",topic.name());
        topicMap.put("partitionNumber",String.valueOf(topic.numPartitions()));
        topicMap.put("replicationFactor",String.valueOf(topic.replicationFactor()));
        return Mono.just(topicMap);
    }

    public List<String> getAllTopics(){
        var listTopicsResult = kafkaAdminClient.listTopics();
        try {
            return getListKafkaFuture(listTopicsResult)
                    .get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return List.of();

    }

    private KafkaFuture<List<String>> getListKafkaFuture(ListTopicsResult listTopicsResult) {
        return listTopicsResult.listings()
                .thenApply(topicListings -> {
                    log.info("KafkaService:: getAllTopics:: listings: {}", topicListings.size());
                    return topicListings.stream()
                            .map(topicListing -> {
                                log.info("KafkaService:: getAllTopics:: topicListing: {}", topicListing.name());
                                return topicListing.name();
                            })
                            .collect(Collectors.toList());
                });
    }
}
