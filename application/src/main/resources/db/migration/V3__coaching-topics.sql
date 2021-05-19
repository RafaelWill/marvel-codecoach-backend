create table coachingtopics
(
    coachingtopicsid uuid not null,
    usercredentialid uuid not null,
    topic varchar(255) not null,
    rate int not null
);

create unique index coachingtopics_coachingtopicsid_uindex
	on coachingtopics (coachingtopicsid);

alter table coachingtopics
    add constraint coachingtopics_pk
        primary key (coachingtopicsid);

