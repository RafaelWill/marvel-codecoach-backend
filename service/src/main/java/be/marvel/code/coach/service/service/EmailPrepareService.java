package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Session;

import java.util.Map;
import java.util.UUID;

public interface EmailPrepareService {
    void sendMail(Map<String, String> contentValues, String toMail, String title, String htmlName);
}
