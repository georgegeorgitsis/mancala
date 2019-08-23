package com.bol.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    private Pit pit1;
    private Pit pit2;
    private Player player1;
    private Player player2;
    private Board board;

    @Before
    public void setUp() {
        List<Pit> pits1 = new ArrayList<>();
        this.pit1 = new Pit(0, 1, 0);
        pits1.add(pit1);
        this.pit2 = new Pit(1, 2, 1);
        pits1.add(pit2);
        this.player1 = new Player(pits1);

        List<Pit> pits2 = new ArrayList<>();
        this.pit1 = new Pit(0, 1, 0);
        pits2.add(pit1);
        this.pit2 = new Pit(1, 2, 1);
        pits2.add(pit2);
        this.player2 = new Player(pits2);

        this.board = new Board(this.player1, this.player2, 1);
    }

    @Test
    public void getPlayer1() {
        assertEquals(this.player1, this.board.getPlayer1());
    }

    @Test
    public void getPlayer2() {
        assertEquals(this.player2, this.board.getPlayer2());
    }

    @Test
    public void getTurn() {
        assertEquals(1, this.board.getTurn());
        this.board.changeTurn();
        assertEquals(2, this.board.getTurn());
        this.board.changeTurn();
    }

    @Test
    public void getTurnsPlayer() {
        assertEquals(this.player1, this.board.getTurnsPlayer());
        this.board.changeTurn();
        assertEquals(this.player2, this.board.getTurnsPlayer());
        this.board.changeTurn();
    }
}