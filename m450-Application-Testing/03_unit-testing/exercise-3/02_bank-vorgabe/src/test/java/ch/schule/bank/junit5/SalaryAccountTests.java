package ch.schule.bank.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.schule.SalaryAccount;


/**
 * Tests der Klasse SalaryAccount.
 *
 * @author sxpersxnic
 * @version 1.1
 */
public class SalaryAccountTests
{
	private SalaryAccount account;

	@BeforeEach
	public void setUp() {
		// Create salary account with credit limit of -50000
		account = new SalaryAccount("P-1000", -50000);
	}

	/**
	 * Test initialization of salary account.
	 */
	@Test
	public void testInit()
	{
		assertEquals("P-1000", account.getId());
		assertEquals(0, account.getBalance());
	}

	/**
	 * Test deposit on salary account.
	 */
	@Test
	public void testDeposit()
	{
		assertTrue(account.deposit(1000, 100000));
		assertEquals(100000, account.getBalance());
	}

	/**
	 * Test withdrawal within credit limit.
	 */
	@Test
	public void testWithdrawWithinLimit()
	{
		account.deposit(1000, 30000);

		// Withdraw all deposited money
		assertTrue(account.withdraw(1001, 30000));
		assertEquals(0, account.getBalance());

		// Withdraw using credit limit
		assertTrue(account.withdraw(1002, 40000));
		assertEquals(-40000, account.getBalance());
	}

	/**
	 * Test withdrawal exceeding credit limit.
	 */
	@Test
	public void testWithdrawExceedingLimit()
	{
		// Try to withdraw more than credit limit allows
		assertFalse(account.withdraw(1000, 60000));
		assertEquals(0, account.getBalance());

		// Deposit and then try to exceed limit
		account.deposit(1000, 20000);
		// Balance would be 20000 - 80000 = -60000, which exceeds limit of -50000
		assertFalse(account.withdraw(1001, 80000));
		assertEquals(20000, account.getBalance());
	}

	/**
	 * Test withdrawal exactly at credit limit.
	 */
	@Test
	public void testWithdrawAtLimit()
	{
		// Withdraw exactly to the credit limit
		assertTrue(account.withdraw(1000, 50000));
		assertEquals(-50000, account.getBalance());

		// Any further withdrawal should fail
		assertFalse(account.withdraw(1001, 1));
		assertEquals(-50000, account.getBalance());
	}

	/**
	 * Test with zero credit limit.
	 */
	@Test
	public void testZeroCreditLimit()
	{
		SalaryAccount zeroCreditAccount = new SalaryAccount("P-2000", 0);
		zeroCreditAccount.deposit(1000, 10000);

		// Can withdraw up to balance
		assertTrue(zeroCreditAccount.withdraw(1001, 10000));
		assertEquals(0, zeroCreditAccount.getBalance());

		// Cannot go negative
		assertFalse(zeroCreditAccount.withdraw(1002, 1));
	}
}
