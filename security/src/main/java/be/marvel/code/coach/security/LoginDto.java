package be.marvel.code.coach.security;

public class LoginDto {

    private String email;
    private String password;

    public LoginDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
