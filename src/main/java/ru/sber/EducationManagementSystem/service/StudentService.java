package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    List<Student> students = new ArrayList<>() {{
        add(new Student(1L, "Pavel"));
        add(new Student(2L, "Olga"));
    }};

    public List<Student> getAllStudents() {
        //return studentRepository.findAll();

        return students;
    }

    public Student findById(Long id) {
//        return studentRepository.findById(id).orElseThrow(() -> {
//            throw new RuntimeException("Студент не найден");
//        });

        return students.stream().filter(studentAny -> studentAny.getId().equals(id)).findFirst().orElseThrow(() -> {
            throw new RuntimeException("Студент не найден");
        });
    }

    public void updateStudent(Student student) {
        Student oldStudent = students.stream().filter(studentAny -> studentAny.getId().equals(student.getId())).findFirst().orElseThrow(() -> {
            throw new RuntimeException("Студент не найден");
        });

        oldStudent.setName(student.getName());

    }

    public void deleteStudent(Long id) {
        students.removeIf(student -> student.getId().equals(id));
    }

}
