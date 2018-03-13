package com.brandongossen.bodg.clientmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/body-parts")
    public String bodyPart() {
        return "body-parts";
    }

    @GetMapping("/exercises")
    public String exercise() {
        return "exercises";
    }

    @GetMapping("/arm-exercises")
    public String armExercise() {
        return "arm-exercises";
    }

    @GetMapping("/ab-exercises")
    public String abExercise() {
        return "ab-exercises";
    }

    @GetMapping("/back-exercises")
    public String backExercise() {
        return "back-exercises";
    }

    @GetMapping("/leg-exercises")
    public String legExercise() {
        return "leg-exercises";
    }

    @GetMapping("/chest-exercises")
    public String chestExercise() {
        return "chest-exercises";
    }


}