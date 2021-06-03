package be.marvel.code.coach.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreatePersonDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Valid
    private CreateUserCredentialDto userCredential;

    public CreatePersonDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateUserCredentialDto getUserCredential() {
        return userCredential;
    }

    public CreatePersonDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreatePersonDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreatePersonDto setUserCredential(CreateUserCredentialDto userCredential) {
        this.userCredential = userCredential;
        return this;
    }
}
