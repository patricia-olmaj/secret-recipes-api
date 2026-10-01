package com.patricia.secretrecipes.auth.application;

import com.patricia.secretrecipes.auth.api.dto.AuthResponse;
import com.patricia.secretrecipes.auth.api.dto.LoginRequest;
import com.patricia.secretrecipes.auth.api.dto.RegisterRequest;
import com.patricia.secretrecipes.auth.persistence.Role;
import com.patricia.secretrecipes.auth.persistence.UserEntity;
import com.patricia.secretrecipes.auth.persistence.UserRepository;
import com.patricia.secretrecipes.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse login (LoginRequest request){
        return new AuthResponse();

    }

    public AuthResponse register(RegisterRequest request) {
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
