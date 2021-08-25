package com.home.example.kafkaproducer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.example.kafkaproducer.services.ReviewersSummaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/reviewers")
@RequiredArgsConstructor
public class ReviewersForSummaryController {

    private final ReviewersSummaryService reviewersSummaryService;

    @GetMapping("/generate")
    public String lookup() {
        reviewersSummaryService.generateAndSendRandomRequests();
        return "Domain crawler has scrapped your data";
    }
}
