package ru.sber.EducationManagementSystem.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sber.EducationManagementSystem.exception.ItemNotFoundException;

//@ControllerAdvice
public class ItemNotFoundHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public String page404() {
        return "error/404";
    }
}
