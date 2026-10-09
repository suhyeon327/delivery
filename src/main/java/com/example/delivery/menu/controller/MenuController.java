package com.example.delivery.menu.controller;

import com.example.delivery.global.security.UserDetailsImpl;
import com.example.delivery.menu.dto.request.MenuRequestDto;
import com.example.delivery.menu.dto.response.MenuResponseDto;
import com.example.delivery.menu.service.MenuService;
import com.example.delivery.user.entity.UserRoleEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<MenuResponseDto> getMenus() {
        return menuService.getMenus();
    }

    @GetMapping("/{menuId}")
    public MenuResponseDto getMenu(@PathVariable Long menuId) {
        return menuService.getMenu(menuId);
    }

    @Secured(UserRoleEnum.Authority.OWNER)
    @PutMapping("/{menuId}")
    public MenuResponseDto updateMenu(
            @PathVariable Long menuId,
            @Valid @RequestBody MenuRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return menuService.updateMenu(menuId, requestDto, userDetails.getUser());
    }

    @Secured(UserRoleEnum.Authority.OWNER)
    @DeleteMapping("/{menuId}")
    public void deleteMenu(
            @PathVariable Long menuId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        menuService.deleteMenu(menuId, userDetails.getUser());
    }
}
