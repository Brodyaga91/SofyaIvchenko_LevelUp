package ru.levelp.at.homework6.go.rests.posts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import ru.levelp.at.homework6.go.rests.BaseApiTest;
import ru.levelp.at.homework6.go.rests.TestData;

public class PostsDeleteTest extends BaseApiTest {
    @Test
    void successDelete(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("postId", TestData.createTestPost())
            .contentType(ContentType.JSON)
            .when()
            .delete("/posts/{postId}")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void failedAuth(){
        RestAssured
            .given()
            .log().all()
            .pathParam("postId", TestData.createTestPost())
            .contentType(ContentType.JSON)
            .when()
            .delete("/posts/{postId}")
            .then()
            .spec(failRespSpecification());

        TestData.deleteTestPost();
    }

    @Test
    void incorrectData(){
        RestAssured
            .given()
            .spec(requestSpecification)
            .pathParam("postId", TestData.INCORRECT_ID)
            .contentType(ContentType.JSON)
            .when()
            .delete("/posts/{postId}")
            .then()
            .spec(failRespSpecification());
    }
}
