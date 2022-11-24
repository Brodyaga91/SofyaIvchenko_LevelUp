package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;

public class UsersPutTest extends BaseApiTest {

    @Test
    void updateData(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", TestData.createTestUser())
            .contentType(ContentType.JSON)
            .body(GenerationUser.createNewUser())
            .when()
            .put("/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_OK)
            .body("name", Matchers.equalTo(GenerationUser.name))
            .body("email", Matchers.equalTo(GenerationUser.email))
            .body("gender", Matchers.equalTo(GenerationUser.gender))
            .body("status", Matchers.equalTo(GenerationUser.status));

        TestData.deleteTestUser();
    }

    @Test
    void failAuth() {

        RestAssured
                .given()
                .log().all()
                .pathParam("userId", TestData.createTestUser())
                .contentType(ContentType.JSON)
                .body(GenerationUser.createNewUser())
                .when()
                .put("/users/{userId}")
                .then()
                .spec(failRespSpecification());

        TestData.deleteTestUser();

    }

    @Test
    void incorrectResource(){

            RestAssured
                .given()
                .spec(requestSpecification)
                .pathParam("userId", TestData.INCORRECT_ID)
                .contentType(ContentType.JSON)
                .body(GenerationUser.createNewUser())
                .when()
                .put("/users/{userId}")
                .then()
                .spec(failRespSpecification());

    }

    @Test//параметризировать
    void sendBlankName(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", TestData.createTestUser())
            .contentType(ContentType.JSON)
            .body(GenerationUser.createNewUser())
            .when()
            .put("/users/{userId}")
            .then()
            .spec(blankFieldRespSpecification());

        TestData.deleteTestUser();
    }
}
