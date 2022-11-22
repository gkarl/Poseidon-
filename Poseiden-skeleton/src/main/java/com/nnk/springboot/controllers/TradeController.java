package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
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

@Controller
public class TradeController {

    Logger logger = LoggerFactory.getLogger(TradeController.class);

    // TODO: Inject Trade service
    @Autowired
    TradeService tradeService;

    // Va sur la page trade/list.html => affiche la liste des trade dans un tableau
    @RequestMapping("/trade/list")
    public String home(Model model) {
        // TODO: find all Trade, add to model
        logger.info("displays the trade liste page");
        List<Trade> trade = tradeService.home();
        model.addAttribute("trade", trade);
        return "trade/list";
    }

    // Va sur la page trade/add.html => affiche un formulaire pour ajouter un trade
    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        logger.info("displays the form page to create a trade");
        return "trade/add";
    }

    // Sauve le trade entrer dans le formulaire et retourne sur la page trade/list.html
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
        if (result.hasErrors()) {
            logger.error("ERROR for create a trade");
            return "trade/add";
        }
        logger.info("SUCCESS create new trade");
        tradeService.validate(trade);
        model.addAttribute("trade", tradeService.home());
        return "redirect:/trade/list";
    }

    // Va sur la page trade/update.html => formulaire pour editer un trade
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        logger.info("displays the form page to update a bid");
        Trade trade = tradeService.showUpdateForm(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    // Sauve l'update du trade choisi et retourne sur la page trade/list.html
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            logger.error("ERROR for update a trade");
            return "redirect:/trade/update";
        }
        logger.info("SUCCESS update a trade");
        tradeService.updateTrade(id, trade);
        model.addAttribute("trade", tradeService.home());
        return "redirect:/trade/list";
    }

    // Supprimer un trade depuis le tableau des trade
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        logger.info("SUCCESS delete a trade");
        tradeService.deleteTrade(id);
        model.addAttribute("trade", tradeService.home());
        return "redirect:/trade/list";
    }
}
