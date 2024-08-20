
import java.util.Scanner;

/**
 * A program that calculates a football player's fantasy point score
 * from in-game statlines.
 *
 * This class includes methods to get user input, validate it, and calculate
 * the fantasy score based on standard fantasy football scoring rules.

 * @author Ajay Shenoy
 */
public class H6CustomApp {

    /**
     * The main method that drives the program. It collects input from the user
     * regarding the player's stats and calculates the fantasy score based on
     * standard scoring rules.
     *
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String position = "";

        // Validate the position input
        while (true) {
            System.out.print("What position does the player play (QB, RB, WR, TE)? ");
            if (scanner.hasNextLine()) {
                position = scanner.nextLine().toUpperCase();
            } else {
                System.out.println("No input found. Exiting the program.");
                return;
            }

            if (position.equals("QB") || position.equals("RB") || position.equals("WR")
                                                                || position.equals("TE")) {
                break;
            } else {
                System.out.println("Invalid position. Valid positions are: QB, RB, WR, TE.");
            }
        }

        double passingYards = getDoubleInput(scanner, "How many passing yards did they have?");
        double rushingYards = getDoubleInput(scanner, "How many rushing yards did they have?");
        double receivingYards = getDoubleInput(scanner, "How many receiving yards did they have?");
        int catches = getIntInput(scanner, "How many catches did they have?");
        int fumbles = getIntInput(scanner, "How many fumbles did they have?");
        int interceptions = getIntInput(scanner, "How many interceptions did they throw?");
        int passingTouchdowns = getIntInput(scanner, "How many passing touchdowns did they score?");
        int otherTouchdowns = getIntInput(scanner, "How many rushing, receiving, or special teams" +
                                                    " touchdowns did they score?");
        int twoPointConversions = getIntInput(scanner, "How many two-point conversions " +
                "                                                       did they score?");

        double fantasyScore = calculateFantasyScore(position, passingYards, rushingYards,
                receivingYards, catches, fumbles, interceptions, passingTouchdowns,
                otherTouchdowns, twoPointConversions);

        System.out.printf("The player's fantasy score is: %.2f points.%n", fantasyScore);
    }

    /**
     * Calculates the fantasy score based on the player's position and statistics.
     *
     * @param position            The player's position (QB, RB, WR, TE).
     * @param passingYards        The player's passing yards.
     * @param rushingYards        The player's rushing yards.
     * @param receivingYards      The player's receiving yards.
     * @param catches             The player's catches.
     * @param fumbles             The player's fumbles.
     * @param interceptions       The player's interceptions.
     * @param passingTouchdowns   The player's passing touchdowns.
     * @param otherTouchdowns     The player's rushing, receiving, or special teams touchdowns.
     * @param twoPointConversions The player's two-point conversions.
     * @return                    The calculated fantasy score.
     */
    public static double calculateFantasyScore(String position, double passingYards,
                                               double rushingYards, double receivingYards,
                                               int catches, int fumbles, int interceptions,
                                               int passingTouchdowns, int otherTouchdowns,
                                               int twoPointConversions) {
        double score = 0.0;

        score += (passingYards / 25.0); // 1 point per 25 passing yards
        score += (rushingYards / 10.0); // 1 point per 10 rushing yards
        score += (receivingYards / 10.0); // 1 point per 10 receiving yards
        score += (4.0 * passingTouchdowns); // 4 points per passing touchdown
        score += (6.0 * otherTouchdowns); // 6 points per rushing, receiving,
                                                // or special teams touchdown
        score -= (2.0 * interceptions); // -2 points per interception
        score -= (2.0 * fumbles); // -2 points per fumble
        score += (2.0 * twoPointConversions); // 2 points per two-point conversion
        score += (0.5 * catches); // 0.5 points per catch for non-QB positions

        return score;
    }

    /**
     * Gets a double input from the user with validation.
     *
     * @param scanner The Scanner object for user input.
     * @param prompt  The prompt to show to the user.
     * @return        The validated double input.
     */
    public static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // consume the newline character
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // consume the invalid input
            }
        }
    }

    /**
     * Gets an integer input from the user with validation.
     *
     * @param scanner The Scanner object for user input.
     * @param prompt  The prompt to show to the user.
     * @return        The validated integer input.
     */
    public static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // consume the invalid input
            }
        }
    }
}
