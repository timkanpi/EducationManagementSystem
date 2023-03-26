insert into ROLES (NAME)
values ('ROLE_ADMIN'),
       ('ROLE_STUDENT'),
       ('ROLE_TEACHER');

insert into USERS(username, password)
values ('student1@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student2@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student3@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student4@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student5@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student6@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student7@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student8@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student9@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student10@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student11@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student12@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student13@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student14@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),

       ('teacher1@mail.plus', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),

       ('admin', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq');

insert into USERS_ROLES(user_id, role_id)
values (1, 2),
       (15, 3),
       (16, 1);

insert into TEACHER(name, user_id)
values ('Учитель 1', 6),
       ('Учитель 2', 7),
       ('Учитель 3', 8);

insert into GROUPS(name)
values ('группа 1'),
       ('группа 2'),
       ('группа 3'),
       ('группа 4'),
       ('группа 5');

insert into STUDENT(name, email, age, course, stud_ticket, group_id, user_id)
values ('Павел', 'student1@mail.plus', 1, 18, 1, 1, 1),
       ('Ольга', 'student2@mail.plus', 2, 18, 2, 1, 2),
       ('Анатолий', 'student3@mail.plus', 18, 3, 3, null, 3),
       ('Виктор', 'student4@mail.plus', 18, 1, 4, 2, 4),
       ('Петр', 'student5@mail.plus', 18, 5, 5, 1, 5),
       ('Анна', 'student6@mail.plus', 20, 2, 6, 1, 6),
       ('Вера', 'student7@mail.plus', 28, 2, 7, null, 7),
       ('Иван', 'student8@mail.plus', 20, 2, 8, 3, 8),
       ('Олег', 'student9@mail.plus', 19, 2, 9, null, 9),
       ('Инна', 'student10@mail.plus', 20, 2, 10, 3, 10),
       ('Виктория', 'student11@mail.plus', 23, 11, 123, 5, 11),
       ('Вячеслав', 'student12@mail.plus', 20, 12, 123, null, 12),
       ('Егор', 'student13@mail.plus', 22, 2, 13, 4, 13),
       ('Дарья', 'student14@mail.plus', 21, 2, 14, 4, 14);

insert into LESSON(name, homework, group_id, teacher_id, date)
values ('Занятие 1', 'ДЗ Занятие 1', 1, 1, '2020-02-01'),
       ('Занятие 2', 'ДЗ Занятие 2', 1, 2, '2020-02-02'),
       ('Занятие 3', 'ДЗ Занятие 3', 2, 1, '2020-02-03'),
       ('Занятие 4', 'ДЗ Занятие 4', 3, 1, '2020-02-04'),
       ('Занятие 5', 'ДЗ Занятие 5', 1, 2, '2020-02-05'),
       ('Занятие 6', 'ДЗ Занятие 6', 1, 2, '2020-02-05'),
       ('Занятие 7', 'ДЗ Занятие 7', 1, 2, '2020-02-05'),
       ('Занятие 8', 'ДЗ Занятие 8', 5, 3, '2020-02-06');

insert into MARK(lesson_id, student_id, rating)
values (1, 1, 3),
       (1, 2, 4),
       (1, 3, 5),
       (2, 3, 5);