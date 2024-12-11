import java.util.*;

/**
 * Utility class providing various helper methods for the flight booking system.
 */
public class Util {

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
}