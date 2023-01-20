package ru.sber.EducationManagementSystem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.EducationManagementSystem.entity.User;

/**
 * Контроллер для обработки запросов по адресу "/"
 */


@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Домашняя страница
     *
     * @param user информация о авторизированном пользователе
     * @return страница "домашняя страница"
     */
    @GetMapping
    public String homePage(@AuthenticationPrincipal User user) {
        return "home";
    }
}
