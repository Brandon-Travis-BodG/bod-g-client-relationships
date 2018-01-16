package com.brandongossen.bodg.clientmanager.controllers;

import com.brandongossen.bodg.clientmanager.models.User;
import com.brandongossen.bodg.clientmanager.repositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UsersController {
    private UsersRepository usersDao;

    public UsersController(UsersRepository usersDao) {
        this.usersDao = usersDao;

    }

    @GetMapping("/register")
    public String showRegistrationForm(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "users/registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        String password = user.getPassword();
        user.setPassword(password);
        usersDao.save(user);
        return "redirect:/login";
    }
}






