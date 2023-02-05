package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Mark;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.EmailService;
import ru.sber.EducationManagementSystem.service.GroupService;
import ru.sber.EducationManagementSystem.service.MarkService;
import ru.sber.EducationManagementSystem.service.StudentService;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для обработки запросов по адресу "/student/.."
 */

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;
    private final MarkService markService;

    /**
     * Получить страницу со списком студентов
     *
     * @param model модель
     * @return страница "список студентов"
     */
    @GetMapping
    public String getStudents(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                              @RequestParam(defaultValue = "name") String sortBy,
                              @RequestParam(defaultValue = "asc") String order,
                              Model model) {
        Page<Student> students = studentService.getAllStudentsPaged(pageNumber, sortBy, order);

        model.addAttribute("students", students);

        return "student/student-list";
    }

    /**
     * Получить страницу создания студента
     *
     * @param model модель
     * @return страница "создание студента"
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String newStudent(Model model) {
        List<Group> groups = groupService.getAll();

        model.addAttribute("student", new Student());
        model.addAttribute("groups", groups);
        return "student/student-new";
    }

    /**
     * Создать студента
     * А так же создать юзера, для авториазации в системе
     *
     * @param student       создаваемый студент
     * @param bindingResult результат валидации студента
     * @param model         модель
     * @return страница "список студентов", если студент создан успешно, иначе отобразить ошибки
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("groups", groupService.getAll());

            return "student/student-new";
        }

        if (!studentService.createStudent(student)) {
            bindingResult.addError(new ObjectError("studentError", "Студент с таким email уже существет"));
            model.addAttribute("groups", groupService.getAll());

            return "student/student-new";
        }

        return "redirect:/student";
    }

    /**
     * Получить детальную информацию о студенте
     *
     * @param id    id студента
     * @param model модель
     * @return страница "детальная карточка студента"
     */
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

    /**
     * Обновить студента
     *
     * @param id            id студента
     * @param student       атрибут модели - студент
     * @param bindingResult результат валидации студента
     * @param model         модель
     * @return страница "список студентов", если обновление прошло успешно
     */
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

    /**
     * Удалить студента
     *
     * @param id id студента
     * @return страница "список студентов"
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

        return "redirect:/student";
    }
}
