create table groups
(
    id   bigint generated by default as identity,
    name varchar(255),
    primary key (id)
);

create table lesson
(
    id         bigint generated by default as identity,
    date       date not null,
    homework   varchar(255),
    name       varchar(255),
    group_id   bigint,
    teacher_id bigint,
    primary key (id)
);


create table mark
(
    id         bigint generated by default as identity,
    rating     integer,
    lesson_id  bigint,
    student_id bigint,
    primary key (id)
);


create table roles
(
    id   bigint generated by default as identity,
    name varchar(255),
    primary key (id)
);


create table student
(
    id          bigint generated by default as identity,
    age         integer not null,
    course      integer not null,
    email       varchar(255),
    name        varchar(255),
    stud_ticket integer not null,
    group_id    bigint,
    user_id     bigint,
    primary key (id)
);


create table teacher
(
    id         bigint generated by default as identity,
    car_number integer,
    experience integer,
    name       varchar(255),
    user_id    bigint,
    primary key (id)
);


create table users
(
    id       bigint generated by default as identity,
    password varchar(255),
    username varchar(255),
    primary key (id)
);


create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);


alter table lesson
    add constraint FKbv4ah6ibkgynpwx3ycarrwgj
        foreign key (group_id)
            references groups;


alter table lesson
    add constraint FK9yhaoqrjxt5gwmn6icp1lf35n
        foreign key (teacher_id)
            references teacher;


alter table mark
    add constraint FKqw2aqrsqut82rwjb469mpyari
        foreign key (lesson_id)
            references lesson;


alter table mark
    add constraint FKcwocngy0rfmqdhqwm3qlrfamx
        foreign key (student_id)
            references student;


alter table student
    add constraint FKsflcrdigyrhbqi27vvioiw53q
        foreign key (group_id)
            references groups;


alter table student
    add constraint FKk0thg920a3xk3v59yjbsatw1l
        foreign key (user_id)
            references users;

alter table teacher
    add constraint FKcp1vpkh4bh0qux9vtvs0fkwrn
        foreign key (user_id)
            references users;

alter table users_roles
    add constraint FKj6m8fwv7oqv74fcehir1a9ffy
        foreign key (role_id)
            references roles;

alter table users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id)
            references users;