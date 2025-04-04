package d1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Bank ubs = new Bank("UBS");
        ATM atm = new ATM();
        boolean run = true;

        while (run) {
            System.out.println("Select: [1] Start Transaction, [2] Create Account");
            int choice = Integer.parseInt(System.console().readLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter account ID: ");
                    int id = Integer.parseInt(System.console().readLine());
                    ArrayList<Account> customers = ubs.getCustomers();
                    Account current = customers.get(id-1);
                    atm.setCurrent(current);
                    System.out.println("Welcome back " + current.getName() + "!");
                    System.out.println("Select: [1] Withdraw, [2] Deposit, [3] Display current balance");
                    int action = Integer.parseInt(System.console().readLine());

                    switch (action) {
                        case 1:
                            System.out.println("Enter amount to withdraw: ");
                            int amount = Integer.parseInt(System.console().readLine());
                            int newAmount = atm.withdraw(amount);
                            System.out.println("Your new balance is: " + newAmount);
                            break;
                        case 2:
                            System.out.println("Enter amount to deposit: ");
                            int depositAmount = Integer.parseInt(System.console().readLine());
                            int depositedAmount = atm.deposit(depositAmount);
                            System.out.println("Your new balance is: " + depositedAmount);
                            break;
                        case 3:
                            System.out.println("Your current balance is: " + atm.getBalance());
                            break;
                        default:
                            System.out.println("Goodbye");
                            run = false;
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Enter Name: ");
                    String name = System.console().readLine();
                    Account newAccount = new Account(ubs, name);
                    int accId = ubs.addCustomer(newAccount);
                    System.out.println("Welcome at UBS, your ID is " + accId + ". Remember it!");
                    break;
                default:
                    System.out.println("Goodbye");
                    run = false;
                    break;
            }
        }
    }
}

