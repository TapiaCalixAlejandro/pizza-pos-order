package com.msvc.order.service;

import com.msvc.order.dto.order.request.OrderRequest;
import com.msvc.order.dto.order.response.OrderResponse;
import com.msvc.order.enums.OrderStatus;
import com.msvc.order.enums.PaymentStatus;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest request);

    OrderResponse getOrderById(Long id);

    List<OrderResponse> findAll();

    List<OrderResponse> getByStatus(OrderStatus status);

    OrderResponse updateOrderStatus(Long id, OrderStatus status);

    OrderResponse updatePaymentStatus(Long id, PaymentStatus newPaymentStatus);

    void cancelOrder(Long id);

}
