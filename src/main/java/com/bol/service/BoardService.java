package com.bol.service;

import com.bol.model.Board;
import com.bol.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    PlayerService playerService;

    public Board initBoard() {
        Player player1 = playerService.initPlayer();
        Player player2 = playerService.initPlayer();

        System.out.println("Initialised");

        return new Board(player1, player2);
    }


}
