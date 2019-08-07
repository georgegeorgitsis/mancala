package com.bol.service;

import com.bol.config.GameConfiguration;
import com.bol.model.Pit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PitService {

    @Autowired
    GameConfiguration gameConfiguration;

    /**
     * Initialising Pits
     *
     * @return List<Pit>
     */
    public List<Pit> initPits() {
        List pits = new ArrayList();
        int i;
        for (i = 0; i < gameConfiguration.getNumberOfPits(); i++) {
            pits.add(new Pit(i, gameConfiguration.getStonesPerPit(), Pit.getRegularPitType()));
        }

        pits.add(new Pit(i, 0, Pit.getBigPitType()));

        return pits;
    }

    public void emptyPit(Pit pit) {
        pit.emptyStones();
    }

    public int addStone(Pit pit) {
        pit.addStone();

        return pit.getStones();
    }

    public int addStones(Pit pit, int stones) {
        pit.addStones(stones);

        return pit.getStones();
    }
}
