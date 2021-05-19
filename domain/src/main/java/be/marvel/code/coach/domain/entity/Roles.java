package be.marvel.code.coach.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role")
public class Roles {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "roleid")
    private UUID id;

    @Column(name = "rolename")
    private String name;

    public Roles() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
