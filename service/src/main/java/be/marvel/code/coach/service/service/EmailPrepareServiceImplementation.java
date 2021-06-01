package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Session;
import be.marvel.code.coach.infrastructure.mail.HtmlReader;
import be.marvel.code.coach.infrastructure.mail.HtmlReplace;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
// TODO: This looks fine for now, but will become unmaintainable when you got too many different mails to send. Trust me, I speak from experience :). Is there a way to organise this differently?
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
    public void sendMail(Map<String, String> contentValues, String toMail, String title, String htmlName) {
        try {
            var text = htmlReplace.replacePlaceholders(contentValues, htmlReader.readFile(htmlName));
            emailService.send(toMail, title, text);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Mail not send!", ex);
        }
    }
}
