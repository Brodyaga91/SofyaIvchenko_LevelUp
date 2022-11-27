package ru.levelp.at.homework6.go.rests.comments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;

public class CommentsDeleteTest extends BaseApiTest {

    @Test
    void successDelete() {
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("commentId", TestData.createTestComment())
            .contentType(ContentType.JSON)
            .when()
            .delete("/comments/{commentId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void failedAuth() {
        RestAssured
            .given()
            .log().all()
            .pathParam("commentId", TestData.createTestComment())
            .contentType(ContentType.JSON)
            .when()
            .delete("/comments/{commentId}")
            .then()
            .spec(failRespSpecification());

        TestData.deleteTestComment();
    }

    @Test
    void incorrectData() {
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("commentId", TestData.INCORRECT_ID)
            .contentType(ContentType.JSON)
            .when()
            .delete("/comments/{commentId}")
            .then()
            .spec(failRespSpecification());
    }
}
