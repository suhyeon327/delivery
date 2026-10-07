package com.example.delivery.user.dto.response;

import com.example.delivery.user.entity.User;
import com.example.delivery.user.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String username;
    private final UserRoleEnum role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}