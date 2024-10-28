package com.example.ecom2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom2.daos.CartDAO;
import com.example.ecom2.models.Cart;
import com.example.ecom2.models.Customer;

@Service
public class CartService {

    @Autowired
    private CartDAO cartRepository;

    public Cart getCartByCustomer(Customer currentCustomer) {
        return cartRepository.findByCustomer(currentCustomer)
                .orElse(null); 
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }
    
}
