package be.marvel.code.coach.infrastructure.mail;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class HtmlReader {
    public static final String RESOURCES_TEMPLATES = "/templates/";
    public static final int CAPACITY = 1024;

    public HtmlReader() {
    }

    public String readFile(String nameHtml) throws IOException {
        String line = "";
        StringBuilder content = new StringBuilder(CAPACITY);

        try (InputStream is = HtmlReader.class.getResourceAsStream(RESOURCES_TEMPLATES + nameHtml)) {
            if (null == is) {
                throw new FileNotFoundException(RESOURCES_TEMPLATES + nameHtml);
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                // do stuff here
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
            }
        }
        return content.toString();
    }
}
