package be.marvel.code.coach.api.dto;

public class CreateUserCredentialDto {

    private String email;
    private String password;

    public CreateUserCredentialDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
