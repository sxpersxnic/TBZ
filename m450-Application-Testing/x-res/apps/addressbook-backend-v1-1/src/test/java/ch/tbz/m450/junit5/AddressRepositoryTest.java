package ch.tbz.m450.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;

@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressRepository addressRepository;

    private Address address1;
    private Address address2;

    @BeforeEach
    void setUp() {
        // Clear any existing data
        addressRepository.deleteAll();
        entityManager.flush();

        address1 = new Address(1, "John", "Doe", "123456", new Date());
        address2 = new Address(2, "Jane", "Smith", "654321", new Date());
    }

    @Test
    void save_shouldPersistAddress() {
        Address saved = addressRepository.save(address1);

        assertNotNull(saved);
        assertEquals(address1.getId(), saved.getId());
        assertEquals(address1.getFirstname(), saved.getFirstname());
        assertEquals(address1.getLastname(), saved.getLastname());
        assertEquals(address1.getPhonenumber(), saved.getPhonenumber());
    }

    @Test
    void findById_shouldReturnAddress_whenExists() {
        entityManager.persist(address1);
        entityManager.flush();

        Optional<Address> found = addressRepository.findById(address1.getId());

        assertTrue(found.isPresent());
        assertEquals(address1.getFirstname(), found.get().getFirstname());
        assertEquals(address1.getLastname(), found.get().getLastname());
    }

    @Test
    void findById_shouldReturnEmpty_whenNotExists() {
        Optional<Address> found = addressRepository.findById(999);

        assertFalse(found.isPresent());
    }

    @Test
    void findAll_shouldReturnAllAddresses() {
        entityManager.persist(address1);
        entityManager.persist(address2);
        entityManager.flush();

        List<Address> addresses = addressRepository.findAll();

        assertEquals(2, addresses.size());
    }

    @Test
    void findAll_shouldReturnEmptyList_whenNoAddresses() {
        List<Address> addresses = addressRepository.findAll();

        assertTrue(addresses.isEmpty());
    }

    @Test
    void delete_shouldRemoveAddress() {
        entityManager.persist(address1);
        entityManager.flush();

        addressRepository.deleteById(address1.getId());
        entityManager.flush();

        Optional<Address> found = addressRepository.findById(address1.getId());
        assertFalse(found.isPresent());
    }

    @Test
    void update_shouldModifyExistingAddress() {
        entityManager.persist(address1);
        entityManager.flush();

        address1.setFirstname("UpdatedName");
        addressRepository.save(address1);
        entityManager.flush();

        Optional<Address> found = addressRepository.findById(address1.getId());
        assertTrue(found.isPresent());
        assertEquals("UpdatedName", found.get().getFirstname());
    }
}
