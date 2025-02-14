package com.reactivespring.financial_transaction.mapper;

import com.reactivespring.financial_transaction.dto.PaymentResponse;
import com.reactivespring.financial_transaction.entity.FinancialTransaction;
import com.reactivespring.financial_transaction.dto.PaymentData;
import org.springframework.stereotype.Component;


@Component
public class EntityDTOMapper {

    public PaymentData mapToPaymentData(FinancialTransaction transaction, PaymentResponse paymentResponse) {
        PaymentData paymentData = new PaymentData();
        paymentData.setPaymentId(transaction.getPaymentId());
        paymentData.setTransactionDate(transaction.getTransactionDate());
        paymentData.setUserId(transaction.getUserId());
        paymentData.setService(transaction.getService());
        paymentData.setStatus(transaction.getStatus());
        paymentData.setReference(transaction.getReference());
        paymentData.setAmount(transaction.getAmount());
        paymentData.setCurrency(transaction.getCurrency());

        if (paymentResponse != null) {
            paymentData.setPaymentDetails(paymentResponse.getDetails());
        }

        return paymentData;
    }

}
