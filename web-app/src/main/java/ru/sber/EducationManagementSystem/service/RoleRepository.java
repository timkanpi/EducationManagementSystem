package ru.sber.EducationManagementSystem.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.EducationManagementSystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
