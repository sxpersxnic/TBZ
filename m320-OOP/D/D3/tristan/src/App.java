import java.util.Scanner;

public class App {
    Scanner userInput = new Scanner(System.in);
    boolean running = true;
    String cryptoCurrency;
    String realCurrency;
    APICaller newAPICall = new APICaller();

    public void menu() {
        while (running) {
            System.out.println("Welche Kryptowährung möchten Sie ansehen?");
            cryptoCurrency = userInput.nextLine();
            System.out.println("In welche Fiat-Währung soll diese umgerechnet werden?");
            realCurrency = userInput.nextLine();
            newAPICall.call(cryptoCurrency, realCurrency);

            System.out.println("Möchten Sie sich noch eine Kryptowährung ansehen? (y/n)");
            switch (userInput.nextLine()) {
                case "y":
                    running = true;
                    break;

                case "n":
                    running = false;
                    break;

                default:
                    System.out.println("Ungültige Eingabe!");
            }
        }
    }
}
