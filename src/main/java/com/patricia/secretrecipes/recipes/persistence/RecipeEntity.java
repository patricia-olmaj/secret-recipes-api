package com.patricia.secretrecipes.recipes.persistence;

import com.patricia.secretrecipes.auth.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name="recipes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    Category category;
    private String ingredients;
    private String instructions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
