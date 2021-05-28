package be.marvel.code.coach.infrastructure.mail;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HtmlReplace {

    public HtmlReplace() {
    }

    public String SimpleReplace(String wordToFind,String wordToReplace,String text){
        return text.replace(wordToFind,wordToReplace);
    }

    public String SimpleReplace(String wordToFind, String name, String wordToFindMotivation, String motivation, String text) {
        String result = text.replace(wordToFind,name);
        return result.replace(wordToFindMotivation,motivation);
    }

    public String SessionReplace(String wordToFind, String name, String s1, String topic, String s2, LocalDateTime sessionMoment, String s3, String location, String text) {
        String result = text.replace(wordToFind,name);
        result = result.replace(s1,topic);
        result = result.replace(s2,sessionMoment.toString());
        return result.replace(s3,location);
    }
}
