package com.example.ecom2.daos;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecom2.models.Item;
import com.example.ecom2.models.Rating;
import java.util.List;


@Repository
public interface RatingDAO extends JpaRepository<Rating, UUID> {
    List<Rating> findByItem(Item item);
}
