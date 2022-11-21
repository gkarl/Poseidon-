package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {

    Logger logger = LoggerFactory.getLogger(CurveController.class);

    // TODO: Inject Curve Point service
    @Autowired
    CurvePointService curvePointService;

    // Va sur la page curvePoint/list.html => affiche la liste des curvePoint dans un tableau
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        logger.info("displays the CurvePoint liste page");
        List<CurvePoint> bidList = curvePointService.home();
        model.addAttribute("curvePoint", bidList);
        return "curvePoint/list";
    }

    // Va sur la page curvePoint/add.html => affiche un formulaire pour ajouter un curvePoint
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        logger.info("displays the form page to create a curvePoint");
        return "curvePoint/add";
    }

    // Sauve le curvePoint entrer dans le formulaire et retourne sur la page curvePoint/list.html
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if (result.hasErrors()) {
            logger.error("ERROR for create a curvePoint");
            return "curvePoint/add";
        }
        logger.info("SUCCESS create new curvePoint");
        curvePointService.validate(curvePoint);
        model.addAttribute("curvePoint", curvePointService.home());
        return "redirect:/curvePoint/list";

    }
    // Va sur la page curvePoint/update.html => formulaire pour editer un curvePoint
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        logger.info("displays the form page to update a bid");
        CurvePoint curvePoint = curvePointService.showUpdateForm(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    // Sauve l'update du curvePoint choisi et retourne sur la page curvePoint/list.html
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            logger.error("ERROR for update a curvePoint");
            return "redirect:/curvePoint/update";
        }
        logger.info("SUCCESS update a curvePoint");
        curvePointService.updateBid(id, curvePoint);
        model.addAttribute("curvePoint", curvePointService.home());
        return "redirect:/curvePoint/list";
    }

    // Supprimer un curvePoint depuis le tableau des bids
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        logger.info("SUCCESS delete a curvePoint");
        curvePointService.deleteBid(id);
        model.addAttribute("curvePoint", curvePointService.home());
        return "redirect:/curvePoint/list";
    }
}
