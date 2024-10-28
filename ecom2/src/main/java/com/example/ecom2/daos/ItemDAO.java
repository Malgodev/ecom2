package com.example.ecom2.daos;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom2.models.Item;

@Repository
public interface ItemDAO extends JpaRepository<Item, UUID> {

}
