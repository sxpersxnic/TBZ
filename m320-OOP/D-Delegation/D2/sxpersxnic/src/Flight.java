import java.util.Random;

public class Flight {

  // <---------Passengers--------->
  private List<Passenger> passengers = new ArrayList<Passenger>();

  // <---------Constructor--------->
  public Flight(List<Passenger> passengers) { this.passengers = passengers }
  
  // <---------printPassengers--------->
  public void printPassengers() {
    System.out.println("Passengers of Flight " + randomFlightNumber() + ":");
    System.out.println("Firstname Lastname");
    for (Passenger passenger : passengers) {
      passenger.printName();
    }
  }

  public String randomFlightNumber() {
    
    Random random = new Random();
    String[] chars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"; "U", "V", "W", "X", "Y", "Z"};
    
    String firstChar = chars[random.nextInt(chars.length)];
    String secondChar = chars[random.nextInt(chars.length];

    return firstChar + secondChar + "-" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
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
