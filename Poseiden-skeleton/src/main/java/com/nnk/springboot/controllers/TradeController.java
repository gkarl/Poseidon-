package com.nnk.springboot.controllers;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
 * Controller use CRUD TradeService methods to generate enpoints for Trade entity
 * @see TradeService
 * @author gavillot
 * @version 2.0
 */
@Controller
public class TradeController {

    Logger logger = LoggerFactory.getLogger(TradeController.class);


    @Autowired
    TradeService tradeService;

    /**
     * Display trade in a table
     * @see TradeService#home()
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @RequestMapping("/trade/list")
    public String home(Model model) {

        logger.info("displays the trade liste page");
        List<Trade> trade = tradeService.home();
        model.addAttribute("trade", trade);
        return "trade/list";
    }

    /**
     * Display add new trade form
     * @param trade item to save from form
     * @return frontend
     */
    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        logger.info("displays the form page to create a trade");
        return "trade/add";
    }

    /**
     * Save new trade on form then return to the trade list table
     * @see TradeService#validate(Trade)
     * @param trade item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for create a trade");
            return "trade/add";
        }
        logger.info("SUCCESS create new trade");
        tradeService.validate(trade);
        model.addAttribute("trade", tradeService.home());
        return "redirect:/trade/list";
    }

    /**
     * Display edit trade form
     * @see TradeService#showUpdateForm(Integer)
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("displays the form page to update a trade");
        Trade trade = tradeService.showUpdateForm(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    /**
     * Update trade by id then return to the trade list table
     * @see TradeService#updateTrade(Integer, Trade)
     * @param id Item id
     * @param trade item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for update a trade");
            return "redirect:/trade/update";
        }
        logger.info("SUCCESS update a trade");
        tradeService.updateTrade(id, trade);
        model.addAttribute("trade", tradeService.home());
        return "redirect:/trade/list";
    }

    /**
     * Delete trade by id
     * @see TradeService#deleteTrade(Integer)
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        logger.info("SUCCESS delete a trade");
        tradeService.deleteTrade(id);
        model.addAttribute("trade", tradeService.home());
        return "redirect:/trade/list";
    }
}
