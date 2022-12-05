package com.nnk.springboot.service;


import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Rating Service use CRUD RatingRepository methods to communicate whith rating table
 * @see RatingRepository
 */
@Service
public class RatingService {

    Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    RatingRepository ratingRepository;

    /**
     * Query all the rows of the table rating table
     * @return all Rating items
     */
    public List<Rating> home(){
        logger.info("displays the rating");
        return ratingRepository.findAll();
    }

    /**
     * Create new rating
     * @param rating item to save
     */
    public void validate(Rating rating) {
        logger.info("Save a rating");
        ratingRepository.save(rating);
    }

    /**
     * Show edit form for one rating using it's id
     * @param id Item id
     * @return Item whith this id
     */
    public Rating showUpdateForm(Integer id) {
        logger.info("Find by id from rating");
        return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID:"+ id));
    }

    /**
     * Update rating by id
     * @param id Item id
     * @param rating item to save
     */
    public void updateRating(Integer id, Rating rating) {
        logger.info("Update a definited rating by id");
        rating.setId(id);
        ratingRepository.save(rating);
    }

    /**
     * Delete rating by id
     * @param id Item id
     */
    public void deleteRating(Integer id) {
        logger.info("Delete a definited rating by id");
        Rating rating = ratingRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid ID:" + id));
        ratingRepository.delete(rating);
    }


}
