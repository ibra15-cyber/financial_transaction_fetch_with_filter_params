package com.reactivespring.financial_transaction.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentData {
    private String paymentId;
    private LocalDate transactionDate;
    private String userId;
    private String service;
    private String status;
    private String reference;
    private BigDecimal amount;
    private String currency;
    private PaymentDetailsDto paymentDetails;
}
