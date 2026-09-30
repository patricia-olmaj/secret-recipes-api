package com.patricia.secretrecipes.auth;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    public String username;

    public String password;

    public String firstname;

    public String lastname;

    public String country;

}
