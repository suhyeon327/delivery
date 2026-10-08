package com.example.delivery.menu.repository;

import com.example.delivery.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByName(String name);

    List<Menu> findByDeletedAtIsNull();

    Optional<Menu> findByIdAndDeletedAtIsNull(Long menuId);
}
