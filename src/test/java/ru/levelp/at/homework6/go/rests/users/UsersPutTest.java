package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
            .pathParam("userId", TestData.createTestData())
            .contentType(ContentType.JSON)
            .body("{\"name\": \"" + TestData.name
                + "\", \"email\": \"" + TestData.email
                + "\",       \"gender\": \"" + TestData.gender
                + "\",      \"status\": \"" + TestData.changeStatus +"\"}")
            .when()
            .put("/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(200)
            .body("name", Matchers.equalTo(TestData.name))
            .body("email", Matchers.equalTo(TestData.email))
            .body("gender", Matchers.equalTo(TestData.gender))
            .body("status", Matchers.equalTo(TestData.changeStatus));
    }

    @Test
    void failAuth() {

            RestAssured
                .given()
                .log().all()
                .pathParam("userId", TestData.createTestData())
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + TestData.name
                    + "\", \"email\": \"" + TestData.email
                    + "\",       \"gender\": \"" + TestData.gender
                    + "\",      \"status\": \"" + TestData.changeStatus +"\"}")
                .when()
                .put("/users/{userId}")
                .then()
                .spec(failRespSpecification());

    }

    @Test
    void incorrectResource(){

            RestAssured
                .given()
                .spec(requestSpecification)
                .pathParam("userId", TestData.INCORRECT_ID)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + TestData.name
                    + "\", \"email\": \"" + TestData.email
                    + "\",       \"gender\": \"" + TestData.gender
                    + "\",      \"status\": \"" + TestData.changeStatus +"\"}")
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
            .pathParam("userId", TestData.createTestData())
            .contentType(ContentType.JSON)
            .body("{\"name\": \"\""
                + ", \"email\": \"" + TestData.email
                + "\",       \"gender\": \"" + TestData.gender
                + "\",      \"status\": \"" + TestData.changeStatus +"\"}")
            .when()
            .put("/users/{userId}")
            .then()
            .spec(blankFieldRespSpecification());
    }
}
