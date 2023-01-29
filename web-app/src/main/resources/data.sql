insert into ROLES (NAME)
values ('ROLE_ADMIN'),
       ('ROLE_STUDENT'),
       ('ROLE_TEACHER');

insert into USERS(username, password)
values ('pavel', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('olga', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('tolik', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('viktor', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('petr', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student1', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student2', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student3', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student4', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student5', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student6', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student7', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student8', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
       ('student9', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),

       ('teacher', '$2a$12$U9JOi25XeKl01PgYK/90OOGbdBEvjXpJw9qKYDimAiU//atBqjeiq'),
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

insert into STUDENT(name, age, course, stud_ticket, group_id, user_id)
values ('Павел', 1, 18, 3421, 1, 1),
       ('Ольга', 2, 18, 3422, 1, 2),
       ('Анатолий', 18, 3, 3423, null, 3),
       ('Виктор', 18, 1, 3424, 2, 4),
       ('Петр', 18, 5, 44444, 1, 5),
       ('Анна', 20, 2, 123, 1, 6),
       ('Вера', 28, 2, 123, null, 7),
       ('Иван', 20, 2, 123, 3, 8),
       ('Олег', 19, 2, 123, null, 9),
       ('Инна', 20, 2, 123, 3, 10),
       ('Виктория', 23, 2, 123, 5, 11),
       ('Вячеслав', 20, 2, 123, null, 12),
       ('Егор', 22, 2, 123, 4, 13),
       ('Дарья', 21, 2, 123, 4, 14);

insert into LESSON(homework, group_id, teacher_id, date)
values ('ДЗ Занятие 1', 1, 1, '2020-02-01'),
       ('ДЗ Занятие 2', 1, 2, '2020-02-02'),
       ('ДЗ Занятие 3', 2, 1, '2020-02-03'),
       ('ДЗ Занятие 4', 3, 1, '2020-02-04'),
       ('ДЗ Занятие 5', 4, 2, '2020-02-05'),
       ('ДЗ Занятие 6', 5, 3, '2020-02-06');

insert into MARK(lesson_id, student_id, rating)
values (1, 1, 3),
       (1, 2, 4),
       (1, 3, 5),
       (2, 3, 5);