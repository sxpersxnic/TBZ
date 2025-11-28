package ch.schule.bank.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import ch.schule.Booking;


/**
 * Tests für die Klasse Booking.
 *
 * @author Luigi Cavuoti
 * @version 1.1
 */
public class BookingTests
{
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	/**
	 * Tests für die Erzeugung von Buchungen.
	 */
	@Test
	public void testInitialization()
	{
		// Test booking creation with positive amount (deposit)
		Booking depositBooking = new Booking(1000, 50000);
		assertEquals(1000, depositBooking.getDate());
		assertEquals(50000, depositBooking.getAmount());

		// Test booking creation with negative amount (withdrawal)
		Booking withdrawalBooking = new Booking(1001, -30000);
		assertEquals(1001, withdrawalBooking.getDate());
		assertEquals(-30000, withdrawalBooking.getAmount());

		// Test booking with zero amount
		Booking zeroBooking = new Booking(500, 0);
		assertEquals(500, zeroBooking.getDate());
		assertEquals(0, zeroBooking.getAmount());
	}

	/**
	 * Experimente mit print().
	 */
	@Test
	public void testPrint()
	{
		System.setOut(new PrintStream(outputStream));

		Booking booking = new Booking(1000, 50000);
		long previousBalance = 100000;
		booking.print(previousBalance);

		String output = outputStream.toString();
		// Output should contain formatted date and amounts
		assertFalse(output.isEmpty());

		System.setOut(originalOut);
	}
}
