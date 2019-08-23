package com.bol.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;
    private Pit pit1;
    private Pit pit2;

    @Before
    public void setUp() {
        List<Pit> pits = new ArrayList<>();
        this.pit1 = new Pit(0, 1, 0);
        pits.add(pit1);

        this.pit2 = new Pit(1, 2, 1);
        pits.add(pit2);

        this.player = new Player(pits);
    }

    @Test
    public void getPits() {
        assertEquals(2, this.player.getPits().size());
    }

    @Test
    public void getRegularPits() {
        assertEquals(1, this.player.getRegularPits().size());
    }

    @Test
    public void getBigPit() {
        assertEquals(this.pit2, this.player.getBigPit());
    }

    @Test
    public void getNumberOfRemainingStones() {
        assertEquals(1, this.player.getNumberOfRemainingStones());
    }

    @Test
    public void getPitPerPosition() {
        assertEquals(this.pit1, this.player.getPitPerPosition(0));
    }
}