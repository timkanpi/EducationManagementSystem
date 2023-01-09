package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

//    @ModelAttribute("students")
//    public List<Student> createStudents() {
//        List<Student> students = studentService.getAllStudents();
//
//        students.add(new Student(1L, "Pavel"));
////        model.addAttribute("students",students);
//
//        return students;
//    }

    @GetMapping
    public String getStudents(Model model) {
        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);

        return "ListOfStudents";
    }

    @GetMapping(path = "/{id}")
    public String getStudent(@PathVariable Long id, Model model) {

        Student student = studentService.findById(id);

        model.addAttribute("student", student);

        return "studentDetail";
    }

    @PostMapping(path ="/{id}")
    public String updateStudent(@PathVariable Long id,
                                Student student) {

//        Student oldStudent = studentService.findById(id);
        studentService.updateStudent(student);

        return "redirect:/student";
    }

    @DeleteMapping(path ="/{id}")
    public String deleteStudent(@PathVariable Long id) {
        log.info("Удаляем студента id="+id);

        studentService.deleteStudent(id);

        return "redirect:/student";
    }


}
