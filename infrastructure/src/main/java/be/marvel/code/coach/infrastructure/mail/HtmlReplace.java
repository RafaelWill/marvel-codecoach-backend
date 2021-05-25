package be.marvel.code.coach.infrastructure.mail;

import org.springframework.stereotype.Component;

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
}
