import javax.sound.sampled.Port;

public class Main {
    public static void main(String[] args) {
        // Börsenplätze instanziieren
        NewYorkStockExchange nyse = new NewYorkStockExchange();
        ZuerichStockExchange zse = new ZuerichStockExchange();

        //Neues Portfolio erstellen
        Portfolio myPortfolio = new Portfolio();


        myPortfolio.addStock("Microsoft", nyse);
        myPortfolio.addStock("Apple", zse);

        myPortfolio.printStockPrices();
    }
}