package com.reactivespring.financial_transaction.enums;

public enum PaymentStatusDto {
    PENDING,
    COMPLETED,
    FAILED,
    CANCELED,
    REFUNDED,
    REJECTED,
    PENDING_VERIFICATION,
    IN_PROGRESS,
    CHARGEBACK,
    EXPIRED,
    AUTHORIZED,
    PROCESSING,
    PARTIALLY_REFUNDED;

    // You can also add helper methods or logic if needed
    public boolean isSuccessful() {
        return this == COMPLETED;
    }

    public boolean isFailed() {
        return this == FAILED || this == REJECTED || this == CHARGEBACK;
    }
}
