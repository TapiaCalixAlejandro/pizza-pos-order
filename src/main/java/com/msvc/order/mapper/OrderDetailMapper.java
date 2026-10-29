package com.msvc.order.mapper;

import com.msvc.order.config.CentralMapperConfig;
import com.msvc.order.dto.orderdetail.request.OrderDetailRequest;
import com.msvc.order.dto.orderdetail.response.OrderDetailResponse;
import com.msvc.order.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        config = CentralMapperConfig.class,
        uses = OrderDetailIngredientMapper.class
)
public interface OrderDetailMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "productName", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "discount", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "detailIngredients", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    OrderDetail toEntity(OrderDetailRequest request);

    @Mapping(target = "orderId", source = "order.id")
    OrderDetailResponse toResponse(OrderDetail orderDetail);

    List<OrderDetailResponse> toResponseList(List<OrderDetail> orderDetails);

}
