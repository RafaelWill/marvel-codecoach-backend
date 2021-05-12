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

    public CreateUserCredentialDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateUserCredentialDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
