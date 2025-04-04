
import stocks.StockExchange;
import java.util.Collection;
import java.util.List;

public class Portfolio {
    private List<String> stocks;
    private StockExchange stockExchange;

    public Portfolio(List<String> stocks, StockExchange stockExchange) {
        this.stocks = stocks;
        this.stockExchange = stockExchange;
    }

    public void printStockPrices() {
        for (String stock : stocks) {
            System.out.println(stock + ": " + stockExchange.getPrice(stock));
        }
    }
}
