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
    private Set<GameConsole> gameConsoles;

    @JsonGetter("gamePrice")
    public Map<String, Float> gamePrice(){
        Map<String, Float> map = new HashMap();
        this.gameConsoles.forEach(gc -> {
            map.put(gc.getConsole().getName(), gc.getPrice());
        });
        return map;
    }

    @JsonGetter("gameImg")
    public Map<String, String> gameImg(){
        Map<String, String> map = new HashMap();
        this.gameConsoles.forEach(gc -> {
            map.put(gc.getConsole().getName(), gc.getImg());
        });
        return map;
    }

    @JsonGetter("consoles")
    public List<String> jsonConsoles() {
        return gameConsoles.stream().map(gameConsole -> gameConsole.getConsole().getName()).sorted().collect(Collectors.toList());
    }

    public Game(long id){
        this.id = id;
    } //TODO : find how to fix this (autoincrement on both child tables)
}
