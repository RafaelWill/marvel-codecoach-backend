
create table userrole
(
    usercredentialid uuid not null
        constraint userrole_usercredential_usercredentialid_fk
            references usercredential,
     rolename varchar(30) not null,

         PRIMARY KEY (usercredentialid, rolename)
);
