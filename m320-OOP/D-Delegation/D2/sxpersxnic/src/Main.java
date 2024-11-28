import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    Flight flight = Seed.seedFlight();
    List<Passenger> passengers = flight.getPassengers();
    
    System.out.println("------------D2------------");
    
    System.out.print("Enter your firstname: ");
    String firstName = scanner.nextLine();
    
    System.out.print("Enter your lastname: ");
    String lastName = scanner.nextLine();
    
    String name = firstName + " " + lastName;
    Passenger you = new Passenger(name);

    passengers.add(you);
    flight.setPassengers(passengers);
    flight.printPassengers();
  
    System.out.println("--------------------------");
  }
}
