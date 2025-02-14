package com.reactivespring.financial_transaction.service;

import com.reactivespring.financial_transaction.dto.PaymentResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Mono<PaymentResponse> retrieveFinancialTransaction(String paymentId);
}