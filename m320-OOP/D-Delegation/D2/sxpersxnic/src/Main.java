public class Main {
  public static void main(String[] args) {
    System.out.println("------------D2------------");
    Flight flight = Seed.seedFlight();
    List<Passenger> passengers = flight.getPassengers();
    
    // Enter firstName ...
    String firstName = "firstName";
    // Enter lastName ...
    String lastName = "lastName";
    String name = firstName + " " + lastName;
    Passenger you = new Passenger(name);

    passengers.add(you);
    flight.setPassengers(passengers);
    flight.printPassengers();
    System.out.println("--------------------------");
  }
}
