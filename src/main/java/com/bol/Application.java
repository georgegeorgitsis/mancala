package com.bol;

import com.bol.exception.NoStonesToMoveException;
import com.bol.model.Board;
import com.bol.service.BoardService;
import com.bol.service.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    BoardService boardService;

    @Autowired
    ScannerService scannerService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        Board board = boardService.initBoard();

        while (!boardService.gameIsFinished(board)) {
            board._toString();

            int selectedPitNumber = scannerService.getPlayersInput(scanner, board);

            try {
                boardService.handleTurn(board, selectedPitNumber);
            } catch (NoStonesToMoveException e) {
                System.out.println(e.toString());
            }
        }

        boardService.printResults(board);
    }
}
