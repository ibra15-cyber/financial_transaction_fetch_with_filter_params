package com.reactivespring.financial_transaction.repository;

import com.reactivespring.financial_transaction.entity.FinancialTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface CustomFinancialTransactionRepository {
    Mono<Long> countByFilters(
            LocalDate dateFrom, LocalDate dateTo, String userId,
            String service, String status, String reference);

    Flux<FinancialTransaction> findByFilters(
            LocalDate dateFrom, LocalDate dateTo, String userId,
            String service, String status, String reference,
            int offset, int limit);

}
