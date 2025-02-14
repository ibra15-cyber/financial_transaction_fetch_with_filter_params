package com.reactivespring.financial_transaction.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("financial_transactions")
public class FinancialTransaction {
    @Id
    private Long id;

    @Column("payment_id")
    private String paymentId;

    @Column("transaction_date")
    private LocalDate transactionDate;

    @Column("user_id")
    private String userId;

    @Column("service")
    private String service;

    @Column("status")
    private String status;

    @Column("reference")
    private String reference;

    @Column("amount")
    private BigDecimal amount;

    @Column("currency")
    private String currency;
}