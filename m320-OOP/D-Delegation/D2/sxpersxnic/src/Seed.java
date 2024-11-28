import java.util.ArrayList;
import java.util.Random;

public class Seed {

    public static Flight seedFlight() {
        ArrayList<Passenger>() passengers = new ArrayList<>();

        for (int i = 0; i <= 20; i++) {
            Passenger passenger = new Passenger(Util.getRandomName());
            passengers.add(passenger);
        }

        return new Flight(passengers);
    }
}
