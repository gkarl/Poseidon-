package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {

    Logger logger = LoggerFactory.getLogger(RuleNameController.class);

    // TODO: Inject RuleName service
    @Autowired
    RuleNameService ruleNameService;

    // Va sur la page ruleName/list.html => affiche la liste des ruleName dans un tableau
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        logger.info("displays the bid liste page");
        List<RuleName> ruleName = ruleNameService.home();
        model.addAttribute("ruleName", ruleName);
        return "ruleName/list";
    }

    // Va sur la page ruleName/add.html => affiche un formulaire pour ajouter un ruleName
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        logger.info("displays the form page to create a ruleName");
        return "ruleName/add";
    }

    // Sauve le ruleName entrer dans le formulaire et retourne sur la page ruleName/list.html
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (result.hasErrors()) {
            logger.error("ERROR for create a ruleName");
            return "ruleName/add";
        }
        logger.info("SUCCESS create new ruleName");
        ruleNameService.validate(ruleName);
        model.addAttribute("ruleName", ruleNameService.home());
        return "redirect:/ruleName/list";
    }

    // Va sur la page ruleName/update.html => formulaire pour editer un ruleName
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        logger.info("displays the form page to update a ruleName");
        RuleName ruleName = ruleNameService.showUpdateForm(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    // Sauve l'update du ruleName choisi et retourne sur la page ruleName/list.html
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            logger.error("ERROR for update a ruleName");
            return "redirect:/ruleName/update";
        }
        logger.info("SUCCESS update a ruleName");
        ruleNameService.updateRuleName(id, ruleName);
        model.addAttribute("ruleName", ruleNameService.home());
        return "redirect:/ruleName/list";
    }

    // Supprimer un ruleName depuis le tableau des ruleName
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        logger.info("SUCCESS delete a ruleName");
        ruleNameService.deleteRuleName(id);
        model.addAttribute("ruleName", ruleNameService.home());
        return "redirect:/ruleName/list";
    }
}
