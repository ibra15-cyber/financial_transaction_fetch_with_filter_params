package com.reactivespring.financial_transaction.repository;

import com.reactivespring.financial_transaction.entity.FinancialTransaction;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

// REPOSITORY
public interface FinancialTransactionRepository extends ReactiveCrudRepository<FinancialTransaction, Long>, CustomFinancialTransactionRepository{

    @Query("SELECT COUNT(*) FROM financial_transactions " +
            "WHERE (:dateFrom IS NULL OR transaction_date >= :dateFrom) " +
            "AND (:dateTo IS NULL OR transaction_date <= :dateTo) " +
            "AND (:userId IS NULL OR user_id = :userId) " +
            "AND (:service IS NULL OR service = :service) " +
            "AND (:status IS NULL OR status = :status) " +
            "AND (:reference IS NULL OR reference = :reference)")
    Mono<Long> countByFilters(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("userId") String userId,
            @Param("service") String service,
            @Param("status") String status,
            @Param("reference") String reference);


    @Query("SELECT * FROM financial_transactions " +
            "WHERE (:dateFrom IS NULL OR transaction_date >= :dateFrom) " +
            "AND (:dateTo IS NULL OR transaction_date <= :dateTo) " +
            "AND (:userId IS NULL OR user_id = :userId) " +
            "AND (:service IS NULL OR service = :service) " +
            "AND (:status IS NULL OR status = :status) " +
            "AND (:reference IS NULL OR reference = :reference) " +
            "LIMIT :limit OFFSET :offset")
    Flux<FinancialTransaction> findByFilters(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("userId") String userId,
            @Param("service") String service,
            @Param("status") String status,
            @Param("reference") String reference,
            @Param("offset") int offset,
            @Param("limit") int limit);
}

