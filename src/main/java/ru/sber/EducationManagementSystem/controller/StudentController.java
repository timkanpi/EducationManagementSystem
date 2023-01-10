package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.GroupService;
import ru.sber.EducationManagementSystem.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    @GetMapping
    public String getStudents(Model model) {
        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);

        return "students";
    }

    @GetMapping("/create")
    public String newStudent(Model model){
        log.debug("Переход на страницу создания студента");

        List<Group> groups = groupService.getAll();

        model.addAttribute("student", new Student());
        model.addAttribute("groups", groups);
        return "student-new";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student){

        studentService.createStudent(student);

        return "redirect:/student";
    }

    @GetMapping( "/{id}")
    public String getStudent(@PathVariable Long id, Model model) {

        Student student = studentService.findById(id);

        model.addAttribute("student", student);

        return "student-detail";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id,
                                Student student) {

        studentService.updateStudent(student);

        return "redirect:/student";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/student";
    }
}
