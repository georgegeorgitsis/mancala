package com.bol.services;

import com.bol.models.Pit;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PitServiceTest {

    PitService pitService;

    Pit pit;

    @Before
    public void setUp() {
        this.pitService = new PitService();
        this.pit = new Pit(1, 10, 1);
    }

    @Test
    public void initPits() {
        List<Pit> pits = this.pitService.initPits();
        assertEquals(pits.size(), 7);

        List<Pit> regularPits = new ArrayList<Pit>();
        List<Pit> bigPits = new ArrayList<Pit>();
        for (Pit pit : pits) {
            if (pit.isRegular()) {
                regularPits.add(pit);
            } else {
                bigPits.add(pit);
            }
        }

        assertEquals(regularPits.size(), 6);
        assertEquals(bigPits.size(), 1);
    }

    @Test
    public void emptyPit() {
        this.pitService.emptyPit(this.pit);
        assertEquals(0, this.pit.getStones());
    }

    @Test
    public void addStone() {
        this.pit.setStones(10);
        this.pitService.addStone(this.pit);
        assertEquals(11, this.pit.getStones());
    }

    @Test
    public void addStones() {
        this.pit.setStones(10);
        this.pitService.addStones(this.pit, 2);
        assertEquals(12, this.pit.getStones());
    }
}