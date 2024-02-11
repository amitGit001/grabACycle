package com.grabACycle.grabACycle.controller;


import com.grabACycle.grabACycle.services.UserService;
import com.grabACycle.grabACycle.web.dto.UserRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

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
        return "registration";
    }


    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto)
    {
        userService.save(registrationDto);

        return "redirect:/registration?success";
    }

}
