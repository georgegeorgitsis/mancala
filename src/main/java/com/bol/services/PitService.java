package com.bol.services;

import com.bol.configs.GameConfiguration;
import com.bol.models.Pit;
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

    /**
     *
     * @param pit
     */
    public void emptyPit(Pit pit) {
        pit.emptyStones();
    }

    /**
     *
     * @param pit
     * @return
     */
    public int addStone(Pit pit) {
        pit.addStone();

        return pit.getStones();
    }

    /**
     *
     * @param pit
     * @param stones
     * @return
     */
    public int addStones(Pit pit, int stones) {
        pit.addStones(stones);

        return pit.getStones();
    }
}
