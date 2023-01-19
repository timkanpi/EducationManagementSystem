package ru.sber.EducationManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Заполните имя студента")
    private String name;

    @PositiveOrZero(message = "Возраст должен быть больше 0")
    private Integer age;

    @NotNull(message = "Заполните курс")
    private Integer course;

    @NotNull(message = "Заполните студенческий билет")
    @Column(name = "stud_ticket")
    private Integer studTicket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Mark> marks;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", course=" + course +
                ", studTicket=" + studTicket +
                '}';
    }
}
