public class Flight {

  // <---------Passengers--------->
  private List<Passenger> passengers = new ArrayList<Passenger>();

  // <---------Constructor--------->
  public Flight(List<Passenger> passengers) { this.passengers = passengers }
  
  // <---------printPassengers--------->
  public void printPassengers() {
    for (Passenger passenger : passengers) {
      passenger.printName();
    }
  }

  // <---------Getter--------->
  public List<Passenger> getPassengers() {
    return passengers;
  } 
  
  // <---------Setter--------->
  public void setPassengers(List<Passenger> passengers) {
    this.passengers = passengers;
  }
  
}
