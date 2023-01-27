package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.exception.ItemNotFoundRestException;
import ru.sber.EducationManagementSystem.repository.GroupRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    /**
     * найти группу по id
     *
     * @param id id группы
     * @return найденная группа
     */
    public Group findById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundRestException("Группа id=" + id + " не найдена");
        });
    }

    /**
     * Создать группу
     * В группе передается список студентов. Для каждого студента в группе обновляется свзяь с группой
     *
     * @param group Объект группа
     */
    public void createGroup(Group group) {
        group.getStudents().forEach(student -> student.setGroup(group));
        Group groupSaved = groupRepository.save(group);

        log.info("Группа создана: {}", groupSaved);
    }

    /**
     * Получить список всех групп
     *
     * @return List групп
     */
    public List<Group> getAll() {
        return groupRepository.findAll();
    }
}
