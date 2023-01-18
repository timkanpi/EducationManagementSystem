package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Mark;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.GroupService;
import ru.sber.EducationManagementSystem.service.MarkService;
import ru.sber.EducationManagementSystem.service.StudentService;

import javax.validation.Valid;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String newStudent(Model model) {
        List<Group> groups = groupService.getAll();

        model.addAttribute("student", new Student());
        model.addAttribute("groups", groups);
        return "student/student-new";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Group> groups = groupService.getAll();

            model.addAttribute("groups", groups);
            return "student/student-new";
        }

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
                                @Valid Student student,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            List<Group> groups = groupService.getAll();
            List<Mark> marks = markService.findMarksByStudentId(id);

            model.addAttribute("groups", groups);
            model.addAttribute("marks", marks);
            return "student/student-detail";
        }

        studentService.updateStudent(student);

        return "redirect:/student";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

        return "redirect:/student";
    }
}
