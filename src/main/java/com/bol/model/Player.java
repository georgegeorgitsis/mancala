package com.bol.model;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private List<Pit> pits;

    public Player(List<Pit> pits) {
        this.pits = pits;
    }

    public List<Pit> getPits() {
        return pits;
    }

    public void setPits(List<Pit> pits) {
        this.pits = pits;
    }

    public List<Pit> getRegularPits() {
        return this.getPits().stream().filter(p -> p.isRegular() == true).collect(Collectors.toList());
    }

    public Pit getBigPit() {
        return this.getPits().stream().filter(p -> p.isRegular() == false).findFirst().orElse(null);
    }

    public int getNumberOfRemainingStones() {
        return this.getRegularPits().stream().mapToInt(p -> p.getStones()).sum();
    }

    public Pit getPitPerPosition(int position) {
        return this.getPits().stream().filter(p -> p.getPosition() == position).findFirst().orElse(null);
    }
}
