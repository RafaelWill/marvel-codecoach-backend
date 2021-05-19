create table role
(
    roleid   uuid         not null
        constraint role_pk
            primary key,
    rolename varchar(100) not null
);

alter table role
    owner to student;

create unique index role_roleid_uindex
    on role (roleid);

create unique index role_rolename_uindex
    on role (rolename);



create table userroles
(
    userrolesid      uuid not null
        constraint userroles_pk
            primary key,
    usercredentialid uuid not null
        constraint userroles_usercredential_usercredentialid_fk
            references usercredential,
    roleid           uuid not null
        constraint userroles_role_roleid_fk
            references role
);

alter table userroles
    owner to student;

create unique index userroles_userrolesid_uindex
    on userroles (userrolesid);
