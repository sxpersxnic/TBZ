package ch.tbz.m450.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.tbz.m450.repository.Address;

public class AddressTest {

    private Address address;
    private Date testDate;

    @BeforeEach
    void setUp() {
        testDate = new Date();
        address = new Address(1, "John", "Doe", "123456789", testDate);
    }

    @Test
    void constructor_shouldCreateAddressWithAllFields() {
        assertNotNull(address);
        assertEquals(1, address.getId());
        assertEquals("John", address.getFirstname());
        assertEquals("Doe", address.getLastname());
        assertEquals("123456789", address.getPhonenumber());
        assertEquals(testDate, address.getRegistrationDate());
    }

    @Test
    void noArgsConstructor_shouldCreateEmptyAddress() {
        Address emptyAddress = new Address();
        assertNotNull(emptyAddress);
        assertEquals(0, emptyAddress.getId());
        assertNull(emptyAddress.getFirstname());
        assertNull(emptyAddress.getLastname());
        assertNull(emptyAddress.getPhonenumber());
        assertNull(emptyAddress.getRegistrationDate());
    }

    @Test
    void setId_shouldUpdateId() {
        address.setId(99);
        assertEquals(99, address.getId());
    }

    @Test
    void setFirstname_shouldUpdateFirstname() {
        address.setFirstname("Jane");
        assertEquals("Jane", address.getFirstname());
    }

    @Test
    void setLastname_shouldUpdateLastname() {
        address.setLastname("Smith");
        assertEquals("Smith", address.getLastname());
    }

    @Test
    void setPhonenumber_shouldUpdatePhonenumber() {
        address.setPhonenumber("987654321");
        assertEquals("987654321", address.getPhonenumber());
    }

    @Test
    void setRegistrationDate_shouldUpdateRegistrationDate() {
        Date newDate = new Date(0);
        address.setRegistrationDate(newDate);
        assertEquals(newDate, address.getRegistrationDate());
    }

    @Test
    void getId_shouldReturnCorrectId() {
        assertEquals(1, address.getId());
    }

    @Test
    void getFirstname_shouldReturnCorrectFirstname() {
        assertEquals("John", address.getFirstname());
    }

    @Test
    void getLastname_shouldReturnCorrectLastname() {
        assertEquals("Doe", address.getLastname());
    }

    @Test
    void getPhonenumber_shouldReturnCorrectPhonenumber() {
        assertEquals("123456789", address.getPhonenumber());
    }

    @Test
    void getRegistrationDate_shouldReturnCorrectDate() {
        assertEquals(testDate, address.getRegistrationDate());
    }
}
