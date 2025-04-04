import java.util.*;

/**
 * Utility class providing various helper methods for the flight booking system.
 */
public class Util {

    /**
     * Method generates a random Flight-number-like String.
     * Each character is randomly generated using {@code getRandomChar()} and {@code getRandomInt(0, 10)}
     *
     * @return String - A random Flight number in the format XX-000
     */
    public static String randomFlightNumber() {

        String firstChar = getRandomChar();
        String secondChar = getRandomChar();

        return firstChar + secondChar + "-" + getRandomInt(0,10) + getRandomInt(0,10) + getRandomInt(0,10);
    }

    /**
     * Generates a random name by combining a random first name and last name.
     * Uses predefined arrays of first and last names.
     *
     * @return Randomly generated full name as a String
     */
    public static String getRandomName() {
        String[] firstNames = {"John", "Jane", "Alex", "Emily", "Chris", "Katie", "Michael", "Sarah", "David", "Laura", "Mike", "Joe", "Linus", "Daniel", "Robert", "Tux", "Foo", "Lorem", "Julius", "Donald", "Mickey", "Miguel"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Martinez", "Hernandez", "Robinson", "Torvalds", "Penguin", "Bar", "Ipsum", "Duck", "Mouse", "Fernandez"};

        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }

    /**
     * Reads a line of input from the console.
     *
     * @return The input line as a String
     */
    public static String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints all items in a given list to the console.
     *
     * @param <T> The type of elements in the list
     * @param list The list to be printed
     */
    public static <T>void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }

    /**
     * Generates a random uppercase alphabetic character.
     *
     * @return A random uppercase letter
     */
    public static String getRandomChar() {
        String[] chars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return chars[getRandomInt(0,chars.length - 1)];
    }

    /**
     * Generates a random integer within a specified range (inclusive).
     *
     * @param min The minimum value of the range
     * @param max The maximum value of the range
     * @return A random integer between min and max
     */
    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates a random name of variable length with alternating vowels and consonants.
     *
     * @return Randomly generated name as a String
     */
    public static String getRandomName2() {
        int length = getRandomInt(3,15);

        StringBuilder name = new StringBuilder();
        int vowelsCount = length / 3;
        int consonantsCount = length - vowelsCount;

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                name.append(getRandomVowel());
            } else if (consonantsCount > 0) {
                name.append(getRandomConsonant());
            } else {
                name.append(getRandomVowel());
            }
        }
        return name.toString();
    }

    /**
     * Generates a random vowel.
     *
     * @return A random lowercase vowel
     */
    public static String getRandomVowel() {
        String[] vowels = {"a", "e", "i", "o", "u"};
        return vowels[getRandomInt(0, vowels.length - 1)];
    }

    /**
     * Generates a random consonant.
     *
     * @return A random lowercase consonant
     */
    public static String getRandomConsonant() {
        String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
        return consonants[getRandomInt(0, consonants.length - 1)];
    }
}
