package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.GroupService;
import ru.sber.EducationManagementSystem.service.StudentService;

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

        return "groups";
    }

    @GetMapping("/create")
    public String newGroup(Model model){
        Group group = new Group();
        model.addAttribute("group", group);


        List<Student> allStudents = studentService.getAllStudents();

        model.addAttribute("students", allStudents);


        return "group-new";
    }

    @PostMapping("/create")
    public String createGroup(@ModelAttribute("group") Group group){
        groupService.createGroup(group);

        return "redirect:/group";
    }

}
