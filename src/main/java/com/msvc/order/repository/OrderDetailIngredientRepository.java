package com.msvc.order.repository;

import com.msvc.order.entity.OrderDetailIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailIngredientRepository extends JpaRepository<OrderDetailIngredient, Long> {

    List<OrderDetailIngredient> findAllByOrderDetailId(Long orderDetailId);

}
