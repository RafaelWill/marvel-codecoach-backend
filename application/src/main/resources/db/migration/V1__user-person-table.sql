create table "user"
(
    userid   uuid         not null
        constraint user_pk
            primary key,
    email    varchar(255),
    password varchar(255) not null
);

alter table "user"
    owner to student;

create unique index user_email_uindex
    on "user" (email);

create unique index user_userid_uindex
    on "user" (userid);

create table person
(
    personid  uuid not null
        constraint person_pk
            primary key,
    userid    uuid
        constraint person_user_userid_fk
            references "user",
    firstname varchar(255),
    lastname  varchar(255)
);

alter table person
    owner to student;

create unique index person_personid_uindex
    on person (personid);
