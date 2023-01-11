package ru.sber.EducationManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.EducationManagementSystem.entity.Mark;
import ru.sber.EducationManagementSystem.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
