package com.reactivespring.financial_transaction.controller;

import com.reactivespring.financial_transaction.dto.DataListPaymentResponse;
import com.reactivespring.financial_transaction.service.FinancialTransactionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FinancialTransactionController {
    private final FinancialTransactionService transactionService;
    private final Logger log = LoggerFactory.getLogger(FinancialTransactionController.class);

    @GetMapping("/transactions")
    public Mono<ResponseEntity<DataListPaymentResponse>> getFinancialTransactions(
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String service,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String reference) {

        int pageOffset = offset != null ? offset : 0;
        int pageLimit = limit != null ? limit : 10;

        return transactionService.getTransactions(dateFrom, dateTo, userId, service, status, reference, pageOffset, pageLimit)
                .map(ResponseEntity::ok)
                .onErrorResume(WebClientResponseException.class, ex ->
                        Mono.just(ResponseEntity.status(ex.getStatusCode()).build()))
                .onErrorResume(ex -> {
                    log.error("Error processing financial transactions", ex);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}
