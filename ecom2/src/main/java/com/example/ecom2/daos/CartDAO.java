package com.example.ecom2.daos;

import java.util.*;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom2.models.Cart;
import com.example.ecom2.models.Customer;

@Repository
public interface CartDAO extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByCustomer(Customer customer);
}
