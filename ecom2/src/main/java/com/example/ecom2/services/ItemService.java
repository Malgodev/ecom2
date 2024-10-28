package com.example.ecom2.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecom2.models.Customer;
import com.example.ecom2.models.Item;
import com.example.ecom2.daos.ItemDAO;

@Service
public class ItemService {
    private final ItemDAO itemRepository;

    @Autowired
    public ItemService(ItemDAO itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> searchItemsByName(String name) {
        return itemRepository.findByNameContaining(name);
    }

    public Item findById(UUID itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }
}