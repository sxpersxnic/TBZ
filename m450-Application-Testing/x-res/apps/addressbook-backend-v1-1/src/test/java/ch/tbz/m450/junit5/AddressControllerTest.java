package ch.tbz.m450.junit5;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.tbz.m450.controller.AddressController;
import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;

@Import({AddressController.class})
@WebMvcTest(controllers = AddressController.class)
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddressService addressService;

    @Autowired
    private ObjectMapper objectMapper;

    private Address address1;
    private Address address2;

    @BeforeEach
    public void setup() {
        address1 = new Address(1, "John", "Doe", "123456", new Date());
        address2 = new Address(2, "Jane", "Smith", "654321", new Date());
    }

    @Test
    void createAddress_shouldReturnCreatedAddress() throws Exception {
        when(addressService.save(any(Address.class))).thenReturn(address1);

        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(address1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.phonenumber").value("123456"));

        verify(addressService, times(1)).save(any(Address.class));
    }

    @Test
    void getAddresses_shouldReturnAllAddresses() throws Exception {
        List<Address> addresses = Arrays.asList(address1, address2);
        when(addressService.getAll()).thenReturn(addresses);

        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstname").value("Jane"));

        verify(addressService, times(1)).getAll();
    }

    @Test
    void getAddresses_shouldReturnEmptyList_whenNoAddresses() throws Exception {
        when(addressService.getAll()).thenReturn(List.of());

        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(addressService, times(1)).getAll();
    }

    @Test
    void getAddress_shouldReturnAddress_whenFound() throws Exception {
        when(addressService.getAddress(1)).thenReturn(Optional.of(address1));

        mockMvc.perform(get("/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.phonenumber").value("123456"));

        verify(addressService, times(1)).getAddress(1);
    }

    @Test
    void getAddress_shouldReturn404_whenNotFound() throws Exception {
        when(addressService.getAddress(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/address/999"))
                .andExpect(status().isNotFound());

        verify(addressService, times(1)).getAddress(999);
    }
}
