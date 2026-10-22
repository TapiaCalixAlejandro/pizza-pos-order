package com.msvc.order.dto.order.response;

import com.msvc.order.dto.orderdetail.response.OrderDetailResponse;
import com.msvc.order.enums.OrderServiceType;
import com.msvc.order.enums.OrderStatus;
import com.msvc.order.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {

    private Long id;
    private Long customerId;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private Long orderNumber;
    private OrderServiceType serviceType;
    private String customerName;
    private String notes;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal discount;
    private BigDecimal total;
    private List<OrderDetailResponse> orderDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
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

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderDetailResponse> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailResponse> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
