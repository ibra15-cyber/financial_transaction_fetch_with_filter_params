package com.reactivespring.financial_transaction.service.impl;

import com.reactivespring.financial_transaction.dto.PaymentResponse;
import com.reactivespring.financial_transaction.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {
    // you might have a WebClient or another repository here
    private final WebClient webClient;

    public PaymentServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://payment-api-url").build();
    }

    @Override
    public Mono<PaymentResponse> retrieveFinancialTransaction(String paymentId) {
        return webClient.get()
                .uri("/payments/{id}", paymentId)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .onErrorResume(e -> {
                    // Log error and return empty
                    return Mono.empty();
                });
    }
}
