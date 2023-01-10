package ru.sber.EducationManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.EducationManagementSystem.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
