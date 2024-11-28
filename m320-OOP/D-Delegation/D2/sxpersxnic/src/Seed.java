import java.util.ArrayList;
import java.util.Random;

public class Seed {

  public static Flight seedFlight() {
    ArrayList<Passenger>() passengers = new ArrayList<>();

    for (int i = 0; i <= 20; i++) {
      Passenger passenger = new Passenger(getRandomName());
      passengers.add(passenger);
    }

    return new Flight(passengers);
  }

  public static String getRandomName() {
    String[] firstNames = {"John", "Jane", "Alex", "Emily", "Chris", "Katie", "Michael", "Sarah", "David", "Laura", "Mike", "Joe", "Linus", "Daniel", "Robert", "Tux", "Foo", "Lorem", "Julius", "Donald", "Mickey", "Miguel"};
    String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Martinez", "Hernandez", "Robinson", "Torvalds", "Penguin", "Bar", "Ipsum", "Duck", "Mouse", "Fernandez"};

    Random random = new Random();
    String firstName = firstNames[random.nextInt(firstNames.length)];
    String lastName = lastNames[random.nextInt(lastNames.length)];

    return firstName + " " + lastName;
  }
}
