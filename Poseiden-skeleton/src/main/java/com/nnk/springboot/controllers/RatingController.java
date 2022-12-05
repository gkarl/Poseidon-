package com.nnk.springboot.controllers;


import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller use CRUD RatingService methods to generate enpoints for Rating entity
 * @see RatingService
 * @author gavillot
 * @version 2.0
 */
@Controller
public class RatingController {

    Logger logger = LoggerFactory.getLogger(RatingController.class);


    @Autowired
    RatingService ratingService;

    /**
     * Display rating in a table
     * @see RatingService#home() 
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {

        logger.info("displays the rating liste page");
        List<Rating> ratingList = ratingService.home();
        model.addAttribute("rating", ratingList);
        return "rating/list";
    }
    
    /**
     * Display add new rating form
     * @param rating item to save from form
     * @return frontend
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        logger.info("displays the form page to create a rating");
        return "rating/add";
    }

    /**
     * Save new rating on form then return to the rating list table
     * @see RatingService#validate(Rating) 
     * @param rating item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for create a rating");
            return "rating/add";
        }
        logger.info("SUCCESS create new rating");
        ratingService.validate(rating);
        model.addAttribute("rating", ratingService.home());
        return "redirect:/rating/list";
    }

    /**
     * Display edit rating form
     * @see RatingService#showUpdateForm(Integer) 
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("displays the form page to update a rating");
        Rating rating = ratingService.showUpdateForm(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Update rating by id then return to the rating list table
     * @see RatingService#updateRating(Integer, Rating) 
     * @param id Item id
     * @param rating item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for update a rating");
            return "redirect:/rating/update";
        }
        logger.info("SUCCESS update a rating");
        ratingService.updateRating(id, rating);
        model.addAttribute("rating", ratingService.home());
        return "redirect:/rating/list";
    }

    /**
     * Delete rating by id
     * @see RatingService#deleteRating(Integer) 
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        logger.info("SUCCESS delete a rating");
        ratingService.deleteRating(id);
        model.addAttribute("rating", ratingService.home());
        return "redirect:/rating/list";
    }
}
