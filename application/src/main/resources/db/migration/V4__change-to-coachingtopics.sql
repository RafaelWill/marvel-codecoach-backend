ALTER TABLE coachingtopic RENAME COLUMN usercredentialid TO personid;

alter table coachingtopic
    add constraint coachingtopic_person_personid_fk
        foreign key (personid) references person;
