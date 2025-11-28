package ch.schule.bank.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.schule.PromoYouthSavingsAccount;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 *
 * @author sxpersxnic
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests
{
	private PromoYouthSavingsAccount account;

	@BeforeEach
	public void setUp() {
		account = new PromoYouthSavingsAccount("Y-1000");
	}

	/**
	 * Test initialization.
	 */
	@Test
	public void testInit()
	{
		assertEquals("Y-1000", account.getId());
		assertEquals(0, account.getBalance());
	}

	/**
	 * Test that deposit adds 1% bonus.
	 */
	@Test
	public void testDepositBonus()
	{
		// Deposit 10000, should get 1% bonus = 100, total = 10100
		assertTrue(account.deposit(1000, 10000));
		assertEquals(10100, account.getBalance());

		// Deposit another 50000, bonus = 500, total added = 50500
		assertTrue(account.deposit(1001, 50000));
		assertEquals(10100 + 50500, account.getBalance());
	}

	/**
	 * Test bonus calculation with small amounts.
	 */
	@Test
	public void testSmallDepositBonus()
	{
		// Deposit 99, bonus = 0 (99/100 = 0 due to integer division)
		assertTrue(account.deposit(1000, 99));
		assertEquals(99, account.getBalance());

		// Deposit 100, bonus = 1, total = 101
		account = new PromoYouthSavingsAccount("Y-1001");
		assertTrue(account.deposit(1000, 100));
		assertEquals(101, account.getBalance());
	}

	/**
	 * Test withdrawal (inherited from SavingsAccount).
	 */
	@Test
	public void testWithdraw()
	{
		account.deposit(1000, 10000); // Gets 10100 with bonus

		// Withdraw 5000
		assertTrue(account.withdraw(1001, 5000));
		assertEquals(5100, account.getBalance());

		// Cannot withdraw more than balance (inherited from SavingsAccount)
		assertFalse(account.withdraw(1002, 10000));
		assertEquals(5100, account.getBalance());
	}

	/**
	 * Test large deposit bonus calculation.
	 */
	@Test
	public void testLargeDeposit()
	{
		// Deposit 1,000,000, bonus = 10,000
		assertTrue(account.deposit(1000, 1000000));
		assertEquals(1010000, account.getBalance());
	}
}
