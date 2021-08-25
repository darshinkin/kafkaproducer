package com.home.example.kafkaproducer.services;

import java.util.List;

import com.epam.feedback.infra.trigger.TriggerEventType;
import com.epam.feedback.infra.trigger.summary.RequestReviewersForSummary;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReviewersForSummaryGeneratorService {

    public List<RequestReviewersForSummary> generateRequests() {
        return log.traceExit(List.of(RequestReviewersForSummary.newBuilder()
                        .setTriggerName(TriggerEventType.POSITION_TERMINATION)
                        .setRevieweePmcid(1456L)
                        .setSummaryId("summaryId")
                        .setTriggeredRoleName("Developer")
                        .setTriggeredProjectCode("RVM")
                        .build(),
                RequestReviewersForSummary.newBuilder()
                        .setTriggerName(TriggerEventType.POSITION_TERMINATION)
                        .setRevieweePmcid(4565734L)
                        .setSummaryId("summaryId")
                        .setTriggeredRoleName("Key Developer")
                        .setTriggeredProjectCode("RVM")
                        .build()));
    }
}
