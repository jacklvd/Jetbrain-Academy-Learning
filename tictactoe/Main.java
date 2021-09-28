package tictactoe;

public class Main {

    public static void main(String[] args) {
        
        intro();
        Play.play();
    }
    
    // add fun intruction part
    public static void intro() {
      
      System.out.println("Welcome to the Tic Tac Toe Game!");
      System.out.println("Simple rule try to beat the AI or other player");
      System.out.println("Enter \"start\" + keywords: easy, medium, hard, or user to play");
      System.out.println("Also, you can make 2 bots fighting together as well");
      System.out.println("Enter \"exit\" to exit the game");
      System.out.println();
    }
    
}

/*
Input command: > start user medium
 |===========|
 |   |   |   | 
 |   |   |   | 
 |   |   |   | 
 |===========|
 Enter the coordinates: 1 2
 |===========|
 |   | X |   | 
 |   |   |   | 
 |   |   |   | 
 |===========|
 Making move level "medium"
 |===========|
 |   | X |   | 
 |   |   |   | 
 |   | O |   | 
 |===========|
 Enter the coordinates: 1 3
 |===========|
 |   | X | X | 
 |   |   |   | 
 |   | O |   | 
 |===========|
 Making move level "medium"
 |===========|
 | O | X | X | 
 |   |   |   | 
 |   | O |   | 
 |===========|
 Enter the coordinates: 2 2
 |===========|
 | O | X | X | 
 |   | X |   | 
 |   | O |   | 
 |===========|
 Making move level "medium"
 |===========|
 | O | X | X | 
 |   | X |   | 
 | O | O |   | 
 |===========|
 Enter the coordinates: 2 1
 |===========|
 | O | X | X | 
 | X | X |   | 
 | O | O |   | 
 |===========|
 Making move level "medium"
 |===========|
 | O | X | X | 
 | X | X |   | 
 | O | O | O | 
 |===========|
 O wins
 Input command: > exit
*/