
/**
 * Represents an individual passenger on a flight.
 */
public class Passenger {

    /** Passenger's full name */
    private final String name;

    /**
     * Constructs a passenger with a given name.
     *
     * @param name Full name of the passenger
     */
    public Passenger(String name) {
        this.name = name;
    }

    /**
     * Prints the passenger's name.
     */
    public void printName() {
        System.out.println(name);
    }
}
