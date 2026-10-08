package com.example.delivery.order.repository;

import com.example.delivery.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByMenuId(Long menuId);
}
