package com.msvc.order.mapper;

import com.msvc.order.config.CentralMapperConfig;
import com.msvc.order.dto.orderdetailingredient.response.OrderDetailIngredientResponse;
import com.msvc.order.entity.OrderDetailIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface OrderDetailIngredientMapper {

    @Mapping(target = "orderDetailId", source = "orderDetail.id")
    OrderDetailIngredientResponse toResponse(OrderDetailIngredient detailIngredient);

    List<OrderDetailIngredientResponse> toResponseList(List<OrderDetailIngredient> detailIngredients);

}
