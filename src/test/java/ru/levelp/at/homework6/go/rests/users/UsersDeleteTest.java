package ru.levelp.at.homework6.go.rests.users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;

public class UsersDeleteTest extends BaseApiTest {

    @Test
    void successDelete() {
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", TestData.createTestUser())
            .contentType(ContentType.JSON)
            .when()
            .delete("/users/{userId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void failedAuth() {
        RestAssured
            .given()
            .log().all()
            .pathParam("userId", TestData.createTestUser())
            .contentType(ContentType.JSON)
            .when()
            .delete("/users/{userId}")
            .then()
            .spec(failRespSpecification());

        TestData.deleteTestUser();
    }

    @Test
    void incorrectData() {
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("userId", TestData.INCORRECT_ID)
            .contentType(ContentType.JSON)
            .when()
            .delete("/users/{userId}")
            .then()
            .spec(failRespSpecification());
    }
}
