import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    // Eine Map zur Speicherung von Aktien und zugehörigen Börsen
    private Map<String, StockExchange> stocks = new HashMap<>();

    // Methode zum Hinzufügen einer Aktie mit der zugehörigen Börse
    public void addStock(String stock, StockExchange stockExchange) {
        stocks.put(stock, stockExchange);
    }

    // Methode zum Abrufen des Preises aller Aktien im Portfolio
    public void printStockPrices() {
        for (Map.Entry<String, StockExchange> entry : stocks.entrySet()) {
            String stock = entry.getKey();
            StockExchange exchange = entry.getValue();
            System.out.println(stock + " Preis: " + exchange.getPrice(stock));
        }
    }
}
