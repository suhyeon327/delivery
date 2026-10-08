package com.example.delivery.menu.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MenuRequestDto {

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Long price;

    private String description;
}
