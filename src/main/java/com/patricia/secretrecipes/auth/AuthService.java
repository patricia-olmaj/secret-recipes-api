package com.patricia.secretrecipes.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public AuthResponse login (LoginRequest request){
        return new AuthResponse();

    }

    public AuthResponse register (RegisterRequest request){
        return new AuthResponse();
    }
}
