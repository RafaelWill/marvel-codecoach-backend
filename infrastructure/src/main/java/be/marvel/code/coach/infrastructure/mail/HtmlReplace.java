package be.marvel.code.coach.infrastructure.mail;

import org.springframework.stereotype.Component;

@Component
public class HtmlReplace {

    public HtmlReplace() {
    }

    public String SimpleReplace(String wordToFind,String wordToReplace,String text){
        return text.replace(wordToFind,wordToReplace);
    }
}
