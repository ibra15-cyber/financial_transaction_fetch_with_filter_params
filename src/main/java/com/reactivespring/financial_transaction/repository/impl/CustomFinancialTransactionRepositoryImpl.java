package com.reactivespring.financial_transaction.repository.impl;

import com.reactivespring.financial_transaction.entity.FinancialTransaction;
import com.reactivespring.financial_transaction.repository.CustomFinancialTransactionRepository;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public class CustomFinancialTransactionRepositoryImpl implements CustomFinancialTransactionRepository {
    private final R2dbcEntityTemplate template;

    public CustomFinancialTransactionRepositoryImpl(R2dbcEntityTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<Long> countByFilters(
            LocalDate dateFrom, LocalDate dateTo, String userId,
            String service, String status, String reference) {

        Criteria criteria = buildCriteria(dateFrom, dateTo, userId, service, status, reference);
        return template.count(Query.query(criteria), FinancialTransaction.class);
    }

    @Override
    public Flux<FinancialTransaction> findByFilters(
            LocalDate dateFrom, LocalDate dateTo, String userId,
            String service, String status, String reference,
            int offset, int limit) {

        Criteria criteria = buildCriteria(dateFrom, dateTo, userId, service, status, reference);
        return template.select(FinancialTransaction.class)
                .matching(Query.query(criteria).offset(offset).limit(limit))
                .all();
    }

    private Criteria buildCriteria(
            LocalDate dateFrom, LocalDate dateTo, String userId,
            String service, String status, String reference) {

        Criteria criteria = Criteria.empty();

        if (dateFrom != null) {
            criteria = criteria.and("transaction_date").greaterThanOrEquals(dateFrom);
        }
        if (dateTo != null) {
            criteria = criteria.and("transaction_date").lessThanOrEquals(dateTo);
        }
        if (userId != null && !userId.isEmpty()) {
            criteria = criteria.and("user_id").is(userId);
        }
        if (service != null && !service.isEmpty()) {
            criteria = criteria.and("service").is(service);
        }
        if (status != null && !status.isEmpty()) {
            criteria = criteria.and("status").is(status);
        }
        if (reference != null && !reference.isEmpty()) {
            criteria = criteria.and("reference").is(reference);
        }

        return criteria;
    }
}
