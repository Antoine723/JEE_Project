package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Order")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "orderNumber")
    private UUID orderNumber;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
