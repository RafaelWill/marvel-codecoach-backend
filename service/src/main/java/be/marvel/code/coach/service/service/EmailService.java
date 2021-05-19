package be.marvel.code.coach.service.service;

public interface EmailService {
    void send(String to, String title, String body);
}
