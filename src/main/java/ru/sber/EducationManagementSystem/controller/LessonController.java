package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.*;
import ru.sber.EducationManagementSystem.repository.GroupRepository;
import ru.sber.EducationManagementSystem.repository.LessonRepository;
import ru.sber.EducationManagementSystem.repository.MarkRepository;
import ru.sber.EducationManagementSystem.repository.TeacherRepository;
import ru.sber.EducationManagementSystem.wrapper.MarksWrapper;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final TeacherRepository teacherRepository;
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final MarkRepository markRepository;

    @GetMapping
    public String getLessons(Model model) {
        List<Lesson> lessonList = lessonRepository.findAll();

        model.addAttribute("lessons", lessonList);

        return "lesson/lesson-list";
    }

    @GetMapping("/create")
    public String newLesson(Model model) {
        List<Group> groupList = groupRepository.findAll();
        List<Teacher> teacherList = teacherRepository.findAll();

        model.addAttribute("groups", groupList);
        model.addAttribute("teachers", teacherList);
        model.addAttribute("lesson", new Lesson());

        return "lesson/lesson-new";
    }

    @PostMapping("/create")
    public String createLesson(@ModelAttribute Lesson lesson) {
        lessonRepository.save(lesson);

        return "redirect:/lesson";
    }

    @GetMapping("/{id}")
    public String getLesson(@PathVariable Long id, Model model) {
        List<Teacher> teacherList = teacherRepository.findAll();
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Занятие c id=" + id + " не найдено");
        });

        List<Student> studentList = lesson.getGroup().getStudents();

        List<Mark> markList = new ArrayList<>();

        for (Student student : studentList) {
            Mark mark = markRepository.findMarkByLessonIdAndStudentId(lesson.getId(), student.getId())
                    .orElse(new Mark(lesson, student));

            markList.add(mark);
        }

        MarksWrapper marksWrapper = new MarksWrapper();
        marksWrapper.setMarks(markList);



        model.addAttribute("lesson", lesson);
        model.addAttribute("teachers", teacherList);
        model.addAttribute("markWrapper", marksWrapper);

        return "lesson/lesson-detail";
    }

    @PostMapping("/{id}")
    public String updateLesson(@PathVariable("id") Long id,
                               @ModelAttribute("lesson") Lesson lesson) {
        Lesson lessonFromDb = lessonRepository.findById(id).orElseThrow();

        lessonFromDb.setDate(lesson.getDate());
        lessonFromDb.setHomework(lesson.getHomework());
        lessonFromDb.setTeacher(lesson.getTeacher());

        lessonRepository.save(lessonFromDb);

        return "redirect:/lesson";
    }
}
