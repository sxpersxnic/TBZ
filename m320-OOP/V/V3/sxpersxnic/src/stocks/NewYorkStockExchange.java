package stocks;

public class NewYorkStockExchange implements StockExchange {
    @Override
    public double getPrice(String stock) {
        return switch (stock) {
            case "Microsoft" -> 100.0;
            case "Apple" -> 150.0;
            case "Google" -> 200.0;
            case "Nvidia" -> 300.0;
            default -> 0.0;
        };
    }
}
