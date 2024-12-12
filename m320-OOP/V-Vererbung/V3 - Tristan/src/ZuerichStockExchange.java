public class ZuerichStockExchange implements StockExchange {
    @Override
    public double getPrice(String stock) {
        // Feste Preise für die Demonstration
        return switch (stock) {
            case "Microsoft" -> 120.0;
            case "Apple" -> 170.0;
            default -> 0.0; // Standardwert für unbekannte Aktien
        };
    }
}
