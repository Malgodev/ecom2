package com.example.ecom2.controllers;

import com.example.ecom2.models.Customer;
import com.example.ecom2.models.Item;
import com.example.ecom2.services.CustomerService;
import com.example.ecom2.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
}
