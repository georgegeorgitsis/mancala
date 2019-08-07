package com.bol.service;

import com.bol.exception.NoStonesToMoveException;
import com.bol.model.Board;
import com.bol.model.Pit;
import com.bol.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BoardService {

    @Autowired
    PlayerService playerService;

    @Autowired
    PitService pitService;

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
     * @param board
     * @return boolean
     */
    public boolean gameIsFinished(Board board) {
        return board.getPlayer1().getNumberOfRemainingStones() == 0 || board.getPlayer2().getNumberOfRemainingStones() == 0;
    }

    /**
     * Moves the stones through the pits
     *
     * @param board
     * @param selectedPitNumber
     * @throws NoStonesToMoveException
     */
    public void handleTurn(Board board, int selectedPitNumber) throws NoStonesToMoveException {
        Pit selectedPit = board.getTurnsPlayer().getPitPerPosition(selectedPitNumber);
        int stonesToMove = selectedPit.getStones();
        if (stonesToMove == 0) {
            throw new NoStonesToMoveException("Pit has no stones");
        }
        pitService.emptyPit(selectedPit);

        List<Pit> allBoardPits = this.combineAllBoardPits(board);

        //player2 pits are mirrored to players1 by 6 indexes
        int index = (board.getTurnsPlayer() == board.getPlayer2()) ? selectedPitNumber + 6 : selectedPitNumber;
        int finalPitIndex = moveStones(allBoardPits, index, stonesToMove);

        checkLastStone(board, finalPitIndex);

        if (allBoardPits.get(finalPitIndex).getType() == Pit.getRegularPitType()) { //if stone lands to small pit, change turn
            board.changeTurn();
        }
    }

    /**
     * Create a temp board with all pits in order to iterate through them in case of a lot of stones
     *
     * @param board
     * @return
     */
    public List<Pit> combineAllBoardPits(Board board) {
        List<Pit> player1Pits;
        List<Pit> player2Pits;
        if (board.getTurnsPlayer() == board.getPlayer1()) { //get all pits except opponents big pit
            player1Pits = board.getPlayer1().getPits();
            player2Pits = board.getPlayer2().getRegularPits();
        } else {
            player1Pits = board.getPlayer1().getRegularPits();
            player2Pits = board.getPlayer2().getPits();
        }

        return Stream.concat(player1Pits.stream(), player2Pits.stream()).collect(Collectors.toList());
    }

    /**
     * @param allBoardPits
     * @param index
     * @param stonesToMove
     * @return
     */
    public int moveStones(List<Pit> allBoardPits, int index, int stonesToMove) {
        while (stonesToMove != 0) {
            index++;
            if (index > 12) { //opponent's big pit is not included
                index = 0;
            }
            pitService.addStone(allBoardPits.get(index));

            stonesToMove--;
        }

        return index;
    }

    /**
     * @param board
     * @param finalPitIndex
     */
    public void checkLastStone(Board board, int finalPitIndex) {
        List<Pit> allBoardPits = this.combineAllBoardPits(board);
        Pit lastPit = allBoardPits.get(finalPitIndex);
        int opponentsIndex;

        if ((board.getTurnsPlayer() == board.getPlayer1() && finalPitIndex <= 5) ||
                (board.getTurnsPlayer() == board.getPlayer2() && finalPitIndex > 5)) {
            if (lastPit.getType() == Pit.getRegularPitType() && lastPit.getStones() == 1) { //if it was last stone

                //for player1 opponent's index is +7 because of own big pit
                //for player2 opponent's index is -6 because player's 1 big pit is missing
                opponentsIndex = (board.getTurnsPlayer() == board.getPlayer1()) ? finalPitIndex + 7 : finalPitIndex - 6;

                handleLastStoneCapture(allBoardPits, board.getTurnsPlayer(), finalPitIndex, opponentsIndex);
            }
        }
    }

    /**
     * @param allBoardPits
     * @param player
     * @param finalPitIndex
     * @param opponentsIndex
     */
    public void handleLastStoneCapture(List<Pit> allBoardPits, Player player, int finalPitIndex, int opponentsIndex) {
        Pit opponentsPit = allBoardPits.get(opponentsIndex);

        int opponentsStones = opponentsPit.getStones(); //get opponent's stones
        pitService.addStones(player.getBigPit(), opponentsStones);
        pitService.emptyPit(opponentsPit);

        pitService.addStone(player.getBigPit()); //get last stone
        pitService.emptyPit(allBoardPits.get(finalPitIndex));
    }

    /**
     *
     * @param board
     */
    public void printResults(Board board) {
        Player winner = findWinner(board);
        if (winner == board.getPlayer1()) {
            System.out.println("Player 1 is the winner! Congratulations buddy!");
        } else {
            System.out.println("Player 2 is the winner! Congratulations buddy!");
        }
    }

    /**
     * @param board
     * @return
     */
    public Player findWinner(Board board) {
        int player1Stones = playerService.sumStones(board.getPlayer1());
        int player2Stones = playerService.sumStones(board.getPlayer2());

        if (player1Stones >= player2Stones) { //We assume Player1 wins if they are even
            return board.getPlayer1();
        } else {
            return board.getPlayer2();
        }
    }
}
