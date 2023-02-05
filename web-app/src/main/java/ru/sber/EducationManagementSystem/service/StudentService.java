package ru.sber.EducationManagementSystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.sber.EducationManagementSystem.entity.Role;
import ru.sber.EducationManagementSystem.entity.Student;
import ru.sber.EducationManagementSystem.entity.User;
import ru.sber.EducationManagementSystem.enums.RoleEnum;
import ru.sber.EducationManagementSystem.exception.ItemNotFoundException;
import ru.sber.EducationManagementSystem.repository.StudentRepository;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final EmailService emailService;

    @Value("${page.size}")
    private int pageSize;

    /**
     * Получить всех студентов в представлении "страница"
     *
     * @return список всех студентов
     */
    public Page<Student> getAllStudentsPaged(int pageNumber, String sortedBy, String order) {
        Sort sorting = Sort.by(sortedBy);
        Pageable paging = PageRequest.of(--pageNumber, pageSize, order.equals("asc") ? sorting.ascending() : sorting.descending());

        return studentRepository.findAll(paging);
    }

    /**
     * Найти студента по id
     *
     * @param id id студента
     * @return найденный студент
     */
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Студент c id=" + id + " не найден");
        });
    }

    /**
     * Создать студента
     * Дополнительно создать юзера для возможности авторизации студентом
     *
     * @param student объект нового студента
     */
    public boolean createStudent(Student student) {
        Role roleStudent = roleRepository.findByName(RoleEnum.ROLE_STUDENT);

        if (userService.isExist(student.getEmail()))
            return false;

        String password = "password";
        User user = userService.createUser(student.getEmail(), password, Set.of(roleStudent));

        student.setUser(user);

        studentRepository.save(student);

        emailService.sendSuccessfulRegistrationEmail(user.getUsername(), password);

        log.info("Студент создан: {}", student);

        return true;
    }

    /**
     * Обновить студента
     *
     * @param student объект студента
     */
    public void updateStudent(Student student) {
        findById(student.getId());

        studentRepository.save(student);
        log.info("Студент обновлен: {}", student);
    }

    /**
     * Удалить студента по id
     *
     * @param id id студента
     */
    public void deleteStudent(Long id) {
        Student student = findById(id);

        studentRepository.delete(student);

        log.info("Студент id={} удален", id);
    }

    /**
     * Найти студента без группы
     *
     * @return List студентов
     */
    public List<Student> findStudentsWithoutGroup() {

        return studentRepository.findStudentsByGroupIsNull();
    }


}
