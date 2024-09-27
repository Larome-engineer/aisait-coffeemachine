package aisa.coffeemachine.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@Table(name = "statistics")
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {

    @Id
    @Column(name = "stat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statId;

    @Column(name = "stat_start_date")
    private Date startStat;

    @Column(name = "stat_quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "stat_drink_id")
    private Drink drink;

}
