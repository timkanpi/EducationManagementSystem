insert into USERS(login, password)
values ('pavel', '1'),
       ('olga', '1'),
       ('tolik', '1'),
       ('viktor', '1'),
       ('viktor', '1'),
       ('teacher1', '1'),
       ('teacher2', '1'),
       ('teacher3', '1');

insert into TEACHER(name, user_id)
values ('Teacher1', 5),
       ('Teacher2', 6),
       ('Teacher3', 7);

insert into GROUPS(name)
values ('group_1'),
       ('group_2'),
       ('group_3');

insert into STUDENT(name, course, stud_ticket, group_id, user_id)
values ('Pavel', 1, 3421, 1, 1),
       ('Olga', 2, 3422, 2, 2),
       ('Tolik', 3, 3423, null, 3),
       ('Viktor', 1, 3424, 2, 4);

insert into LESSON(homework, group_id, teacher_id , date)
values ('lesson1', 1, 1, '2020-02-01'),
       ('lesson2', 1, 2, '2020-02-02'),
       ('lesson3', 2, 1, '2020-02-03'),
       ('lesson4', 3, 1, '2020-02-04');

insert into MARK(lesson_id, student_id, rating)
values (1, 1, 3),
       (1, 2, 4),
       (1, 3, 5),
       (1, 3, 5),
       (1, 1, 2);