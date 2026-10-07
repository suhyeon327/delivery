package com.example.delivery.user.service;

import com.example.delivery.user.dto.request.SignupRequestDto;
import com.example.delivery.user.dto.response.UserResponse;
import com.example.delivery.user.entity.User;
import com.example.delivery.user.entity.UserRoleEnum;
import com.example.delivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse signup(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = requestDto.getRole();

        // 아이디 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        User user = new User(username, password, role);
        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser);
    }
}
