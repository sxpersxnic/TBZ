package ch.schule.bank.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.schule.Booking;
import ch.schule.SavingsAccount;

/**
 * Tests für die Klasse Account.
 * Uses SavingsAccount as concrete implementation since Account is abstract.
 *
 * @author sxpersxnic
 * @version 1.0
 */
public class AccountTests {

    private SavingsAccount testAccount;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        testAccount = new SavingsAccount("Test-account");
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Tested die Initialisierung eines Kontos.
     */
    @Test
    public void testInit() {
        SavingsAccount account = new SavingsAccount("S-1000");

        assertEquals("S-1000", account.getId());
        assertEquals(0, account.getBalance());
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        // Test successful deposit
        assertTrue(testAccount.deposit(1000, 10000));
        assertEquals(10000, testAccount.getBalance());

        // Test deposit with negative amount should fail
        assertFalse(testAccount.deposit(1001, -5000));
        assertEquals(10000, testAccount.getBalance());

        // Test multiple deposits
        assertTrue(testAccount.deposit(1002, 5000));
        assertEquals(15000, testAccount.getBalance());
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        // Setup: deposit some money first
        testAccount.deposit(1000, 20000);

        // Test successful withdrawal
        assertTrue(testAccount.withdraw(1001, 5000));
        assertEquals(15000, testAccount.getBalance());

        // Test withdrawal with negative amount should fail
        assertFalse(testAccount.withdraw(1002, -1000));
        assertEquals(15000, testAccount.getBalance());
    }

    /**
     * Tests the reference from SavingsAccount
     */
    @Test
    public void testReferences() {
        // Test that booking can be set and retrieved
        Booking booking = new Booking(1000, 5000);
        testAccount.setBooking(booking);

        assertNotNull(testAccount.getBooking());
        assertEquals(1000, testAccount.getBooking().getDate());
        assertEquals(5000, testAccount.getBooking().getAmount());
    }

    /**
     * Test the canTransact Flag
     */
    @Test
    public void testCanTransact() {
        // Empty account should allow transaction on any date
        assertTrue(testAccount.canTransact(1000));

        // After a transaction, should allow transaction on same or later date
        testAccount.deposit(1000, 5000);
        assertTrue(testAccount.canTransact(1000)); // Same date
        assertTrue(testAccount.canTransact(1001)); // Later date

        // Should not allow transaction on earlier date
        assertFalse(testAccount.canTransact(999));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        testAccount.deposit(1000, 10000);
        testAccount.withdraw(1001, 3000);

        testAccount.print();

        String output = outputStream.toString();
        assertTrue(output.contains("Kontoauszug 'Test-account'"));
        assertTrue(output.contains("Datum"));
        assertTrue(output.contains("Betrag"));
        assertTrue(output.contains("Saldo"));

        System.setOut(originalOut);
    }

    /**
     * Experimente mit print(year,month).
     */
    @Test
    public void testMonthlyPrint() {
        // Date 1000 is approximately year 1972, month 10 (1000/360 = 2.77 years, 1000%360/30 = 9.67 months)
        // Let's use a more predictable date: year 1971, month 1 = (1)*360 + (0)*30 = 360
        int date = 360; // Year 1971, Month 1
        testAccount.deposit(date, 10000);
        testAccount.withdraw(date + 1, 3000);

        testAccount.print(1971, 1);

        String output = outputStream.toString();
        assertTrue(output.contains("Kontoauszug 'Test-account'"));
        assertTrue(output.contains("Monat: 1.1971"));

        System.setOut(originalOut);
    }

}
