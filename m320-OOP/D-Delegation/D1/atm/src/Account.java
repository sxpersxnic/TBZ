package d1;

public class Account {
    private int id;
    private Bank bank;
    private final String name;
    private int balance;

    public Account(Bank bank, String name) {
        this.bank = bank;
        this.name = name;
        this.balance = 0;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void changeBank(Bank oldBank, Bank newBank) {
        oldBank.removeCustomer(this);
        newBank.addCustomer(this);
    }
}