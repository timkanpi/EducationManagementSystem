package ru.sber.EducationManagementSystem.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();

        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    /**
     * Создать студента
     *
     * @param student создаваемый студент
     * @return ответ "ок", если студент создан успешно
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {

        studentService.createStudent(student);

        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    /**
     * Получить детальную информацию о студенте
     *
     * @param id    id студента
     * @return страница "детальная карточка студента"
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findById(id);

        return new ResponseEntity<>(student,HttpStatus.OK);
    }
}
