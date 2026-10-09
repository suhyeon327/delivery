package com.example.delivery.payment.controller;

import com.example.delivery.global.security.UserDetailsImpl;
import com.example.delivery.payment.dto.request.PaymentRequestDto;
import com.example.delivery.payment.service.PaymentService;
import com.example.delivery.user.entity.UserRoleEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Secured(UserRoleEnum.Authority.CUSTOMER)
    @PostMapping

    public void processPayment(
            @Valid @RequestBody PaymentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        paymentService.processPayment(requestDto, userDetails.getUser());
    }
}
