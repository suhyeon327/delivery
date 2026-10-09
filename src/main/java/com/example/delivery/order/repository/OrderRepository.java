package com.example.delivery.order.repository;

import com.example.delivery.order.entity.Order;
import org.apache.logging.log4j.simple.internal.SimpleProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Id(Long id);

    List<Order> findByMenu_User_Id(Long id);
}
