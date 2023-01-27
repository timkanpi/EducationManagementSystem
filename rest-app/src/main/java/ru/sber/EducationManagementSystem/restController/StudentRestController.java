package ru.sber.EducationManagementSystem.restController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequiredArgsConstructor
@Tag(name = "Студенты", description = "Контроллер для работы с студентами")
@RequestMapping(value = "api/v1/student", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentRestController {

    private final StudentService studentService;

    @Operation(summary = "Получение списка студентов")
    @GetMapping("/list")
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
    @Operation(summary = "Создание студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created",
                    content = {@Content(mediaType = "application/json")})})
    @ResponseStatus(HttpStatus.CREATED)
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
    @Operation(summary = "Получение студента по id")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findById(id);

        return new ResponseEntity<>(student,HttpStatus.OK);
    }
}
