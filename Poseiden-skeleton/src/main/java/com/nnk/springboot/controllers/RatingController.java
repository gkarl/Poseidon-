package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
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

@Controller
public class RatingController {

    Logger logger = LoggerFactory.getLogger(RatingController.class);

    // TODO: Inject Rating service
    @Autowired
    RatingService ratingService;

    // Va sur la page rating/list.html => affiche la liste des rating dans un tableau
    @RequestMapping("/rating/list")
    public String home(Model model) {
        // TODO: find all Rating, add to model
        logger.info("displays the rating liste page");
        List<Rating> ratingList = ratingService.home();
        model.addAttribute("rating", ratingList);
        return "rating/list";
    }
    // Va sur la page rating/add.html => affiche un formulaire pour ajouter un rating
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        logger.info("displays the form page to create a rating");
        return "rating/add";
    }
    // Sauve le rating entrer dans le formulaire et retourne sur la page rating/list.html
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        if (result.hasErrors()) {
            logger.error("ERROR for create a rating");
            return "rating/add";
        }
        logger.info("SUCCESS create new rating");
        ratingService.validate(rating);
        model.addAttribute("rating", ratingService.home());
        return "redirect:/rating/list";
    }
    // Va sur la page rating/update.html => formulaire pour editer un rating
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        logger.info("displays the form page to update a rating");
        Rating rating = ratingService.showUpdateForm(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    // Sauve l'update du rating choisi et retourne sur la page rating/list.html
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        if (result.hasErrors()) {
            logger.error("ERROR for update a rating");
            return "redirect:/rating/update";
        }
        logger.info("SUCCESS update a rating");
        ratingService.updateRating(id, rating);
        model.addAttribute("rating", ratingService.home());
        return "redirect:/rating/list";
    }

    // Supprimer un bid depuis le tableau des rating
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        logger.info("SUCCESS delete a rating");
        ratingService.deleteRating(id);
        model.addAttribute("rating", ratingService.home());
        return "redirect:/rating/list";
    }
}
