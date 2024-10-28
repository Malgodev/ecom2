package com.example.ecom2.controllers;

import java.util.UUID;
import java.util.Vector;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.ecom2.models.Cart;
import com.example.ecom2.models.Customer;
import com.example.ecom2.models.Item;
import com.example.ecom2.models.Order;
import com.example.ecom2.models.Payment;
import com.example.ecom2.models.Shipment;
import com.example.ecom2.services.CartService;
import com.example.ecom2.services.CustomerService;
import com.example.ecom2.services.ItemService;
import com.example.ecom2.services.OrderService;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

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
                cart.setTotal(cart.getTotal() + item.getPrice());
                cartService.save(cart);              
            }
        }
        return "redirect:/items";
    }

    @GetMapping("/remove-from-cart/{itemId}")
    public String RemoveFromCart(@PathVariable UUID itemId, Model model) {
        Customer currentCustomer = getCurrentCustomer();
        Item item = itemService.findById(itemId);

        if (item != null) {
            Cart cart = cartService.getCartByCustomer(currentCustomer);

            if (cart.getItems().contains(item)) {
                cart.getItems().remove(item);
                cart.setTotal(cart.getTotal() - item.getPrice());
                cartService.save(cart);              
            }
        }
        return "redirect:/cart";
    }


    @GetMapping("/cart")
    public String viewCart(Model model) {
        Customer currentCustomer = getCurrentCustomer();
        Cart cart = cartService.getCartByCustomer(currentCustomer);
    
        if (cart != null) {
            model.addAttribute("cart", cart);
            model.addAttribute("items", cart.getItems());
        } else {
            model.addAttribute("items", new Vector<Item>());
        }
    
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam String paymentMethod, @RequestParam String shippingType, Model model) {
        Customer currentCustomer = getCurrentCustomer();
        Cart cart = cartService.getCartByCustomer(currentCustomer);

        String tmp = """
                Customer: %s order: \n
                %s\n
                using %s and %s.
                """;

        String items = "";

        for (Item item : cart.getItems()){
            items += "\t" + item.getName() + ": " + item.getPrice() + "\n";
        }

        System.out.format(tmp, currentCustomer.getName(), items, paymentMethod, shippingType);

        return "redirect:/items";
    }

    private Customer getCurrentCustomer() {
        Customer customer = customerService.getFirstCustomer().orElse(null);
        return customer;
    }
}
