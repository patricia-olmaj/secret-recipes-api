package com.patricia.secretrecipes.auth.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users" , uniqueConstraints= {@UniqueConstraint(columnNames = {"username"})})
public class UserEntity  implements UserDetails{
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String lastname;
    private String firstname;
    private String password;
    private String country;
    @Enumerated(EnumType.STRING)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
