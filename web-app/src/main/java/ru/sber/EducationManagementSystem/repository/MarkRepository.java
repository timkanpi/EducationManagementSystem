package ru.sber.EducationManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.EducationManagementSystem.entity.Mark;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    List<Mark> findMarksByStudentId(Long id);

    List<Mark> findMarksByLessonId(Long id);

    Optional<Mark> findMarkByLessonIdAndStudentId(Long lessonId, Long studentId);
}
