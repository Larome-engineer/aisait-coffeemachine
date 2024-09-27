package aisa.coffeemachine.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter
@Setter
@Table(name = "drinks")
@NoArgsConstructor
@AllArgsConstructor
public class Drink {

    @Id
    @Column(name = "drink_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "drink_name")
    private String drinkName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "drink")
    private Recipe recipe;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> order;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Statistic> statistic;

}
