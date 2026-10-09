package com.example.delivery.order.dto.request;

import com.example.delivery.order.entity.OrderStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderStatusResponseDto {
    @NotNull
    private OrderStatusEnum status;
}
