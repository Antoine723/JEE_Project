package entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
@Getter @Setter
public class Comment extends GenericEntity {

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private int rating;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
