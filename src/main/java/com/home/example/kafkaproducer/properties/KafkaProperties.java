package com.home.example.kafkaproducer.properties;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Kafka properties.
 */
@RequiredArgsConstructor
@Data
public class KafkaProperties {

    private String bootstrapServer;
    private String acks;
    private String retries;
    private String batchSize;
    private String lingerMs;
    private String bufferMemory;
    private String registryUrl;
    private String topic;
}
