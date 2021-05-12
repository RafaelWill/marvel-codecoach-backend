package be.marvel.code.coach.e2e;

import be.marvel.code.coach.api.controller.PersonController;
import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.CreateUserCredentialDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.domain.entity.UserCredential;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonControllerE2ETest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void init() {

    }

    @Test
    void createPerson_givenCorrectParameters_thenReturnCreatedPerson() {
        //GIVEN
        CreateUserCredentialDto createUserCredentialDto = new CreateUserCredentialDto()
                .setEmail("fake@mail.com")
                .setPassword("P@sswordTest");
        CreatePersonDto createPersonDto = new CreatePersonDto()
                .setUserCredential(createUserCredentialDto)
                .setFirstName("firstnameE2E")
                .setLastName("lastnameE2E");
        //WHEN
        PersonDto actualResult = given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .body(createPersonDto)
                .when()
                .post("/"+ PersonController.RESOURCE_NAME)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(PersonDto.class);
        //THEN
        Assertions.assertThat(actualResult.getFirstName()).isEqualTo(createPersonDto.getFirstName());
        Assertions.assertThat(actualResult.getLastName()).isEqualTo(createPersonDto.getLastName());
        Assertions.assertThat(actualResult.getEmail()).isEqualTo(createPersonDto.getUserCredential().getEmail());
        Assertions.assertThat(actualResult.getId()).isInstanceOf(UUID.class);
    }
}
