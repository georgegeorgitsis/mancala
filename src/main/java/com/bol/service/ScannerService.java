package com.bol.service;

import com.bol.config.GameConfiguration;
import com.bol.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class ScannerService {

    @Autowired
    GameConfiguration gameConfiguration;

    public int getPlayersInput(Scanner in, Board board) {
        System.out.println("Player " + board.getTurn() + " is playing");

        boolean correct = false;
        int val = 0;

        while (!correct) {
            try {
                System.out.println("Select pit number between: " + gameConfiguration.getFirstPitPosition() + " and: " + gameConfiguration.getLastPitPosition());
                val = in.nextInt();
                if (val >= 0 && val < 5) {
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
