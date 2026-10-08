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
        Menu menu = new Menu(name, price, description, user);
        menuRepository.save(menu);
    }

    public List<MenuResponseDto> getMenus() {
        return menuRepository.findAll().stream().map(MenuResponseDto::new).toList();
//        return menuRepository.findByDeletedAtIsNull().stream().map(MenuResponseDto::new).toList();
    }
}
