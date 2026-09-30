package com.patricia.secretrecipes.auth.api;

import com.patricia.secretrecipes.auth.api.dto.AuthResponse;
import com.patricia.secretrecipes.auth.application.AuthService;
import com.patricia.secretrecipes.auth.api.dto.LoginRequest;
import com.patricia.secretrecipes.auth.api.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping (value = "/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest request){
        return ResponseEntity.ok(new AuthResponse());
    }

    @PostMapping (value = "/register")
    public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(new AuthResponse());
    }

}
