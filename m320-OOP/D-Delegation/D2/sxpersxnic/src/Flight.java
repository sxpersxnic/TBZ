public class Flight {
  List<Passenger> passengers = new ArrayList<Passenger>();

  public void printPassengers() {
    for (Passenger passenger : passengers) {
      passenger.printName();
    }
  }
}
