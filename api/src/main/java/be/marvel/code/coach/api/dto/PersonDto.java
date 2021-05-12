package be.marvel.code.coach.api.dto;

import java.util.UUID;

public class PersonDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    public PersonDto() {
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonDto setId(UUID id) {
        this.id = id;
        return this;
    }

    public PersonDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public PersonDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonDto setLastName(String lastname) {
        this.lastName = lastname;
        return this;
    }
}