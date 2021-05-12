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
}
