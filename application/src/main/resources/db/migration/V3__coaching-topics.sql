create table coachingtopic
(
    coachingtopicid uuid not null,
    usercredentialid uuid not null,
    topic varchar(255) not null,
    grade int not null
);

create unique index coachingtopic_coachingtopicid_uindex
	on coachingtopic (coachingtopicid);

alter table coachingtopic
    add constraint coachingtopic_pk
        primary key (coachingtopicid);

