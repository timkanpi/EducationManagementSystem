package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Lesson;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.entity.Teacher;
import ru.sber.EducationManagementSystem.repository.GroupRepository;
import ru.sber.EducationManagementSystem.repository.TeacherRepository;
import ru.sber.EducationManagementSystem.service.LessonService;
import ru.sber.EducationManagementSystem.service.MarkService;
import ru.sber.EducationManagementSystem.wrapper.MarksWrapper;

import java.util.List;

@Controller
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final TeacherRepository teacherRepository;
    private final LessonService lessonService;
    private final GroupRepository groupRepository;
    private final MarkService markService;

    @GetMapping
    public String getLessons(Model model) {
        List<Lesson> lessonList = lessonService.findAll();

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
        lessonService.createLesson(lesson);

        return "redirect:/lesson";
    }

    @GetMapping("/{id}")
    public String getLesson(@PathVariable Long id, Model model) {
        List<Teacher> teacherList = teacherRepository.findAll();
        Lesson lesson = lessonService.findById(id);

        List<Student> studentList = lesson.getGroup().getStudents();

        MarksWrapper marksWrapper = markService.getMarkWrapperForStudentListForLesson(lesson, studentList);

        model.addAttribute("lesson", lesson);
        model.addAttribute("teachers", teacherList);
        model.addAttribute("markWrapper", marksWrapper);

        return "lesson/lesson-detail";
    }

    @PostMapping("/{id}")
    public String updateLesson(@PathVariable("id") Long id,
                               @ModelAttribute("lesson") Lesson lesson) {
        lessonService.updateLesson(id, lesson);

        return "redirect:/lesson";
    }
}
