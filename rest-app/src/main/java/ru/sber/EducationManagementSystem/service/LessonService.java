package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Lesson;
import ru.sber.EducationManagementSystem.exception.ItemNotFoundException;
import ru.sber.EducationManagementSystem.repository.LessonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    /**
     * Получить список всех занятий
     *
     * @return List занятий
     */
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    /**
     * Найти занятие по id
     *
     * @param id id занятия
     * @return объект занятия
     */
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Занятие c id=" + id + " не найдено");
        });
    }

    /**
     * Создать занятие
     *
     * @param lesson объект занятие
     */
    public void createLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    /**
     * Обновить занятие
     *
     * @param id     id занятия
     * @param lesson объект занятия
     */
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lessonDb = findById(id);

        lessonDb.setDate(lesson.getDate());
        lessonDb.setHomework(lesson.getHomework());
        lessonDb.setTeacher(lesson.getTeacher());

        lessonRepository.save(lessonDb);
    }
}
