package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import java.util.List;

public class UsersGetTest {

    @Test
    void getOnePerson(){
        RestAssured
            .given()
            .pathParam("userId", "3786")
            .log().all()
            .when()
            .get("https://gorest.co.in/public/v2/users/{userId}")
            .then()
            .log().all()
            .statusCode(200)
            .body("name", Matchers.equalTo("Ekaling Agarwal"))
            .body("email", Matchers.equalTo("ekaling_agarwal@cruickshank.net"))
            .body("gender", Matchers.equalTo("female"))
            .body("status", Matchers.equalTo("active"));
    }

    @Test //не работает
    void getPersons(){
        RestAssured
            .when()
            .get("https://gorest.co.in/public/v2/users")
            .then()
            .log().all()
            .statusCode(200)
            .body(Matchers.containsInAnyOrder(List.of(3787, 3786, 3785, 3783, 3781, 3780, 3778, 3774, 3768, 3762)));
    }

    @Test
    void getIncorrectPerson(){
        RestAssured
            .given()
            .pathParam("userId", "000")
            .log().all()
            .when()
            .get("https://gorest.co.in/public/v2/users/{userId}")
            .then()
            .log().all()
            .statusCode(404);
    }
}
