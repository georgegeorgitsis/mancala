package com.bol.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PitTest {

    private Pit pit;

    @Before
    public void setUp() {
        this.pit = new Pit(1, 1, 1);
    }

    @Test
    public void getPosition() {
        assertEquals(1, this.pit.getPosition());
    }

    @Test
    public void getStones() {
        assertEquals(1, this.pit.getStones());
    }


    @Test
    public void addStones() {
        this.pit.setStones(1);
        this.pit.addStones(2);
        assertEquals(3, this.pit.getStones());
    }

    @Test
    public void addStone() {
        this.pit.setStones(1);
        this.pit.addStone();
        assertEquals(2, this.pit.getStones());
    }

    @Test
    public void emptyStones() {
        this.pit.setStones(1);
        this.pit.emptyStones();
        assertEquals(0, this.pit.getStones());
    }

    @Test
    public void getType() {
        assertEquals(1, this.pit.getType());
    }

    @Test
    public void getRegularPitType() {
        assertEquals(0, Pit.getRegularPitType());
    }

    @Test
    public void getBigPitType() {
        assertEquals(1, Pit.getBigPitType());
    }

    @Test
    public void isRegular() {
        this.pit.setType(0);
        assertTrue(this.pit.isRegular());
    }

    @Test
    public void _toString() {
    }
}