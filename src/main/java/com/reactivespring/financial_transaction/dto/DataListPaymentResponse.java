package com.reactivespring.financial_transaction.dto;

import lombok.Data;

import java.util.List;

@Data
public class DataListPaymentResponse {
    private List<PaymentData> payments;
    private List<Link> links;
    private long totalElements;
    private int totalPages;
}
