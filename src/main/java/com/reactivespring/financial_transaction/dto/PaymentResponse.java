package com.reactivespring.financial_transaction.dto;

import com.reactivespring.financial_transaction.enums.PaymentStatusDto;
import lombok.Data;

@Data
public class PaymentResponse {
    private String id;
    private PaymentDetailsDto details;
    private PaymentStatusDto status;
}
