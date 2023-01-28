package ru.sber.EducationManagementSystem.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sber.EducationManagementSystem.entity.Group;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void findAll() {
        List<Group> groupList = groupRepository.findAll();

        Assertions.assertThat(groupList).hasSize(3);
    }
}