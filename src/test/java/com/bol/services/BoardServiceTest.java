package com.bol.services;

import com.bol.configs.GameConfiguration;
import com.bol.models.Board;
import com.bol.models.Pit;
import com.bol.models.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {
    @Mock
    private PitService pitService;

    @Mock
    private PlayerService playerService;

    @Mock
    private GameConfiguration gameConfiguration;

    private BoardService boardService;

    @Mock
    private Player player;

    @Before
    public void setUp() {
        this.boardService = new BoardService(gameConfiguration, playerService, pitService);
    }

    @Test
    public void initBoard() {
        Mockito.when(playerService.initPlayer()).thenReturn(this.player);
        Board board = this.boardService.initBoard();
        assertEquals(board.getTurn(), 1);
    }

    @Test
    public void gameIsFinished() {
        Mockito.when(this.player.getNumberOfRemainingStones()).thenReturn(0);
        Mockito.when(this.playerService.initPlayer()).thenReturn(this.player);
        Board board = this.boardService.initBoard();
        assertTrue(this.boardService.gameIsFinished(board));

        Mockito.when(this.player.getNumberOfRemainingStones()).thenReturn(1);
        Mockito.when(this.playerService.initPlayer()).thenReturn(this.player);
        board = this.boardService.initBoard();
        assertFalse(this.boardService.gameIsFinished(board));
    }

    @Test
    public void combineAllBoardPits() {
        Mockito.when(this.player.getPits()).thenReturn(this._givenPits());
        Mockito.when(this.player.getRegularPits()).thenReturn(this._givenRegularPits());
        Mockito.when(this.playerService.initPlayer()).thenReturn(this.player);
        Board board = this.boardService.initBoard();

        List<Pit> combinedPits = this.boardService.combineAllBoardPits(board);
        assertEquals(3, combinedPits.size());
    }

    @Test
    public void moveStones() {
        List<Pit> pits = this._givenPits();
        int index = this.boardService.moveStones(pits, 0, 3);
        assertEquals(1, index); //resets at 2
    }

    @Test
    public void findWinner() {
        Player player1 = this._givenPlayerWithStones(3);
        Player player2 = this._givenPlayerWithStones(1);
        Board board = new Board(player1, player2, 1);
        assertEquals(player1, this.boardService.findWinner(board));
    }

    private Player _givenPlayerWithStones(int stones) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, stones, 0));
        pits.add(new Pit(2, stones, 1));

        this.player.setPits(pits);
        return this.player;
    }

    private List<Pit> _givenPits() {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, 1, 0));
        pits.add(new Pit(2, 1, 1));

        return pits;
    }

    private List<Pit> _givenRegularPits() {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, 1, 0));

        return pits;
    }
}