package com.bol;

import com.bol.exceptions.NoStonesToMoveException;
import com.bol.models.Board;
import com.bol.services.BoardService;
import com.bol.services.ScannerService;
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
        this.boardService.welcomeMsg();
        Board board = this.boardService.initBoard();

        while (!this.boardService.gameIsFinished(board)) {
            board._toString();

            int selectedPitNumber = this.scannerService.getPlayersInput(this.scanner, board);

            try {
                this.boardService.handleTurn(board, selectedPitNumber);
            } catch (NoStonesToMoveException e) {
                System.out.println(e.toString());
            }
        }

        this.boardService.printResults(board);
    }
}
