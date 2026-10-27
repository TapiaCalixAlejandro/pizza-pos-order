package com.msvc.order.entity;

import com.msvc.order.enums.OrderServiceType;
import com.msvc.order.enums.OrderStatus;
import com.msvc.order.enums.PaymentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Convencion de cálculo
 * - subtotal: Suma bruta de (precio * cantidad) de todos los detalles.
 * - tax: IVA calculado sobre (subtotal - descuentos_linea - descuento_general).
 * - discount: SOLO el descuento general a nivel orden. los descuentos de linea viven en OrderDetail.
 * - total: (subtotal - descuentos_linea - descuento_general) + tax.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "order_number",
            nullable = false,
            unique = true
    )
    private Long orderNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name", length = 150)
    private String customerName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "payment_status",
            nullable = false,
            length = 20
    )
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "service_type",
            nullable = false,
            length = 30
    )
    private OrderServiceType serviceType;

    @Column(length = 500)
    private String notes;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal subtotal;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal tax;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal discount;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal total;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Order() {
    }

    public Order(
            Long customerId,
            String customerName,
            OrderServiceType serviceType,
            String notes
    ) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.serviceType = serviceType;
        this.notes = notes;
    }

    public Order(
            Long id,
            Long customerId,
            OrderStatus status,
            PaymentStatus paymentStatus,
            Long orderNumber,
            OrderServiceType serviceType,
            String customerName,
            String notes,
            BigDecimal subtotal,
            BigDecimal tax,
            BigDecimal discount,
            BigDecimal total,
            List<OrderDetail> orderDetails,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.orderNumber = orderNumber;
        this.serviceType = serviceType;
        this.customerName = customerName;
        this.notes = notes;
        this.subtotal = subtotal;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
        this.orderDetails = orderDetails;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public void addOrderDetail(OrderDetail detail) {
        if (detail == null) {
            throw new IllegalArgumentException("Order detail cannot be nul.");
        }

        this.orderDetails.add(detail);
        detail.setOrder(this);
    }

    public void removeOrderDetail(OrderDetail detail) {
        if (this.orderDetails.remove(detail)) {
            detail.setOrder(null);
        }
    }

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
