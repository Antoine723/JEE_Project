package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("1")
@Table(name="Console")
public class Console extends Product {

    @ManyToMany
    @JoinTable(name="games_on_consoles",
            joinColumns = @JoinColumn(name = "console_id"),
            inverseJoinColumns = @JoinColumn(name="game_id"))
    private List<Game> games;
}
