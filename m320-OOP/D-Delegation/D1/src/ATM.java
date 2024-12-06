package d1;

public class ATM {

    private Account current;

    public int withdraw(int amount) {
        Account current = getCurrent();

        if (current.getBalance() <= 0) {
            System.out.println("You don't have enough money");
            return current.getBalance();
        }

        int sanitized = current.getBalance();
        int newAmount = sanitized - amount;
        current.setBalance(newAmount);
        return newAmount;
    }

    public int deposit(int amount) {
        Account current = getCurrent();
        int newAmount = current.getBalance() + amount;
        current.setBalance(newAmount);
        return newAmount;
    }

    public int getBalance() {
        Account current = getCurrent();
        return current.getBalance();
    }

    public Account getCurrent() {
        return current;
    }

    public void setCurrent(Account current) {
        this.current = current;
    }
}