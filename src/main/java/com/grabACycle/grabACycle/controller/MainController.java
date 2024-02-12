package com.grabACycle.grabACycle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/login")
    public String login()
    {
        logger.info("Request received to access login page.");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is already authenticated
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            // User is already logged in, redirect to the home page
            logger.info("User is already authenticated. Redirecting to the home page.");
            return "redirect:/";
        }

        // User is not logged in, show the login page
        logger.info("User is not logged in. Showing the login page.");

        return "login";
    }




}
