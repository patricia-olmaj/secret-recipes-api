package com.patricia.secretrecipes.auth.application;

import com.patricia.secretrecipes.auth.api.dto.AuthResponse;
import com.patricia.secretrecipes.auth.api.dto.LoginRequest;
import com.patricia.secretrecipes.auth.api.dto.RegisterRequest;
import com.patricia.secretrecipes.exception.UserAlreadyExistsException;
import com.patricia.secretrecipes.auth.persistence.Role;
import com.patricia.secretrecipes.auth.persistence.UserEntity;
import com.patricia.secretrecipes.auth.persistence.UserRepository;
import com.patricia.secretrecipes.exception.UserNotFoundException;
import com.patricia.secretrecipes.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login (LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new UserNotFoundException("Usuario no encontrado"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("El usuario ya está registrado");
        }
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
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
