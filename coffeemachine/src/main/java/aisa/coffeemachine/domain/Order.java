package aisa.coffeemachine.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "o_date")
    private Date date;

    @Column(name = "o_quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "o_drink_id")
    private Drink drink;
}
