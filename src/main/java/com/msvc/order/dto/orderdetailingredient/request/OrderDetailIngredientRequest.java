package com.msvc.order.dto.orderdetailingredient.request;

import com.msvc.order.enums.OrderDetailIngredientAction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class OrderDetailIngredientRequest {

    @NotNull(message = "Ingredient ID is required.")
    private Long ingredientId;

    @NotNull(message = "Action is required.")
    private OrderDetailIngredientAction action;

    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be greater than zero.")
    private BigDecimal quantity;

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

}
