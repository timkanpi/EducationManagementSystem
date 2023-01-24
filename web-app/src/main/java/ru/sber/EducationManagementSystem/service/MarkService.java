package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Lesson;
import ru.sber.EducationManagementSystem.entity.Mark;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.repository.MarkRepository;
import ru.sber.EducationManagementSystem.wrapper.MarksWrapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkService {

    private final MarkRepository markRepository;

    /**
     * Получить объект обертку со списком объектов "оценка" для каждого студента в группе, в переданном занятии
     *
     * @param lesson занятие
     * @return объект со списком оценок для студентов в занятии
     */
    public MarksWrapper getMarkWrapperForStudentListForLesson(Lesson lesson) {
        List<Mark> markList = new ArrayList<>();

        List<Student> studentList = lesson.getGroup().getStudents();

        for (Student student : studentList) {
            Mark mark = markRepository.findMarkByLessonIdAndStudentId(lesson.getId(), student.getId())
                    .orElse(new Mark(lesson, student));

            markList.add(mark);
        }

        MarksWrapper marksWrapper = new MarksWrapper();
        marksWrapper.setMarks(markList);

        return marksWrapper;
    }

    /**
     * Получить все оценки для студента
     *
     * @param id id студента
     * @return List оценок
     */
    public List<Mark> findMarksByStudentId(Long id) {

        return markRepository.findMarksByStudentId(id);
    }
}
