package ch.schule.bank.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.schule.Bank;


/**
 * Tests für die Klasse 'Bank'.
 *
 * @author sxpersxnic
 * @version 1.0
 */
public class BankTests {

    private Bank bank;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Tests to create new Accounts
     */
    @Test
    public void testCreate() {
        // Test creating savings account
        String savingsId = bank.createSavingsAccount();
        assertNotNull(savingsId);
        assertEquals("S-1000", savingsId);

        // Test creating second savings account
        String savingsId2 = bank.createSavingsAccount();
        assertEquals("S-1001", savingsId2);

        // Test creating promo youth savings account
        String youthId = bank.createPromoYouthSavingsAccount();
        assertEquals("Y-1002", youthId);

        // Test creating salary account
        String salaryId = bank.createSalaryAccount(-5000);
        assertEquals("P-1003", salaryId);

        // Test creating salary account with invalid credit limit (positive)
        String invalidSalary = bank.createSalaryAccount(1000);
        assertNull(invalidSalary);
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        String accountId = bank.createSavingsAccount();

        // Test successful deposit
        assertTrue(bank.deposit(accountId, 1000, 10000));
        assertEquals(10000, bank.getBalance(accountId));

        // Test deposit on non-existent account
        assertFalse(bank.deposit("INVALID-ID", 1001, 5000));

        // Test multiple deposits
        assertTrue(bank.deposit(accountId, 1001, 5000));
        assertEquals(15000, bank.getBalance(accountId));
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        String accountId = bank.createSavingsAccount();
        bank.deposit(accountId, 1000, 20000);

        // Test successful withdrawal
        assertTrue(bank.withdraw(accountId, 1001, 5000));
        assertEquals(15000, bank.getBalance(accountId));

        // Test withdrawal on non-existent account
        assertFalse(bank.withdraw("INVALID-ID", 1002, 1000));

        // Test withdrawal exceeding balance (should fail for savings account)
        assertFalse(bank.withdraw(accountId, 1002, 100000));
        assertEquals(15000, bank.getBalance(accountId));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        String accountId = bank.createSavingsAccount();
        bank.deposit(accountId, 1000, 10000);
        bank.withdraw(accountId, 1001, 3000);

        bank.print(accountId);

        String output = outputStream.toString();
        assertTrue(output.contains("Kontoauszug '" + accountId + "'"));

        System.setOut(originalOut);
    }

    /**
     * Experimente mit print(year, month).
     */
    @Test
    public void testMonthlyPrint() {
        String accountId = bank.createSavingsAccount();
        int date = 360; // Year 1971, Month 1
        bank.deposit(accountId, date, 10000);

        bank.print(accountId, 1971, 1);

        String output = outputStream.toString();
        assertTrue(output.contains("Monat: 1.1971"));

        System.setOut(originalOut);
    }

    /**
     * Testet den Gesamtkontostand der Bank.
     */
    @Test
    public void testBalance() {
        // Initially bank balance should be 0
        assertEquals(0, bank.getBalance());

        // Create accounts and deposit
        String acc1 = bank.createSavingsAccount();
        String acc2 = bank.createSavingsAccount();

        bank.deposit(acc1, 1000, 10000);
        bank.deposit(acc2, 1000, 20000);

        // Bank balance is negative of sum of all account balances
        // (bank owes money to account holders)
        assertEquals(-30000, bank.getBalance());

        // Test getBalance for non-existent account
        assertEquals(0, bank.getBalance("INVALID-ID"));
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testTop5() {
        // Create multiple accounts with different balances
        String[] accounts = new String[6];
        for (int i = 0; i < 6; i++) {
            accounts[i] = bank.createSavingsAccount();
            bank.deposit(accounts[i], 1000, (i + 1) * 10000L);
        }

        bank.printTop5();

        String output = outputStream.toString();
        // Top 5 should be accounts with highest balance (S-1005, S-1004, S-1003, S-1002, S-1001)
        assertTrue(output.contains("S-1005"));
        assertTrue(output.contains("S-1004"));
        assertTrue(output.contains("S-1003"));
        assertTrue(output.contains("S-1002"));
        assertTrue(output.contains("S-1001"));

        System.setOut(originalOut);
    }

    /**
     * Tested die Ausgabe der "bottom 5" konten.
     */
    @Test
    public void testBottom5() {
        // Create multiple accounts with different balances
        String[] accounts = new String[6];
        for (int i = 0; i < 6; i++) {
            accounts[i] = bank.createSavingsAccount();
            bank.deposit(accounts[i], 1000, (i + 1) * 10000L);
        }

        bank.printBottom5();

        String output = outputStream.toString();
        // Bottom 5 should be accounts with lowest balance (S-1000, S-1001, S-1002, S-1003, S-1004)
        assertTrue(output.contains("S-1000"));
        assertTrue(output.contains("S-1001"));
        assertTrue(output.contains("S-1002"));
        assertTrue(output.contains("S-1003"));
        assertTrue(output.contains("S-1004"));

        System.setOut(originalOut);
    }

}
