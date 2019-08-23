package com.bol.services;

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

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {
    @Mock
    private PitService pitService;

    private PlayerService playerService;

    private Player player;

    @Before
    public void setup() {
        this.playerService = new PlayerService(pitService);
    }

    @Test
    public void initPlayer() {
        this._givenAPlayer();
        assertEquals(this.player.getPits().size(), 1);
    }

    @Test
    public void sumStones() {
        this._givenAPlayer();
        assertEquals(this.playerService.sumStones(this.player), 1);
    }

    @Test
    public void addStonesToBigPit() {
        this._givenAPlayer();
        assertEquals(this.playerService.addStonesToBigPit(this.player, 11), 12);
    }

    private Player _givenAPlayer() {
        Mockito.when(pitService.initPits()).thenReturn(this._givenPits());
        this.player = this.playerService.initPlayer();

        return this.player;
    }

    private List<Pit> _givenPits() {
        List<Pit> pits = new ArrayList();
        pits.add(new Pit(1, 1, 1));

        return pits;
    }
}