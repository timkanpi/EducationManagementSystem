package ru.sber.EducationManagementSystem.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import ru.sber.EducationManagementSystem.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name= "roles")
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private RoleEnum name;

    @Override
    public String getAuthority() {
        return name.toString();
    }
}
