package com.mydeveloperplanet.myarchunitplanet.example2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mydeveloperplanet.myarchunitplanet.example2.model.Customer;
import com.mydeveloperplanet.myarchunitplanet.example2.repository.CustomerRepository;
import com.mydeveloperplanet.myarchunitplanet.example2.service.CustomerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(1L, "John", "Doe"), new Customer(2L, "Jane", "Smith"));
        when(customerRepository.getAllCustomers()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertThat(result).isEqualTo(customers);
        verify(customerRepository, times(1)).getAllCustomers();
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer(1L, "John", "Doe");
        when(customerRepository.getCustomerById(1L)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(1L);

        assertThat(result).isEqualTo(Optional.of(customer));
        verify(customerRepository, times(1)).getCustomerById(1L);
    }

    @Test
    void testCreateCustomer() {
        Customer customer = new Customer(null, "John", "Doe");
        when(customerRepository.createCustomer(any(Customer.class))).thenReturn(new Customer(1L, "John", "Doe"));

        Customer result = customerService.createCustomer(customer);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("John");
        assertThat(result.getLastName()).isEqualTo("Doe");
        verify(customerRepository, times(1)).createCustomer(any(Customer.class));
    }

    @Test
    void testUpdateCustomer() {
        Customer existingCustomer = new Customer(1L, "John", "Doe");
        Customer updatedCustomer = new Customer(1L, "Jane", "Smith");

        when(customerRepository.updateCustomer(anyLong(), any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(1L, updatedCustomer);

        assertThat(result).isEqualTo(updatedCustomer);
        verify(customerRepository, times(1)).updateCustomer(anyLong(), any(Customer.class));
    }


    @Test
    void testDeleteCustomer() {
        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteCustomer(1L);
    }
}
