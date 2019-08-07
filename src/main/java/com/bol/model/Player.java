package com.bol.model;

import java.util.List;

public class Player {
    private List<Pit> pits;
    private static final int numberOfPits = 6;

    public List<Pit> getPits() {
        return pits;
    }

    public void setPits(List<Pit> pits) {
        this.pits = pits;
    }

    public static int getNumberOfPits() {
        return numberOfPits;
    }

    public Player(List<Pit> pits) {
        this.pits = pits;
    }
}
