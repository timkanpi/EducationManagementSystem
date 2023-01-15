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

@Slf4j
@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final StudentService studentService;

    @GetMapping
    public String getGroups(Model model) {
        List<Group> groupList = groupService.getAll();

        model.addAttribute("groups", groupList);

        return "group/group-list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String newGroup(Model model) {
        List<Student> allStudents = studentService.findStudentsWithoutGroup();

        model.addAttribute("students", allStudents);
        model.addAttribute("group", new Group());

        return "group/group-new";
    }

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

    @GetMapping("/{id}")
    public String getGroup(@PathVariable Long id, Model model) {
        Group group = groupService.findById(id);

        model.addAttribute("group", group);

        return "group/group-detail";
    }

}
