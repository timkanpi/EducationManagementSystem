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

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Студент c id=" + id + " не найден");
        });
    }

    public void updateStudent(Student student) {
        findById(student.getId());

        studentRepository.save(student);
        log.info("Студент обновлен: {}", student);
    }

    public void deleteStudent(Long id) {
        findById(id);

        studentRepository.deleteById(id);

        log.info("Студент id={} удален", id);
    }

    public void createStudent(Student student) {
        studentRepository.save(student);

        log.info("Студент создан: {}", student);
    }
}
