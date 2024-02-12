package com.grabACycle.grabACycle.controller;


import com.grabACycle.grabACycle.services.UserService;
import com.grabACycle.grabACycle.web.dto.UserRegistrationDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

    UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto()
    {
        return new UserRegistrationDto();
    }
    @GetMapping
    public String showRegistrationForm()
    {

        logger.info("Request received to show registration form.");
        return "registration";
    }


    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto)
    {
        try{
            logger.info("Request received to register a new user account.");
            userService.save(registrationDto);
            logger.info("User account registered successfully.");
        }catch (Exception e)
        {
            e.printStackTrace();
            return "redirect:/registration?failure";
        }


        return "redirect:/registration?success";
    }

}
