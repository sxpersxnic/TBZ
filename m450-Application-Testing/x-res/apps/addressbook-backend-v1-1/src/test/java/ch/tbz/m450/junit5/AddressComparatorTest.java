package ch.tbz.m450.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.util.AddressComparator;

public class AddressComparatorTest {

    private AddressComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new AddressComparator();
    }

    @Test
    void compare_shouldReturnNegative_whenFirstLastnameComesBeforeSecond() {
        Address a1 = new Address(1, "John", "Anderson", "123456", new Date());
        Address a2 = new Address(2, "Jane", "Brown", "654321", new Date());

        int result = comparator.compare(a1, a2);

        assertTrue(result < 0);
    }

    @Test
    void compare_shouldReturnPositive_whenFirstLastnameComesAfterSecond() {
        Address a1 = new Address(1, "John", "Zimmerman", "123456", new Date());
        Address a2 = new Address(2, "Jane", "Anderson", "654321", new Date());

        int result = comparator.compare(a1, a2);

        assertTrue(result > 0);
    }

    @Test
    void compare_shouldCompareByFirstname_whenLastnamesAreEqual() {
        Address a1 = new Address(1, "Alice", "Smith", "123456", new Date());
        Address a2 = new Address(2, "Bob", "Smith", "654321", new Date());

        int result = comparator.compare(a1, a2);

        assertTrue(result < 0);
    }

    @Test
    void compare_shouldReturnZero_whenBothNamesAreEqual() {
        Date sameDate = new Date();
        Address a1 = new Address(1, "John", "Doe", "123456", sameDate);
        Address a2 = new Address(2, "John", "Doe", "123456", sameDate);

        int result = comparator.compare(a1, a2);

        assertEquals(0, result);
    }

    @Test
    void compare_shouldBeCaseInsensitive() {
        Date sameDate = new Date();
        Address a1 = new Address(1, "john", "doe", "123456", sameDate);
        Address a2 = new Address(2, "JOHN", "DOE", "123456", sameDate);

        int result = comparator.compare(a1, a2);

        assertEquals(0, result);
    }

    @Test
    void compare_shouldSortListCorrectly() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1, "John", "Zimmerman", "111", new Date()));
        addresses.add(new Address(2, "Alice", "Brown", "222", new Date()));
        addresses.add(new Address(3, "Bob", "Brown", "333", new Date()));
        addresses.add(new Address(4, "Jane", "Anderson", "444", new Date()));

        addresses.sort(comparator);

        assertEquals("Anderson", addresses.get(0).getLastname());
        assertEquals("Alice", addresses.get(1).getFirstname());
        assertEquals("Bob", addresses.get(2).getFirstname());
        assertEquals("Zimmerman", addresses.get(3).getLastname());
    }

    @Test
    void compare_shouldCompareByPhonenumber_whenNamesAreEqual() {
        Address a1 = new Address(1, "John", "Doe", "111", new Date());
        Address a2 = new Address(2, "John", "Doe", "222", new Date());

        int result = comparator.compare(a1, a2);

        assertTrue(result < 0);
    }

    @Test
    void compare_shouldCompareByRegistrationDate_whenNamesAndPhoneAreEqual() {
        Date earlierDate = new Date(1000);
        Date laterDate = new Date(2000);
        Address a1 = new Address(1, "John", "Doe", "123", earlierDate);
        Address a2 = new Address(2, "John", "Doe", "123", laterDate);

        int result = comparator.compare(a1, a2);

        assertTrue(result < 0);
    }

    @Test
    void compare_shouldReturnZero_whenAllFieldsAreEqual() {
        Date sameDate = new Date(1000);
        Address a1 = new Address(1, "John", "Doe", "123", sameDate);
        Address a2 = new Address(2, "John", "Doe", "123", sameDate);

        int result = comparator.compare(a1, a2);

        assertEquals(0, result);
    }

    @Test
    void compare_shouldHandleNullRegistrationDate_bothNull() {
        Address a1 = new Address(1, "John", "Doe", "123", null);
        Address a2 = new Address(2, "John", "Doe", "123", null);

        int result = comparator.compare(a1, a2);

        assertEquals(0, result);
    }

    @Test
    void compare_shouldHandleNullRegistrationDate_firstNull() {
        Address a1 = new Address(1, "John", "Doe", "123", null);
        Address a2 = new Address(2, "John", "Doe", "123", new Date());

        int result = comparator.compare(a1, a2);

        assertTrue(result < 0);
    }

    @Test
    void compare_shouldHandleNullRegistrationDate_secondNull() {
        Address a1 = new Address(1, "John", "Doe", "123", new Date());
        Address a2 = new Address(2, "John", "Doe", "123", null);

        int result = comparator.compare(a1, a2);

        assertTrue(result > 0);
    }

    @Test
    void compare_shouldSortCompletelyByAllFields() {
        Date date1 = new Date(1000);
        Date date2 = new Date(2000);
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1, "John", "Doe", "123", date2));
        addresses.add(new Address(2, "John", "Doe", "123", date1));
        addresses.add(new Address(3, "John", "Doe", "111", date1));
        addresses.add(new Address(4, "Alice", "Doe", "123", date1));
        addresses.add(new Address(5, "John", "Brown", "123", date1));

        addresses.sort(comparator);

        // Order: Brown, then Doe (Alice before John), then by phone (111 before 123), then by date
        assertEquals("Brown", addresses.get(0).getLastname());
        assertEquals("Alice", addresses.get(1).getFirstname());
        assertEquals("111", addresses.get(2).getPhonenumber());
        assertEquals(date1, addresses.get(3).getRegistrationDate());
        assertEquals(date2, addresses.get(4).getRegistrationDate());
    }
}
