package com.msvc.order.repositories;

import com.msvc.order.entity.Order;
import com.msvc.order.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndDeletedAtIsNull(Long id);

    List<Order> findByCustomerIdAndDeletedAtIsNull(Long customerId);

    boolean existsByOrderNumberAndDeletedAtIsNull(Long orderNumber);

    List<Order> findAllByDeletedAtIsNull();

    List<Order> findByStatusAndDeletedAtIsNull(OrderStatus status);

}
