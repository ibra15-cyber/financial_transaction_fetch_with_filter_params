package com.reactivespring.financial_transaction.service;

import com.reactivespring.financial_transaction.dto.DataListPaymentResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface FinancialTransactionService {
    Mono<DataListPaymentResponse> getTransactions(
            LocalDate dateFrom, LocalDate dateTo, String userId, String service,
            String status, String reference, int offset, int limit);
}
