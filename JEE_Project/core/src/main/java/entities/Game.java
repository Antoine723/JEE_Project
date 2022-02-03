package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Game extends Product {

    @Column(name="offline_players_number")
    private int offlinePlayersNumber;

    @Column(name="offline_players_number")
    private int onlinePlayersNumber;

    @OneToMany
    @JoinColumn(name = "game_id")
    private List<Console> consoles = new ArrayList<>();
}
