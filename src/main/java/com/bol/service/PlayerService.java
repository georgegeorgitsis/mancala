package com.bol.service;

import com.bol.model.Pit;
import com.bol.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PitService pitService;

    /**
     * Initialising Player
     *
     * @return Player
     */
    public Player initPlayer() {
        List<Pit> pits = pitService.initPits();
        Player player = new Player(pits);

        return player;
    }
}
