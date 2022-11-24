package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import ru.levelp.at.homework6.go.rests.BaseApiTest;

public class UsersPostTest extends BaseApiTest {


    @Test
    void createNewUser() {
        RestAssured
            .given()
            .spec(requestSpecification)
            .body(GenerationUser.createNewUser())
            .when()
            .post("/users")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .body("name", Matchers.equalTo(GenerationUser.name))
            .body("email", Matchers.equalTo(GenerationUser.email))
            .body("gender", Matchers.equalTo(GenerationUser.gender))
            .body("status", Matchers.equalTo(GenerationUser.status));

    }

    @Test
    void failAuth(){
        RestAssured
            .given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(GenerationUser.createNewUser())
            .when()
            .post("/users")
            .then()
            .spec(failRespSpecification());
    }

    @Test//параметризовать
    void sendBlankName(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(GenerationUser.createNewUser())
            .when()
            .post("/users")
            .then()
            .spec(blankFieldRespSpecification());
    }

    @Test
    void incorrectEmail(){}

    @Test
    void incorrectGender(){}

    @Test
    void incorrectStatus(){}
}
