package com.example.ecom2.controllers;

import java.util.*;

import com.example.ecom2.models.Cart;
import com.example.ecom2.models.Comment;
import com.example.ecom2.models.Customer;
import com.example.ecom2.models.Item;
import com.example.ecom2.models.Rating;
import com.example.ecom2.services.CommentService;
import com.example.ecom2.services.CustomerService;
import com.example.ecom2.services.ItemService;
import com.example.ecom2.services.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/add_item")
    public String addItem(Model model) {
        return "add_item";
    }

    @PostMapping("/add_item")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemService.saveItem(item);
        redirectAttributes.addFlashAttribute("success", "Item added successfully.");
        return "redirect:/items";
    }

    @GetMapping("/comment-rate/{itemId}")
    public String AddReview(@PathVariable UUID itemId, Model model) {
        Customer currentCustomer = getCurrentCustomer();
        Item item = itemService.findById(itemId);
        List<Comment> comments;
        List<Rating> ratings;

        comments = commentService.getAllCommentsByItem(item);
        ratings = ratingService.getAllRatingsByItem(item);

        return "redirect:/items";
    }

    // @GetMapping("/items")
    // public String listItem(Model model) {
    //     List<Item> items = itemService.getAllItems();
    //     model.addAttribute("items", items);
    //     return "item_list";
    // }

    @GetMapping("/items")
    public String getItems(@RequestParam(required = false, defaultValue = "") String search, Model model) {
        List<Item> items;
        if (search != null && !search.isEmpty()) {
            items = itemService.searchItemsByName(search);
        } else {
            items = itemService.getAllItems();
        }
        model.addAttribute("items", items);
        model.addAttribute("searchQuery", search);
        return "item_list";
    }    

    private Customer getCurrentCustomer() {
        Customer customer = customerService.getFirstCustomer().orElse(null);
        return customer;
    }
}
