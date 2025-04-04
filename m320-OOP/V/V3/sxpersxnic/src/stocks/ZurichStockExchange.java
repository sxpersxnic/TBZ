package stocks;

public class ZurichStockExchange implements StockExchange {
    @Override
    public double getPrice(String stock) {
        return switch (stock) {
            case "Microsoft" -> 120.0;
            case "Apple" -> 160.0;
            case "Google" -> 300.0;
            case "Nvidia" -> 400.0;
            default -> 0.0;
        };
    }
}
