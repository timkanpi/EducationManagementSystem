package ru.sber.EducationManagementSystem.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "student_id")
    private Student student;


    public Mark(Lesson lesson, Student student) {
        this.lesson = lesson;
        this.student = student;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", rating=" + rating +
                ", lesson=" + lesson +
                '}';
    }
}
