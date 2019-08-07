package com.bol;

import com.bol.model.Board;
import com.bol.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@SpringBootApplication
@Configuration
public class Application implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    BoardService boardService;

    public static void main(String[] args) {
        System.out.println("Application started");
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) {
        Board board = boardService.initBoard();
    }
}
