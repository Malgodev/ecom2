package com.example.ecom2.services;

import com.example.ecom2.models.Customer;
import com.example.ecom2.daos.CustomerDAO;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerRepository;

    @Autowired
    public CustomerService(CustomerDAO customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getFirstCustomer() {
        return customerRepository.findAll().stream().findFirst();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}