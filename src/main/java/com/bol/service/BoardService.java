package com.bol.service;

import com.bol.config.GameConfiguration;
import com.bol.exception.NoStonesToMoveException;
import com.bol.model.Board;
import com.bol.model.Pit;
import com.bol.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    PlayerService playerService;

    @Autowired
    GameConfiguration gameConfiguration;

    /**
     * Initialising board
     *
     * @return Board
     */
    public Board initBoard() {
        System.out.println(" * ");
        System.out.println(" * Welcome to bol.com game * ");
        System.out.println(" * ");
        System.out.println("Initialising Game...");

        Player player1 = playerService.initPlayer();
        Player player2 = playerService.initPlayer();

        return new Board(player1, player2, 1);
    }

    /**
     * Game is finished when a player has no remaining stones
     *
     * @param Board board
     * @return boolean
     */
    public boolean gameIsFinished(Board board) {
        return board.getPlayer1().getNumberOfRemainingStones() == 0 || board.getPlayer2().getNumberOfRemainingStones() == 0;
    }

    /**
     * Moves the stones through the pits
     *
     * @param Board board
     * @param int   selectedPitNumber
     * @throws NoStonesToMoveException
     */
    public void moveStones(Board board, int selectedPitNumber) throws NoStonesToMoveException {
        Pit selectedPit = board.getTurnsPlayer().getPitPerPosition(selectedPitNumber);

        int stonesToMove = selectedPit.getStones();

        if (stonesToMove == 0) {
            throw new NoStonesToMoveException("Pit has no stones");
        }

        while (stonesToMove != 0) {
            stonesToMove--;
        }
    }
}
