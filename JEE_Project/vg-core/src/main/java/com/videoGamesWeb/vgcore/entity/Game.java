package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("2")
@Table(name="Game")
public class Game extends Product {

    @Column(name="offline_players_number")
    private int offlinePlayersNumber;

    @Column(name="online_players_number")
    private int onlinePlayersNumber;

    @ManyToMany
    @JoinTable(name="games_on_consoles",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name="console_id"))
    private List<Console> consoles = new ArrayList<>();
}
