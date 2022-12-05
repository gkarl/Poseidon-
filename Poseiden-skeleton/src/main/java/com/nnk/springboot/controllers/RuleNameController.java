package com.nnk.springboot.controllers;


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

/**
 * Controller use CRUD RuleNameService methods to generate enpoints for RuleName entity
 * @see RuleNameService
 * @author gavillot
 * @version 2.0
 */
@Controller
public class RuleNameController {

    Logger logger = LoggerFactory.getLogger(RuleNameController.class);


    @Autowired
    RuleNameService ruleNameService;

    /**
     * Display ruleName in a table
     *
     * @see RuleNameService#home()
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {

        logger.info("displays the ruleName liste page");
        List<RuleName> ruleName = ruleNameService.home();
        model.addAttribute("ruleName", ruleName);
        return "ruleName/list";
    }

    /**
     * Display add new ruleName form
     * @param ruleName item to save from form
     * @return frontend
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        logger.info("displays the form page to create a ruleName");
        return "ruleName/add";
    }

    /**
     * Save new ruleName on form then return to the ruleName list table
     * @see RuleNameService#validate(RuleName)
     * @param ruleName item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for create a ruleName");
            return "ruleName/add";
        }
        logger.info("SUCCESS create new ruleName");
        ruleNameService.validate(ruleName);
        model.addAttribute("ruleName", ruleNameService.home());
        return "redirect:/ruleName/list";
    }

    /**
     * Display edit ruleName form
     * @see RuleNameService#showUpdateForm(Integer)
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("displays the form page to update a ruleName");
        RuleName ruleName = ruleNameService.showUpdateForm(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * Update ruleName by id then return to the ruleName list table
     * @see RuleNameService#updateRuleName(Integer, RuleName)
     * @param id Item id
     * @param ruleName item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for update a ruleName");
            return "redirect:/ruleName/update";
        }
        logger.info("SUCCESS update a ruleName");
        ruleNameService.updateRuleName(id, ruleName);
        model.addAttribute("ruleName", ruleNameService.home());
        return "redirect:/ruleName/list";
    }

    /**
     * Delete ruleName by id
     * @see RuleNameService#deleteRuleName(Integer)
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

        logger.info("SUCCESS delete a ruleName");
        ruleNameService.deleteRuleName(id);
        model.addAttribute("ruleName", ruleNameService.home());
        return "redirect:/ruleName/list";
    }
}
