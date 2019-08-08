package com.bol.models;

public class Board {
    private Player player1;
    private Player player2;
    private int turn;

    public Board(Player player1, Player player2, int turn) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = turn;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getTurn() {
        return this.turn;
    }

    public Player getTurnsPlayer() {
        return (this.getTurn() == 1) ? this.getPlayer1() : this.getPlayer2();
    }

    public void changeTurn() {
        this.turn = (this.turn == 1) ? 2 : 1;
    }

    public void _toString() {
        System.out.println(" ");
        System.out.println("------------- ------------- -------------");
        System.out.println("Player 1");

        for (Pit pit : this.getPlayer1().getRegularPits()) {
            pit._toString();
        }
        System.out.print(" -> Big Pit: " + this.getPlayer1().getBigPit().getStones());

        System.out.println(" ");
        System.out.println(" ");

        System.out.println("Player 2");
        for (Pit pit : this.getPlayer2().getRegularPits()) {
            pit._toString();
        }
        System.out.println(" -> Big Pit: " + this.getPlayer2().getBigPit().getStones());
        System.out.println(" ");
    }
}
