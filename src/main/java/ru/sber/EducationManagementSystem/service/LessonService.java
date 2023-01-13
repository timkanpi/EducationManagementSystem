package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Lesson;
import ru.sber.EducationManagementSystem.repository.LessonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;


    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Занятие c id=" + id + " не найдено");
        });
    }

    public void createLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void updateLesson(Long id, Lesson lesson) {
        Lesson lessonDb = findById(id);

        lessonDb.setDate(lesson.getDate());
        lessonDb.setHomework(lesson.getHomework());
        lessonDb.setTeacher(lesson.getTeacher());

        lessonRepository.save(lessonDb);
    }


}
