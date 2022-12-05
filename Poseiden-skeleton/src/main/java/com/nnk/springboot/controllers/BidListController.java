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

/**
 * Controller use CRUD BidListService methods to generate enpoints for BidList entity
 * @see BidListService
 * @author gavillot
 * @version 2.0
 */
@Controller
public class BidListController {

    Logger logger = LoggerFactory.getLogger(BidListController.class);


    @Autowired
    private BidListService bidListService;


    /**
     * Display bidlist in a table
     * @see BidListService#home() 
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {

        logger.info("displays the bid liste page");
        List<BidList> bidList = bidListService.home();
        model.addAttribute("bidList", bidList);
        return "bidList/list";
    }

    /**
     * Display add new bid form
     * @param bid item to save from form
     * @return frontend
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("displays the form page to create a bid");
        return "bidList/add";
    }

    /**
     * Save new bid on form then return to the bid list table
     * @see BidListService#validate(BidList) 
     * @param bid item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for create a bid");
            return "bidList/add";
        }
        logger.info("SUCCESS create new bid");
        bidListService.validate(bid);
        model.addAttribute("bidList", bidListService.home());
        return "redirect:/bidList/list";
    }

    /**
     * Display edit bid form
     * @see BidListService#showUpdateForm(Integer) 
     * @param bidListId Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/bidList/update/{bidListId}")
    public String showUpdateForm(@PathVariable("bidListId") Integer bidListId, Model model) {

        logger.info("displays the form page to update a bid");
        BidList bidList = bidListService.showUpdateForm(bidListId);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Update bid by id then return to the bid list table
     * @see BidListService#updateBid(Integer, BidList) 
     * @param id Item id
     * @param bidList item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for update a bid");
            return "redirect:/bidList/update";
        }
        logger.info("SUCCESS update a bid");
        bidListService.updateBid(id, bidList);
        model.addAttribute("bidList", bidListService.home());
        return "redirect:/bidList/list";
    }

    /**
     * Delete bid by id
     * @see BidListService#deleteBid(Integer) 
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {

        logger.info("SUCCESS delete a bid");
        bidListService.deleteBid(id);
        model.addAttribute("bidList", bidListService.home());
        return "redirect:/bidList/list";
    }
}
