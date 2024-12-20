import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Scanner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean running = true;
        String cryptoCurrency;
        String realCurrency;


        while(running)
        {
            System.out.println("Welche Kryptowährung möchten Sie ansehen?");
            cryptoCurrency = userInput.nextLine();
            System.out.println("In welche Fiat-Währung soll diese umgerechnet werden?");
            realCurrency = userInput.nextLine();
            APICaller(cryptoCurrency, realCurrency);

            System.out.println("Möchten Sie sich noch eine Kryptowährung ansehen? (y/n)");
            switch(userInput.nextLine())
            {
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

    private static void APICaller(String cryptoCurrency, String realCurrency) {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=" + cryptoCurrency + "&vs_currencies=" + realCurrency;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // JSON-Antwort verarbeiten
            String responseBody = response.body();
            ObjectMapper mapper = new ObjectMapper();

            JsonNode node = mapper.readTree(responseBody);
            if (node.has(cryptoCurrency) && node.get(cryptoCurrency).has(realCurrency)) {
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

                String formattedPrice = decimalFormat.format(node.get(cryptoCurrency).get(realCurrency).asDouble());
                System.out.println("Ein " + cryptoCurrency + " = " + formattedPrice + " " + realCurrency);
            } else {
                System.out.println("Die eingegebene Kryptowährung oder Fiat-Währung ist ungültig.");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Fehler beim Abrufen des Preises: " + e.getMessage());
        }
    }
}