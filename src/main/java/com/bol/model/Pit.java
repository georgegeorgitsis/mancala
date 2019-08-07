package com.bol.model;

public class Pit {
    private int position;
    private int stones;
    private int type;

    private static final int regularPitType = 0;
    private static final int bigPitType = 1;
    private static final int stonesPerPit = 6;

    public int getPosition() {
        return position;
    }

    public int getStones() {
        return stones;
    }

    public int getType() {
        return type;
    }

    public static int getBigPitType() {
        return bigPitType;
    }

    public static int getRegularPitType() {
        return regularPitType;
    }

    public static int getStonesPerPit() {
        return stonesPerPit;
    }

    public Pit(int position, int stones, int type) {
        this.position = position;
        this.stones = stones;
        this.type = type;
    }
}
