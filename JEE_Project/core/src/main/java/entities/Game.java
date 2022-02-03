package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Game extends Product{

    @Column(name="offline_players_number")
    private int offlinePlayersNumber;

    @Column(name="offline_players_number")
    private int onlinePlayersNumber;
}
