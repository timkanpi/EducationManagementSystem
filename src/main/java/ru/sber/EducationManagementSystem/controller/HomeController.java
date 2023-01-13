package ru.sber.EducationManagementSystem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.EducationManagementSystem.entity.User;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String homePage(@AuthenticationPrincipal User user) {
        return "home";
    }
}
