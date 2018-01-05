package com.brandongossen.bodg.clientmanager.controllers;

import com.brandongossen.bodg.clientmanager.repositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    private UsersRepository repository;

    @GetMapping("/register")
    public String showRegistrationForm(Model viewModel) {
        User user = new User();
        viewModel.addAttribute("user", user);
        return "templates/registration";
    }

}


