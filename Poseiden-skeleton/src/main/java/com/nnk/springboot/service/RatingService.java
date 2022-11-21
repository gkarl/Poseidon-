package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> home(){
        logger.info("displays the rating");
        return ratingRepository.findAll();
    }

    public void validate(Rating rating) {
        logger.info("Save a rating");
        ratingRepository.save(rating);
    }

    public Rating showUpdateForm(Integer id) {
        logger.info("Find by id from rating");
        return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
    }

    public void updateRating(Integer id, Rating rating) {
        logger.info("Update a definited bid by id");
        rating.setId(id);
        ratingRepository.save(rating);
    }

    public void deleteRating(Integer id) {
        logger.info("Delete a definited rating by id");
        Rating rating = ratingRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        ratingRepository.delete(rating);
    }


}
