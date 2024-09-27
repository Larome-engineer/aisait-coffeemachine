package aisa.coffeemachine.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter
@Setter
@Table(name = "inventories")
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @Column(name = "inv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "inv_name")
    private String inventoryName;

    @Column(name = "inv_num")
    private int inventoryNum;

    @Column(name = "inv_unit")
    private String inventoryUnit;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_inventory",
            joinColumns = @JoinColumn(name = "ri_invent_id", referencedColumnName = "inv_id"),
            inverseJoinColumns = @JoinColumn(name = "ri_recipe_id", referencedColumnName = "recipe_id")
    )
    private List<Recipe> recipes;
}
