package bullscows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Game game = new Game(sc);
        game.play(sc);
    }
}

class Game {

    private int cows;
    private int bulls;
    private String secretCode;
    private int turn = 0;

    // Initialize the game
    Game(Scanner sc) {

        cows = 0;
        bulls = 0;
        System.out.println("Please, enter the secret code's length: ");
        int numLength = readNumber(sc);
        System.out.println("Input the number of possible symbols in the code: ");
        int totalLength = possibleLength(sc);

        if (numLength > totalLength || numLength == 0) {
            System.out.println("Error: it's not possible to generate a code with a length of " + numLength +
                    " with " + totalLength + " unique symbols.");
            System.exit(0);
        } else {
            secretCode = randomGenerator(numLength, totalLength);
            System.out.println("The secret is prepared: " + statement(numLength, totalLength));
        }
    }

    // Execute method
    void play(Scanner sc) {

        System.out.println("Okay, let's start a game!");

        while (bulls != secretCode.length()) {
            bulls = 0; cows = 0;
            System.out.printf("Turn %d:%n", ++turn);
            char[] guessedNumber = sc.next().toCharArray();
            checkCode(guessedNumber, secretCode.length());
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    // Check the code to increment the number of Cows and Bulls
    private void checkCode(char[] guessedNumber, int numberOfDigits) {

        try {
            for (int i = 0; i < numberOfDigits; i++) {
                for (int j = 0; j < numberOfDigits; j++) {
                    if (i == j && secretCode.charAt(i) == guessedNumber[j]) {
                        bulls++;
                    } else if (secretCode.charAt(i) == guessedNumber[j]) {
                        cows++;
                    }
                }
            }
            printResult();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input");
        }
    }

    // Print those checked numbers out
    private void printResult() {

        String noBulls = bulls > 1 ? " bulls" : " bull";
        String noCows  = cows > 1 ? " cows" : " cow";

        System.out.print("Grade: ");
        if (bulls > 0 && cows > 0) {
            System.out.println(bulls + "" + noBulls + " " + cows + "" + noCows);
        } else if (bulls > 0 && cows == 0) {
            System.out.println(bulls + noBulls);
        } else if (bulls == 0 && cows > 0) {
            System.out.print(cows + noCows);
        } else {
            System.out.println("None. ");
        }
    }

    // Stage 3: Generate random secret code
    private void randomGenerator(int length) {

        List<Integer> randomList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        try {
            // to prevent starting with 0
            while (randomList.get(0) == 0) {
                Collections.shuffle(randomList);
            }

            StringBuilder result = new StringBuilder();
            for (var ch : randomList.subList(0, length)) {
                result.append(ch);
            }
            secretCode = result.toString();

        } catch (Exception e) {
            System.out.println("Error: can't generate a secret number with a length of " + length +
                    " because there aren't enough unique digits.");
            System.exit(0);
        }
    }

    // Stage 5: Generate random secret code using built-in Random library
    private void randomNumGenerator(int length) {

        Random random = new Random();
        long pseudoRandomNumber = Math.abs(random.nextLong());
        String stringNumber = String.valueOf(pseudoRandomNumber);
        StringBuilder secret = new StringBuilder();

        int count = 0;
        while (secret.length() < length && count < stringNumber.length()) {
            // check position of number to generate the number randomly
            if (!secret.toString().contains(stringNumber.substring(count, count + 1))) {
                secret.append(stringNumber.charAt(count));
            }
            // increment the size of the string generator
            count++;
        }

        if (secret.length() < length) {
            randomNumGenerator(length);
        }
        secretCode = secret.toString();
    }

    // Stage 6: Generate random secret code included both numbers and char
    private String randomGenerator(int secretLength, int symbolsRangeLength) {

        Random random = new Random();
        StringBuilder secret = new StringBuilder(secretLength);
        String[] allowedChars = "0123456789abcdefghijklmnopqrstuvwxyz".split("");

        while (secret.length() < secretLength) {
            int randomIndex = random.nextInt(symbolsRangeLength);
            String digit = allowedChars[randomIndex];
            // check to see if all the random number are unique
            if (secret.indexOf(digit) == -1) {
                secret.append(digit);
            }
        }
        return secret.toString();
    }

    // Printing out the range of the character
    private String charRange(int charLength) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        if (charLength <= 0) {
            return "a-a";
        }
        return "a-" + alphabet.charAt(charLength - 1);
    }

    // Printing out stars with number & char range
    private String statement(int numLength, int charLength) {

        int length = charLength - 10;

        return "*".repeat(Math.max(0, numLength)) +
                " (0-9, " + charRange(length) + ").";
    }

    // Stage 7: Check valid input number
    private int readNumber(Scanner sc) {

        int number = 0;
        if (sc.hasNextInt()) {
            number = sc.nextInt();
        } else {
            String userInput = sc.next();
            System.out.printf("Error: %s isn't a valid number.", userInput);
            System.exit(0);
        }
        return number;
    }

    // Stage 7: Check valid length
    private int possibleLength(Scanner sc) {

        int length = readNumber(sc);

        if (length > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        } else if (length < 1) {
            System.out.println("Error: symbols range length cannot be less than 1");
        }
        return length;
    }
}