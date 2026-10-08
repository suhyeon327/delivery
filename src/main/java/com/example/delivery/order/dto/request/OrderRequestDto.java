package com.example.delivery.order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderRequestDto {

    @NotNull
    private Long menuId;

    @NotNull
    @Min(1)
    private Long quantity;

    @NotBlank
    private String address;
}
