package com.mydeveloperplanet.myarchunitplanet.example5.service;

import java.util.List;
import java.util.Optional;

import com.mydeveloperplanet.myarchunitplanet.example5.model.Customer;

public interface CustomerServiceInterface {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customerDetails);
    void deleteCustomer(Long id);
}
