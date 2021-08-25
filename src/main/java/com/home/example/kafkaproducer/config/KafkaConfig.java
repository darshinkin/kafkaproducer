package com.home.example.kafkaproducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.feedback.infra.trigger.summary.RequestReviewersForSummary;
import com.home.example.kafkaproducer.properties.KafkaProperties;
import com.home.example.kafkaproducer.services.ProducerService;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

@Configuration
public class KafkaConfig {

    @Bean
    @ConfigurationProperties(prefix = "kafka")
    public KafkaProperties kafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    ProducerService producerService() {
        return new ProducerService(getProducer(), kafkaProperties().getTopic());
    }

    @Bean
    Producer<String, RequestReviewersForSummary> getProducer() {
        return new KafkaProducer<>(producerProperties());
    }

    @Bean
    Map<String, Object> producerProperties() {
        Map<String, Object> properties = new HashMap<>();
        // normal producer
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties().getBootstrapServer());
        properties.put(ProducerConfig.ACKS_CONFIG, kafkaProperties().getAcks());
        properties.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties().getRetries());
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProperties().getBatchSize());
        properties.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProperties().getLingerMs());
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProperties().getBufferMemory());
        // avro part
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.put("schema.registry.url", kafkaProperties().getRegistryUrl());
        return properties;
    }
}
