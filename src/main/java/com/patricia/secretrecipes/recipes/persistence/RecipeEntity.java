package com.patricia.secretrecipes.recipes.persistence;

import com.patricia.secretrecipes.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

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
