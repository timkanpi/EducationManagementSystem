package ru.sber.EducationManagementSystem.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.exception.ItemNotFoundException;
import ru.sber.EducationManagementSystem.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @InjectMocks
    private GroupService groupService;

    @Mock
    private GroupRepository groupRepository;

    @Test
    @DisplayName("Проверка создания группы")
    void createGroup() {
        Group group = new Group(null, "группа1", emptyList(), emptyList());

        when(groupRepository.save(any())).thenReturn(group);

        groupService.createGroup(group);

        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    @DisplayName("Проверка поиска всех групп")
    void getAll() {
        List<Group> groupList = new ArrayList<>() {{
            add(new Group(1L, "группа1", emptyList(), emptyList()));
            add(new Group(2L, "группа2", emptyList(), emptyList()));
        }};

        when(groupRepository.findAll()).thenReturn(groupList);

        List<Group> groupListReturned = groupService.getAll();

        Assertions.assertThat(groupListReturned).hasSize(2);
    }

    @Test
    @DisplayName("Проверка поиска группы по Id")
    void findById() {
        Group group = new Group(1L, "группа1", emptyList(), emptyList());

        when(groupRepository.findById(group.getId())).thenReturn(Optional.of(group));

        Group groupFounded = groupService.findById(group.getId());

        assertThat(groupFounded).isEqualTo(group);
    }

    @Test
    @DisplayName("Проверка ошибки при поиске группы по несуществующему id")
    void shouldDisplayErrorWhenFindByIdIffIdIsNotExist() {
        when(groupRepository.findById(any())).thenReturn(Optional.empty());

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrowsExactly(ItemNotFoundException.class, () -> groupService.findById(1L));
        Assertions.assertThat(exception).hasMessage("Группа id=1 не найдена");
    }
}