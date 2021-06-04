package be.marvel.code.coach.infrastructure.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class HtmlReaderTest {

    @Test
    void given_file_reader_can_readFile() throws IOException {
        //given
        HtmlReader htmlReader = new HtmlReader();
        //when
        var result = htmlReader.readFile("welcome.html");
        //then
        assertThat(result.length()).isGreaterThan(0);
    }

    @Test
    void given_fileNotFound_thenThrowIOException() {
        //GIVEN
        HtmlReader htmlReader = new HtmlReader();
        //THEN
        Assertions.assertThatIOException().isThrownBy(()->htmlReader.readFile("false.html"));
    }
}
