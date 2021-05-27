create table session
(
    sessionid uuid not null
        constraint session_pk
            primary key,
    coacheeid uuid not null
        constraint session_person_personid_fk
            references person,
    coachingtopicid uuid not null
        constraint session_coachingtopic_coachingtopicid_fk
            references coachingtopic,
    sessionmoment timestamp not null,
    location varchar(50) not null,
    remarks varchar(255)
);
