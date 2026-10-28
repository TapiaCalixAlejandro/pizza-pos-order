package com.msvc.order.repository;

import com.msvc.order.entity.Order;
import com.msvc.order.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndDeletedAtIsNull(Long id);

    List<Order> findByCustomerIdAndDeletedAtIsNull(Long customerId);

    boolean existsByOrderNumberAndDeletedAtIsNull(Long orderNumber);

    List<Order> findAllByDeletedAtIsNull();

    List<Order> findByStatusAndDeletedAtIsNull(OrderStatus status);

    @Query(value = "SELECT nextval('order_number_seq')", nativeQuery = true)
    Long getNextOrderNumber();
}
