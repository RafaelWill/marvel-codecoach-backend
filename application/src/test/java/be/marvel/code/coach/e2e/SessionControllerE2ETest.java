package be.marvel.code.coach.e2e;

import be.marvel.code.coach.api.controller.SessionController;
import be.marvel.code.coach.api.dto.*;
import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.domain.entity.UserCredential;
import be.marvel.code.coach.security.LoginDto;
import be.marvel.code.coach.service.service.CoachingTopicService;
import be.marvel.code.coach.service.service.PersonService;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SessionControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private PersonService personService;
    @Autowired
    private CoachingTopicService coachingTopicService;

    @Test
    void createSession_givenValidParameters_thenCreateASession() {
        //GIVEN
        UserCredential coachCredentials = new UserCredential("coach@codecoach.be","P@ssword1");
        Person coachPerson = new Person(coachCredentials,"Uncle","Bob");
        Person savedCoach = personService.save(coachPerson);
        List<CoachingTopic> topicList = List.of(new CoachingTopic(savedCoach, "Java", 6));
        savedCoach = personService.becomeCoach(topicList, savedCoach.getId());

        Person coachee = personFactory();
        Person savedCoachee = personService.save(coachee);

        LoginDto login = new LoginDto()
                .setEmail(coachee.getUserCredential().getEmail())
                .setPassword(coachee.getUserCredential().getPassword());
        var token = authenticate(login);

        CreateSessionDto createSessionDto = createSessionFactory(savedCoach.getId(), savedCoachee.getId());

        //WHEN
        SessionDto actualResult = given()
                .baseUri("http://localhost")
                .port(port)
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(createSessionDto)
                .when()
                .post("/"+ SessionController.RESOURCE_NAME)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(SessionDto.class);

        //THEN
        Assertions.assertThat(actualResult.getCoacheeId()).isEqualTo(savedCoachee.getId());
        Assertions.assertThat(actualResult.getCoachingtopic().getCoach().getId()).isEqualTo(coachPerson.getId());
        Assertions.assertThat(actualResult.getId()).isInstanceOf(UUID.class);
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

    private CreateSessionDto createSessionFactory(UUID coach, UUID coachee){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        Date time = new Date();
        formatter = new SimpleDateFormat("HH:mm");
        String strTime = formatter.format(time);

        List<CoachingTopic> topics = coachingTopicService.getAllTopicsFromCoach(coach);

        return new CreateSessionDto()
                .setCoacheeId(coachee)
                .setTopic(topics.get(0).getId())
                .setDate(strDate)
                .setTime(strTime)
                .setLocation("Online")
                .setRemarks("Remarks");
    }

    private Person personFactory() {
        UserCredential userCredential = new UserCredential("fake@gmail.com","P@sswordTest");

        return new Person(userCredential, "firstnameE2E","lastnameE2E");
    }
}
