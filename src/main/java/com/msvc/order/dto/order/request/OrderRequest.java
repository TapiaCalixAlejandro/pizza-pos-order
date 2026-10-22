package com.msvc.order.dto.order.request;

import com.msvc.order.dto.orderdetail.request.OrderDetailRequest;
import com.msvc.order.enums.OrderServiceType;
import com.msvc.order.enums.OrderStatus;
import com.msvc.order.enums.PaymentStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class OrderRequest {

    private Long customerId;

    @NotNull(message = "Status is required.")
    private OrderStatus status;

    @NotNull(message = "Payment status is required.")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Service type is required.")
    private OrderServiceType serviceType;

    @Size(max = 150, message = "Customer name must not exceed 150 characters.")
    private String customerName;

    @Size(max = 500, message = "Notes must not exceed 500 characters.")
    private String notes;

    @NotEmpty(message = "Order details are required.")
    @Valid
    private List<OrderDetailRequest> orderDetails;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(OrderServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderDetailRequest> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailRequest> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
