package com.bol.services;

import com.bol.configs.GameConfiguration;
import com.bol.exceptions.NoStonesToMoveException;
import com.bol.models.Board;
import com.bol.models.Pit;
import com.bol.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BoardService {

    private GameConfiguration gameConfiguration;
    private PlayerService playerService;
    private PitService pitService;

    @Autowired
    BoardService(GameConfiguration gameConfiguration, PlayerService playerService, PitService pitService) {
        this.pitService = pitService;
        this.playerService = playerService;
        this.gameConfiguration = gameConfiguration;
    }

    public void welcomeMsg() {
        for (int i = 0; i <= 5; i++) {
            System.out.println();
        }
        for (int i = 0; i <= 20; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println(" * Welcome to bol.com game * ");
        for (int i = 0; i <= 20; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println();
        System.out.println("Initialising Board...");
        System.out.println();
    }

    /**
     * Initialising board
     */
    public Board initBoard() {
        Player player1 = this.playerService.initPlayer();
        Player player2 = this.playerService.initPlayer();

        return new Board(player1, player2, 1);
    }

    /**
     * Game is finished when a player has no remaining stones
     */
    public boolean gameIsFinished(Board board) {
        return board.getPlayer1().getNumberOfRemainingStones() == 0 || board.getPlayer2().getNumberOfRemainingStones() == 0;
    }

    /**
     * Handles player's turn
     */
    public void handleTurn(Board board, int selectedPitNumber) throws NoStonesToMoveException {
        Pit selectedPit = board.getTurnsPlayer().getPitPerPosition(selectedPitNumber);
        int stonesToMove = selectedPit.getStones();
        if (stonesToMove == 0) {
            throw new NoStonesToMoveException("Pit has no stones");
        }
        this.pitService.emptyPit(selectedPit); //take stones from pit

        List<Pit> allBoardPits = this.combineAllBoardPits(board);

        //player2 pits are mirrored to players1 by 6 indexes
        int index = (board.getTurnsPlayer() == board.getPlayer2()) ?
                selectedPitNumber + this.gameConfiguration.getNumberOfPits() :
                selectedPitNumber;
        int finalPitIndex = moveStones(allBoardPits, index, stonesToMove);

        checkLastStone(board, finalPitIndex);

        if (allBoardPits.get(finalPitIndex).getType() == Pit.getRegularPitType()) { //if stone lands to small pit, change turn
            board.changeTurn();
        }
    }

    /**
     * Create a temp board with all pits in order to iterate through them in case of a lot of stones
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
     * Moves stones through pits
     */
    public int moveStones(List<Pit> allBoardPits, int index, int stonesToMove) {
        while (stonesToMove != 0) {
            index++;
            if (index >= allBoardPits.size()) {
                index = 0;
            }
            this.pitService.addStone(allBoardPits.get(index));

            stonesToMove--;
        }

        return index;
    }

    /**
     * Checks the last stone if places on empty own pit
     */
    public void checkLastStone(Board board, int finalPitIndex) {
        List<Pit> allBoardPits = this.combineAllBoardPits(board);
        Pit lastPit = allBoardPits.get(finalPitIndex);
        int opponentsIndex;

        if ((board.getTurnsPlayer() == board.getPlayer1() && finalPitIndex <= 5) ||
                (board.getTurnsPlayer() == board.getPlayer2() && finalPitIndex > 5)) {
            if (lastPit.getType() == Pit.getRegularPitType() && lastPit.getStones() == 1) { //if it was last stone

                //for player2 opponent's index is -6 because player's 1 big pit is missing
                opponentsIndex = (board.getTurnsPlayer() == board.getPlayer1()) ?
                        finalPitIndex + (this.gameConfiguration.getNumberOfPits() + 1) :
                        finalPitIndex - this.gameConfiguration.getNumberOfPits();

                handleLastStoneCapture(allBoardPits, board.getTurnsPlayer(), finalPitIndex, opponentsIndex);
            }
        }
    }

    /**
     * Captures opponents and own stones
     */
    public void handleLastStoneCapture(List<Pit> allBoardPits, Player player, int finalPitIndex, int opponentsIndex) {
        Pit opponentsPit = allBoardPits.get(opponentsIndex);

        int opponentsStones = opponentsPit.getStones(); //get opponent's stones
        this.pitService.addStones(player.getBigPit(), opponentsStones);
        this.pitService.emptyPit(opponentsPit);

        this.pitService.addStone(player.getBigPit()); //get last stone
        this.pitService.emptyPit(allBoardPits.get(finalPitIndex));
    }

    /**
     * Printing results
     */
    public void printResults(Board board) {
        Player winner = this.findWinner(board);
        if (winner == board.getPlayer1()) {
            System.out.println("Player 1 is the winner! Congratulations buddy!");
        } else {
            System.out.println("Player 2 is the winner! Congratulations buddy!");
        }
    }

    /**
     * Finds the winner. In case of draw, player2 is the winner cause he started second
     */
    public Player findWinner(Board board) {
        int player1Stones = this.playerService.sumStones(board.getPlayer1());
        int player2Stones = this.playerService.sumStones(board.getPlayer2());

        return (player1Stones > player2Stones) ? board.getPlayer1() : board.getPlayer2();
    }
}
