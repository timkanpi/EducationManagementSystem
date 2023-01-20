package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.repository.GroupRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public void createGroup(Group group) {
        group.getStudents().forEach(student -> student.setGroup(group));
        Group groupSaved = groupRepository.save(group);

        log.info("Группа создана: {}", groupSaved);
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Группа id=" + id + " не найдена");
        });
    }
}
