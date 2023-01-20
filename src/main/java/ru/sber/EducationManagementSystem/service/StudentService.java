package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.repository.StudentRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Получить всех студентов
     *
     * @return список всех студентов
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Найти студента по id
     *
     * @param id id студента
     * @return найденный студент
     */
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Студент c id=" + id + " не найден");
        });
    }

    /**
     * Обновить студента
     *
     * @param student объект студента
     */
    public void updateStudent(Student student) {
        findById(student.getId());

        studentRepository.save(student);
        log.info("Студент обновлен: {}", student);
    }

    /**
     * Удалить студента по id
     * @param id id студента
     */
    public void deleteStudent(Long id) {
        Student student = findById(id);

        studentRepository.delete(student);

        log.info("Студент id={} удален", id);
    }

    /**
     * Создать студента
     * @param student объект нового студента
     */
    public void createStudent(Student student) {
        studentRepository.save(student);

        log.info("Студент создан: {}", student);
    }

    /**
     * Найти студента без группы
     * @return List студентов
     */
    public List<Student> findStudentsWithoutGroup() {

        return studentRepository.findStudentsByGroupIsNull();
    }


}
