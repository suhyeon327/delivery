package com.example.delivery.menu.controller;

import com.example.delivery.global.security.UserDetailsImpl;
import com.example.delivery.menu.dto.request.MenuRequestDto;
import com.example.delivery.menu.service.MenuService;
import com.example.delivery.user.entity.UserRoleEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @Secured(UserRoleEnum.Authority.OWNER)
    @PostMapping
    public void createMenu(
            @Valid @RequestBody MenuRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        menuService.createMenu(requestDto, userDetails.getUser());
    }
}
