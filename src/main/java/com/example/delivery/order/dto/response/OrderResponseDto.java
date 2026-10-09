package com.example.delivery.order.dto.response;

import com.example.delivery.order.entity.Order;
import com.example.delivery.order.entity.OrderStatusEnum;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private String menuName;
    private Long quantity;
    private Long price;
    private Long totalAmount;
    private String address;
    private OrderStatusEnum status;

    public OrderResponseDto(Order order) {
        this.menuName = order.getMenu().getName();
        this.quantity = order.getQuantity();
        this.price = order.getMenu().getPrice();
        this.totalAmount = order.getTotalAmount();
        this.address = order.getAddress();
        this.status = order.getStatus();
    }
}
