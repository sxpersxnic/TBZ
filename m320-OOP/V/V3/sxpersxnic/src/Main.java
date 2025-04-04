import stocks.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StockExchange nyse = new NewYorkStockExchange();
        StockExchange zhse = new ZurichStockExchange();
        List<String> stocks = new ArrayList<>();
        stocks.add("Microsoft");
        stocks.add("Apple");
        stocks.add("Google");
        stocks.add("Nvidia");

        Scanner sc = new Scanner(System.in);
        System.out.println("Select stock exchange: (1) New York, (2) Zürich");
        int choice = sc.nextInt();

        StockExchange selectedExchange = (choice == 1) ? nyse : zhse;

        Portfolio portfolio = new Portfolio(stocks, selectedExchange);
        portfolio.printStockPrices();
    }
}
