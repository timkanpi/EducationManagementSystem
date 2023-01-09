package ru.sber.EducationManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.EducationManagementSystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
