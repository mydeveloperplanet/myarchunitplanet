package com.mydeveloperplanet.myarchunitplanet.example5.service;

import java.util.List;
import java.util.Optional;

import com.mydeveloperplanet.myarchunitplanet.example5.model.Customer;
import com.mydeveloperplanet.myarchunitplanet.example5.repository.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceInterface {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.updateCustomer(id, customerDetails);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteCustomer(id);
    }
}
