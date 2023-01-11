package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Lesson;
import ru.sber.EducationManagementSystem.entity.Mark;
import ru.sber.EducationManagementSystem.entity.Teacher;
import ru.sber.EducationManagementSystem.repository.GroupRepository;
import ru.sber.EducationManagementSystem.repository.LessonRepository;
import ru.sber.EducationManagementSystem.repository.MarkRepository;
import ru.sber.EducationManagementSystem.repository.TeacherRepository;

import java.util.List;

@Controller
@RequestMapping("/mark")
@RequiredArgsConstructor
public class MarkController {

    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final MarkRepository markRepository;

    @GetMapping("/all")
    public String getAllMarks(Model model) {
        List<Mark> markList = markRepository.findAll();

        model.addAttribute("marks", markList);

        return "mark-list";
    }

    @GetMapping("/create")
    public String newMark(Model model) {
//        List<Group> groupList = groupRepository.findAll();
//        List<Teacher> teacherList = teacherRepository.findAll();
//
//        model.addAttribute("groups", groupList);
//        model.addAttribute("teachers", teacherList);
//        model.addAttribute("lesson", new Lesson());

        return "lesson-new";
    }

    @PostMapping("/create")
    public String createLesson(@ModelAttribute Mark mark){

        markRepository.save(mark);
        return "redirect:/lesson/all";
    }


}
