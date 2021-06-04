package be.marvel.code.coach.infrastructure.mail;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class HtmlReplace {

    public HtmlReplace() {
    }

    public String replacePlaceholders(Map<String,String> contentValues, String htmlContent){
        String result = htmlContent;
        for (var entry : contentValues.entrySet()){
            result = result.replace("{" + entry.getKey() + "}",entry.getValue());
        }
        return result;
    }
}
