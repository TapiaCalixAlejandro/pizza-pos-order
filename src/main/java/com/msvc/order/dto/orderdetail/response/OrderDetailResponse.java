package com.msvc.order.dto.orderdetail.response;

import com.msvc.order.dto.orderdetailingredient.response.OrderDetailIngredientResponse;
import com.msvc.order.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailResponse {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    private String notes;
    private List<OrderDetailIngredientResponse> detailIngredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderDetailIngredientResponse> getDetailIngredients() {
        return detailIngredients;
    }

    public void setDetailIngredients(List<OrderDetailIngredientResponse> detailIngredients) {
        this.detailIngredients = detailIngredients;
    }

}
