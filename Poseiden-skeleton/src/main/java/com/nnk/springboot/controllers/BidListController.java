package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    Logger logger = LoggerFactory.getLogger(BidListController.class);

    // TODO: Inject Bid service
    @Autowired
    private BidListService bidListService;

    // Va sur la page bidList/list.html => affiche la liste des bid dans un tableau
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        // TODO: call service find all bids to show to the view
        logger.info("displays the bid liste page");
        List<BidList> bidList = bidListService.home();
        model.addAttribute("bidList", bidList);
        return "bidList/list";
    }

    // Va sur la page bidList/add.html => affiche un formulaire pour ajouter un bid
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("displays the form page to create a bid");
        return "bidList/add";
    }

    // Sauve le bid entrer dans le formulaire et retourne sur la page bidList/list.html
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (result.hasErrors()) {
            logger.error("ERROR for create a bid");
            return "bidList/add";
        }
        logger.info("SUCCESS create new bid");
        bidListService.validate(bid);
        model.addAttribute("bidList", bidListService.home());
        return "redirect:/bidList/list";
    }

    // Va sur la page bidList/update.html => formulaire pour editer un bid
    @GetMapping("/bidList/update/{bidListId}")
    public String showUpdateForm(@PathVariable("bidListId") Integer bidListId, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        logger.info("displays the form page to update a bid");
        BidList bidList = bidListService.showUpdateForm(bidListId);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    // Sauve l'update du bid choisi et retourne sur la page bidList/list.html
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            logger.error("ERROR for update a bid");
            return "redirect:/bidList/update";
        }
        logger.info("SUCCESS update a bid");
        bidListService.updateBid(id, bidList);
        model.addAttribute("bidList", bidListService.home());
        return "redirect:/bidList/list";
    }

    // Supprimer un bid depuis le tableau des bids
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        logger.info("SUCCESS delete a bid");
        bidListService.deleteBid(id);
        model.addAttribute("bidList", bidListService.home());
        return "redirect:/bidList/list";
    }
}
