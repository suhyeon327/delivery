package com.example.delivery.menu.service;

import com.example.delivery.menu.dto.request.MenuRequestDto;
import com.example.delivery.menu.dto.response.MenuResponseDto;
import com.example.delivery.menu.entity.Menu;
import com.example.delivery.menu.repository.MenuRepository;
import com.example.delivery.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public void createMenu(MenuRequestDto requestDto, User user) {
        String name = requestDto.getName();
        Long price = requestDto.getPrice();
        String description = requestDto.getDescription();

        // 메뉴 중복 확인
        Optional<Menu> checkName = menuRepository.findByName(name);
        if (checkName.isPresent()) {
            throw new IllegalArgumentException("중복된 메뉴가 존재합니다.");
        }

        // 메뉴 등록
        Menu menu = new Menu(user, name, price, description);
        menuRepository.save(menu);
    }

    public List<MenuResponseDto> getMenus() {
        return menuRepository.findByDeletedAtIsNull().stream().map(MenuResponseDto::new).toList();
    }

    public MenuResponseDto getMenu(Long menuId) {
        Menu menu = menuRepository.findByIdAndDeletedAtIsNull(menuId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴가 없습니다."));

        return new MenuResponseDto(menu);
    }

    public MenuResponseDto updateMenu(Long menuId, @Valid MenuRequestDto requestDto, User user) {
        String name = requestDto.getName();
        Long price = requestDto.getPrice();
        String description = requestDto.getDescription();

        Menu menu = menuRepository.findByIdAndDeletedAtIsNull(menuId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴가 없습니다."));

        if (!menu.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인이 등록한 메뉴가 아닙니다.");
        }

        menu.update(name, price, description);

        return new MenuResponseDto(menu);
    }

    public void deleteMenu(Long menuId, User user) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴가 존재하지 않습니다."));

        if (!menu.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인이 등록한 메뉴가 이닙니다.");
        }

        menu.delete();
    }
}
