package com.patricia.secretrecipes.auth.application;

import com.patricia.secretrecipes.auth.api.dto.AuthResponse;
import com.patricia.secretrecipes.auth.api.dto.LoginRequest;
import com.patricia.secretrecipes.auth.api.dto.RegisterRequest;
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
