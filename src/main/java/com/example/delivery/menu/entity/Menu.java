package com.example.delivery.menu.entity;


import com.example.delivery.global.entity.BaseEntity;
import com.example.delivery.menu.dto.request.MenuRequestDto;
import com.example.delivery.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "menus")
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    private String description;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Menu(String name, Long price, String description, User user) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void update(String name, Long price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
