create table usercredential
(
    usercredentialid   uuid         not null
        constraint usercredential_pk
            primary key,
    email    varchar(255) not null,
    password varchar(255) not null
);

create unique index usercredential_email_uindex
    on usercredential (email);

create unique index usercredential_usercredentialid_uindex
    on usercredential (usercredentialid);

create table person
(
    personid  uuid not null
        constraint person_pk
            primary key,
    usercredentialid    uuid
        constraint person_usercredential_usercredentialid_fk
            references usercredential,
    firstname varchar(255) not null,
    lastname  varchar(255) not null
);

create unique index person_personid_uindex
    on person (personid);
