package com.nnk.springboot.controllers;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.security.PasswordValidator;
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
 * Controller use CRUD UserService methods to generate enpoints for User entity
 * @see UserService
 * @author gavillot
 * @version 2.0
 */
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    /**
     * Display user in a table
     *
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @RequestMapping("/user/list")
    public String home(Model model) {

        logger.info("displays the user liste page");
        List<User> users = userService.home();
        model.addAttribute("users", users);
        return "user/list";
    }

    /**
     * Display add new user form
     * @param user item to save from form
     * @return frontend
     */
    @GetMapping("/user/add")
    public String addUser(User user) {
        logger.info("displays the form page to create a user");
        return "user/add";
    }

    /**
     * Save new user on form then return to the user list table
     *
     * @param user item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

        model.addAttribute("users", userService.home());
        if (result.hasErrors()) {
            logger.error("ERROR for update a user");
            return "redirect:/user/add";
        }
        PasswordValidator passwordValidator = new PasswordValidator();
        if (!passwordValidator.isValid(user.getPassword())) {
            logger.error("ERROR, password not valid");
            return "/user/add";
        } else {
            logger.info("SUCCESS create new user");
            userService.validate(user);
            return "redirect:/user/list";
        }

    }

    /**
     * Display edit user form
     *
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("displays the form page to update a user");
        User user = userService.showUpdateForm(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Update user by id then return to the user list table
     *
     * @param id Item id
     * @param user item to save from form
     * @param result Handled errors
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            logger.error("ERROR for update a user");
            return "user/update";
        }
        PasswordValidator passwordValidator = new PasswordValidator();
        if (!passwordValidator.isValid(user.getPassword())) {
            logger.error("ERROR, password not valid");
        } else {
            logger.info("SUCCESS update a user");
            userService.updateUser(id, user);
            model.addAttribute("users", userService.home());
            return "redirect:/user/list";
        }

        return "redirect:/user/list";
    }

    /**
     * Delete user by id
     *
     * @param id Item id
     * @param model Displaying Model Attributes
     * @return frontend
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        logger.info("SUCCESS delete a user");
        userService.deleteUser(id);
        model.addAttribute("users", userService.home());
        return "redirect:/user/list";
    }
}
