package com.patricia.secretrecipes.auth.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String username;

    private String password;

}
