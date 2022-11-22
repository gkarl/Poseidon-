package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
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
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    /*@Autowired
    private UserRepository userRepository;*/

    @Autowired
    private UserService userService;

    // Va sur la page user/list.html => affiche la liste des user dans un tableau
    @RequestMapping("/user/list")
    public String home(Model model) {
        //model.addAttribute("users", userRepository.findAll());
        logger.info("displays the user liste page");
        List<User> users = userService.home();
        model.addAttribute("users", users);
        return "user/list";
    }

    // Va sur la page user/add.html => affiche un formulaire pour ajouter un user
    @GetMapping("/user/add")
    public String addUser(User bid) {
        logger.info("displays the form page to create a user");
        return "user/add";
    }

    // Sauve le user entrer dans le formulaire et retourne sur la page user/list.html
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        //if (!result.hasErrors()) {
            //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            //user.setPassword(encoder.encode(user.getPassword()));
            //userRepository.save(user);
            //model.addAttribute("users", userRepository.findAll());
            //return "redirect:/user/list";
       // }

        if (result.hasErrors()) {
            logger.error("ERROR for update a user");
            return "redirect:/user/update";
        }
        logger.info("SUCCESS create new user");
        userService.validate(user);
        model.addAttribute("users", userService.home());
        return "redirect:/user/list";
        //return "user/add";
    }

    // Va sur la page user/update.html => formulaire pour editer un user
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        //user.setPassword("");
        //model.addAttribute("user", user);
        logger.info("displays the form page to update a user");
        User user = userService.showUpdateForm(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    // Sauve l'update du user choisi et retourne sur la page user/list.html
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("ERROR for update a user");
            return "user/update";
        }
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //user.setPassword(encoder.encode(user.getPassword()));
        //user.setId(id);
        //userRepository.save(user);
        //model.addAttribute("users", userRepository.findAll());
        logger.info("SUCCESS update a user");
        userService.updateUser(id, user);
        model.addAttribute("users", userService.home());
        return "redirect:/user/list";
    }

    // Supprimer un user depuis le tableau des users
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        //User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        //userRepository.delete(user);
        //model.addAttribute("users", userRepository.findAll());
        logger.info("SUCCESS delete a user");
        userService.deleteUser(id);
        model.addAttribute("users", userService.home());
        return "redirect:/user/list";
    }
}
