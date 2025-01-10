import java.util.List;

/**
 * Represents a flight with passengers and flight-specific details.
 */
public class Flight {

    /** List of passengers on the flight */
    private List<Passenger> passengers;

    /** Unique flight number */
    private final String flightNumber;


    /**
     * Constructs a flight with a list of passengers.
     * Generates a random flight number upon creation.
     *
     * @param passengers Initial list of passengers on the flight
     */
    public Flight(List<Passenger> passengers) {
        this.passengers = passengers;
        this.flightNumber = Util.randomFlightNumber();
    }

    /**
     * Retrieves the flight number.
     *
     * @return The unique flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Prints the list of passengers on the flight.
     * Displays flight number and passenger names.
     */
    public void printPassengers() {

        System.out.println("Passengers of Flight " + getFlightNumber() + ":");
        System.out.println("--------------------------");
        System.out.println("Firstname Lastname");
        System.out.println("--------------------------");
        for (Passenger passenger : passengers) {
            passenger.printName();
        }
    }

    /**
     * Retrieves the current list of passengers.
     *
     * @return List of passengers on the flight
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Updates the list of passengers on the flight.
     *
     * @param passengers New list of passengers
     */
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

}
