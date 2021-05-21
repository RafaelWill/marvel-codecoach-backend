package be.marvel.code.coach.infrastructure.mail;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class HtmlReader {
    public static final String RESOURCES_TEMPLATES = "templates\\";
    public static final int CAPACITY = 1024;

    public HtmlReader() {
    }

    public String readFile(String nameHtml) throws IOException {
        String line = "";

        //https://www.baeldung.com/java-classpath-resource-cannot-be-opened
        var ips = this.getClass().getClassLoader().getResourceAsStream(RESOURCES_TEMPLATES + nameHtml);
        //FileReader fr = new FileReader(RESOURCES_TEMPLATES + nameHtml);
        BufferedReader br = new BufferedReader(new InputStreamReader(ips));
        StringBuilder content = new StringBuilder(CAPACITY);
        while ((line = br.readLine()) != null) {
            content.append(line);
        }
        ips.close();
        return content.toString();
    }
}
