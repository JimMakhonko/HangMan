import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HangmanR {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra"};

    public static String[] gallows = {
            "+---+\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\ |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\ |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\ |\n" +
                    "/ \\ |\n" +
                    "     |\n" +
                    " =========\n"};

    public static void main(String[] args) {

        String word = randomWord(words);

        char[] tempWord = new char[word.length()];
        Arrays.fill(tempWord, '_');

        System.out.print("\n");

        int misses = 0;
        char[] missedGuesses = new char[6];
        Arrays.fill(missedGuesses, '_');

        while (misses < 6) {
            System.out.print(gallows[misses]);

            System.out.print("Word: ");
            printPlaceHolders(tempWord);
            System.out.print("\n");

            System.out.print("Misses: ");
            printMissedGuesses(missedGuesses);
            System.out.print("\n\n");

            System.out.print("Guess: ");
            char input = readInput();
            System.out.print("\n");


            if (checkGuess(input, word)) {
                updatePlaceholders(word, tempWord, input);
            } else {
                missedGuesses[misses] = input;
                misses++;
            }
            if (Arrays.equals(tempWord, word.toCharArray())) {
                System.out.print(gallows[misses]);
                System.out.print("\nWord: ");
                printPlaceHolders(tempWord);
                System.out.println("\nGood work");
                break;
            }
            if (misses == 6) {
                System.out.println("You didn't guess the word, LOOSER!");
                System.out.print("\nThe word was: " + word);

                break;
            }
        }

    }

    /**
     * Returns true if matching letter found
     *
     * @param guess        - the guess chars we input
     * @param originalWord - the word we compare our guess chars
     * @return true/false
     */
    private static boolean checkGuess(char guess, String originalWord) {
        for (int i = 0; i < originalWord.length(); i++) {
            if (originalWord.charAt(i) == guess) {
                return true;
            }
        }
        return false;
    }

    /**
     * unmask the word letter by letter
     *
     * @param word         - original word
     * @param placeholders - masked characters
     * @param guess        - guess input from user
     */
    private static void updatePlaceholders(String word, char[] placeholders, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                placeholders[i] = guess;
            }
        }
    }

    private static char readInput() {
        Scanner scan = new Scanner(System.in);
        return scan.next().charAt(0);
    }

    /**
     * return random input from the array of words
     *
     * @param words - the word from an array
     * @return the random word
     */
    public static String randomWord(String[] words) {
        Random random = new Random();
        return words[random.nextInt(0, words.length)];
    }

    /**
     * we mask the word we need to guess
     *
     * @param placeholders
     */
    private static void printPlaceHolders(char[] placeholders) {
        for (char c : placeholders) {
            System.out.print(c + " ");
        }
        System.out.print("\n");
    }

    private static void printMissedGuesses(char[] misses) {
        for (int i = 0; i < misses.length; i++) {
            System.out.print(misses[i] + " ");
        }
    }

}