package com.example.delivery.user.controller;

import com.example.delivery.user.dto.request.SignupRequestDto;
import com.example.delivery.user.dto.response.UserResponse;
import com.example.delivery.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponse signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }
}
