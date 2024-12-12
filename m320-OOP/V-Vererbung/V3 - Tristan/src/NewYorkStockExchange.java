public class NewYorkStockExchange implements StockExchange {
    @Override
    public double getPrice(String stock) {
        // Feste Preise für die Demonstration
        return switch (stock) {
            case "Microsoft" -> 100.0;
            case "Apple" -> 150.0;
            default -> 0.0; // Standardwert für unbekannte Aktien
        };
    }
}