package ru.sber.EducationManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Lesson;
import ru.sber.EducationManagementSystem.entity.Teacher;
import ru.sber.EducationManagementSystem.repository.GroupRepository;
import ru.sber.EducationManagementSystem.repository.TeacherRepository;
import ru.sber.EducationManagementSystem.service.LessonService;
import ru.sber.EducationManagementSystem.service.MarkService;
import ru.sber.EducationManagementSystem.wrapper.MarksWrapper;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для обработки запросов по адресу "/lesson/.."
 */

@Controller
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {

    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final LessonService lessonService;
    private final MarkService markService;

    /**
     * Получить страницу со списком занятий
     *
     * @param model модель
     * @return страница "список занятий"
     */
    @GetMapping
    public String getLessons(Model model) {
        List<Lesson> lessonList = lessonService.findAllByOrderByDateDesc();

        model.addAttribute("lessons", lessonList);

        return "lesson/lesson-list";
    }

    /**
     * Получить страницу создания занятия
     *
     * @param model модель
     * @return страница "создание занятия"
     */
    @GetMapping("/create")
    public String newLesson(Model model) {
        List<Group> groupList = groupRepository.findAll();
        List<Teacher> teacherList = teacherRepository.findAll();

        model.addAttribute("groups", groupList);
        model.addAttribute("teachers", teacherList);
        model.addAttribute("lesson", new Lesson());

        return "lesson/lesson-new";
    }

    /**
     * Создать занятие
     *
     * @param lesson        создаваемое занятие
     * @param bindingResult результат валидации занятия
     * @param model         модель
     * @return страница "список групп", если занятие создано успешно, иначе отобразить ошибки
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createLesson(@Valid @ModelAttribute Lesson lesson,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            List<Group> groupList = groupRepository.findAll();
            List<Teacher> teacherList = teacherRepository.findAll();

            model.addAttribute("groups", groupList);
            model.addAttribute("teachers", teacherList);

            return "lesson/lesson-new";
        }

        lessonService.createLesson(lesson);

        return "redirect:/lesson";
    }

    /**
     * Получить детальную информацию занятия
     *
     * @param id    id занятия
     * @param model модель
     * @return страница "детальная карточка занятия"
     */
    @GetMapping("/{id}")
    public String getLesson(@PathVariable Long id, Model model) {
        List<Teacher> teacherList = teacherRepository.findAll();
        Lesson lesson = lessonService.findById(id);

        MarksWrapper marksWrapper = markService.getMarkWrapperForStudentListForLesson(lesson);

        model.addAttribute("lesson", lesson);
        model.addAttribute("teachers", teacherList);
        model.addAttribute("markWrapper", marksWrapper);

        return "lesson/lesson-detail";
    }

    /**
     * Обновить занятие
     *
     * @param id            id занятия
     * @param lesson        атрибут модели занятие
     * @param bindingResult результат валидации занятия
     * @param model         модель
     * @return страница "список занятий", если обновление прошло успешно
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @PostMapping("/{id}")
    public String updateLesson(@PathVariable("id") Long id,
                               @Valid @ModelAttribute("lesson") Lesson lesson,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            List<Teacher> teacherList = teacherRepository.findAll();

            MarksWrapper marksWrapper = markService.getMarkWrapperForStudentListForLesson(lesson);
            model.addAttribute("teachers", teacherList);
            model.addAttribute("markWrapper", marksWrapper);

            return "lesson/lesson-detail";
        }

        lessonService.updateLesson(id, lesson);

        return "redirect:/lesson";
    }
}
