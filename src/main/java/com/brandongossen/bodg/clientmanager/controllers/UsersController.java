package com.brandongossen.bodg.clientmanager.controllers;

import com.brandongossen.bodg.clientmanager.models.User;
import com.brandongossen.bodg.clientmanager.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UsersController {
    private UsersRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public UsersController(UsersRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/register")
    public String showRegistrationForm(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "users/registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {

        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //place the hashing encoder to storing password in a variable

        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        usersDao.save(user);
        return "redirect:/login";
    }
}






