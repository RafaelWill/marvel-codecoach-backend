package be.marvel.code.coach.service.service;

import java.util.UUID;

public interface EmailPrepareService {
    void sendSimpleEmail(String name, String toMail,String title, String htmlName);
    void sendSimpleEmailAndMotivation(String name, String motivation,String toMail,String title, String htmlName);
}
