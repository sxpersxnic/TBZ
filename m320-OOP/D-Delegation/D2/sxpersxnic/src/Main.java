import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Main application class for flight booking system.
 * Handles flight registration, passenger management, and flight initialization.
 */
public class Main {

    /**
     * Primary entry point of the flight booking application.
     * Allows user to join a flight, view passengers, and start or remove themselves from the flight.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Flight flight = Seed.seedFlight();
        List<Passenger> passengers = flight.getPassengers();

        System.out.println("------------D2------------");
        System.out.println(" ");

        System.out.print("Enter your firstname: ");
        String firstName = Util.read();

        System.out.print("Enter your lastname: ");
        String lastName = Util.read();

        String name = firstName + " " + lastName;
        Passenger you = new Passenger(name);

        System.out.println(" ");
        passengers.add(you);
        flight.setPassengers(passengers);
        flight.printPassengers();
        System.out.println("--------------------------");

        System.out.print("Start flight? ");
        System.out.print("[r] Remove me from list [s] Start: ");
        System.out.println(" ");
        String choice = Util.read();

        switch (choice) {
            case "s":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                System.out.println(" ");
                System.out.println("Flight " + flight.getFlightNumber() + " started at " + Instant.now().atZone(ZoneId.systemDefault()).format(formatter) + "!");
                break;
            case "r":
                List<Passenger> passengerList = flight.getPassengers();
                passengerList.remove(you);
                flight.setPassengers(passengerList);
                System.out.println(" ");
                System.out.println("You have been removed from the flight!");
                System.out.println(" ");
                System.out.println("New list: ");
                System.out.println(" ");
                flight.printPassengers();
                break;
            default:
                break;
        }

        System.out.println(" ");
        System.out.println("Goodbye!");
    }
}
