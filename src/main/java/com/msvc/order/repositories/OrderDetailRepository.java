package com.msvc.order.repositories;

import com.msvc.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Optional<OrderDetail> findByIdAndDeletedAtIsNull(Long id);

    List<OrderDetail> findAllByOrderIdAndDeletedAtIsNull(Long orderId);

    List<OrderDetail> findAllByProductIdAndDeletedAtIsNull(Long productId);

}
