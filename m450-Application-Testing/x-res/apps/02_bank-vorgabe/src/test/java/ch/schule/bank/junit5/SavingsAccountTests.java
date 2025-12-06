package ch.schule.bank.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.schule.SavingsAccount;


/**
 * Tests für die Klasse SavingsAccount.
 *
 * @author sxpersxnic
 * @version 1.0
 */
public class SavingsAccountTests
{
	private SavingsAccount account;

	@BeforeEach
	public void setUp() {
		account = new SavingsAccount("S-1000");
	}

	/**
	 * Test initialization of savings account.
	 */
	@Test
	public void testInit()
	{
		assertEquals("S-1000", account.getId());
		assertEquals(0, account.getBalance());
	}

	/**
	 * Test deposit on savings account.
	 */
	@Test
	public void testDeposit()
	{
		assertTrue(account.deposit(1000, 50000));
		assertEquals(50000, account.getBalance());

		// Multiple deposits
		assertTrue(account.deposit(1001, 25000));
		assertEquals(75000, account.getBalance());
	}

	/**
	 * Test withdrawal from savings account.
	 * SavingsAccount cannot withdraw more than the current balance.
	 */
	@Test
	public void testWithdraw()
	{
		account.deposit(1000, 50000);

		// Successful withdrawal
		assertTrue(account.withdraw(1001, 20000));
		assertEquals(30000, account.getBalance());

		// Withdrawal exceeding balance should fail
		assertFalse(account.withdraw(1002, 50000));
		assertEquals(30000, account.getBalance());

		// Withdrawal of exact balance should succeed
		assertTrue(account.withdraw(1002, 30000));
		assertEquals(0, account.getBalance());
	}

	/**
	 * Test that savings account cannot go negative.
	 */
	@Test
	public void testNoNegativeBalance()
	{
		// Try to withdraw from empty account
		assertFalse(account.withdraw(1000, 1000));
		assertEquals(0, account.getBalance());
	}
}

