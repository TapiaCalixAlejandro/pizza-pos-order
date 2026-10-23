package com.msvc.order.mapper;

import com.msvc.order.config.CentralMapperConfig;
import com.msvc.order.dto.order.response.OrderResponse;
import com.msvc.order.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface OrderMapper {

    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponseList(List<Order> orders);

}
