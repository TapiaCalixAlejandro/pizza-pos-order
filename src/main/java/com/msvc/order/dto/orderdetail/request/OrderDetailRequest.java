package com.msvc.order.dto.orderdetail.request;

import com.msvc.order.dto.orderdetailingredient.request.OrderDetailIngredientRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public class OrderDetailRequest {

    @NotNull(message = "Product id is required.")
    private Long productId;

//    @NotBlank(message = "Product name is required.")
//    @Size(max = 150, message = "Product name  must not exceed 150 characters.")
//    private String productName;

    @NotNull(message = "Quantity is required.")
    @Positive(message = "Quantity must be greater than zero.")
    private Integer quantity;

//    @NotNull(message = "Unit price is required.")
//    @Positive(message = "Unit price mest be greater than zero.")
//    private BigDecimal unitPrice;

    @Size(max = 255, message = "Notes must not exceed 255 characters.")
    private String notes;

    @Valid
    private List<OrderDetailIngredientRequest> detailIngredients;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderDetailIngredientRequest> getDetailIngredients() {
        return detailIngredients;
    }

    public void setDetailIngredients(List<OrderDetailIngredientRequest> detailIngredients) {
        this.detailIngredients = detailIngredients;
    }

}
