package com.videoGamesWeb.vgcore.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name="game")
public class Game extends Product {

    @Column(name="offline_players_number")
    private int offlinePlayersNumber;

    @Column(name="online_players_number")
    private int onlinePlayersNumber;

    @ManyToMany
    @JoinTable(name="games_on_consoles",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name="console_id"))
    private List<Console> consoles;

    @JsonGetter("consoles")
    public List<String> jsonConsoles() {
        return consoles.stream().map(Console::getName).collect(Collectors.toList());
    }

    public Game(long id){
        this.id = id;
    } //TODO : find how to fix this (autoincrement on both child tables)
}
