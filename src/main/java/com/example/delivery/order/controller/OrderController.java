package com.example.delivery.order.controller;

import com.example.delivery.global.security.UserDetailsImpl;
import com.example.delivery.order.dto.request.OrderRequestDto;
import com.example.delivery.order.dto.request.OrderStatusResponseDto;
import com.example.delivery.order.dto.response.OrderResponseDto;
import com.example.delivery.order.service.OrderService;
import com.example.delivery.user.entity.UserRoleEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Secured(UserRoleEnum.Authority.CUSTOMER)
    @PostMapping
    public void createOrder(
            @Valid @RequestBody OrderRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        orderService.createOrder(requestDto, userDetails.getUser());
    }

    @Secured({UserRoleEnum.Authority.OWNER, UserRoleEnum.Authority.CUSTOMER})
    @GetMapping
    public List<OrderResponseDto> getOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return orderService.getOrders(userDetails.getUser());
    }

    @Secured(UserRoleEnum.Authority.CUSTOMER)
    @DeleteMapping("/{orderId}")
    public void cancelOrder(
            @PathVariable Long orderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        orderService.cancelOrder(orderId, userDetails.getUser());
    }

    @Secured(UserRoleEnum.Authority.OWNER)
    @PatchMapping("/{orderId}/status")
    public void changeOrderStatus(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderStatusResponseDto responseDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        orderService.changeOrderStatus(orderId, responseDto, userDetails.getUser());
    }
}
