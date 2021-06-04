package be.marvel.code.coach.e2e;

import be.marvel.code.coach.api.controller.PersonController;
import be.marvel.code.coach.api.dto.BecomeCoachDto;
import be.marvel.code.coach.api.dto.CreatePersonDto;
import be.marvel.code.coach.api.dto.CreateUserCredentialDto;
import be.marvel.code.coach.api.dto.PersonDto;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.UserCredential;
import be.marvel.code.coach.security.LoginDto;
import be.marvel.code.coach.service.service.PersonService;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private PersonService personService;

    @Test
    void createPerson_givenCorrectParameters_thenReturnCreatedPerson() {
        //GIVEN
        CreatePersonDto createPersonDto = createPersonFactory();

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

    @Test
    void BecomeCoach_givenValidParameters_thenAddTopicsAndCoachRoleToDatabase(){
        //GIVEN
        Person person = personFactory();
        Person savedPerson = personService.save(person);

        BecomeCoachDto becomeCoachDto = new BecomeCoachDto()
                .setMotivation("none")
                .setTopic("First topic")
                .setGrade(6)
                .setExtraTopics(new ArrayList<>())
                .setExtraGrades(new ArrayList<>());

        LoginDto login = new LoginDto()
                .setEmail(person.getEmail())
                .setPassword("PasswordTest1");

        var token = authenticate(login);

        //WHEN
        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .body(becomeCoachDto)
                .when()
                .post("/"+ PersonController.RESOURCE_NAME + "/" + savedPerson.getId() + "/become-coach")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void getPersonById_givenValidParameters_thenReturnsRequestedPerson() {
        //GIVEN
        Person person = personFactory();
        Person savedPerson = personService.save(person);

        LoginDto login = new LoginDto()
                .setEmail(person.getEmail())
                .setPassword("PasswordTest1");
        var token = authenticate(login);

        //WHEN
        PersonDto actualResult = given()
                .baseUri("http://localhost")
                .port(port)
                .header("Authorization",token)
                .when()
                .get("/"+ PersonController.RESOURCE_NAME + "/" + savedPerson.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(PersonDto.class);

        //THEN
        Assertions.assertThat(actualResult.getFirstName()).isEqualTo(person.getFirstName());
        Assertions.assertThat(actualResult.getLastName()).isEqualTo(person.getLastName());
        Assertions.assertThat(actualResult.getEmail()).isEqualTo(person.getEmail());
        Assertions.assertThat(actualResult.getId()).isInstanceOf(UUID.class);
    }

    @Test
    void getOverviewCoaches_givenValidParameters_thenReturnListOfCoaches() {
        //GIVEN
        Person person = personFactory();
        Person savedPerson = personService.save(person);
        List<CoachingTopic> topicList = List.of(new CoachingTopic(savedPerson, "Java", 6));
        personService.becomeCoach(topicList , savedPerson);

        LoginDto login = new LoginDto()
                .setEmail(person.getEmail())
                .setPassword("PasswordTest1");
        var token = authenticate(login);

        //WHEN
        PersonDto[] actualResult = given()
                .baseUri("http://localhost")
                .port(port)
                .header("Authorization",token)
                .when()
                .get("/"+ PersonController.RESOURCE_NAME + "/coaches")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(PersonDto[].class);

        assertThat(actualResult.length).isEqualTo(1);
    }

    private String authenticate(LoginDto loginDto) {

        var token  = given()
                .baseUri("http://localhost")
                .port(port)
                .contentType(ContentType.JSON)
                .body(loginDto)
                .when().post("/authenticate")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response().headers().get("Authorization");

        return token.getValue();
    }

    private CreatePersonDto createPersonFactory() {
        CreateUserCredentialDto createUserCredentialDto = new CreateUserCredentialDto()
                .setEmail("fake@mail.com")
                .setPassword("PasswordTest1");

        return new CreatePersonDto()
                .setUserCredential(createUserCredentialDto)
                .setFirstName("firstnameE2E")
                .setLastName("lastnameE2E");
    }

    private Person personFactory() {
        UserCredential userCredential = new UserCredential("fake@gmail.com","$2y$12$OJqIUwDoab6WIy1mu0mz3u6m4LXKnx.y4NxnRouGcKoC9TtDIkTuq");

        return new Person(userCredential, "firstnameE2E","lastnameE2E");
    }
}
