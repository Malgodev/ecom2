package com.example.ecom2.services;

import com.example.ecom2.models.Item;
import com.example.ecom2.models.Rating;
import com.example.ecom2.daos.RatingDAO;
import com.example.ecom2.daos.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingDAO ratingRepository;

    @Autowired
    public RatingService(RatingDAO ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> getAllRatingsByItem(Item item) {
        return ratingRepository.findByItem(item);
    }
}