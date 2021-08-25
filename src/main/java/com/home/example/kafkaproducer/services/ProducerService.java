package com.home.example.kafkaproducer.services;

import java.util.List;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.epam.feedback.infra.trigger.summary.RequestReviewersForSummary;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class ProducerService {

    private final Producer<String, RequestReviewersForSummary> producer;
    private final String topicName;

    public void send(List<RequestReviewersForSummary> requests) {
        log.info("Started sending staffing to kafka topic {}. Size: {}", topicName, requests.size());
        requests.forEach(request -> {
            String keyRecord = request.getSummaryId();
            ProducerRecord<String, RequestReviewersForSummary> producerRecord = new ProducerRecord<>(topicName, 0,
                    System.currentTimeMillis(), keyRecord, request);
            producer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    log.info("Received new metadata. \n Topic: {} \n Partition: {} \n Offset: {} \n Timestamp: {} \n Record: {}.",
                            metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp(), producerRecord);
                } else {
                    log.error("Error occurred during sending message to kafka. \n Topic: {} \n Partition: {} \n Offset: {} \n Timestamp: {}"
                                    + "\n Record: {}.",
                            metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp(), producerRecord);
                    exception.printStackTrace();
                }
            });
        });
        log.info("Finished of sending the triggers to kafka topic {}. Size: {}", topicName, requests.size());
    }
}
