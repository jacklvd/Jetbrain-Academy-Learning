package cinema;

import java.util.*;

public class Cinema {

    // constant variables
    private static Scanner user = new Scanner(System.in);
    private static final int EXPENSIVE_PRICE = 10;
    private static final int CHEAP_PRICE = 8;
    // size of cinema and chosen seats by user
    private static int row;
    private static int seat;
    private static int chosenRow;
    private static int chosenSeat;
    // variables for the statistic
    private static int totalSeat;
    private static int curIncome = 0;
    private static int ticketPurchased = 0;

    public static void main(String[] args) {

        getCinema();
        
    }

    public static void fill(String[][] cinema) {

        for (int row = 0; row < cinema.length; row++) {
            for (int seat = 0; seat < cinema[0].length; seat++) {
                if (row == 0 && seat == 0) {
                    cinema[0][0] = " ";
                } else if (row == 0) {
                    cinema[0][seat] = String.valueOf(seat);
                } else if (seat == 0) {
                    cinema[row][0] = String.valueOf(row);
                } else {
                    cinema[row][seat] = "S";
                }
            }
        }
    }

    public static void display(String[][] cinema) {

        System.out.println("Cinema: ");
        for (String[] rows : cinema) {
            for (int seat = 0; seat < cinema[0].length; seat++) {
                System.out.print(" " + rows[seat]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void totalIncome() {

        int income = 0;
        int frontRow = row / 2;
        int backRow = row - frontRow;
        int totalSeats = row * seat;

        if (totalSeats < 60) {
            income = totalSeats * 10;
        } else {
            if (row % 2 != 0) {
                income = frontRow * seat * 10 + backRow * 8 * seat;
            } else {
                income = frontRow * seat * 10 + frontRow * 8 * seat;
            }
        }
        System.out.println("Total income: $" + income);
    }
    
    public static void buyTicket(String[][] cinema) {

        boolean invalid;

        do {
            invalid = false;
            getSeat();
            if (chosenRow > row || chosenSeat > seat ||
                chosenRow < 0 || chosenSeat < 0) {
                System.out.println("Wrong input!");
                System.out.println();
                invalid = true;
            } else if (cinema[chosenRow][chosenSeat] == "B") {
                System.out.println("That ticket has already been purchased");
                System.out.println();
                invalid = true;
            }
        } while (invalid);

        if ((row * seat) <= 60) {
            System.out.println("Ticket price: $" + EXPENSIVE_PRICE);
            curIncome += 10;
        } else {
            if (chosenRow < (row / 2)) {
                System.out.println("Ticket price: $" + EXPENSIVE_PRICE);
                curIncome += 10;
            } else {
                System.out.println("Ticket price: $" + CHEAP_PRICE);
                curIncome += 8;
            }
        }
        System.out.println();
        cinema[chosenRow][chosenSeat] = "B";
        ticketPurchased++;
    }

    // display out the summary of statistic
    public static void displayStatistics(){
        System.out.println("Number of purchased tickets: " + ticketPurchased);
        System.out.printf("Percentage: %.2f%%" ,(float)ticketPurchased/totalSeat * 100);
        System.out.println();
        System.out.println("Current income: $" + curIncome);
        totalIncome();
        System.out.println();
    }

    // ask many questions to get action from user
    public static void getRequest() {

        String[][] cinema = new String[row + 1][seat + 1];
        fill(cinema);
        boolean run = true;

        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int answer = user.nextInt();
            System.out.println();

            switch(answer) {

                case 1:
                    display(cinema);
                    break;

                case 2:
                    buyTicket(cinema);
                    break;

                case 3:
                    displayStatistics();
                    break;

                case 0:
                    run = false;
                    break;

                default:
                    System.out.println("You have entered a wrong choice.");

            }
        } while (run);
    }
    public static void getCinema() {

        System.out.println("Enter the number of rows: ");
        row = user.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seat = user.nextInt();
        totalSeat = row * seat;
        System.out.println();
        getRequest();


    }

    public static void getSeat() {

        System.out.println("Enter the number of rows: ");
        chosenRow = user.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        chosenSeat = user.nextInt();

    }
}