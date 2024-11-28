public class Flight {
  List<Passenger> passengers = new ArrayList<Passenger>();

  void printPassengers() {
    for (Passenger passenger : passengers) {
      passenger.printName();
    }
  }
}
