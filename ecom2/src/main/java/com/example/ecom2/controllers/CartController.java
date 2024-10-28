package com.example.ecom2.controllers;

import java.util.UUID;
import java.util.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import com.example.ecom2.models.Cart;
import com.example.ecom2.models.Customer;
import com.example.ecom2.models.Item;
import com.example.ecom2.services.CartService;
import com.example.ecom2.services.CustomerService;
import com.example.ecom2.services.ItemService;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/add-to-cart/{itemId}")
    public String addToCart(@PathVariable UUID itemId, Model model) {
        Customer currentCustomer = getCurrentCustomer();
        Item item = itemService.findById(itemId);

        if (item != null) {
            Cart cart = cartService.getCartByCustomer(currentCustomer);

            if (cart == null) {
                cart = new Cart();
                cart.setCustomer(currentCustomer);
                cart.setItems(new Vector<Item>());
            }else if (cart.getItems() == null) {
                cart.setItems(new Vector<Item>());
            }

            if (!cart.getItems().contains(item)) {
                cart.getItems().add(item);
                cartService.save(cart);              
            }
        }
        return "redirect:/items";
    }

    private Customer getCurrentCustomer() {
        Customer customer = customerService.getFirstCustomer().orElse(null);
        return customer;
    }
}
