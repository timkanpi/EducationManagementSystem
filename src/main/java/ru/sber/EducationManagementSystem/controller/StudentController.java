package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Mark;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.GroupService;
import ru.sber.EducationManagementSystem.service.MarkService;
import ru.sber.EducationManagementSystem.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;
    private final MarkService markService;

    @GetMapping
    public String getStudents(Model model) {
        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);

        return "student/student-list";
    }

    @GetMapping("/create")
    public String newStudent(Model model) {
        List<Group> groups = groupService.getAll();

        model.addAttribute("student", new Student());
        model.addAttribute("groups", groups);
        return "student/student-new";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student) {
        studentService.createStudent(student);

        return "redirect:/student";
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable Long id, Model model) {
        List<Group> groups = groupService.getAll();
        Student student = studentService.findById(id);
        List<Mark> marks = markService.findMarksByStudentId(id);


        model.addAttribute("student", student);
        model.addAttribute("groups", groups);
        model.addAttribute("marks", marks);

        return "student/student-detail";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id,
                                Student student) {
        studentService.updateStudent(student);

        return "redirect:/student";
    }

    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

        return "redirect:/student";
    }
}
