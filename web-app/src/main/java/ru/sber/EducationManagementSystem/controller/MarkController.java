package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.EducationManagementSystem.entity.Mark;
import ru.sber.EducationManagementSystem.repository.MarkRepository;
import ru.sber.EducationManagementSystem.wrapper.MarksWrapper;

import java.util.List;

/**
 * Контроллер для обработки запросов по адресу "/mark/.."
 */

@Controller
@RequestMapping("/mark")
@RequiredArgsConstructor
public class MarkController {

    private final MarkRepository markRepository;

    /**
     * Создать список оценок
     *
     * @param marks атрибут модели - оценки. обертка для коллекции объектов "оценка"
     * @return страница "детальная карточка занятия"
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @PostMapping(value = "/addAll", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String addAllMarks(@ModelAttribute("marks") MarksWrapper marks) {
        List<Mark> markList = marks.getMarks();

        markRepository.saveAll(markList);

        return "redirect:/lesson";
    }


}
