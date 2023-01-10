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

    private  final GroupRepository groupRepository;

    public void createGroup(Group group){
        groupRepository.save(group);

        log.info("Группа создана: {}", group);
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }
}
