package com.msvc.order.mapper;

import com.msvc.order.config.CentralMapperConfig;
import com.msvc.order.dto.orderdetail.response.OrderDetailResponse;
import com.msvc.order.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface OrderDetailMapper {

    @Mapping(target = "orderId", source = "order.id")
    OrderDetailResponse toResponse(OrderDetail orderDetail);

    List<OrderDetailResponse> toResponseList(List<OrderDetail> orderDetails);

}
