package com.bol.service;

import com.bol.model.Pit;
import com.bol.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PitService {
    public List<Pit> initPits() {
        List pits = new ArrayList();
        for (int i = 0; i <= Player.getNumberOfPits(); i++) {
            pits.add(new Pit(i, Pit.getStonesPerPit(), Pit.getRegularPitType()));
        }

        return pits;
    }
}
