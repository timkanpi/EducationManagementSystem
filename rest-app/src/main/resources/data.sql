insert into TEACHER(name)
values ('Учитель 1'),
       ('Учитель 2'),
       ('Учитель 3');

insert into GROUPS(name)
values ('группа 1'),
       ('группа 2'),
       ('группа 3');

insert into STUDENT(name, course, stud_ticket, group_id)
values ('Павел', 1, 3421, 1),
       ('Ольга', 2, 3422, 1),
       ('Анатолий', 3, 3423, null),
       ('Виктор', 1, 3424, 2),
       ('Петр', 5, 44444, 1;

insert into LESSON(homework, group_id, teacher_id, date)
values ('ДЗ Занятие 1', 1, 1, '2020-02-01'),
       ('ДЗ Занятие 2', 1, 2, '2020-02-02'),
       ('ДЗ Занятие 3', 2, 1, '2020-02-03'),
       ('ДЗ Занятие 4', 3, 1, '2020-02-04');

insert into MARK(lesson_id, student_id, rating)
values (1, 1, 3),
       (1, 2, 4),
       (1, 3, 5),
       (2, 3, 5);