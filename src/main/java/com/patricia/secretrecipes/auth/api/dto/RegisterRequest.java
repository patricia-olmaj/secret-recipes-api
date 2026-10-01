package com.patricia.secretrecipes.auth.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String country;

}
