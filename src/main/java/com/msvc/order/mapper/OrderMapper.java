package com.msvc.order.mapper;

import com.msvc.order.config.CentralMapperConfig;
import com.msvc.order.dto.order.request.OrderRequest;
import com.msvc.order.dto.order.response.OrderResponse;
import com.msvc.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        config = CentralMapperConfig.class,
        uses = OrderDetailMapper.class
)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderNumber", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "paymentStatus", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "tax", ignore = true)
    @Mapping(target = "discount", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Order toEntity(OrderRequest request);

    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponseList(List<Order> orders);

}
