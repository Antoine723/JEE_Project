package com.videoGamesWeb.vgcore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class GameConsoleId implements Serializable {
    @Column(name = "game_id")
    private long gameId;

    @Column(name = "console_id")
    private long consoleId;

}
