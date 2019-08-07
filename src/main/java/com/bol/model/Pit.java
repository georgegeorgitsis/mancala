package com.bol.model;

public class Pit {
    private int position;
    private int stones;
    private int type;
    private static final int regularPitType = 0;
    private static final int bigPitType = 1;

    public Pit(int position, int stones, int type) {
        this.position = position;
        this.stones = stones;
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public int getStones() {
        return stones;
    }

    public int getType() {
        return type;
    }

    public static int getRegularPitType() {
        return regularPitType;
    }

    public static int getBigPitType() {
        return bigPitType;
    }

    public boolean isRegular() {
        return this.getType() == this.getRegularPitType();
    }
}
