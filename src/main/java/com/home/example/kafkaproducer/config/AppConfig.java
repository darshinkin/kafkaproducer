package com.home.example.kafkaproducer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.home.example.kafkaproducer.services.ProducerService;
import com.home.example.kafkaproducer.services.ReviewersForSummaryGeneratorService;
import com.home.example.kafkaproducer.services.ReviewersSummaryService;

@Configuration
@Import(value = {KafkaConfig.class})
public class AppConfig {

    @Autowired
    private ProducerService producerService;

    @Bean
    public ReviewersForSummaryGeneratorService reviewersForSummaryGeneratorService() {
        return new ReviewersForSummaryGeneratorService();
    }

    @Bean
    public ReviewersSummaryService reviewersSummaryService() {
        return new ReviewersSummaryService(reviewersForSummaryGeneratorService(), producerService);
    }
}
