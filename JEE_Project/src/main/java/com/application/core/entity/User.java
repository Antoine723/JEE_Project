package com.application.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Getter @Setter
public class User extends GenericEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @Column(name = "nbrComments")
    private int nbrComments;

}
