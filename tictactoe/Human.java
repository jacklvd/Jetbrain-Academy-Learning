package tictactoe;

import java.util.Scanner;

public class Human {

    static Scanner scanner = new Scanner(System.in);

    static void makeMove(char[][] board, int player) {

        char symbol = 'X';

        switch (player) {
            case 1:
                break;
            case 2:
                symbol = 'O';
                break;
        }
        System.out.print("Enter the coordinates: ");
        boolean loopAgain;
        do {
            loopAgain = false;
            int a = 0;
            char firstInput = scanner.next().charAt(0);
            if (Character.isDigit(firstInput)) {
                a = firstInput - '0';
            }
            else {
                System.out.println("You should enter numbers!");
                loopAgain = true;
            }
            int b = 0;
            if (!loopAgain) {
                char secondInput = scanner.next().charAt(0);
                if (Character.isDigit(secondInput)) {
                    b = secondInput - '0';
                }
                else {
                    System.out.println("You should enter numbers!");
                    loopAgain = true;
                }
            }
            if (!loopAgain) {
                int index1 = a - 1;
                int index2 = b - 1;
                if (a < 1 || a > 3 || b < 1 || b > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    loopAgain = true;
                }
                else if (board[index1][index2] != ' '){
                    System.out.println("This cell is occupied! Choose another one!");
                    loopAgain = true;
                }
                else {
                    board[index1][index2] = symbol;
                }
            }
        }
        while (loopAgain);
    }
}