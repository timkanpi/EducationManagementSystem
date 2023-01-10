package ru.sber.EducationManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.EducationManagementSystem.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
