package com.mydeveloperplanet.myarchunitplanet.example1.util;

import com.mydeveloperplanet.myarchunitplanet.example1.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;

public class Util {

    @Autowired
    CustomerService customerService;

    public void doSomething() {
        // use the CustomerService
        customerService.deleteCustomer(1L);
    }

}
