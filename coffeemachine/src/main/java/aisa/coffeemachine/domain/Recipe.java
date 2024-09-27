package aisa.coffeemachine.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter
@Setter
@Table(name = "recipes")
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @Column(name = "recipe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;

    @Column(name = "recipe_desc")
    private String recipeDesc;

    @OneToOne
    @JoinColumn(name = "recipe_drink_id")
    private Drink drink;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_inventory",
            joinColumns = @JoinColumn(name = "ri_recipe_id", referencedColumnName = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ri_invent_id", referencedColumnName = "inv_id")
    )
    private List<Inventory> inventories;

}
