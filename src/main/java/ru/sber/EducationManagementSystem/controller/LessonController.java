package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.*;
import ru.sber.EducationManagementSystem.repository.GroupRepository;
import ru.sber.EducationManagementSystem.repository.LessonRepository;
import ru.sber.EducationManagementSystem.repository.TeacherRepository;

import java.util.List;

@Controller
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @GetMapping("/all")
    public String getLessonsList(Model model) {
        List<Lesson> lessonList = lessonRepository.findAll();

        model.addAttribute("lessons", lessonList);

        return "lesson-list";
    }

    @GetMapping("/create")
    public String newLesson(Model model) {
        List<Group> groupList = groupRepository.findAll();
        List<Teacher> teacherList = teacherRepository.findAll();

        model.addAttribute("groups", groupList);
        model.addAttribute("teachers", teacherList);
        model.addAttribute("lesson", new Lesson());

        return "lesson-new";
    }

    @PostMapping("/create")
    public String createLesson(@ModelAttribute Lesson lesson){

        lessonRepository.save(lesson);

        return "redirect:/lesson/all";
    }

    @GetMapping( "/{id}")
    public String getLesson(@PathVariable Long id, Model model) {

        Lesson lesson= lessonRepository.findById(id).orElseThrow();

        model.addAttribute("lesson", lesson);
        model.addAttribute("mark", new Mark());

        return "lesson-detail";
    }

}
