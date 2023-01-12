package ru.sber.EducationManagementSystem.wrapper;

import ru.sber.EducationManagementSystem.entity.Mark;

import java.util.List;

public class MarksWrapper {
    List<Mark> marks;

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }
}
