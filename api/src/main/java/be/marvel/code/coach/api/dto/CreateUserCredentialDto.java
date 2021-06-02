package be.marvel.code.coach.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class CreateUserCredentialDto {

    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "at least 8 characters (1 uppercase letter, 1 lowercase letter, 1 number)")
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
