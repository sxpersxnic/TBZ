import java.util.*;

public class Util {
    public static String randomFlightNumber() {

        Random random = new Random();

        String firstChar = getRandomChar();
        String secondChar = getRandomChar();

        return firstChar + secondChar + "-" + getRandomInt(0,10) + getRandomInt(0,10) + getRandomInt(0,10);
    }

    public static String getRandomName() {
        String[] firstNames = {"John", "Jane", "Alex", "Emily", "Chris", "Katie", "Michael", "Sarah", "David", "Laura", "Mike", "Joe", "Linus", "Daniel", "Robert", "Tux", "Foo", "Lorem", "Julius", "Donald", "Mickey", "Miguel"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Martinez", "Hernandez", "Robinson", "Torvalds", "Penguin", "Bar", "Ipsum", "Duck", "Mouse", "Fernandez"};

        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }

    public static String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static <T>void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }

    public static String getRandomChar() {
        String[] chars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return chars[getRandomInt(0,chars.length - 1)];
    }

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

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

    public static String getRandomVowel() {
        String[] vowels = {"a", "e", "i", "o", "u"};
        return vowels[getRandomInt(0, vowels.length - 1)];
    }

    public static String getRandomConsonant() {
        String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
        return consonants[getRandomInt(0, consonants.length - 1)];
    }
}
