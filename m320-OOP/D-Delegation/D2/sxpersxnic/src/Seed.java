import java.util.ArrayList;

/**
 * Utility class for seeding initial flight data.
 */
public class Seed {

    /**
     * Creates a flight with a predefined number of randomly generated passengers.
     *
     * @return A flight with randomly generated passengers
     */
    public static Flight seedFlight() {
        ArrayList<Passenger> passengers = new ArrayList<>();

        for (int i = 0; i <= 20; i++) {
            Passenger passenger = new Passenger(Util.getRandomName());
            passengers.add(passenger);
        }

        return new Flight(passengers);
    }
}
