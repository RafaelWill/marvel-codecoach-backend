package be.marvel.code.coach.api.dto;

public class CreatePersonDto {

    private String firstName;
    private String lastName;
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
