package com.bol.configs;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {
    private static final int stonesPerPit = 6;
    private static final int numberOfPits = 6;
    private static final int firstPitPosition = 0;
    private static final int lastPitPosition = 5;

    public static int getStonesPerPit() {
        return stonesPerPit;
    }

    public static int getNumberOfPits() {
        return numberOfPits;
    }

    public static int getFirstPitPosition() {
        return firstPitPosition;
    }

    public static int getLastPitPosition() {
        return lastPitPosition;
    }
}
