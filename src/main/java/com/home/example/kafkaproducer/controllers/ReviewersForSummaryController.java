package com.home.example.kafkaproducer.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.epam.feedback.infra.trigger.summary.RequestReviewersForSummary;
import com.home.example.kafkaproducer.services.ReviewersSummaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/reviewers")
@RequiredArgsConstructor
public class ReviewersForSummaryController {

    private final ReviewersSummaryService reviewersSummaryService;

    @GetMapping("/generate")
    public String lookup() {
        List<RequestReviewersForSummary> requests = reviewersSummaryService.generateAndSendRandomRequests();
        return "Request review summary has been generated: " + requests;
    }
}
