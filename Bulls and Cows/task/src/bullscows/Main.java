package bullscows;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static String generateSecretCode(int length, int numSymbols) {
        if (length > numSymbols) {
            throw new IllegalArgumentException("Code length should not exceed the number of possible symbols.");
        }

        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder secretCode = new StringBuilder();

        Set<Character> usedSymbols = new HashSet<>();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char symbol;
            do {
                int index = random.nextInt(numSymbols);
                symbol = symbols.charAt(index);
            } while (!usedSymbols.add(symbol)); // Ensure uniqueness

            secretCode.append(symbol);
        }

        return secretCode.toString();
    }

    public static void printSecretCodeWithSymbols(String secretCode, int numSymbols) {
        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        Set<Character> symbolsUsed = new HashSet<>();
        for (char symbol : secretCode.toCharArray()) {
            symbolsUsed.add(symbol);
        }

        String maskedCode = "*".repeat(secretCode.length());
        String symbolRange = symbols.substring(0, numSymbols);
        System.out.println("The secret is prepared: " + maskedCode + " (" + symbolRange + ").");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the length of the secret code
        System.out.print("Input the length of the secret code:\n> ");
        int codeLength;
        try {
            codeLength = Integer.parseInt(scanner.next());
            if (codeLength <= 0) {
                System.out.println("Error: Code length should be a positive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input for code length.");
            return;
        }

        // Input the number of possible symbols in the code
        System.out.print("Input the number of possible symbols in the code:\n> ");
        int numSymbols;
        try {
            numSymbols = Integer.parseInt(scanner.next());
            if (numSymbols <= 0 || numSymbols > 36) {
                System.out.println("Error: Number of possible symbols should be between 1 and 36.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input for the number of symbols.");
            return;
        }

        if (codeLength > numSymbols) {
            System.out.println("Error: It's not possible to generate a code with a length of " +
                    codeLength + " with " + numSymbols + " unique symbols.");
            return;
        }

        if (numSymbols > 36) {
            System.out.println("Error: Maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        // Generate secret code
        String secretCode = generateSecretCode(codeLength, numSymbols);

        // Print the secret code with symbols used
        printSecretCodeWithSymbols(secretCode, numSymbols);

        System.out.println("Okay, let's start a game!");

        int turns = 1;
        while (true) {
            // Player's guess
            System.out.println("Turn " + turns + ":");
            String guess = scanner.next().toLowerCase();

            // Evaluate the guess
            int bulls = 0;
            int cows = 0;
            Set<Character> checkedDigits = new HashSet<>();
            Set<Integer> checkedIndices = new HashSet<>();

            for (int i = 0; i < codeLength; i++) {
                char secretChar = secretCode.charAt(i);
                char guessChar = guess.charAt(i);

                if (secretChar == guessChar) {
                    bulls++;
                    checkedDigits.add(secretChar);
                    checkedIndices.add(i);
                }
            }

            for (int i = 0; i < codeLength; i++) {
                char secretChar = secretCode.charAt(i);
                char guessChar = guess.charAt(i);

                if (!checkedIndices.contains(i) && !checkedDigits.contains(secretChar) && countOccurrences(secretCode, secretChar) > countOccurrences(guess, secretChar)) {
                    cows++;
                    checkedDigits.add(secretChar);
                }
            }

            // Print the grade
            System.out.printf("Grade: %d bull%s and %d cow%s\n", bulls, bulls != 1 ? "s" : "", cows, cows != 1 ? "s" : "");

            // Check if the guess is correct
            if (bulls == codeLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            turns++;
        }

        scanner.close();
    }

    private static int countOccurrences(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }
}
