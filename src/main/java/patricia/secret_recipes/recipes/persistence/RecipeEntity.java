package patricia.secret_recipes.recipes.persistence;

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
    @ManyToOne
    private UserEntity user;
}
