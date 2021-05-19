package be.marvel.code.coach.infrastructure.mail;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class HtmlReader {
    public static final String RESOURCES_TEMPLATES = "infrastructure\\src\\main\\resources\\templates/";
    public static final int CAPACITY = 1024;

    public HtmlReader() {
    }

    public String readFile(String nameHtml) throws IOException {
        String line = "";

        FileReader fr = new FileReader(RESOURCES_TEMPLATES + nameHtml);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder content = new StringBuilder(CAPACITY);
        while ((line = br.readLine()) != null) {
            content.append(line);
        }
        return content.toString();
    }
}
