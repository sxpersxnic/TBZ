import java.util.Random;

public class Flight {

    // <---------Passengers--------->
    private List<Passenger> passengers = new ArrayList<Passenger>();

    // <---------Constructor--------->
    public Flight(List<Passenger> passengers) { this.passengers = passengers }

    // <---------printPassengers--------->
    public void printPassengers() {
        System.out.println("Passengers of Flight " + Util.randomFlightNumber() + ":");
        System.out.println("Firstname Lastname");
        for (Passenger passenger : passengers) {
            passenger.printName();
        }
    }
}
