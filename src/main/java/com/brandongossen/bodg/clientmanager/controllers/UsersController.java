package com.brandongossen.bodg.clientmanager.controllers;

import com.brandongossen.bodg.clientmanager.models.Gender;
import com.brandongossen.bodg.clientmanager.models.GenderConverter;
import com.brandongossen.bodg.clientmanager.models.User;
import com.brandongossen.bodg.clientmanager.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


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
    public String registerUser(@Valid User user, Errors validation, Model viewModel) {

        User existingUser = usersDao.findByUsername(user.getUsername());

        User existingEmail = usersDao.findByEmail(user.getEmail());

        User existingPhoneNumber = usersDao.findByPhoneNumber(user.getPhoneNumber());

        if (existingUser != null) {
            validation.rejectValue(
                    "username",
                    "user.username",
                    "Username already taken!"
            );
        }
        if (existingEmail != null) {
            validation.rejectValue(
                    "email",
                    "user.email",
                    "Email already taken!"
            );
        }

        if (existingPhoneNumber != null) {
            validation.rejectValue(
                    "user.phoneNumber",
                    "user.phoneNumber",
                    "Phone number is already taken!"
            );
        }

        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/registration";
        }


        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //place the hashing encoder to storing password in a variable

        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        usersDao.save(user);
        return "redirect:/login";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Gender.class, new GenderConverter());
        //Gender Converter is changing from a string to a gender object
    }
}







