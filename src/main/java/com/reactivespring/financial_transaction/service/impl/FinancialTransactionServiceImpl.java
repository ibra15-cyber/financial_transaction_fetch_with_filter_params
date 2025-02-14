package com.reactivespring.financial_transaction.service.impl;

import com.reactivespring.financial_transaction.dto.DataListPaymentResponse;
import com.reactivespring.financial_transaction.entity.FinancialTransaction;
import com.reactivespring.financial_transaction.dto.Link;
import com.reactivespring.financial_transaction.dto.PaymentData;
import com.reactivespring.financial_transaction.mapper.EntityDTOMapper;
import com.reactivespring.financial_transaction.repository.FinancialTransactionRepository;
import com.reactivespring.financial_transaction.service.FinancialTransactionService;
import com.reactivespring.financial_transaction.service.PaymentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FinancialTransactionServiceImpl implements FinancialTransactionService {
    private final FinancialTransactionRepository repository;
    private final PaymentService paymentService;
    private final EntityDTOMapper entityDTOMapper;

    public FinancialTransactionServiceImpl(FinancialTransactionRepository repository, PaymentService paymentService, EntityDTOMapper entityDTOMapper) {
        this.repository = repository;
        this.paymentService = paymentService;
        this.entityDTOMapper = entityDTOMapper;
    }

    public Mono<DataListPaymentResponse> getTransactions(
            LocalDate dateFrom, LocalDate dateTo, String userId, String service,
            String status, String reference, int offset, int limit) {

        return repository.countByFilters(dateFrom, dateTo, userId, service, status, reference)
                .flatMap(totalCount -> {
                    return repository.findByFilters(dateFrom, dateTo, userId, service, status, reference, offset, limit)
                            .flatMap(this::enrichWithPaymentDetails)
                            .collectList()
                            .map(payments -> {
                                payments.sort(Comparator.comparing(PaymentData::getPaymentId).reversed());
                                return createResponse(payments, offset, limit, totalCount);
                            });
                });
    }

    private Mono<PaymentData> enrichWithPaymentDetails(FinancialTransaction transaction) {
        return paymentService.retrieveFinancialTransaction(transaction.getPaymentId())
                .map(paymentResponse -> entityDTOMapper.mapToPaymentData(transaction, paymentResponse))
                .onErrorResume(e -> Mono.just(entityDTOMapper.mapToPaymentData(transaction, null)));
    }


    private DataListPaymentResponse createResponse(List<PaymentData> payments, int offset, int limit, long totalCount) {
        DataListPaymentResponse response = new DataListPaymentResponse();
        response.setPayments(payments);
        response.setTotalElements(totalCount);
        response.setTotalPages((int) Math.ceil((double) totalCount / limit));
        response.setLinks(createHateoasLinks(offset, limit, totalCount));
        return response;
    }

    private List<Link> createHateoasLinks(int offset, int limit, long totalCount) {
        List<Link> links = new ArrayList<>();
        String baseUrl = "/api/v1/transactions";
        int totalPages = (int) Math.ceil((double) totalCount / limit);
        int currentPage = offset / limit;

        // Self link
        links.add(new Link("self", buildPageUrl(baseUrl, offset, limit), "GET"));

        // Previous page
        if (currentPage > 0) {
            links.add(new Link("prev", buildPageUrl(baseUrl, offset - limit, limit), "GET"));
        }

        // Next page
        if (currentPage < totalPages - 1) {
            links.add(new Link("next", buildPageUrl(baseUrl, offset + limit, limit), "GET"));
        }

        // First page
        if (currentPage > 0) {
            links.add(new Link("first", buildPageUrl(baseUrl, 0, limit), "GET"));
        }

        // Last page
        if (totalPages > 0 && currentPage < totalPages - 1) {
            links.add(new Link("last", buildPageUrl(baseUrl, (totalPages - 1) * limit, limit), "GET"));
        }

        return links;
    }

    private String buildPageUrl(String baseUrl, int offset, int limit) {
        return String.format("%s?offset=%d&limit=%d", baseUrl, offset, limit);
    }
}