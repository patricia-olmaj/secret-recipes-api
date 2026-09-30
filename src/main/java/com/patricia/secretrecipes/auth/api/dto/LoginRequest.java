package com.patricia.secretrecipes.auth.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    public String username;

    public String password;

}
