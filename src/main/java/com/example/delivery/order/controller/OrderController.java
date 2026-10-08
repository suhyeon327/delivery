package com.example.delivery.order.controller;

import com.example.delivery.global.security.UserDetailsImpl;
import com.example.delivery.order.dto.request.OrderRequestDto;
import com.example.delivery.order.service.OrderService;
import com.example.delivery.user.entity.UserRoleEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Transactional
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

}
