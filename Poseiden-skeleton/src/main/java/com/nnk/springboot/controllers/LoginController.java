package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Methods for login
 */
@Controller
@RequestMapping("/app")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * user login
     * @return frontend
     */
    @GetMapping("login")
    public ModelAndView login() {
        logger.info("login ok");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

   /* @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }*/

    /**
     * login not valide
     * @return
     */
    @GetMapping("error")
    public ModelAndView error() {
        logger.info("ERROR, login not valide");
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }

    /**
     * User logout
     * @return frontend
     */
    @GetMapping("/logout")
    public ModelAndView logout() {
        logger.info("SUCCESS, user logout");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logout");
        return mav;
    }

}
