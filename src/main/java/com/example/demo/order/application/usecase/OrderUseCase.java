package com.example.demo.order.application.usecase;

import com.example.demo.order.presentation.dto.req.CreateOrderRequest;
import com.example.demo.order.presentation.dto.res.OrderResponse;

import java.time.LocalDate;
import java.util.List;

public interface OrderUseCase {
    OrderResponse create(CreateOrderRequest request);

    List<OrderResponse> findAll();

    List<OrderResponse> findSettlementCandidates(LocalDate settlementDate);
}
