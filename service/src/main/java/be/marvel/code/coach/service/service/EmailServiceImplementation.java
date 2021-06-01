package be.marvel.code.coach.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImplementation implements EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${mailfrom}")
    private String from;

    @Autowired
    public EmailServiceImplementation(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String to, String title, String body) {
        this.javaMailSender.send(createMessage(to, title, body));
    }

    private MimeMessage createMessage(String to, String title, String body) {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        try {
            if (from != null) {
                mimeMessageHelper.setFrom(from);
            }
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setTo(to);
            return message;
        } catch (MessagingException messageException) {
            throw new RuntimeException(messageException);
        }
    }
}
