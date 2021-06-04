package be.marvel.code.coach.infrastructure.mail;

import be.marvel.code.coach.infrastructure.mail.HtmlReplace;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HtmlReplaceTest {

    @Test
    void given_correct_map_AllTags_replaced(){
        //given
        HtmlReplace htmlReplace = new HtmlReplace();
        Map<String, String> map = new HashMap<>();
        map.put("ok","vervangen");
        map.put("nok","vervangen");

        //when
        var result = htmlReplace.replacePlaceholders(map,"{ok},{nok}");
        //then
        assertThat(result).isEqualTo("vervangen,vervangen");
    }
}
