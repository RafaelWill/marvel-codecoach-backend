package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Session;
import be.marvel.code.coach.infrastructure.mail.HtmlReader;
import be.marvel.code.coach.infrastructure.mail.HtmlReplace;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailPrepareServiceImplementation implements EmailPrepareService {
    private final EmailService emailService;
    private final HtmlReader htmlReader;
    private final HtmlReplace htmlReplace;

    public EmailPrepareServiceImplementation(EmailService emailService, HtmlReader htmlReader, HtmlReplace htmlReplace) {
        this.emailService = emailService;
        this.htmlReader = htmlReader;
        this.htmlReplace = htmlReplace;
    }

    @Override
    public void sendSimpleEmail(String name,String toMail ,String title, String htmlName) {
        try {
            var text = htmlReplace.SimpleReplace("{replaceme}",name,htmlReader.readFile(htmlName));
            emailService.send(toMail, title, text);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Mail not send!",ex);
        }
    }

    @Override
    public void sendSimpleEmailAndMotivation(String name, String motivation, String toMail, String title, String htmlName) {
        try {
            var text = htmlReplace.SimpleReplace("{replaceme}",name,"{motivation}",motivation,htmlReader.readFile(htmlName));
            emailService.send(toMail, title, text);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Mail not send!",ex);
        }
    }

    @Override
    public void sendSessionMail(String name, String toMail, Session savedPerson, String title, String htmlName) {
        try {
            var text = htmlReplace.SessionReplace("{replaceme}",name,"{topic}",savedPerson.getCoachingtopic().getTopic(),"{when}",savedPerson.getSessionMoment(),"{where}",savedPerson.getLocation(),htmlReader.readFile(htmlName));
            emailService.send(toMail, title, text);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Mail not send!",ex);
        }
    }
}
