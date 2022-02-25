package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
public class GameConsole{

    @EmbeddedId
    private GameConsoleId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("consoleId")
    @JoinColumn(name = "console_id")
    private Console console;

    private String img;

    private float price;

}
