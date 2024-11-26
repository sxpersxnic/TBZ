package d1;
import java.util.*;

public class Bank {
    private final String name;
    private ArrayList<ATM> atms;
    private final ArrayList<Account> customers;

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<ATM> getAtms() {
        return atms;
    }

    public ArrayList<Account> getCustomers() {
        return customers;
    }

    public void removeCustomer(Account customer) {
        customers.remove(customer);
    }
    public int addCustomer(Account customer) {
        customers.add(customer);

        return customers.size();
    }

    public void removeATM(ATM atm) {
        atms.remove(atm);
    }
    public void addATM(ATM atm) {
        atms.add(atm);
    }

}