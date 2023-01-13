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

    public MarksWrapper getMarkWrapperForStudentListForLesson(Lesson lesson, List<Student> studentList) {
        List<Mark> markList = new ArrayList<>();

        for (Student student : studentList) {
            Mark mark = markRepository.findMarkByLessonIdAndStudentId(lesson.getId(), student.getId())
                    .orElse(new Mark(lesson, student));

            markList.add(mark);
        }

        MarksWrapper marksWrapper = new MarksWrapper();
        marksWrapper.setMarks(markList);

        return marksWrapper;
    }

    public List<Mark> findMarksByStudentId(Long id) {

        return markRepository.findMarksByStudentId(id);
    }
}
