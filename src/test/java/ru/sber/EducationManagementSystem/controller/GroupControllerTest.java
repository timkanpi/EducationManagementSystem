package ru.sber.EducationManagementSystem.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.sber.EducationManagementSystem.config.WebSecurityConfig;
import ru.sber.EducationManagementSystem.entity.Group;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.service.GroupService;
import ru.sber.EducationManagementSystem.service.StudentService;
import ru.sber.EducationManagementSystem.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({WebSecurityConfig.class, UserService.class})
@WebMvcTest({GroupController.class})
class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    @MockBean
    private GroupService groupService;
    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Проверка отображения списка групп")
    void getGroups() throws Exception {
        List<Group> groupList = new ArrayList<>() {{
            add(new Group(1L, "группа1", emptyList(), emptyList()));
            add(new Group(2L, "группа2", emptyList(), emptyList()));
        }};

        when(groupService.getAll()).thenReturn(groupList);

        mockMvc.perform(get("/group"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("group/group-list"))
                .andExpect(model().attribute("groups", groupList));
    }

    @Test
    @DisplayName("Проверка отображения страницы создания группы")
    @WithMockUser(username = "duke",roles = "ADMIN")
    void newGroup() throws Exception {
        List<Student> studentsList = new ArrayList<>() {{
            add(new Student(1L, "Студент1", 1, 1, 1, null, null, emptyList()));
            add(new Student(2L, "Студент2", 1, 1, 1, null, null, emptyList()));
        }};

        when(studentService.findStudentsWithoutGroup()).thenReturn(studentsList);

        mockMvc.perform(get("/group/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("group/group-new"))
                .andExpect(model().attribute("students", studentsList))
                .andExpect(model().attributeExists("group"));
    }

    @Test
    void createGroup() {
    }

    @Test
    void getGroup() {
    }
}