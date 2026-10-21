package com.msvc.order.repositories;

import com.msvc.order.entity.OrderDetailIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailIngredientRepository extends JpaRepository<OrderDetailIngredient, Long> {

    List<OrderDetailIngredient> findAllByOrderDetailId(Long orderDetailId);

}
