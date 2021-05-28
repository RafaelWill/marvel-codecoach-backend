package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Session;

import java.util.UUID;

public interface EmailPrepareService {
    void sendSimpleEmail(String name, String toMail,String title, String htmlName);
    void sendSimpleEmailAndMotivation(String name, String motivation,String toMail,String title, String htmlName);

    void sendSessionMail(String name, String toMail, Session savedPerson, String title, String htmlName);
}
