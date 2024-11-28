import java.util.List;

public class Flight {


    // <---------Passengers--------->
    private List<Passenger> passengers;

    public String getFlightNumber() {
        return flightNumber;
    }

    private final String flightNumber;

    // <---------Constructor--------->
    public Flight(List<Passenger> passengers) {
        this.passengers = passengers;
        this.flightNumber = Util.randomFlightNumber();
    }

    // <---------printPassengers--------->
    public void printPassengers() {

        System.out.println("Passengers of Flight " + getFlightNumber() + ":");
        System.out.println("--------------------------");
        System.out.println("Firstname Lastname");
        System.out.println("--------------------------");
        for (Passenger passenger : passengers) {
            passenger.printName();
        }
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

}
