package aisa.coffeemachine.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe_inventory")
public class RecipeInventory {

    @Id
    @Column(name = "ri_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ri_recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ri_invent_id")
    private Inventory inventory;

    @Column(name = "ri_num")
    private int numOfIngredient;

}
