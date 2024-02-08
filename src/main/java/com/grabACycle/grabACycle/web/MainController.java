package com.grabACycle.grabACycle.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is already authenticated
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            // User is already logged in, redirect to the home page
            return "redirect:/";
        }

        // User is not logged in, show the login page

        return "login";
    }

    @GetMapping("/")
    public String home()
    {
        return "home";
    }


}
