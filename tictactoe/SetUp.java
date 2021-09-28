package tictactoe;

class SetUp {

    private final int rows;
    private final int cols;
    static char[][] board;

    SetUp(int cells) {

        this.rows = cells;
        this.cols = cells;
        board = new char[rows][cols];
    }

    static char[][] getBoard() {

        return board.clone();
    }

    static void field() {

        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                board[row][col] = ' ';
            }
        }
    }

    static void display() {

        System.out.println("|===========|");
        for (char[] chars : board) {
            System.out.print("| ");
            // add the user input inside
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(chars[j]+ " |" + " ");
            }
            // print the right layout "|"
            System.out.println();
        }
        System.out.print("|===========|");
        System.out.println();
    }

    static boolean analyzeGame() {

        boolean threeX;
        boolean threeO;
        boolean aGridEmpty;

        threeO = playHasWon(board,'O');
        threeX = playHasWon(board,'X');
        aGridEmpty = isEmptyBoard(board);

        if (threeO) {
            System.out.println("O wins");
            return false;
        }
        else if (threeX) {
            System.out.println("X wins");
            return false;
        }
        else if (aGridEmpty) {
            return true;
        }
        else {
            System.out.println("Draw");
            return false;
        }
    }

    static boolean playHasWon(char[][] board,char element) {

        //diagonal
        if(isThree(board[0][0], board[1][1], board[2][2], element) ||
           isThree(board[2][0], board[1][1], board[0][2], element))
            return true;

        for(int i = 0; i < 3; i++){
            //horizontal
            if(isThree(board[i][0],board[i][1], board[i][2], element)) {
                return true;
            }
            //vertical
            if(isThree(board[0][i], board[1][i], board[2][i], element)){
                return true;
            }
        }
        return false;
    }

    static boolean isThree(char a, char b, char c, char ele) {

        return a == b && b == c && c == ele;
    }

    static boolean isEmptyBoard(char[][] board) {

        for (char[] ch : board) {
            for (char e : ch) {
                if (e == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    static int[][] emptyIndexes(char[][] board) {
        int[][] empties = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    empties[i][j] = 1;
                } else {
                    empties[i][j] = 0;
                }
            }
        }
        return empties;
    }
}