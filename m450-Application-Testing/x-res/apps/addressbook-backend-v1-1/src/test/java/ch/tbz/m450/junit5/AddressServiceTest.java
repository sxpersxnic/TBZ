package ch.tbz.m450.junit5;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import ch.tbz.m450.service.AddressService;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address1;
    private Address address2;
    private Address address3;

    @BeforeEach
    void setUp() {
        address1 = new Address(1, "John", "Doe", "123456", new Date());
        address2 = new Address(2, "Jane", "Smith", "654321", new Date());
        address3 = new Address(3, "Alice", "Brown", "111222", new Date());
    }

    @Test
    void save_shouldSaveAndReturnAddress() {
        when(addressRepository.save(any(Address.class))).thenReturn(address1);

        Address result = addressService.save(address1);

        assertNotNull(result);
        assertEquals(address1.getId(), result.getId());
        assertEquals(address1.getFirstname(), result.getFirstname());
        assertEquals(address1.getLastname(), result.getLastname());
        verify(addressRepository, times(1)).save(address1);
    }

    @Test
    void getAll_shouldReturnAllAddressesSorted() {
        List<Address> addresses = Arrays.asList(address1, address2, address3);
        when(addressRepository.findAll()).thenReturn(addresses);

        List<Address> result = addressService.getAll();

        assertNotNull(result);
        assertEquals(3, result.size());
        // Verify sorted by lastname: Brown, Doe, Smith
        assertEquals("Brown", result.get(0).getLastname());
        assertEquals("Doe", result.get(1).getLastname());
        assertEquals("Smith", result.get(2).getLastname());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void getAll_shouldReturnEmptyList_whenNoAddresses() {
        when(addressRepository.findAll()).thenReturn(List.of());

        List<Address> result = addressService.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void getAddress_shouldReturnAddress_whenFound() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(address1));

        Optional<Address> result = addressService.getAddress(1);

        assertTrue(result.isPresent());
        assertEquals(address1.getId(), result.get().getId());
        assertEquals(address1.getFirstname(), result.get().getFirstname());
        verify(addressRepository, times(1)).findById(1);
    }

    @Test
    void getAddress_shouldReturnEmpty_whenNotFound() {
        when(addressRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Address> result = addressService.getAddress(999);

        assertFalse(result.isPresent());
        verify(addressRepository, times(1)).findById(999);
    }
}
