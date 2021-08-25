package com.home.example.kafkaproducer.services;

import java.util.List;

import com.epam.feedback.infra.trigger.summary.RequestReviewersForSummary;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewersSummaryService {

    private final ReviewersForSummaryGeneratorService generator;
    private final ProducerService producerService;

    public List<RequestReviewersForSummary> generateAndSendRandomRequests() {
        List<RequestReviewersForSummary> requests = generator.generateRequests();
        producerService.send(requests);
        return requests;
    }
}
