package com.msvc.order.dto.orderdetailingredient.response;

import com.msvc.order.entity.OrderDetail;
import com.msvc.order.enums.OrderDetailIngredientAction;

import java.math.BigDecimal;

public class OrderDetailIngredientResponse {

    private Long id;
    private Long orderDetailId;
    private Long ingredientId;
    private OrderDetailIngredientAction action;
    private BigDecimal quantity;
    private BigDecimal unitPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public OrderDetailIngredientAction getAction() {
        return action;
    }

    public void setAction(OrderDetailIngredientAction action) {
        this.action = action;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}
