package com.example.delivery.payment.dto.request;

import com.example.delivery.payment.entity.PaymentMethodEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PaymentRequestDto {

    @NotNull
    private Long orderId;

    @NotNull
    private PaymentMethodEnum method;
}
