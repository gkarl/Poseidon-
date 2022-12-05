package com.nnk.springboot.controllers;


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

/**
 * Controller use CRUD CurvePointService methods to generate enpoints for CurvePoint entity
 * @see CurvePointService
 * @author gavillot
 * @version 2.0
 */
@Controller
public class CurveController {

    Logger logger = LoggerFactory.getLogger(CurveController.class);


    @Autowired
    CurvePointService curvePointService;

    /**
     * Display curvePoint in a table
     * @see CurvePointService#home()
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {

        logger.info("displays the CurvePoint liste page");
        List<CurvePoint> curvePoint = curvePointService.home();
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/list";
    }

    /**
     * Display add new curvePoint form
     * @param curvepoint item to save from form
     * @return frontend
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvepoint) {
        logger.info("displays the form page to create a curvePoint");
        return "curvePoint/add";
    }

    /**
     * Save new curvePoint on form then return to the curvePoint table
     * @see CurvePointService#validate(CurvePoint)
     * @param curvePoint item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for create a curvePoint");
            return "curvePoint/add";
        }
        logger.info("SUCCESS create new curvePoint");
        curvePointService.validate(curvePoint);
        model.addAttribute("curvePoint", curvePointService.home());
        return "redirect:/curvePoint/list";

    }

    /**
     * Display edit curvePoint form
     * @see CurvePointService#showUpdateForm(Integer)
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("displays the form page to update a curvePoint");
        CurvePoint curvePoint = curvePointService.showUpdateForm(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * Update curvePoint by id then return to the curvePoint list table
     * @see CurvePointService#updateCurvePoint(Integer, CurvePoint)
     * @param id Item id
     * @param curvePoint item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for update a curvePoint");
            return "redirect:/curvePoint/update";
        }
        logger.info("SUCCESS update a curvePoint");
        curvePointService.updateCurvePoint(id, curvePoint);
        model.addAttribute("curvePoint", curvePointService.home());
        return "redirect:/curvePoint/list";
    }

    /**
     * Delete curvePoint by id
     * @see CurvePointService#deleteCurvePoint(Integer)
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {

        logger.info("SUCCESS delete a curvePoint");
        curvePointService.deleteCurvePoint(id);
        model.addAttribute("curvePoint", curvePointService.home());
        return "redirect:/curvePoint/list";
    }
}
