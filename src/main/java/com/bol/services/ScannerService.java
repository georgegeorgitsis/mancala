package com.bol.services;

import com.bol.configs.GameConfiguration;
import com.bol.models.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class ScannerService {

    @Autowired
    GameConfiguration gameConfiguration;

    public int getPlayersInput(Scanner in, Board board) {
        System.out.println(" ");
        System.out.println("Player " + board.getTurn() + " is playing");
        System.out.println(" ");

        boolean correct = false;
        int val = 0;

        while (!correct) {
            try {
                System.out.println("Select pit number between: " + gameConfiguration.getFirstPitPosition() + " - " + gameConfiguration.getLastPitPosition());
                val = in.nextInt();
                if (val >= gameConfiguration.getFirstPitPosition() && val <= gameConfiguration.getLastPitPosition()) {
                    correct = true;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Number out of range. ( Range from:" + gameConfiguration.getFirstPitPosition() + " to:" + gameConfiguration.getLastPitPosition() + " )");
                in.nextLine();
            }
        }

        return val;
    }
}
