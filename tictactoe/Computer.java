package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static tictactoe.SetUp.*;

public class Computer {

    static void easyMove(char[][] board, int player) {

        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        int choice;
        do {
            choice = random.nextInt(9);
        } while (board[choice/3][choice%3] != ' ');

        char symbol = 'O';

        switch (player) {
            case 1:
                symbol = 'X';
                break;
            case 2:
                break;
        }

        board[choice/3][choice%3] = symbol;
    }

    static void mediumMove(char[][] board, int player) {

        System.out.println("Making move level \"medium\"");
        int choice;
        int [] coordinate = new int[2];
        boolean choiceMade = false;

        char symbol = 'O';
        char enemySymbol = 'X';
        switch (player) {
            case 1:
                symbol = 'X';
                enemySymbol = 'O';
                break;
            case 2:
                break;
        }
        // start check for winning condition
        int[] bestMove = seekWin(board, symbol);
        if (bestMove[0] != -1) {
            choiceMade = true;
            coordinate = bestMove;
        }
        // block if possible
        if (!choiceMade) {
            bestMove = seekWin(board, enemySymbol);
            if (bestMove[0] != -1) {
                choiceMade = true;
                coordinate = bestMove;
            }
        }

        if (!choiceMade) {
            Random random = new Random();
            do {
                choice = random.nextInt(9);
            } while (board[choice / 3][choice % 3] != ' ');
            board[choice / 3][choice % 3] = symbol;
        // if none of those apply make random move
        } else {
            board[coordinate[0]][coordinate[1]] = symbol;
        }
    }

    static void hardMove(char[][] board, int player) {
        System.out.println("Making move level \"hard\"");

        char symbol = ' ';
        if (player == 1) {
            symbol = 'X';
        } else if (player == 2) {
            symbol = 'O';
        }
        Player bestMove = miniMax(board, player, player);
        board[bestMove.index[0]][bestMove.index[1]] = symbol;
    }

    static int[] seekWin(char[][] board, char symbol) {

        int row = -1;
        int column = -1;

        // check to see if computer could win by diagonally
        // or block if possible
        if ((board[0][0] == symbol && board[2][2] == symbol && board[1][1] == ' ') ||
            (board[2][0] == symbol && board[0][2] == symbol && board[1][1] == ' ')) {
            row = 1;
            column = 1;
        } else if (board[1][1] == symbol) {
            if (board[0][0] == symbol && board[2][2] == ' ') {
                row = 2;
                column = 2;
            } else if (board[2][2] == symbol && board[0][0] == ' ') {
                row = 0;
                column = 0;
            } else if (board[0][2] == symbol && board[2][0] == ' ') {
                row = 2;
                column = 0;
            } else if (board[2][0] == symbol && board[0][2] == ' ') {
                row = 0;
                column = 2;
            }
        }
        // check if computer could win by horizontally and vertically
        // or block if possible
        for (int i = 0; i < 3; i++) {
            // horizontally
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == ' ') {
                row = i;
                column = 2;
            // horizontally
            } else if (board[i][1] == symbol && board[i][2] == symbol && board[i][0] == ' ') {
                row = i;
                column = 0;
            // horizontally
            } else if (board[i][0] == symbol && board[i][2] == symbol && board[i][1] == ' ') {
                row = i;
                column = 1;
            // vertically
            } else if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == ' ') {
                row = 2;
                column = i;
            // vertically
            } else if (board[1][i] == symbol && board[2][i] == symbol && board[0][i] == ' ') {
                row = 0;
                column = i;
            // vertically
            } else if (board[0][i] == symbol && board[2][i] == symbol && board[1][i] == ' ') {
                row = 1;
                column = i;
            }
        }
        return new int[] {row, column};
    }

    static Player miniMax(char[][] board, int callingPlayer, int currentPlayer) {

        // to determine the current symbol of current player
        char enemySymbol = ' ';
        char callingSymbol = ' ';
        if (callingPlayer == 1) {
            callingSymbol = 'X';
            enemySymbol = 'O';
        } else if (callingPlayer == 2) {
            callingSymbol = 'O';
            enemySymbol = 'X';
        }

        // to determine the enemy symbol
        char symbol = ' ';
        int enemyNumber = 0;
        if (currentPlayer == 1) {
            symbol = 'X';
            enemyNumber = 2;
        } else if (currentPlayer == 2) {
            symbol = 'O';
            enemyNumber = 1;
        }

        // find available spots
        int[][] availableSpots = emptyIndexes(board);
        // check if lose condition happen
        if (playHasWon(board, enemySymbol)) {
            return new Player(-10);
        // check if winning condition happen
        } else if (playHasWon(board, callingSymbol)) {
            return new Player(10);
        // check if draw condition
        } else if (!isEmptyBoard(board)) {
            return new Player(0);
        }

        List<Player> moves = new ArrayList<>();
        // preparing a temporary state to guess the next moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (availableSpots[i][j] == 1) {
                    // let's make a possible move
                    Player move = new Player();
                    move.index = new int[]{i, j};
                    board[i][j] = symbol;
                    Player result = miniMax(board, callingPlayer, enemyNumber);
                    // save the score for the minimax
                    move.score = result.score;
                    // then revert the occupied place back to empty, so next guesses can go on
                    board[i][j] = ' ';
                    moves.add(move);
                }
            }
        }
        // when the moves loop has ended, choose the move with the highest score
        int bestMove = 0;
        if (currentPlayer == callingPlayer) {
            int bestScore = -10000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score > bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        } else {
            int bestScore = 10000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score < bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        }
        // minimax returns the best move to the latest function caller
        return moves.get(bestMove);
    }}