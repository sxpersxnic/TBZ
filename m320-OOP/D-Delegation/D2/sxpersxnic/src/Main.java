import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Flight flight = Seed.seedFlight();
        List<Passenger> passengers = flight.getPassengers();

        System.out.println("------------D2------------");

        System.out.print("Enter your firstname: ");
        String firstName = Util.read();

        System.out.print("Enter your lastname: ");
        String lastName = Util.read();

        String name = firstName + " " + lastName;
        Passenger you = new Passenger(name);

        passengers.add(you);
        flight.setPassengers(passengers);
        flight.printPassengers();

        System.out.println("--------------------------");
    }
}
