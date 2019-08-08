package com.bol.services;

import com.bol.models.Pit;
import com.bol.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PitService pitService;

    @Autowired
    PlayerService(PitService pitService) {
        this.pitService = pitService;
    }

    private PitService getPitService() {
        return pitService;
    }

    /**
     * Initialising Player
     */
    public Player initPlayer() {
        List<Pit> pits = this.getPitService().initPits();
        return new Player(pits);
    }

    /**
     * Summarise all player's stones
     */
    public int sumStones(Player player) {
        int stonesToBigPit = 0;
        for (Pit pit : player.getRegularPits()) {
            stonesToBigPit += pit.getStones();
        }

        return addStonesToBigPit(player, stonesToBigPit);
    }

    /**
     * Add stones to big pit
     */
    public int addStonesToBigPit(Player player, int stonesToAdd) {
        Pit bigPit = player.getBigPit();
        bigPit.addStones(stonesToAdd);

        return bigPit.getStones();
    }
}
