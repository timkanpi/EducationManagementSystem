package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.GroupService;
import ru.sber.EducationManagementSystem.service.StudentService;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для обработки запросов по адресу "/group/.."
 */

@Slf4j
@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final StudentService studentService;
    private final GroupService groupService;

    /**
     * Получить страницу со списком групп
     *
     * @param model модель
     * @return страница "список групп"
     */
    @GetMapping
    public String getGroups(Model model) {
        List<Group> groupList = groupService.getAll();

        model.addAttribute("groups", groupList);

        return "group/group-list";
    }

    /**
     * Получить страницу "Создание группы"
     *
     * @param model модель
     * @return страница "Создание группы"
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String newGroup(Model model) {
        List<Student> allStudents = studentService.findStudentsWithoutGroup();

        model.addAttribute("students", allStudents);
        model.addAttribute("group", new Group());

        return "group/group-new";
    }

    /**
     * Запрос на "создание группы"
     *
     * @param group         атрибут модели "Группа"
     * @param bindingResult результат валидации группы
     * @param model         модель
     * @return страница "список групп", если создание прошло успешно
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createGroup(@Valid @ModelAttribute("group") Group group,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            List<Student> allStudents = studentService.findStudentsWithoutGroup();

            model.addAttribute("students", allStudents);
            return "group/group-new";
        }

        groupService.createGroup(group);

        return "redirect:/group";
    }

    /**
     * Получить детальную информацию о группе
     *
     * @param id    id группы
     * @param model модель
     * @return страница "детальная карточка группы"
     */
    @GetMapping("/{id}")
    public String getGroup(@PathVariable Long id, Model model) {
        Group group = groupService.findById(id);

        model.addAttribute("group", group);

        return "group/group-detail";
    }

}
