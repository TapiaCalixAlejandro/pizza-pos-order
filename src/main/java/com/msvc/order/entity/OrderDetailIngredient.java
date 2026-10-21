package com.msvc.order.entity;

import com.msvc.order.enums.OrderDetailIngredientAction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "order_detail_ingredients")
public class OrderDetailIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id", nullable = false)
    private OrderDetail orderDetail;

    @Column(name = "ingredient_id", nullable = false)
    private Long ingredientId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderDetailIngredientAction action;

    @Column(
            nullable = false,
            precision = 10,
            scale = 3
    )
    private BigDecimal quantity;

    @Column(
            name = "unit_price",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal unitPrice;

    public OrderDetailIngredient() {
    }

    public OrderDetailIngredient(
            Long id,
            OrderDetail orderDetail,
            Long ingredientId,
            OrderDetailIngredientAction action,
            BigDecimal quantity,
            BigDecimal unitPrice
    ) {
        this.id = id;
        this.orderDetail = orderDetail;
        this.ingredientId = ingredientId;
        this.action = action;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
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
