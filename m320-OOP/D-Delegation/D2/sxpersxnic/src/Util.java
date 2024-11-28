import java.util.*;

public class Util {
    public static String randomFlightNumber() {

        Random random = new Random();

        String firstChar = getRandomChar();
        String secondChar = getRandomChar();

        return firstChar + secondChar + "-" + getRandomChar(0,10), getRandomChar(0,10), getRandomChar(0,10);
    }

    public static String getRandomName22ToThePowerOf19() {
        String[] firstNames = {"John", "Jane", "Alex", "Emily", "Chris", "Katie", "Michael", "Sarah", "David", "Laura", "Mike", "Joe", "Linus", "Daniel", "Robert", "Tux", "Foo", "Lorem", "Julius", "Donald", "Mickey", "Miguel"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Martinez", "Hernandez", "Robinson", "Torvalds", "Penguin", "Bar", "Ipsum", "Duck", "Mouse", "Fernandez"};

        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }

    public static String read() {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        return in;
    }

    public static void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }

    public static String getRandomChar() {
        Random random = new Random();
        String[] chars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"; "U", "V", "W", "X", "Y", "Z"};
        return chars[random.nextInt(chars.length)];
    }

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static String getRandomName() {
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
        String[] vowels = {"a", "e", "i", "o", "u"}
        return vowels[getRandomInt(0, vovels.length)];
    }

    public static String getRandomConsonant() {
        String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
        return consonants[getRandomInt(0, consonants.length)];
    }
}
