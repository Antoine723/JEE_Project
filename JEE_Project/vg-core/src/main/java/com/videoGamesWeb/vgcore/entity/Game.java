package com.videoGamesWeb.vgcore.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
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

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private List<GameConsole> gameConsoles;

    @JsonGetter("gamePrice")
    public Map<String, Float> gamePrice(){
        return this.gameConsoles.stream().collect(Collectors.toMap(gc -> gc.getConsole().getName(), GameConsole::getPrice));
    }

    @JsonGetter("gameImg")
    public Map<String, String> gameImg(){
        return this.gameConsoles.stream().collect(Collectors.toMap(gc -> gc.getConsole().getName(), GameConsole::getImg));
    }

    @JsonGetter("consoles")
    public List<String> jsonConsoles() {
        return this.gameConsoles.stream().map(gameConsole -> gameConsole.getConsole().getName()).sorted().collect(Collectors.toList());
    }

    public Game(long id){
        this.id = id;
    } //TODO : find how to fix this (autoincrement on both child tables)
}
