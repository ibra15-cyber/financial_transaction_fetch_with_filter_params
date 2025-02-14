package com.reactivespring.financial_transaction.dto;

import lombok.Data;

@Data
public class PaymentDetailsDto {
    private String method;
    private String provider;
    private String description;
}
