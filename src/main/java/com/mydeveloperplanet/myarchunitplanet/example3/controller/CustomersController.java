package com.mydeveloperplanet.myarchunitplanet.example3.controller;

import java.util.List;
import java.util.Optional;

import com.mydeveloperplanet.myarchunitplanet.example3.model.Customer;
import com.mydeveloperplanet.myarchunitplanet.example3.service.CustomerService;
import com.mydeveloperplanet.myarchunitplanet.openapi.api.CustomersApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomersController implements CustomersApi {

    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<List<com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer>> customersGet() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(convertToOpenAPIModel(customers), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> customersPost(@RequestBody com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer openAPICustomer) {
        Customer customer = convertToDomainModel(openAPICustomer);
        customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer> customersIdGet(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerService.getCustomerById(id);
        if (customerOptional.isPresent()) {
            return new ResponseEntity<>(convertToOpenAPIModel(customerOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> customersIdPut(@PathVariable Long id, @RequestBody com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer openAPICustomer) {
        Customer customerDetails = convertToDomainModel(openAPICustomer);
        customerService.updateCustomer(id, customerDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> customersIdDelete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private List<com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer> convertToOpenAPIModel(List<Customer> domainCustomers) {
        return domainCustomers.stream()
                .map(this::convertToOpenAPIModel)
                .toList();
    }

    private com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer convertToOpenAPIModel(Customer customer) {
        com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer openAPICustomer =
                new com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer();
        openAPICustomer.setId(customer.getId());
        openAPICustomer.setFirstName(customer.getFirstName());
        openAPICustomer.setLastName(customer.getLastName());
        return openAPICustomer;
    }

    private Customer convertToDomainModel(com.mydeveloperplanet.myarchunitplanet.openapi.model.Customer openAPICustomer) {
        return new Customer(
                openAPICustomer.getId(),
                openAPICustomer.getFirstName(),
                openAPICustomer.getLastName()
        );
    }
}
