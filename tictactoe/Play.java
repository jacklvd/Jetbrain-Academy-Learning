package tictactoe;

import java.util.Scanner;

import static tictactoe.SetUp.*;

class Play {

    static Scanner scanner = new Scanner(System.in);

    static void play() {

        SetUp set = new SetUp(3);
        set.field();
        int turn = 0;
        boolean gameInProgress = true;
        boolean inProgress = true;
        do {
            // take command from users
            System.out.print("Input command: > ");
            String input = scanner.nextLine();
            String[] inputArr = input.trim().toLowerCase().split(" ");
            // check length command whether satisfy the condition for running
            if (inputArr.length > 3 || inputArr.length <= 0 || inputArr.length == 2) {
                System.out.println("Bad parameters!");
            } else if (inputArr.length == 3 && inputArr[0].equals("start")) {
                String player1 = inputArr[1];
                String player2 = inputArr[2];
                for (String player : new String[]{player1, player2}) {
                    // analyze players
                    switch (player) {
                        case "easy":
                        case "medium":
                        case "hard":
                        case "user":
                            break;
                        default:
                            System.out.println("Bad parameters!");
                    }
                }
                set.display();
                while (inProgress) {
                    if (turn % 2 == 0) {
                        switch (player1) {
                            case "user":
                                Human.makeMove(set.getBoard(), 1);
                                break;
                            case "easy":
                                Computer.easyMove(set.getBoard(), 1);
                                break;
                            case "medium":
                                Computer.mediumMove(set.getBoard(), 1);
                                break;
                            case "hard":
                                Computer.hardMove(set.getBoard(), 1);
                                break;
                            default:
                                System.out.println("Bad parameters");
                                continue;
                        }
                    } else {
                        switch (player2) {
                            case "user":
                                Human.makeMove(set.getBoard(), 2);
                                break;
                            case "easy":
                                Computer.easyMove(set.getBoard(), 2);
                                break;
                            case "medium":
                                Computer.mediumMove(set.getBoard(), 2);
                                break;
                            case "hard":
                                Computer.hardMove(set.getBoard(), 2);
                                break;
                            default:
                                System.out.println("Bad parameters");
                        }
                    }
                    set.display();
                    inProgress = SetUp.analyzeGame();
                    turn++;
                    if (inProgress == false) {
                        play();
                    }
                }
                continue;
            } else {
                if (inputArr[0].equals("exit")) {
                    System.exit(0);
                    continue;
                } else {
                    System.out.println("Bad parameters!");
                }
            }
        } while (gameInProgress);
    }}