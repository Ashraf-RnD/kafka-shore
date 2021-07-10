package com.ashraful.kafkacore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTopicRequest {

    @NotBlank(message = "Invalid topicName name")
    @Pattern(regexp = "^[a-zA-Z]+((([,.][ a-zA-Z0-9])|([' \\-][a-zA-Z0-9]))?[a-zA-Z0-9]*)*$", message = "Invalid topicName name")
    private String topicName;
    @Builder.Default
    private int partitions = 3;
    @Builder.Default
    private int replicas = 1;
}
