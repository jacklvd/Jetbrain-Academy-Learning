package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static Scanner user = new Scanner(System.in);
    private static final int WATER = 200;
    private static final int MILK = 50;
    private static final int COFFEE_BEAN = 15;
    private static int cupsNum;
    private static int money = 550;
    private static int waterAmount = 400;
    private static int milkAmount = 540;
    private static int beansAmount = 120;
    private static int disposableCups = 9;

    public static void main(String[] args) {

        action();
    }

    // stage 1: Print out steps to make coffee
    public static void coffeeSteps() {

        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!");

    }

    // stage 2: Ask user for cup and print out the amount ingredient needs
    public static void request() {

        System.out.println("Write how many cups of coffee you will need: ");
        cupsNum = user.nextInt();
        System.out.println("For " + cupsNum + " cups of coffee you will need:");
        System.out.println((cupsNum * WATER) + " ml of water");
        System.out.println((cupsNum * MILK) + " ml of milk");
        System.out.println((cupsNum * COFFEE_BEAN) + " g of coffee beans");

    }

    // stage 3: take ingredient and generate calculation to get amount of cups
    public static void coffeeStatic() {

        getIngredient();
        int cupsAvailable = Math.min((waterAmount / WATER), Math.min((milkAmount / MILK), (beansAmount / COFFEE_BEAN)));

        if (cupsNum == cupsAvailable) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (cupsNum > cupsAvailable) {
            System.out.println("No, I can make only " + cupsAvailable + "cup(s) of coffee");
        } else {
            System.out.println("Yes, I can make that amount of coffee (and even " +
                    (cupsAvailable - cupsNum) + " more than that)");
        }
    }

    // stage 3: || stage 4: display fill method
    public static void getIngredient() {

        System.out.println("Write how many ml of water the coffee machine you want to add:");
        waterAmount += user.nextInt();
        System.out.println("Write how many ml of milk the coffee machine you want to add:");
        milkAmount += user.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine you want to add: ");
        beansAmount += user.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        disposableCups += user.nextInt();
    }

    // stage 4: display the condition of the coffee machine
    public static void machineCondition() {

        System.out.println("The coffee machine has: ");
        System.out.println(waterAmount + " ml of water");
        System.out.println(milkAmount + " ml of milk");
        System.out.println(beansAmount + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.printf("$%d of money \n", money);
    }

    // stage 4: fill action
    public static void fill() {

        getIngredient();
    }

    // stage 4: take action
    public static void take() {

        System.out.println("I gave you $" + money);
        money = 0;
    }

    // stage 4+5: buy action
    public static void buy() {

        System.out.println("What do you want to buy? " +
                "1 - espresso, 2 - latte, 3 - cappuccino, back - " +
                "to main menu:");
        String s = user.next();

        switch (s) {
            case "back":
                action();
                System.out.println();
                break;
            case "1":
                espresso();
                break;
            case "2":
                latte();
                break;
            case "3":
                cappuccino();
                break;
            default:
                System.out.println();
        }
    }

    // stage 5: checking data to make espresso
    public static void espresso() {

        if (waterAmount >= 250 && beansAmount >= 16 && disposableCups > 0) {
            waterAmount -= 250;
            beansAmount -= 16;
            disposableCups -= 1;
            money += 4;
            System.out.println("I have enough resources, making you a coffee!");
        } else if (waterAmount < 250) {
            System.out.println("Sorry, not enough water!");
        } else if (beansAmount < 16) {
            System.out.println("Sorry, not enough beans!");
        } else {
            System.out.println("Sorry, not enough cups!");
        }
    }

    // stage 5: checking data to make latte
    public static void latte() {

        if (waterAmount >= 350 &&
            milkAmount >= 75 &&
            beansAmount >= 20 &&
            disposableCups > 0) {
            waterAmount -= 350;
            milkAmount -= 75;
            beansAmount -= 20;
            disposableCups -= 1;
            money += 7;
            System.out.println("I have enough resources, making you a coffee!");
        } else if (waterAmount < 350) {
            System.out.println("Sorry, not enough water!");
        } else if (milkAmount < 75) {
            System.out.println("Sorry, not enough milk!");
        } else if (beansAmount < 20) {
            System.out.println("Sorry, not enough beans!");
        } else {
            System.out.println("Sorry, not enough cups!");
        }
    }

    // stage 5: checking data to make cappuccino
    public static void cappuccino() {

        if (waterAmount >= 200 &&
            milkAmount >= 100 &&
            beansAmount >= 12 &&
            disposableCups > 0) {
            waterAmount -= 200;
            milkAmount -= 100;
            beansAmount -= 12;
            disposableCups -= 1;
            money += 6;
            System.out.println("I have enough resources, making you a coffee!");
        } else if (waterAmount < 200) {
            System.out.println("Sorry, not enough water!");
        } else if (milkAmount < 100) {
            System.out.println("Sorry, not enough milk!");
        } else if (beansAmount < 12) {
            System.out.println("Sorry, not enough beans!");
        } else {
            System.out.println("Sorry, not enough cups!");
        }
    }

    // stage 4 + 5: call out list of actions
    public static void action() {
        
        boolean run = true;
        
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String answer = user.next().toLowerCase();
            System.out.println();
            switch (answer) {
                case "buy":
                    buy();
                    System.out.println();
                    break;
                case "fill":
                    fill();
                    System.out.println();
                    break;
                case "take":
                    take();
                    System.out.println();
                    break;
                case "remaining":
                    machineCondition();
                    System.out.println();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Oof oh hello=))");
            }
        } while (run);
    }

}