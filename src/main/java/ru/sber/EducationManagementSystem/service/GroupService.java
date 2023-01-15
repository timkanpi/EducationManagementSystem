package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.repository.GroupRepository;
import ru.sber.EducationManagementSystem.repository.StudentRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public void createGroup(Group group) {
        Group groupSaved = groupRepository.save(group);

        group.getStudents().forEach(student -> student.setGroup(groupSaved));

        studentRepository.saveAll(group.getStudents());

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
