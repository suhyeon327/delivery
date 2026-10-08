package com.example.delivery.menu.dto.response;

import com.example.delivery.menu.entity.Menu;
import lombok.Getter;

@Getter
public class MenuResponseDto {
    private String name;
    private Long price;
    private String description;

    public MenuResponseDto(Menu menu) {
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
    }
}
