package ch.tbz.bank.software;

import java.util.ArrayList;

public class Bank {

    private final ArrayList<Account> accounts = new ArrayList<>();

    public Bank() {

    }

    public Account createAccount(final String name, final Currency currency, final double startBalance) {
        final Account a = new Account(name, currency, startBalance);
        addAccount(a);
        return a;
    }

    private void addAccount(final Account a) {
        accounts.add(a);
    }

    public void deleteAccount(final Account a) {
        System.out.println("Konto mit Nummer " + a.getId() + " wurde gelöscht.");
        accounts.remove(a);
//      The following doesn't work, is copy of a; no passing by reference in Java
//      a.pseudoDeleteAccount();
//      a = null;
    }

    public Account getAccount(final int nr) {
        for (final Account a : accounts) {
            if (a.getId() == nr) {
                return a;
            }
        }

        // Todo: Möglichkeit mit Lambda-Funktion? Im Sinne von:
        // Account a = accounts.get(a -> (a.getId() == Nr));

        return null;
    }

    public void printAccountDetails(final Account a) {
        if (!accounts.contains(a)) {
            System.out.println("Das Konto " +a.getId() + " existiert nicht mehr!");
            return;
        }
        System.out.println("___" );
        System.out.println("Kontonummer: " + a.getId());
        System.out.println("Nachname: " + a.getUserLastName());
        System.out.printf("Kontostand: %.2f %s\n", a.getBalance(), a.getCurrency());
    }

    public void printBalance(final Account a) {
        System.out.printf("Neuer Kontostand: %.2f %s\n", a.getBalance(), a.getCurrency());
    }

    public void printAccountsList() {
        for (final Account a: accounts) {
            System.out.println("Nr. " + a.getId() + ": " + a.getUserLastName() + " (" + a.getCurrency() + ")");
        }
    }

    public void printOtherAccounts(final Account acc) {
        for (final Account a: accounts) {
            if (a != acc) {
                System.out.println("Nr. " + a.getId() + ": " + a.getUserLastName());
            }
        }
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

}

