package com.ashraful.kafkaproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublishMessageRequest {
    private String message;
    private String eventKey;
    private String topicName;

}
