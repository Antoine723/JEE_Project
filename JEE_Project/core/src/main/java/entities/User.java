package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private int gender;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "address")
    private String address;

    @Column(name = "postcode")
    private Long postcode;

    @Column(name = "city")
    private String city;

    @Column(name = "mail")
    private String mail;

    @Column(name = "nbrComments")
    private int nbrComments;

}
